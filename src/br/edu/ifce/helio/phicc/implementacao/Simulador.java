package br.edu.ifce.helio.phicc.implementacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

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

		testeArquivo();
		testeMemoriaCache();
	}

	private static void testeMemoriaCache() {
		MemoriaCache cache = new MemoriaCache(TamanhoPHICC.T40, 4, 1);

//		Random random = new Random();

		String[] palavras = { "1101110110011010", "1001010110111000", "1001010110111000", "0000000011111101",
				"0000000011111101", "0010100010111100", "0010100010111100", "0000000011111101" };
		int i = 0;
		for (String palavra : palavras) {
			cache.lerCache(palavra);

			System.out.println("Iteração " + i);
			// boolean insereErro = random.nextBoolean();

			cache.printCache();
			cache.getMemoriaCache().values().forEach(entrada -> {
				entrada.inserirErro();
			});
			i++;
		}

//		MemoriaCache cache = new MemoriaCache(new LinkedHashMap<>(), TamanhoPHICC.T40, 3);
//
//		List<String> palavras = Arrays.asList("1101110110011010", "1001010110111000", "0000000011111101", "1101110110011010", "0010100010111100");
//		int i = 0;
//		for (String palavra : palavras) {
//			cache.lerCache(palavra);
//
//			System.out.println("IteraÃ§Ã£o " + i);
//			cache.printCache();
//
//			i++;
//		}
	}

	private static void simulacao(TamanhoPHICC tamanhoPHICC, int tamanhoCache, int errosAdjacentes) {

	}

	private static void testeArquivo() {
		Path caminhoLocal = Paths.get("").toAbsolutePath();
		File arquivoTraces = new File(caminhoLocal.toFile(), "enderecosInteiros.txt");
		System.out.println(arquivoTraces.toString());

		BufferedReader leitor;
		try {
			int i = 0;
			leitor = new BufferedReader(new FileReader(arquivoTraces));
			String linha = leitor.readLine();

			while (linha != null && i < 30) {
				String linhaBinaria;
				System.out.println(linhaBinaria = String
						.format("%16s", Integer.toBinaryString(Integer.parseInt(linha) & 0x0000FFFF))
						.replace(" ", "0"));

				linha = leitor.readLine();
				i++;
			}
			leitor.close();
		} catch (IOException exception) {
			System.out.println("Erro detectado na leitura do arquivo");
		}
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
