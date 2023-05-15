package br.edu.ifce.helio.phicc.implementacao;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import br.edu.ifce.helio.phicc.modelo.NovaMemoriaCache;
import br.edu.ifce.helio.phicc.modelo.Paridade;
import br.edu.ifce.helio.phicc.utils.SimuladorFileWriter;
import br.edu.ifce.helio.phicc.utils.SimuladorUtils;

public class Simulador {
	private static int falsosPositivos = 0;

	private static int falsosNegativos = 0;

	private static int errosSubstituidos = 0;

	private static int semIntercorrencias = 0;

	private static int entradasInvalidas = 0;

	private static String nomeArquivo = "trace_merge_sort.txt";

	private static boolean debug = false;

	public static void main(String[] args) {
		SimuladorFileWriter.createFile(nomeArquivo);
		
		System.out.println("Nome do arquivo: " + nomeArquivo);
		Instant startTime = Instant.now();
		Codificador paridade;

//		paridade = Paridade.SEM_PARIDADE;
//		simulacao(paridade, 8, 1, 32);
//		simulacao(paridade, 8, 2, 32);
//		simulacao(paridade, 8, 3, 32);
//
//		paridade = Paridade.PARIDADE_MSB;
//		simulacao(paridade, 8, 1, 32);
//		simulacao(paridade, 8, 2, 32);
//		simulacao(paridade, 8, 3, 32);
//
//		paridade = Paridade.PARIDADE_MSB_4;
//		simulacao(paridade, 8, 1, 32);
//		simulacao(paridade, 8, 2, 32);
//		simulacao(paridade, 8, 3, 32);
//
//		paridade = Paridade.PARIDADE_MSB_8;
//		simulacao(paridade, 8, 1, 32);
//		simulacao(paridade, 8, 2, 32);
//		simulacao(paridade, 8, 3, 32);
//
//		paridade = Paridade.PARIDADE_MSB_12;
//		simulacao(paridade, 8, 1, 32);
//		simulacao(paridade, 8, 2, 32);
//		simulacao(paridade, 8, 3, 32);
//
//		paridade = Paridade.PARIDADE_MSB_16;
//		simulacao(paridade, 8, 1, 32);
//		simulacao(paridade, 8, 2, 32);
//		simulacao(paridade, 8, 3, 32);

		EntradaCodificadaLinear.paridadeEmbutida = true;
		paridade = Paridade.P_BIT_MSB;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);

		paridade = Paridade.P_BIT_MSB_4;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);

		paridade = Paridade.P_BIT_MSB_8;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);

		paridade = Paridade.P_BIT_MSB_12;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);

		paridade = Paridade.P_BIT_MSB_16;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);

		EntradaCodificadaLinear.paridadeEmbutida = false;
//		paridade = Paridade.PARIDADE_2_MSB;
//		simulacao(paridade, 8, 1, 32);
//		simulacao(paridade, 8, 2, 32);
//		simulacao(paridade, 8, 3, 32);
//
//		paridade = Paridade.PARIDADE_2_MSB_4;
//		simulacao(paridade, 8, 1, 32);
//		simulacao(paridade, 8, 2, 32);
//		simulacao(paridade, 8, 3, 32);
//
//		paridade = Paridade.PARIDADE_2_MSB_8;
//		simulacao(paridade, 8, 1, 32);
//		simulacao(paridade, 8, 2, 32);
//		simulacao(paridade, 8, 3, 32);
//
//		paridade = Paridade.PARIDADE_2_MSB_12;
//		simulacao(paridade, 8, 1, 32);
//		simulacao(paridade, 8, 2, 32);
//		simulacao(paridade, 8, 3, 32);
//
//		paridade = Paridade.PARIDADE_2_MSB_16;
//		simulacao(paridade, 8, 1, 32);
//		simulacao(paridade, 8, 2, 32);
//		simulacao(paridade, 8, 3, 32);

		Instant endTime = Instant.now();
		System.out.println("Tempo de execução: " + Duration.between(startTime, endTime));
	}

	private static void simulacao(Codificador codificador, int tamanhoCache, int errosAdjacentes, int tamanhoPalavra) {
		NovaMemoriaCache.debug = debug;
		Random random = new Random();
		int i = 0;
		
		System.out.println("Codificador: " + codificador);
		System.out.println("Quantidade de erros adjacentes: " + errosAdjacentes);
		SimuladorFileWriter.write("Codificador: " + codificador);
		SimuladorFileWriter.write("Quantidade de erros adjacentes: " + errosAdjacentes);
		String[] linhas = SimuladorUtils.getArquivo(nomeArquivo, tamanhoPalavra);
//		codificarArquivo(codificador, linhas);

		int iteracoes = 50000;
		Instant inicio = Instant.now();
		while (i < iteracoes) {
			if (debug) System.out.println("Iteração " + (i + 1));
			int linhaArquivoErro = random.nextInt(linhas.length);

			NovaMemoriaCache cache = new NovaMemoriaCache(codificador, tamanhoCache, errosAdjacentes, tamanhoPalavra);

			if (cache.simulacao(linhas, linhaArquivoErro, random)) {
				falsosPositivos += cache.falsosPositivos;
				falsosNegativos += cache.falsosNegativos;
				errosSubstituidos += cache.errosSubstituidos;
				semIntercorrencias += cache.semIntercorrencias;
				entradasInvalidas += cache.entradasInvalidas;
			} else {
				semIntercorrencias += cache.semIntercorrencias;
			}

			if ((i + 1) % 10000 == 0) {
				System.out.println("Duração da simulação: " + Duration.between(inicio, Instant.now()));
				inicio = Instant.now();
			}

			i++;
		}

		System.out.println("Falsos positivos: " + falsosPositivos);
		System.out.println("Falsos negativos: " + falsosNegativos);
		System.out.println("Erros substituídos: " + errosSubstituidos);
		System.out.println("Não-intercorrências: " + semIntercorrencias);
		System.out.println("Entradas inválidas: " + entradasInvalidas);
		System.out.println();

		SimuladorFileWriter.write("Falsos positivos: " + falsosPositivos);
		SimuladorFileWriter.write("Falsos negativos: " + falsosNegativos);
		SimuladorFileWriter.write("Erros substituídos: " + errosSubstituidos);
		SimuladorFileWriter.write("Não-intercorrências: " + semIntercorrencias);
		SimuladorFileWriter.write("Entradas inválidas: " + entradasInvalidas);
		SimuladorFileWriter.write();

		zerarResultados();
	}

	private static void zerarResultados() {
		falsosPositivos = 0;
		falsosNegativos = 0;
		errosSubstituidos = 0;
		semIntercorrencias = 0;
		entradasInvalidas = 0;
	}
	
	public static void codificarArquivo(Codificador codificador, String[] linhas) {
		Set<String> setLinhas = new HashSet<>(Arrays.asList(linhas));
		
		for (String linha : setLinhas) {
			codificador.codificar(linha);
		}
	}

}
