package br.edu.ifce.helio.phicc.modelo;

import java.util.Map;

public class MemoriaCache {
	
	private Map<String, EntradaMemoriaCache> memoriaCache;
	
	private Integer tamanho;

	public Map<String, EntradaMemoriaCache> getMemoriaCache() {
		return memoriaCache;
	}

	public void setMemoriaCache(Map<String, EntradaMemoriaCache> memoriaCache) {
		this.memoriaCache = memoriaCache;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}
	
	public void lerCache(String tag) {
		if (memoriaCache.containsKey(tag)) {
			System.out.println("Cache hit");
		} else {
			System.out.println("Cache miss");
			if (memoriaCache.size() <= 8) {
				
			} else {
				
			}
		}
	}
}