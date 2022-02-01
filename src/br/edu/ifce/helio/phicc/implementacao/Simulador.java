package br.edu.ifce.helio.phicc.implementacao;

public class Simulador {
	public static void main(String[] args) {
		String palavra = "1101110110011010";
		System.out.println("Palavra inicial: " + palavra);
		
		String[][] palavraCodificada = PHICC.codificaPHICC40(palavra);
		System.out.println("Matriz codificada: ");
		printMatriz(palavraCodificada);
		
		System.out.println("Palavra decodificada: " + PHICC.decodificaPHICC40(palavraCodificada));
	}
	
	private static void printMatriz(String[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
