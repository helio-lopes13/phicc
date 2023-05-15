package br.edu.ifce.helio.phicc.implementacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntradaCodificadaMatricial implements EntradaCodificada {
	private String[][] valor = null;

	private boolean erro;

	private List<int[]> posicoes;
	
	public EntradaCodificadaMatricial(String[][] valor) {
		setValor(valor);
	}

	public Object getValor() {
		if (valor != null) {
			return valor;
		} else {
			throw new RuntimeException("Valor não pode estar vazio");
		}
	}

	public boolean isErro() {
		return erro;
	}

	public void setValor(Object valor) {
		if (valor instanceof String[][]) {
			this.valor = (String[][]) valor;
		} else {
			throw new RuntimeException("Tipo de valor inválido");
		}
	}
	
	public void inserirErro(Integer quantidadeErros) {
		if (valor != null) {
			inserirErroMatricial(quantidadeErros);
		} else {
			throw new RuntimeException("Valor não pode estar vazio");
		}
	}
	
	public void printEntrada() {
		for (int i = 0; i < valor.length; i++) {
			for (int j = 0; j < valor[i].length; j++) {
				if (j != valor[i].length - 1) {
					System.out.print(valor[i][j] + "\t");
				} else {
					System.out.print(valor[i][j]);
				}
			}
			System.out.println();
		}
	}

	public void printPosicoes() {
		System.out.println("Erro nas posições:");
		for (int[] posicao : posicoes) {
			System.out.println("[" + posicao[0] + "][" + posicao[1] + "]");
		}
	}
	
	@Override
	public boolean valorEquals(Object valor) {
		return this.valor.equals(valor);
	}
	
	@Override
	public boolean isValida() {
		return true;
	}
	
	private void inserirErroMatricial(Integer quantidadeErros) {
		erro = true;
		Random random = new Random();
		int linha = 0, coluna = 0, i;
		
		List<int[]> posicoes = null;
		List<int[]> todasPosicoes = new ArrayList<>();

		do {
			linha = random.nextInt(valor.length);
			coluna = random.nextInt(valor[0].length);
			posicoes = extrairVizinhos(linha, coluna);
		} while (quantidadeErros > posicoes.size() + 1);

		inverteBit(linha, coluna);
		int[] posicaoPrincipal = { linha, coluna };
		todasPosicoes.add(posicaoPrincipal);

		for (i = 1; i <= quantidadeErros - 1; i++) {
			int[] posicao = posicoes.remove(random.nextInt(posicoes.size()));
			todasPosicoes.add(posicao);
			inverteBit(posicao);
		}
		
		this.posicoes = todasPosicoes;
	}

	private List<int[]> extrairVizinhos(int linha, int coluna) {
		List<int[]> posicoes = new ArrayList<>();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i != 0 || j != i) {
					if (linha + i >= 0 && linha + i < valor.length && coluna + j >= 0
							&& coluna + j < valor[0].length) {
						int[] posicao = { linha + i, coluna + j };
						posicoes.add(posicao);
					}
				}
			}
		}

		return posicoes;
	}

	private void inverteBit(int... posicao) {
		valor[posicao[0]][posicao[1]] = String.valueOf(Integer.parseInt(valor[posicao[0]][posicao[1]]) ^ 1);
	}
}
