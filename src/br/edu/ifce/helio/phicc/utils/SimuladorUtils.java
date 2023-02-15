package br.edu.ifce.helio.phicc.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimuladorUtils {
	static Path caminhoLocal = Paths.get("").toAbsolutePath();
	
	public static List<String> getArquivo(String nomeArquivo) {
		List<String> linhasArquivo = Arrays.asList("");
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
		
		return linhasArquivo;
	}
}
