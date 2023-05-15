package br.edu.ifce.helio.phicc.implementacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import br.edu.ifce.helio.phicc.utils.CalculadorParidade;

public class EntradaCodificadaLinear implements EntradaCodificada {
	public static boolean paridadeEmbutida = false;
	
	private String valor = null;

	private boolean erro = false;
	
	private boolean validade = true;
	
	private String bitParidade = null;

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

	public String getBitParidade() {
		if (paridadeEmbutida) {
			return bitParidade;
		}
		return "0";
	}

	public boolean isErro() {
		return erro;
	}

	public void setValor(Object valor) {
		if (valor instanceof String) {
			this.valor = (String) valor;
			if (paridadeEmbutida && bitParidade == null) {
				bitParidade = CalculadorParidade.calcularBitParidade(this.valor);
			}
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
			if (valor.length() > 32) {
				posicaoInicial = random.nextInt(1, valor.length());
			} else {
				posicaoInicial = random.nextInt(valor.length());
			}
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
				if (verificarPosicaoEsquerda(posicao, i) && posicao + i < valor.length()) {
					posicoes.add(posicao + i);
				}
			}
		}

		return posicoes;
	}

	private boolean verificarPosicaoEsquerda(int posicao, int i) {
		return valor.length() > 32 ? posicao > 0 : posicao + i >= 0;
	}

	private void inverteBit(int posicao) {
		String[] valorSeparado = valor.split("");
		valorSeparado[posicao] = String.valueOf(Integer.parseInt(valorSeparado[posicao]) ^ 1);
		valor = String.join("", valorSeparado);
	}
	
	public boolean isValida() {
		if (paridadeEmbutida && validade) {
			validade = bitParidade.equals(CalculadorParidade.calcularBitParidade(valor));
		}
		return validade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bitParidade, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntradaCodificadaLinear other = (EntradaCodificadaLinear) obj;
		return Objects.equals(getBitParidade(), other.getBitParidade()) && Objects.equals(valor, other.valor);
	}

}
