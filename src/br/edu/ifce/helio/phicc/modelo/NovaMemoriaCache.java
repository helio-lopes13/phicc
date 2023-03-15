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
	
	private Integer tamanhoCache;
	
	private Integer quantidadeErros;
	
	private Integer tamanhoPalavra;
	
	public Integer falsosPositivos = 0;

	public Integer falsosNegativos = 0;

	public Integer errosSubstituidos = 0;
	
	public boolean debug = false;

	public NovaMemoriaCache(Codificador codificador, Integer tamanhoCache, Integer quantidadeErros) {
		memoriaCache = new LinkedHashMap<>();
		this.codificador = codificador;
		this.tamanhoCache = tamanhoCache;
		this.quantidadeErros = quantidadeErros;
		
		verificarQuantidadeErros(codificador);
	}

	public NovaMemoriaCache(Codificador codificador, Integer tamanhoCache, Integer quantidadeErros, Integer tamanhoPalavra) {
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
		for (int i = 0; i < linhas.length; i++) {
			if (i == linhaArquivoErro) {
				int j = 0;
				Iterator<NovaEntradaMemoriaCache> iteradorMemoria = memoriaCache.values().iterator();
				NovaEntradaMemoriaCache entrada = iteradorMemoria.hasNext() ? iteradorMemoria.next() : null;
				while (j < linhaCacheErro && iteradorMemoria.hasNext()) {
					entrada = iteradorMemoria.next();
					j++;
				}

				int tamanhoAtualCache = memoriaCache.values().size();
				if (entrada != null && linhaCacheErro < tamanhoAtualCache) {
					entrada.setErro(true);
					entrada.inserirErro();
				} else {
					if (debug) System.out.println("Erro não pôde ser inserido");
					return true;
				}
			}
			String linha = obterLinhaDoArquivo(linhas, i);

			if (lerCache(linha, linhaArquivoErro, linhaCacheErro)) {
				return true;
			}
		}

		if (debug) {
			System.out.println("Nenhuma intercorrência");
			System.out.println(String.format("Linha do arquivo com erro: %d\nLinha da cache a ter erro inserido: %d",
					linhaArquivoErro, linhaCacheErro));
		}
		return false;
	}

	public boolean lerCache(String tag, int linhaArquivoErro, int linhaCacheErro) {
		// memoriaCache.values().stream().forEach(entrada -> entrada.incrementarContadorCache());

		for (Entry<String, NovaEntradaMemoriaCache> entradaCache : memoriaCache.entrySet()) {
			String vpnOriginal = entradaCache.getKey();
			NovaEntradaMemoriaCache entradaAtual = entradaCache.getValue();
			EntradaCodificada entradaCodificada = entradaAtual.getEntradaCodificada();
			EntradaCodificada vpnCodificada = codificador.codificar(tag);
			
			if (!tag.equals(vpnOriginal) && entradaAtual.isErro() && entradaCodificada.equals(vpnCodificada)) {
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
			// entrada.incrementarContadorAcesso();
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
	
	private String obterLinhaDoArquivo(String[] linhas, int i) {
		String linha = linhas[i];
		
		if (linha.length() != tamanhoPalavra) {
			throw new RuntimeException("Tamanho da palavra difere do estabelecido.");
		}
		return linha;
	}
	
}
