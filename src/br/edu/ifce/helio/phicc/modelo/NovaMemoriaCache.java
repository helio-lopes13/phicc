package br.edu.ifce.helio.phicc.modelo;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import br.edu.ifce.helio.phicc.implementacao.Codificador;
import br.edu.ifce.helio.phicc.implementacao.EntradaCodificada;

public class NovaMemoriaCache {

	private Map<String, NovaEntradaMemoriaCache> memoriaCache;

	private Codificador codificador;
	
	private int tamanhoCache;
	
	private int quantidadeErros;
	
	private int tamanhoPalavra;
	
	private boolean erroInserido = false;
	
	private String entradaComErro = null;
	
	private String vpnOriginal = null;
	
	public int falsosPositivos = 0;

	public int falsosNegativos = 0;

	public int errosSubstituidos = 0;

	public int semIntercorrencias = 0;
	
	public boolean debug = false;

	public NovaMemoriaCache(Codificador codificador, int tamanhoCache, int quantidadeErros) {
		memoriaCache = new LinkedHashMap<>();
		this.codificador = codificador;
		this.tamanhoCache = tamanhoCache;
		this.quantidadeErros = quantidadeErros;
		
		verificarQuantidadeErros(codificador);
	}

	public NovaMemoriaCache(Codificador codificador, int tamanhoCache, int quantidadeErros, int tamanhoPalavra) {
		this(codificador, tamanhoCache, quantidadeErros);
		this.tamanhoPalavra = tamanhoPalavra;

		verificarQuantidadeErros(codificador);

		if (tamanhoPalavra == 32 && codificador instanceof PHICC) {
			throw new RuntimeException("Tamanho de palavra inválido");
		}
	}

	private void verificarQuantidadeErros(Codificador codificador) {
		if (codificador instanceof Paridade && (quantidadeErros < 0 || quantidadeErros > 3)) {
			throw new RuntimeException("Quantidade de erros deve ser entre 0 e 3");
		} else if (codificador instanceof PHICC && quantidadeErros < 1 || quantidadeErros > 8) {
			throw new RuntimeException("Quantidade de erros deve ser entre 1 e 8");
		} else {
			NovaEntradaMemoriaCache.setQuantidadeErros(this.quantidadeErros);
		}
	}
	
	public boolean simulacao(String[] linhas, int linhaArquivoErro, int linhaCacheErro) {
		if (linhas[0].length() != tamanhoPalavra) throw new RuntimeException("Tamanho da palavra inválido!");
		for (int i = 0; i < linhas.length; i++) {
			if (i == linhaArquivoErro) {
				int j = 0;
				Iterator<Entry<String, NovaEntradaMemoriaCache>> iterador = memoriaCache.entrySet().iterator();
				Entry<String, NovaEntradaMemoriaCache> entradaCache = iterador.hasNext() ? iterador.next() : null;
				while (j < linhaCacheErro && iterador.hasNext()) {
					entradaCache = iterador.next();
					j++;
				}

				int tamanhoAtualCache = memoriaCache.values().size();
				if (entradaCache != null && linhaCacheErro < tamanhoAtualCache) {
					entradaCache.getValue().setErro(true);
					erroInserido = true;
					entradaCache.getValue().inserirErro();
					vpnOriginal = entradaCache.getKey();
					entradaComErro = (String) entradaCache.getValue().getEntradaCodificada().getValor();
				} else {
					if (debug) System.out.println("Erro não pôde ser inserido");
					semIntercorrencias++;
					return true;
				}
			}
			String linha = linhas[i];

			if (lerCache(linha, linhaArquivoErro, linhaCacheErro)) {
				return true;
			}
		}

		if (debug) {
			System.out.println("Nenhuma intercorrência");
			System.out.println(String.format("Linha do arquivo com erro: %d\nLinha da cache a ter erro inserido: %d",
					linhaArquivoErro, linhaCacheErro));
		}

		semIntercorrencias++;
		return false;
	}

	public boolean lerCache(String tag, int linhaArquivoErro, int linhaCacheErro) {
		if (erroInserido && entradaComErro != null && vpnOriginal != null) {
			String vpnCodificada = (String) codificador.codificar(tag).getValor();
			
			if (!tag.equals(vpnOriginal) && entradaComErro.equals(vpnCodificada)) {
				if (debug) {
					System.out.println("Falso positivo");
					System.out.println("Tag: " + tag);
					System.out.println(
							String.format("Linha do arquivo com erro: %d\nLinha da cache a ter erro inserido: %d",
									linhaArquivoErro, linhaCacheErro));
					printCache();
				}
				falsosPositivos++;
				return true;
			}
		}

		if (memoriaCache.containsKey(tag)) {
			NovaEntradaMemoriaCache entrada = memoriaCache.get(tag);

			if (!entrada.getEntradaCodificada().equals(codificador.codificar(tag))) {
				if (debug) System.out.println("Falso negativo");
				falsosNegativos++;
				return true;
			}
			memoriaCache.remove(tag);
			memoriaCache.put(tag, entrada);
		} else {
			if (escreverCache(tag)) {
				return true;
			}
		}

		return false;
	}
	
	public void printCache() {
		for (Map.Entry<String, NovaEntradaMemoriaCache> entry : memoriaCache.entrySet()) {
			String vpnOriginal = entry.getKey();
			NovaEntradaMemoriaCache entrada = entry.getValue();
			EntradaCodificada vpnCodificada = entrada.getEntradaCodificada();

			if (entrada.isErro()) {
				System.out.println("Entrada com erro");
			}
			System.out.println("VPN original: " + vpnOriginal);
			System.out.print("VPN codificada: ");
			vpnCodificada.printEntrada();
			System.out.println("VPN decodificada: " + codificador.decodificar(vpnCodificada));
			System.out.println();
		}
	}
	
	private boolean escreverCache(String tag) {
		NovaEntradaMemoriaCache novaEntrada = new NovaEntradaMemoriaCache();
		EntradaCodificada entradaCodificada = codificador.codificar(tag);
		novaEntrada.setEntradaCodificada(entradaCodificada);

		if (memoriaCache.size() < tamanhoCache) {
			memoriaCache.put(tag, novaEntrada);
		} else {
			if (substituir(tag, novaEntrada)) {
				return true;
			}
		}

		return false;
	}

	private boolean substituir(String tag, NovaEntradaMemoriaCache novaEntrada) {
		String chaveRemovida = memoriaCache.entrySet().iterator().next().getKey();
		NovaEntradaMemoriaCache entradaRemovida = memoriaCache.remove(chaveRemovida);
		memoriaCache.put(tag, novaEntrada);

		if (entradaRemovida.isErro()) {
			if (debug)
				System.out.println("Erro substituído");

			errosSubstituidos++;
			return true;
		}
		return false;
	}
	
}
