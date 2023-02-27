package br.edu.ifce.helio.phicc.modelo;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
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
		if (codificador instanceof TipoParidade && (quantidadeErros < 0 || quantidadeErros > 3)) {
			throw new RuntimeException("Quantidade de erros deve ser entre 0 e 3");
		} else if (codificador instanceof PHICC && quantidadeErros < 1 || quantidadeErros > 8) {
			throw new RuntimeException("Quantidade de erros deve ser entre 1 e 8");
		} else {
			NovaEntradaMemoriaCache.setQuantidadeErros(this.quantidadeErros);
		}
	}
	
	public boolean simulacao(List<String> linhas, int linhaArquivoErro, int linhaCacheErro) {
		for (int i = 0; i < linhas.size(); i++) {
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
					System.out.println("Erro não pôde ser inserido");
					errosSubstituidos++;
					return true;
				}
			}
			String linha = obterLinhaDoArquivo(linhas, i);

			if (lerCache(linha, linhaArquivoErro, linhaCacheErro)) {
				return true;
			}
		}

		System.out.println("Nenhuma intercorrência");
		System.out.println(String.format("Linha do arquivo com erro: %d\nLinha da cache a ter erro inserido: %d",
				linhaArquivoErro, linhaCacheErro));
		return false;
	}

	public boolean lerCache(String tag, int linhaArquivoErro, int linhaCacheErro) {
		memoriaCache.values().stream().forEach(entrada -> entrada.incrementarContadorCache());

		for (Entry<String, NovaEntradaMemoriaCache> entrada : memoriaCache.entrySet()) {
			String entradaDecodificada = codificador.decodificar(entrada.getValue().getEntradaCodificada());

			if (tag.equals(entradaDecodificada) && !tag.equals(entrada.getKey()) && entrada.getValue().isErro()) {
				System.out.println("Falso positivo");
				System.out.println("Tag: " + tag);
				System.out.println(String.format("Linha do arquivo com erro: %d\nLinha da cache a ter erro inserido: %d",
						linhaArquivoErro, linhaCacheErro));
				falsosPositivos++;
				return true;
			}
		}

		if (memoriaCache.containsKey(tag)) {
			NovaEntradaMemoriaCache entrada = memoriaCache.get(tag);

			if (!codificador.decodificar(entrada.getEntradaCodificada()).equals(tag)) {
				System.out.println("Falso negativo");
				falsosNegativos++;
				return true;
			}
			entrada.incrementarContadorAcesso();
			memoriaCache.remove(tag);
			memoriaCache.put(tag, entrada);
		} else {
			if (escreverCache(tag)) {
				return true;
			}
		}

		return false;
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
			if (entradaRemovida.getContadorAcessos() > 1 && codificador.decodificar(entradaRemovida.getEntradaCodificada()).equals(chaveRemovida)) {
				System.out.println("Erro acessado, corrigido e substituído");
				entradaRemovida.getEntradaCodificada().printEntrada();
			} else {
				System.out.println("Erro substituído");
			}
			errosSubstituidos++;
			return true;
		}
		return false;
	}
	
	private String obterLinhaDoArquivo(List<String> linhas, int i) {
		String linha = linhas.get(i);
		
		if (linha.length() != tamanhoPalavra) {
			throw new RuntimeException("Tamanho da palavra difere do estabelecido.");
		}
		return linha;
	}
	
}
