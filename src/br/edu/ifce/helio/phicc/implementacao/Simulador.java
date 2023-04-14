package br.edu.ifce.helio.phicc.implementacao;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import br.edu.ifce.helio.phicc.modelo.NovaMemoriaCache;
import br.edu.ifce.helio.phicc.modelo.Paridade;
import br.edu.ifce.helio.phicc.utils.SimuladorUtils;

public class Simulador {
	private static int falsosPositivos = 0;

	private static int falsosNegativos = 0;

	private static int errosSubstituidos = 0;

	private static int semIntercorrencias = 0;

	private static String nomeArquivo = "lista_traces_menores.txt";

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

		Instant endTime = Instant.now();
		System.out.println("Tempo de execução: " + Duration.between(startTime, endTime));
	}

	private static void simulacao(Codificador codificador, int tamanhoCache, int errosAdjacentes, int tamanhoPalavra) {
		NovaMemoriaCache.debug = debug;
		Random random = new Random();
		int i = 0;
		String[] linhas = SimuladorUtils.getArquivo(nomeArquivo, tamanhoPalavra);

		System.out.println("Codificador: " + codificador);
		System.out.println("Quantidade de erros adjacentes: " + errosAdjacentes);

		int iteracoes = 100000;
		Instant inicio = Instant.now();
		while (i < iteracoes) {
			if (debug) System.out.println("Iteração " + (i + 1));
			int linhaCacheErro = random.nextInt(tamanhoCache);
			int linhaArquivoErro = random.nextInt(linhas.length);

			NovaMemoriaCache cache = new NovaMemoriaCache(codificador, tamanhoCache, errosAdjacentes, tamanhoPalavra);

			if (cache.simulacao(linhas, linhaArquivoErro, linhaCacheErro)) {
				falsosPositivos += cache.falsosPositivos;
				falsosNegativos += cache.falsosNegativos;
				errosSubstituidos += cache.errosSubstituidos;
				semIntercorrencias += cache.semIntercorrencias;
			} else {
				semIntercorrencias += cache.semIntercorrencias;
			}

			if ((i + 1) % 3000 == 0) {
				System.out.println("Duração da simulação: " + Duration.between(inicio, Instant.now()));
				inicio = Instant.now();
			}

			i++;
		}

		System.out.println("Falsos positivos: " + falsosPositivos);
		System.out.println("Falsos negativos: " + falsosNegativos);
		System.out.println("Erros substituídos: " + errosSubstituidos);
		System.out.println("Não-intercorrências: " + semIntercorrencias);
		System.out.println();

		zerarResultados();
	}

	private static void zerarResultados() {
		falsosPositivos = 0;
		falsosNegativos = 0;
		errosSubstituidos = 0;
		semIntercorrencias = 0;
	}

}
