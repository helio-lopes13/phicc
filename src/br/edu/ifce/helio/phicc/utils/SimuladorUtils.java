package br.edu.ifce.helio.phicc.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;


public class SimuladorUtils {
	private static List<String> linhasArquivo = Arrays.asList();

	private static String[] linhas = null;
	
	public static String[] getArquivo(String nomeArquivo, int tamanhoPalavra) {

		if (linhasArquivo.isEmpty()) {
			try {
				linhasArquivo = Files.readAllLines(new File(nomeArquivo).toPath());
			} catch (IOException exception) {
				System.out.println("Erro lendo o arquivo de traces");
				exception.printStackTrace();
			}
		}

		if (linhas != null) return linhas;
		
		String[] arrayLinhas = new String[linhasArquivo.size()];

		linhas = linhasArquivo.toArray(arrayLinhas);
		return linhas;
	}
}
