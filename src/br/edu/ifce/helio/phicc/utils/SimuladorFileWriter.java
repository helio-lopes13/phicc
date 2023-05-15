package br.edu.ifce.helio.phicc.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SimuladorFileWriter {
	
//	private static File arquivoCompression = new File("compression.txt");
	
	private static File resultFile = null;
	
	private static FileWriter resultFileWriter = null;
	
	public static void main(String[] args) {
//		File arquivoMergeSort = new File("merge_sort.out");
//		File arquivoFFT = new File("fft.out");
//		File arquivoBloom = new File("bloom_filter.out");
//		File arquivoMenor = new File("traces_menores.txt");
//
//		escreverArquivo("trace_merge_sort.txt", arquivoMergeSort);
//		escreverArquivo("trace_fft.txt", arquivoFFT);
//		escreverArquivo("trace_bloom_filter.txt", arquivoBloom);
//		escreverArquivo("trace_compression.txt", arquivoCompression);
//		escreverArquivo("lista_traces_menores.txt", arquivoMenor);
		
		String nomeArquivo = "lista_trace_ordenacao.txt";
		String[] linhasBloomFilter = SimuladorUtils.getArquivo(nomeArquivo, 32);
		
		Set<String> setBloomFilter = new HashSet<>(Arrays.asList(linhasBloomFilter));
		
		System.out.println("Número de palavras únicas em trace_bloom_filter: " + setBloomFilter.size());
	}
	
	public static void createFile(String tracefile) {
		resultFile = new File("result_" + tracefile);
		try {
			resultFile.createNewFile();
			resultFileWriter = new FileWriter(resultFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void write(String message) {
		try {
			resultFileWriter.write(message + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void write() {
		try {
			resultFileWriter.write("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//
//	private static void escreverArquivo(String nome, File arquivo) {
//		int byteTamanho = 0xFFFFFFFF;
//		String formatacao = "%32s";
//		try {
//			List<String> linhasArquivo = Files.readAllLines(arquivo.toPath());
//			if (arquivo == arquivoCompression) {
//				int linhaEOF = linhasArquivo.indexOf("#eof");
//				linhasArquivo.remove(linhaEOF);
//			}
//			
//			linhasArquivo = linhasArquivo.stream()
//					.map(linha -> String
//							.format(formatacao, Long.toBinaryString(Long.decode(linha) & byteTamanho))
//							.replace(" ", "0"))
//					.collect(Collectors.toList());
//
//			File novoArquivo = new File(nome);
//
//			if (novoArquivo.createNewFile()) {
//				System.out.println("Arquivo criado");
//
//				FileWriter writerArquivo = new FileWriter(novoArquivo);
//				
//				for (String linha : linhasArquivo) {
//					writerArquivo.write(linha + "\n");
//				}
//				
//				writerArquivo.close();
//			} else {
//				System.out.println("Arquivo já existe");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
}
