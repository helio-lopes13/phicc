package br.edu.ifce.helio.phicc.modelo;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import br.edu.ifce.helio.phicc.implementacao.PHICC;

public class MemoriaCache {

	private Map<String, EntradaMemoriaCache> memoriaCache;

	private TamanhoPHICC tamanhoPHICC;

	private Integer tamanhoCache;

	private Integer falsosPositivos = 0;

	private Integer falsosNegativos = 0;

	private Integer errosSubstituidos = 0;

	private Integer hits = 0;

	private Integer misses = 0;

	private PHICC phicc = new PHICC();

	public MemoriaCache(TamanhoPHICC tamanhoPHICC,
			Integer tamanhoCache, Integer quantidadeErros) {
		this.memoriaCache = new LinkedHashMap<>();
		this.tamanhoPHICC = tamanhoPHICC;
		this.tamanhoCache = tamanhoCache;
		if (quantidadeErros < 1 || quantidadeErros > 8) {
			throw new RuntimeException("Quantidade de erros deve ser entre 1 e 8");
		} else {
			EntradaMemoriaCache.setQuantidadeErros(quantidadeErros);
		}
	}

	public Map<String, EntradaMemoriaCache> getMemoriaCache() {
		return memoriaCache;
	}

	public void setMemoriaCache(LinkedHashMap<String, EntradaMemoriaCache> memoriaCache) {
		this.memoriaCache = memoriaCache;
	}

	public TamanhoPHICC getTamanhoPHICC() {
		return tamanhoPHICC;
	}

	public void setTamanhoPHICC(TamanhoPHICC tamanhoPHICC) {
		this.tamanhoPHICC = tamanhoPHICC;
	}

	public Integer getFalsosPositivos() {
		return falsosPositivos;
	}

	public Integer getFalsosNegativos() {
		return falsosNegativos;
	}

	public Integer getErrosSubstituidos() {
		return errosSubstituidos;
	}

	public Integer getHits() {
		return hits;
	}

	public Integer getMisses() {
		return misses;
	}

	public void printCache() {
		for (Map.Entry<String, EntradaMemoriaCache> entry : memoriaCache.entrySet()) {
			String chave = entry.getKey();
			EntradaMemoriaCache entrada = entry.getValue();

			System.out.println("Chave da entrada: " + chave);
//			System.out.println("No. de acessos na cache: " + entrada.getContadorCache());
//			System.out.println("No. de acessos da entrada: " + entrada.getContadorAcessos());
			System.out.println("Conteúdo: ");
			PHICC.printMatriz(entrada.getConteudo());
		}
	}

	public boolean simulacao(List<String> linhas, int linhaArquivoErro, int linhaCacheErro) {
		for (int i = 0; i < linhas.size(); i++) {
			if (i == linhaArquivoErro) {
				int j = 0;
				Iterator<EntradaMemoriaCache> iteradorMemoria = memoriaCache.values().iterator();
				EntradaMemoriaCache entrada = iteradorMemoria.next();
				while (j < linhaCacheErro && iteradorMemoria.hasNext()) {
					entrada = iteradorMemoria.next();
					j++;
				}
				
				if (entrada != null && j != tamanhoCache && linhaCacheErro != tamanhoCache - 1) {
					entrada.inserirErro();
				}
			}
			String linha = linhas.get(i);
			
			if (lerCache(linha)) {
				return true;
			}
		}
		
		return false;
	}

	public boolean lerCache(String tag) {
		memoriaCache.values().stream().forEach(entrada -> entrada.incrementarContadorCache());

		for (Entry<String, EntradaMemoriaCache> entrada : memoriaCache.entrySet()) {
			String entradaDecodificada = phicc.decodificaPHICC(entrada.getValue().getConteudo(), tamanhoPHICC);
			
			if (tag.equals(entradaDecodificada) && entrada.getValue().isErro()) {
				hits++;
				falsosPositivos++;
				return true;
			}
		}

		if (memoriaCache.containsKey(tag)) {
			hits++;
			EntradaMemoriaCache entrada = memoriaCache.get(tag);

			if (!phicc.decodificaPHICC(entrada.getConteudo(), tamanhoPHICC).equals(tag)) {
				falsosNegativos++;
				return true;
			}
			entrada.incrementarContadorAcesso();
			memoriaCache.remove(tag);
			memoriaCache.put(tag, entrada);
		} else {
			misses++;
			if (escreverCache(tag)) {
				return true;
			}
		}
		
		return false;
	}

	private boolean escreverCache(String tag) {
		EntradaMemoriaCache novaEntrada = new EntradaMemoriaCache();
		String[][] conteudo = phicc.codificaPHICC(tag, tamanhoPHICC);
		novaEntrada.setConteudo(conteudo);

		if (memoriaCache.size() < tamanhoCache) {
			memoriaCache.put(tag, novaEntrada);
		} else {
			if (substituir(tag, novaEntrada)) {
				return true;
			}
		}
		
		return false;
	}

	private boolean substituir(String tag, EntradaMemoriaCache novaEntrada) {
		EntradaMemoriaCache entradaRemovida = memoriaCache.remove(memoriaCache.entrySet().iterator().next().getKey());
		memoriaCache.put(tag, novaEntrada);
		
		if (entradaRemovida.isErro()) {
			errosSubstituidos++;
			return true;
		}
		return false;
	}
}