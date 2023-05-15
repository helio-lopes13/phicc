package br.edu.ifce.helio.phicc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;


public class SimuladorUtils {
	private static List<String> linhasArquivo = Arrays.asList();

	private static String[] linhas = null;
	
	private static int numeroLinhasArquivo = 0;
	
	private static FileInputStream stream = null;
	
	private static Scanner scanner = null;
	
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
	
	public static Scanner getScannerArquivo(String nomeArquivo, int tamanhoPalavra) {
		try {
			stream = new FileInputStream(nomeArquivo);
			scanner = new Scanner(stream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return scanner;
	}
	
	public static int getTamanhoArquivo(String nomeArquivo) {
		try (Stream<String> fileStream = Files.lines(Paths.get(nomeArquivo))) {
	        numeroLinhasArquivo = (int) fileStream.count();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return numeroLinhasArquivo;
	}
	
	public static void fecharScannerArquivo() {
		if (stream != null && scanner != null) {
			try {
				stream.close();;
			} catch (IOException e) {
				e.printStackTrace();
			}
			scanner.close();;
		}
	}
}
