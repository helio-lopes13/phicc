package br.edu.ifce.helio.phicc.implementacao;

import br.edu.ifce.helio.phicc.modelo.TamanhoPHICC;

public class Simulador {
	public static void main(String[] args) {
		String palavra = "1101110110011010";
		System.out.println("Palavra inicial: " + palavra);

		PHICC phicc = new PHICC();
		String[][] palavraCodificada = phicc.codificaPHICC(palavra, TamanhoPHICC.T40);
		System.out.println("Matriz codificada: ");
		printMatriz(palavraCodificada);

		palavra = phicc.decodificaPHICC(palavraCodificada, TamanhoPHICC.T40);
		System.out.println("Palavra decodificada: " + palavra);
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
