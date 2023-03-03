package br.edu.ifce.helio.phicc.implementacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntradaCodificadaLinear implements EntradaCodificada {
	private String valor = null;
	
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

	public void printEntrada() {
		System.out.println(valor);
	}
	
	private void inserirErroLinear(int quantidadeErros) {
		Random random = new Random();
		int posicaoInicial = 0, i;
		
		List<Integer> posicoes = null;
		
		do {
			posicaoInicial = random.nextInt(valor.length());
			posicoes = extrairVizinhos(posicaoInicial);
		} while (quantidadeErros > posicoes.size() + 1);
		
		inverteBit(posicaoInicial);
		for (i = 1; i <= quantidadeErros - 1; i++) {
			int posicao = posicoes.remove(random.nextInt(posicoes.size()));
			inverteBit(posicao);
		}
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

}
