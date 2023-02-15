package br.edu.ifce.helio.phicc.implementacao;

import java.util.List;
import java.util.Random;

import br.edu.ifce.helio.phicc.modelo.NovaMemoriaCache;
import br.edu.ifce.helio.phicc.modelo.TamanhoPHICC;
import br.edu.ifce.helio.phicc.utils.SimuladorUtils;

public class Simulador {
	private static Integer falsosPositivos = 0;

	private static Integer falsosNegativos = 0;

	private static Integer errosSubstituidos = 0;

	public static void main(String[] args) {
		simulacao(TamanhoPHICC.T40, 8, 5, "enderecosInteiros.txt");
		simulacao(TamanhoPHICC.T44, 8, 5, "enderecosInteiros.txt");
		simulacao(TamanhoPHICC.T32, 8, 5, "enderecosInteiros.txt");
		simulacao(TamanhoPHICC.T36, 8, 5, "enderecosInteiros.txt");
	}

	private static void simulacao(Codificador codificador, int tamanhoCache, int errosAdjacentes,
			String nomeArquivo) {
		Random random = new Random();
		List<String> linhasArquivo = SimuladorUtils.getArquivo(nomeArquivo);
		
		int i = 0;
		int numeroLinhas = linhasArquivo.size();

		while (i < 500) {
			System.out.println("Iteração " + (i + 1));
			int linhaCacheErro = random.nextInt(tamanhoCache);
			int linhaArquivoErro = random.nextInt(numeroLinhas);

			NovaMemoriaCache cache = new NovaMemoriaCache(codificador, tamanhoCache, errosAdjacentes, 16);

			if (cache.simulacao(linhasArquivo, linhaArquivoErro, linhaCacheErro)) {
				falsosPositivos += cache.falsosPositivos;
				falsosNegativos += cache.falsosNegativos;
				errosSubstituidos += cache.errosSubstituidos;
			}

			i++;
		}

		System.out.println("Falsos positivos: " + falsosPositivos);
		System.out.println("Falsos negativos: " + falsosNegativos);
		System.out.println("Erros substituídos: " + errosSubstituidos);
	}

}
