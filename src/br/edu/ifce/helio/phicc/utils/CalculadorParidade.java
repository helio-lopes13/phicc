package br.edu.ifce.helio.phicc.utils;

import java.util.HashMap;
import java.util.Map;

public class CalculadorParidade {

	private static Map<String, String> paridades = new HashMap<>();
	
	public static String calcularBitParidade(String palavra) {
		if (paridades.containsKey(palavra)) {
			return paridades.get(palavra);
		}
		
		String bitParidade = calcularParidade(palavra);
		paridades.put(palavra, bitParidade);
		return bitParidade;
	}
	
	private static String calcularParidade(String palavra) {
		String[] palavraSeparada = palavra.split("");
		
		int paridade = 0;

		for (int i = 0; i < palavraSeparada.length; i++) {
			paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
		}

		return String.valueOf(paridade);
	}
}
