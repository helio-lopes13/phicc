package br.edu.ifce.helio.phicc.implementacao;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.Scanner;

import br.edu.ifce.helio.phicc.modelo.NovaMemoriaCache;
import br.edu.ifce.helio.phicc.modelo.Paridade;
import br.edu.ifce.helio.phicc.utils.SimuladorUtils;

public class Simulador {
	private static Integer falsosPositivos = 0;

	private static Integer falsosNegativos = 0;

	private static Integer errosSubstituidos = 0;

	private static String nomeArquivo = "traces.txt";
	
	private static boolean debug = false;
	
	public static void main(String[] args) {
		Instant startTime = Instant.now();

		Codificador paridade = Paridade.SEM_PARIDADE;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);
		
		paridade = Paridade.PARIDADE_MSB;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);
		
		paridade = Paridade.PARIDADE_MSB_4;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);
		
		paridade = Paridade.PARIDADE_MSB_8;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);
		
		paridade = Paridade.PARIDADE_MSB_12;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);
		
		paridade = Paridade.PARIDADE_MSB_16;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);
		
		paridade = Paridade.PARIDADE_2_MSB;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);
		
		paridade = Paridade.PARIDADE_2_MSB_4;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);
//		
		paridade = Paridade.PARIDADE_2_MSB_8;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);
		
		paridade = Paridade.PARIDADE_2_MSB_12;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);
		
		paridade = Paridade.PARIDADE_2_MSB_16;
		simulacao(paridade, 8, 1, 32);
		simulacao(paridade, 8, 2, 32);
		simulacao(paridade, 8, 3, 32);
//		
		Instant endTime = Instant.now();
		System.out.println("Tempo de execução: " + Duration.between(startTime, endTime));
	}

	private static void simulacao(Codificador codificador, int tamanhoCache, int errosAdjacentes,
			int tamanhoPalavra) {
		
		Random random = new Random();
		
		int i = 0;
		
		SimuladorUtils.gerarArquivo(nomeArquivo);
		long numeroLinhas = SimuladorUtils.getNumeroLinhas();

		System.out.println("Codificador: " + codificador);
		System.out.println("Quantidade de erros adjacentes: " + errosAdjacentes);

//		int iteracoes = 807 * 8 * 32 * 5;
		int iteracoes = 30;
		while (i < iteracoes) {
			System.out.println("Iteração " + (i + 1));
			int linhaCacheErro = random.nextInt(tamanhoCache);
			long linhaArquivoErro = random.nextLong(numeroLinhas);

			NovaMemoriaCache cache = new NovaMemoriaCache(codificador, tamanhoCache, errosAdjacentes, tamanhoPalavra);
			Scanner scannerArquivo = SimuladorUtils.getArquivo(nomeArquivo, tamanhoPalavra);

			if (cache.simulacao(scannerArquivo, linhaArquivoErro, linhaCacheErro)) {
				falsosPositivos += cache.falsosPositivos;
				falsosNegativos += cache.falsosNegativos;
				errosSubstituidos += cache.errosSubstituidos;
			}

			i++;
		}

		System.out.println("Falsos positivos: " + falsosPositivos);
		System.out.println("Falsos negativos: " + falsosNegativos);
		System.out.println("Erros substituídos: " + errosSubstituidos);
		System.out.println();
		
		zerarResultados();
	}
	
	private static void zerarResultados() {
		falsosPositivos = 0;
		falsosNegativos = 0;
		errosSubstituidos = 0;
	}

}
