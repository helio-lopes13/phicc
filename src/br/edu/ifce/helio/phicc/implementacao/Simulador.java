package br.edu.ifce.helio.phicc.implementacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import br.edu.ifce.helio.phicc.modelo.MemoriaCache;
import br.edu.ifce.helio.phicc.modelo.TamanhoPHICC;

public class Simulador {
	private static Integer falsosPositivos = 0;

	private static Integer falsosNegativos = 0;

	private static Integer hits = 0;

	private static Integer misses = 0;

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

	private static void simulacao(TamanhoPHICC tamanhoPHICC, int tamanhoCache, int errosAdjacentes,
			String nomeArquivo) {
		List<String> linhasArquivo = new ArrayList<>();
		Path caminhoLocal = Paths.get("").toAbsolutePath();
		Random random = new Random();

		try {
			linhasArquivo = Files.readAllLines(new File(caminhoLocal.toFile(), "traces.txt").toPath());
			linhasArquivo = linhasArquivo.stream().map(linha -> String
					.format("%16s", Integer.toBinaryString(Integer.parseInt(linha) & 0x0000FFFF)).replace(" ", "0"))
					.collect(Collectors.toList());
		} catch (IOException exception) {
			System.out.println("Erro lendo o arquivo de traces");
			exception.printStackTrace();
		}

		int i = 0;
		int numeroLinhas = linhasArquivo.size();

		while (i < 1000) {
			System.out.println("Iteração " + (i + 1));
			int linhaCacheErro = random.nextInt(tamanhoCache);
			int linhaArquivoErro = random.nextInt(numeroLinhas);
			MemoriaCache cache = new MemoriaCache(tamanhoPHICC, tamanhoCache, errosAdjacentes);

			if (cache.simulacao(linhasArquivo, linhaArquivoErro, linhaCacheErro)) {
				falsosPositivos += cache.getFalsosPositivos();
				falsosNegativos += cache.getFalsosNegativos();
				hits += cache.getHits();
				misses += cache.getMisses();
			}
			
			i++;
		}
	}

	private static void testeArquivo() {
		Path caminhoLocal = Paths.get("").toAbsolutePath();
		File arquivoTraces = new File(caminhoLocal.toFile(), "traces.txt");
		System.out.println(arquivoTraces.toString());

		BufferedReader leitor;
		try {
			int i = 0;
			leitor = new BufferedReader(new FileReader(arquivoTraces));
			String linha = leitor.readLine();

			while (linha != null && i < 30) {
				String linhaBinaria = String
						.format("%16s", Integer.toBinaryString(Integer.parseInt(linha) & 0x0000FFFF)).replace(" ", "0");
				System.out.println(linhaBinaria);

				linha = leitor.readLine();
				i++;
			}
			System.out.println(Files.readAllLines(arquivoTraces.toPath()).size());
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
