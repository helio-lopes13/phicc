package br.edu.ifce.helio.phicc.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class SimuladorUtils {
	private static Path caminhoLocal = Paths.get("").toAbsolutePath();

	private static List<String> linhasArquivo = Arrays.asList();

	public static String[] getArquivo(String nomeArquivo, int tamanhoPalavra) {
//		int byteTamanho = tamanhoPalavra == 16 ? 0x0000FFFF : 0xFFFFFFFF;
//		String formatacao = "%" + tamanhoPalavra + "s";

		if (linhasArquivo.isEmpty()) {
			try {
				linhasArquivo = Files.readAllLines(new File(caminhoLocal.toFile(), nomeArquivo).toPath());
			} catch (IOException exception) {
				System.out.println("Erro lendo o arquivo de traces");
				exception.printStackTrace();
			}

//			linhasArquivo = linhasArquivo.stream()
//					.map(linha -> String
//							.format(formatacao, Integer.toBinaryString(Integer.parseInt(linha) & byteTamanho))
//							.replace(" ", "0"))
//					.collect(Collectors.toList());
		}

		String[] arrayLinhas = new String[linhasArquivo.size()];

		return linhasArquivo.toArray(arrayLinhas);
	}

	public static String linhaFormatada(String linha, int tamanhoPalavra) {
		int byteTamanho = tamanhoPalavra == 16 ? 0x0000FFFF : 0xFFFFFFFF;
		String formatacao = "%" + tamanhoPalavra + "s";

		return String.format(formatacao, Integer.toBinaryString(Integer.parseInt(linha) & byteTamanho)).replace(" ",
				"0");
	}
}
