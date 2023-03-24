package br.edu.ifce.helio.phicc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class SimuladorUtils {
	private static Path caminhoLocal = Paths.get("").toAbsolutePath();

	// private static List<String> linhasArquivo = Arrays.asList();

	private static File arquivo = null;

	public static Scanner getArquivo(String nomeArquivo, int tamanhoPalavra) {
//		int byteTamanho = tamanhoPalavra == 16 ? 0x0000FFFF : 0xFFFFFFFF;
//		String formatacao = "%" + tamanhoPalavra + "s";
		Scanner scannerArquivo = null;

//		if (linhasArquivo.isEmpty()) {
			try {
				FileInputStream streamArquivo = new FileInputStream(arquivo);
				scannerArquivo = new Scanner(streamArquivo, "UTF-8");
			} catch (IOException exception) {
				System.out.println("Erro lendo o arquivo de traces");
				exception.printStackTrace();
			}

//			linhasArquivo = linhasArquivo.stream()
//					.map(linha -> String
//							.format(formatacao, Integer.toBinaryString(Integer.parseInt(linha) & byteTamanho))
//							.replace(" ", "0"))
//					.collect(Collectors.toList());
//		}

		// return linhasArquivo.toArray(new String[linhasArquivo.size()]);
		return scannerArquivo;
	}
	
	public static void gerarArquivo(String nomeArquivo) {
		arquivo = new File(caminhoLocal.toFile(), nomeArquivo);
	}

	public static long getNumeroLinhas() {
		long numeroLinhas = 0;
		try {
			numeroLinhas = Files.lines(arquivo.toPath()).count();
		} catch (IOException exception) {
			System.out.println("Exceção: " + exception.getMessage());
		}
		return numeroLinhas;
	}

	public static String linhaFormatada(String linha, int tamanhoPalavra) {
		int byteTamanho = tamanhoPalavra == 16 ? 0x0000FFFF : 0xFFFFFFFF;
		String formatacao = "%" + tamanhoPalavra + "s";

		String linhaFormatada =  String.format(formatacao, Integer.toBinaryString(Integer.parseInt(linha) & byteTamanho)).replace(" ",
				"0");
		// System.out.println("Linha formatada: " + linhaFormatada);
		return linhaFormatada;
	}
}
