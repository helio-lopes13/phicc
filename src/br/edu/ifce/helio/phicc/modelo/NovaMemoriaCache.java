package br.edu.ifce.helio.phicc.modelo;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import br.edu.ifce.helio.phicc.implementacao.Codificador;
import br.edu.ifce.helio.phicc.implementacao.EntradaCodificada;

public class NovaMemoriaCache {

	private Map<String, EntradaCodificada> memoriaCache;

	private Codificador codificador;
	
	private int tamanhoCache;
	
	private int quantidadeErros;
	
	private int tamanhoPalavra;
	
	private boolean erroInserido = false;

	private String vpnOriginal = null;
	
	private EntradaCodificada entradaErro;
	
	public int falsosPositivos = 0;

	public int falsosNegativos = 0;

	public int errosSubstituidos = 0;

	public int semIntercorrencias = 0;
	
	public static boolean debug = false;

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
				Iterator<EntradaCodificada> iteradorMemoria = memoriaCache.values().iterator();
				EntradaCodificada entrada = iteradorMemoria.hasNext() ? iteradorMemoria.next() : null;
				while (j < linhaCacheErro && iteradorMemoria.hasNext()) {
					entrada = iteradorMemoria.next();
					j++;
				}

				int tamanhoAtualCache = memoriaCache.values().size();
				if (entrada != null && linhaCacheErro < tamanhoAtualCache) {
					entrada.inserirErro(quantidadeErros);
					erroInserido = true;
					vpnOriginal = obterVPN(entrada);
					entradaErro = entrada;
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
			
			printCache();
		}

		semIntercorrencias++;
		return false;
	}

	public boolean lerCache(String vpn, int linhaArquivoErro, int linhaCacheErro) {
		EntradaCodificada vpnCodificada = codificador.codificar(vpn);
		
		if (erroInserido) {
			if (memoriaCache.containsValue(entradaErro) && !vpn.equals(vpnOriginal) && vpnCodificada.equals(entradaErro)) {
				if (debug) {
					System.out.println("VPN solicitada: " + vpn);
					System.out.print("VPN codificada: ");
					System.out.println(vpnCodificada);
					System.out.println(String.format("Linha do arquivo com erro: %d\nLinha da cache a ter erro inserido: %d",
							linhaArquivoErro, linhaCacheErro));
				}
				falsosPositivos++;
				return true;
			}
			
			if (memoriaCache.containsKey(vpn)) {
				EntradaCodificada entrada = memoriaCache.get(vpn);
				
				if (!entrada.equals(vpnCodificada) && entrada.isErro()) {
					if (debug) System.out.println("Falso negativo");
					falsosNegativos++;
					return true;
				}
				
				EntradaCodificada entradaCache = memoriaCache.remove(vpn);
				memoriaCache.put(vpn, entradaCache);
			} else {
				if (escreverCache(vpn)) {
					return true;
				}
			}
		} else {
			if (memoriaCache.containsKey(vpn)) {
				EntradaCodificada entradaCache = memoriaCache.remove(vpn);
				memoriaCache.put(vpn, entradaCache);
			} else {
				if (escreverCache(vpn)) {
					return true;
				}
			}
		}

		return false;
	}
	
	private boolean escreverCache(String vpn) {
		EntradaCodificada novaEntrada = codificador.codificar(vpn);
		
		if (memoriaCache.size() < tamanhoCache) {
			memoriaCache.put(vpn, novaEntrada);
		} else {
			if (substituir(vpn, novaEntrada)) {
				return true;
			}
		}

		return false;
	}

	private boolean substituir(String tag, EntradaCodificada novaEntrada) {
		String chaveRemovida = memoriaCache.entrySet().iterator().next().getKey();
		EntradaCodificada entradaRemovida = memoriaCache.remove(chaveRemovida);
		
		if (entradaRemovida.isErro()) {
			if (debug)
				System.out.println("Erro substituído\nEntrada com erro? " + entradaRemovida.equals(entradaErro));

			errosSubstituidos++;
			return true;
		}
		
		memoriaCache.put(tag, novaEntrada);
		return false;
	}
	
	public void printCache() {
		for (Map.Entry<String, EntradaCodificada> entry : memoriaCache.entrySet()) {
			String vpnOriginal = entry.getKey();
			EntradaCodificada vpnCodificada = entry.getValue();
			
			if (vpnCodificada.isErro()) {
				System.out.println("Entrada com erro");
				vpnCodificada.printPosicoes();
			}
			System.out.println("VPN originaria: " + vpnOriginal);
			System.out.print("VPN codificada: ");
			vpnCodificada.printEntrada();
			System.out.println("VPN decodificada: " + codificador.decodificar(vpnCodificada));
			System.out.println();
		}
	}
	
	private String obterVPN(EntradaCodificada entrada) {
		return memoriaCache
			      .entrySet()
			      .stream()
			      .filter(entry -> entrada.equals(entry.getValue()))
			      .map(Map.Entry::getKey).findFirst().get();
	}
}
