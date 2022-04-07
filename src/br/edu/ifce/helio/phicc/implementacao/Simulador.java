package br.edu.ifce.helio.phicc.implementacao;

import java.util.Arrays;
import java.util.List;

import br.edu.ifce.helio.phicc.modelo.MemoriaCache;
import br.edu.ifce.helio.phicc.modelo.TamanhoPHICC;

public class Simulador {
	public static void main(String[] args) {
		String palavra = "1101110110011010";
		System.out.println("Palavra inicial: " + palavra);

		PHICC phicc = new PHICC();
		TamanhoPHICC tamanho = TamanhoPHICC.T44;
		String[][] palavraCodificada = phicc.codificaPHICC(palavra, tamanho);
		System.out.println("Matriz codificada: ");
		printMatriz(palavraCodificada);

		palavra = phicc.decodificaPHICC(palavraCodificada, tamanho);
		System.out.println("Palavra decodificada: " + palavra);

		Integer entrada = 131325;
		entrada &= 0x0000FFFF;

		System.out.println(String.format("%16s", Integer.toBinaryString(entrada)).replace(" ", "0"));

		testeMemoriaCache();
	}

	private static void testeMemoriaCache() {
		MemoriaCache cache = new MemoriaCache(TamanhoPHICC.T40, 4);

		List<String> palavras = Arrays.asList("1101110110011010", "1001010110111000", "1001010110111000",
				"0000000011111101", "0000000011111101", "0010100010111100", "0010100010111100", "0000000011111101");
		int i = 0;
		for (String palavra : palavras) {
			cache.lerCache(palavra);

			System.out.println("Iteração " + i);
			cache.printCache();
			i++;
		}
		
//		MemoriaCache cache = new MemoriaCache(new LinkedHashMap<>(), TamanhoPHICC.T40, 3);
//
//		List<String> palavras = Arrays.asList("1101110110011010", "1001010110111000", "0000000011111101", "1101110110011010", "0010100010111100");
//		int i = 0;
//		for (String palavra : palavras) {
//			cache.lerCache(palavra);
//
//			System.out.println("Iteração " + i);
//			cache.printCache();
//
//			i++;
//		}
	}

	private static void printMatriz(String[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if (j != matriz[i].length - 1) {
					System.out.print(matriz[i][j] + "\t");
				} else {
					System.out.print(matriz[i][j]);
				}
			}
			System.out.println();
		}
	}
}
