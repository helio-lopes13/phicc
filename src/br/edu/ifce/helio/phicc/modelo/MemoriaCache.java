package br.edu.ifce.helio.phicc.modelo;

import java.util.Map;

import br.edu.ifce.helio.phicc.implementacao.PHICC;

public class MemoriaCache {

	private Map<String, EntradaMemoriaCache> memoriaCache;

	private TamanhoPHICC tamanhoPHICC;

	private Integer tamanhoCache;

	private PHICC phicc = new PHICC();

	public MemoriaCache(Map<String, EntradaMemoriaCache> memoriaCache, TamanhoPHICC tamanhoPHICC, Integer tamanhoCache) {
		this.memoriaCache = memoriaCache;
		this.tamanhoPHICC = tamanhoPHICC;
		this.tamanhoCache = tamanhoCache;
	}

	public Map<String, EntradaMemoriaCache> getMemoriaCache() {
		return memoriaCache;
	}

	public void setMemoriaCache(Map<String, EntradaMemoriaCache> memoriaCache) {
		this.memoriaCache = memoriaCache;
	}

	public TamanhoPHICC getTamanhoPHICC() {
		return tamanhoPHICC;
	}

	public void setTamanhoPHICC(TamanhoPHICC tamanhoPHICC) {
		this.tamanhoPHICC = tamanhoPHICC;
	}

	public void lerCache(String tag) {
		if (memoriaCache.containsKey(tag)) {
			System.out.println("Cache hit");
		} else {
			System.out.println("Cache miss");
			memoriaCache.values().stream().forEach(entrada -> entrada.incrementarContadorCache());
			EntradaMemoriaCache novaEntrada = new EntradaMemoriaCache();
			String[][] conteudo = phicc.codificaPHICC(tag, tamanhoPHICC);
			novaEntrada.setConteudo(conteudo);
			
			if (memoriaCache.size() <= tamanhoCache) {
				
				memoriaCache.put(tag, novaEntrada);
			} else {
				substituir(novaEntrada);
			}
		}
	}
	
	private void substituir(EntradaMemoriaCache novaEntrada) {
		
	}
	
	public void printCache() {
		for (Map.Entry<String, EntradaMemoriaCache> entry : memoriaCache.entrySet()) {
			String chave = entry.getKey();
			EntradaMemoriaCache entrada = entry.getValue();
			
			System.out.println("Chave da entrada: " + chave);
			System.out.println("No. de acessos na cache: " + entrada.getContadorCache());
			System.out.println("No. de acessos da entrada: " + entrada.getContadorAcessos());
			System.out.println("Conteúdo: ");
			PHICC.printMatriz(entrada.getConteudo());
		}
	}
}