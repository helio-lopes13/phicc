package br.edu.ifce.helio.phicc.modelo;

import java.util.Map;

import br.edu.ifce.helio.phicc.implementacao.PHICC;

public class MemoriaCache {
	
	private Map<String, EntradaMemoriaCache> memoriaCache;
	
	private TamanhoPHICC tamanho;
	
	private PHICC phicc = new PHICC();

	public Map<String, EntradaMemoriaCache> getMemoriaCache() {
		return memoriaCache;
	}

	public void setMemoriaCache(Map<String, EntradaMemoriaCache> memoriaCache) {
		this.memoriaCache = memoriaCache;
	}

	public TamanhoPHICC getTamanho() {
		return tamanho;
	}

	public void setTamanho(TamanhoPHICC tamanho) {
		this.tamanho = tamanho;
	}
	
	public void lerCache(String tag) {
		if (memoriaCache.containsKey(tag)) {
			System.out.println("Cache hit");
		} else {
			System.out.println("Cache miss");
			if (memoriaCache.size() <= 8) {
				EntradaMemoriaCache novaEntrada = new EntradaMemoriaCache();
				String[][] conteudo = phicc.codificaPHICC(tag, tamanho);
				novaEntrada.setConteudo(conteudo);
				
				memoriaCache.put(tag, novaEntrada);
			} else {
				
			}
		}
	}
}