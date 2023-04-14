package br.edu.ifce.helio.phicc.implementacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class EntradaCodificadaLinear implements EntradaCodificada {
	private String valor = null;

	private boolean erro = false;

	private List<Integer> posicoes = new ArrayList<>();

	public EntradaCodificadaLinear(String valor) {
		setValor(valor);
	}

	public Object getValor() {
		if (valor != null) {
			return valor;
		} else {
			return null;
		}
	}

	public boolean isErro() {
		return erro;
	}

	public void setValor(Object valor) {
		if (valor instanceof String) {
			this.valor = (String) valor;
		} else {
			throw new RuntimeException("Tipo de valor inválido");
		}
	}

	public void inserirErro(Integer quantidadeErros) {
		if (valor != null) {
			inserirErroLinear(quantidadeErros);
		} else {
			throw new RuntimeException("Valor não pode estar vazio");
		}
	}

	public boolean valorEquals(Object valor) {
		return this.valor.equals(valor);
	}

	public void printEntrada() {
		System.out.println(valor);
	}

	public void printPosicoes() {
		System.out.println("Erro nas posicoes:");
		for (Integer posicao : posicoes) {
			System.out.println("" + posicao);
		}
	}

	private void inserirErroLinear(int quantidadeErros) {
		erro = true;
		Random random = new Random();
		int posicaoInicial = 0, i;

		List<Integer> posicoes = null;
		List<Integer> todasPosicoes = new ArrayList<>();

		do {
			posicaoInicial = random.nextInt(valor.length());
			posicoes = extrairVizinhos(posicaoInicial);
		} while (quantidadeErros > posicoes.size() + 1);

		inverteBit(posicaoInicial);
		todasPosicoes.add(posicaoInicial);
		for (i = 1; i <= quantidadeErros - 1; i++) {
			int posicao = posicoes.remove(random.nextInt(posicoes.size()));
			todasPosicoes.add(posicao);
			inverteBit(posicao);
		}
		
		this.posicoes = todasPosicoes;
	}

	private List<Integer> extrairVizinhos(int posicao) {
		List<Integer> posicoes = new ArrayList<>();

		for (int i = -1; i <= 1; i++) {
			if (i != 0) {
				if (posicao + i >= 0 && posicao + i < valor.length()) {
					posicoes.add(posicao + i);
				}
			}
		}

		return posicoes;
	}

	private void inverteBit(int posicao) {
		String[] valorSeparado = valor.split("");
		valorSeparado[posicao] = String.valueOf(Integer.parseInt(valorSeparado[posicao]) ^ 1);
		valor = String.join("", valorSeparado);
	}

	@Override
	public int hashCode() {
		return Objects.hash(valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof EntradaCodificadaLinear))
			return false;
		EntradaCodificadaLinear other = (EntradaCodificadaLinear) obj;
		return Objects.equals(valor, other.valor);
	}

}
