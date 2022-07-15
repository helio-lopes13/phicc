package br.edu.ifce.helio.phicc.implementacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import br.edu.ifce.helio.phicc.modelo.MemoriaCache;
import br.edu.ifce.helio.phicc.modelo.TamanhoPHICC;

@SuppressWarnings("unused")
public class Simulador {
	private static Integer falsosPositivos = 0;

	private static Integer falsosNegativos = 0;

	private static Integer errosSubstituidos = 0;

	private static Integer hits = 0;

	private static Integer misses = 0;

	public static void main(String[] args) {
		/*
		 * A parte a seguir foram testes anteriores à simulação para confirmar se o que
		 * estava sendo feito funcionava
		 */

//		String palavra = "0000010100001001";
//		System.out.println("Palavra inicial: " + palavra);
//
//		PHICC phicc = new PHICC();
//		TamanhoPHICC tamanho = TamanhoPHICC.T44;
//		String[][] palavraCodificada = phicc.codificaPHICC(palavra, tamanho);
//		System.out.println("Matriz codificada: ");
//		printMatriz(palavraCodificada);
//
//		palavraCodificada[3][4] = "1";
//		palavraCodificada[3][5] = "1";
//
//		String palavraD = phicc.decodificaPHICC(palavraCodificada, tamanho);
//		String palavraD2 = phicc.decodificaPHICC(palavraCodificada, tamanho);
//		String palavraD3 = phicc.decodificaPHICC(palavraCodificada, tamanho);
//		System.out.println("Palavra decodificada: " + palavraD);
//		System.out.println("Palavra decodificada 2: " + palavraD2);
//		System.out.println("Palavra decodificada 3: " + palavraD3);
//
//		Integer entrada = 131325;
//		entrada &= 0x0000FFFF;

		// System.out.println(String.format("%16s",
		// Integer.toBinaryString(entrada)).replace(" ", "0"));

		// testeArquivo();
		// testeLinhaArquivo();
		// testeMemoriaCache();
		// testePHICC32();

		/*
		 * O método a seguir é o de simulação que roda várias vezes e obtém informações
		 * sobre os erros
		 */
		simulacao(TamanhoPHICC.T40, 8, 3, "enderecosInteiros.txt");
	}

	private static void simulacao(TamanhoPHICC tamanhoPHICC, int tamanhoCache, int errosAdjacentes,
			String nomeArquivo) {
		List<String> linhasArquivo = new ArrayList<>();
		Path caminhoLocal = Paths.get("").toAbsolutePath();
		Random random = new Random();

		try {
			linhasArquivo = Files.readAllLines(new File(caminhoLocal.toFile(), nomeArquivo).toPath());
		} catch (IOException exception) {
			System.out.println("Erro lendo o arquivo de traces");
			exception.printStackTrace();
		}

		linhasArquivo = linhasArquivo
				.stream().map(linha -> String
						.format("%16s", Integer.toBinaryString(Integer.parseInt(linha) & 0x0000FFFF)).replace(" ", "0"))
				.collect(Collectors.toList());
		int i = 0;
		int numeroLinhas = linhasArquivo.size();

		while (i < 500000) {
			System.out.println("Iteração " + (i + 1));
			int linhaCacheErro = random.nextInt(tamanhoCache);
			int linhaArquivoErro = random.nextInt(numeroLinhas);
//			System.out.println(String.format("Linha do arquivo com erro: %d\nLinha da cache a ter erro inserido: %d",
//					linhaArquivoErro, linhaCacheErro));
			MemoriaCache cache = new MemoriaCache(tamanhoPHICC, tamanhoCache, errosAdjacentes, 16);

			if (cache.simulacao(linhasArquivo, linhaArquivoErro, linhaCacheErro)) {
				falsosPositivos += cache.getFalsosPositivos();
				falsosNegativos += cache.getFalsosNegativos();
				errosSubstituidos += cache.getErrosSubstituidos();
			}
//			hits += cache.getHits();
//			misses += cache.getMisses();
//			System.out.println("Hits: " + hits);
//			System.out.println("Misses: " + misses);

			i++;
		}

		System.out.println("Falsos positivos: " + falsosPositivos);
		System.out.println("Falsos negativos: " + falsosNegativos);
		System.out.println("Erros substituídos: " + errosSubstituidos);
	}

	private static void testeMemoriaCache() {
		MemoriaCache cache = new MemoriaCache(TamanhoPHICC.T40, 4, 1, 16);

//		Random random = new Random();

		String[] palavras = { "1101110110011010", "1001010110111000", "1001010110111000", "0000000011111101",
				"0000000011111101", "0010100010111100", "0010100010111100", "0000000011111101" };
		int i = 0;
		for (String palavra : palavras) {
			//cache.lerCache(palavra);

			System.out.println("Iteração " + i);
			// boolean insereErro = random.nextBoolean();

			cache.printCache();
			cache.getMemoriaCache().values().forEach(entrada -> {
				entrada.inserirErro();
			});
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

	private static void testeLinhaArquivo() {
		List<String> palavras = Arrays.asList("1101110110011010", "1001010110111000", "1001010110111000",
				"1001010110111000", "0000000011111101", "0000000011111101");// , "0010000011111101",
																			// "0000110011111101");
		// new ArrayList<>();
		palavras = new LinkedList<>(palavras);
		int linhaCacheErro = 0;
		int tamanhoAtualCache = palavras.size();
		int j = 0;
		Iterator<String> iterador = palavras.iterator();
		String entrada = iterador.hasNext() ? iterador.next() : null;

		while (iterador.hasNext() && j < linhaCacheErro) {
			entrada = iterador.next();
			j++;
		}

		if (entrada != null && linhaCacheErro < tamanhoAtualCache) {
			System.out.println("Deu igual, entrada é " + entrada + ", j é " + j);
		} else {
			System.out.println("Entrada é " + entrada + ", j é " + j);
		}
	}

	private static void testePHICC32() {
		int i;
		PHICC phicc = new PHICC();
		TamanhoPHICC tamanho = TamanhoPHICC.T32;
		List<String> palavrasErro = new ArrayList<>();

		for (i = 0; i < 256 * 256; i++) {
			String palavra = String.format("%16s", Integer.toBinaryString(i & 0x0000FFFF)).replace(" ", "0");
			String[][] palavraCodificada = phicc.codificaPHICC(palavra, tamanho);
			if (i == 57890) {
				System.out.println("Matriz codificada: ");
				printMatriz(palavraCodificada);
			}

			String palavraDecodificada = phicc.decodificaPHICC(palavraCodificada, tamanho);

			if (i == 57890) {
				System.out.println("Matriz após uma decodificação: ");
				printMatriz(palavraCodificada);
				System.out.println(String.format("Palavra vinda do método: %s\tPalavra: %s",
						phicc.decodificaPHICC(palavraCodificada, tamanho), palavraDecodificada));
			}

			if (!phicc.decodificaPHICC(palavraCodificada, tamanho).equals(palavraDecodificada)) {
				palavrasErro.add(palavra);
			} else if (i == 57890) {
				System.out.println("Matriz após três decodificações: ");
				printMatriz(palavraCodificada);
				System.out.println(String.format("Palavra vinda do método: %s\tPalavra: %s",
						phicc.decodificaPHICC(palavraCodificada, tamanho), palavraDecodificada));
				System.out.println("Matriz após quatro decodificações: ");
				printMatriz(palavraCodificada);
				System.out.println(String.format("Palavra vinda do método: %s\tPalavra: %s",
						phicc.decodificaPHICC(palavraCodificada, tamanho), palavraDecodificada));
			}
		}

		System.out.println("Palavras com erro: " + palavrasErro.size());
//		for (String palavra : palavrasErro) {
//			System.out.println(String.format("Palavra: %s", palavra));
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
