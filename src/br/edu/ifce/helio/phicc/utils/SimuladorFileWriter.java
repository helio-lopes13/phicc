package br.edu.ifce.helio.phicc.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class SimuladorFileWriter {
	public static void main(String[] args) {
		File arquivoOrdenacao = new File("trace_ordenacao.txt");
		File arquivoTraces = new File("traces.txt");
		File arquivoBloom = new File("trace_bloom_integer.txt");
		File arquivoInteger = new File("trace_integer.txt");
		File arquivoMenor = new File("traces_menores.txt");

		escreverArquivo("lista_trace_ordenacao.txt", arquivoOrdenacao);
		escreverArquivo("lista_traces.txt", arquivoTraces);
		escreverArquivo("lista_trace_bloom_integer.txt", arquivoBloom);
		escreverArquivo("lista_trace_integer.txt", arquivoInteger);
		escreverArquivo("lista_traces_menores.txt", arquivoMenor);
	}

	private static void escreverArquivo(String nome, File arquivo) {
		int byteTamanho = 0xFFFFFFFF;
		String formatacao = "%32s";
		try {
			List<String> linhasArquivo = Files.readAllLines(arquivo.toPath());
			linhasArquivo = linhasArquivo.stream()
					.map(linha -> String
							.format(formatacao, Integer.toBinaryString(Integer.parseInt(linha) & byteTamanho))
							.replace(" ", "0"))
					.collect(Collectors.toList());

			File novoArquivo = new File(nome);

			if (novoArquivo.createNewFile()) {
				System.out.println("Arquivo criado");

				FileWriter writerArquivo = new FileWriter(novoArquivo);
				
				for (String linha : linhasArquivo) {
					writerArquivo.write(linha + "\n");
				}
				
				writerArquivo.close();
			} else {
				System.out.println("Arquivo já existe");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
