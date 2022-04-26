package br.edu.ifce.helio.phicc.modelo;

import java.util.Arrays;
import java.util.Random;

public class EntradaMemoriaCache {

	private String[][] conteudo;

	private boolean erro = false;

	private Integer contadorAcessos = 1;

	private Integer contadorCache = 1;

	public String[][] getConteudo() {
		return conteudo;
	}

	public void setConteudo(String[][] conteudo) {
		this.conteudo = conteudo;
	}

	public boolean isErro() {
		return erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro;
	}

	public Integer getContadorAcessos() {
		return contadorAcessos;
	}

	public void setContadorAcessos(Integer contadorAcessos) {
		this.contadorAcessos = contadorAcessos;
	}

	public Integer getContadorCache() {
		return contadorCache;
	}

	public void setContadorCache(Integer contadorCache) {
		this.contadorCache = contadorCache;
	}

	public void incrementarContadorAcesso() {
		contadorAcessos++;
	}

	public void incrementarContadorCache() {
		contadorCache++;
	}

	public void inserirErro() {
		Random random = new Random();
		Integer linha = random.nextInt(conteudo.length);
		Integer coluna = random.nextInt(conteudo[0].length);

		System.out.println("Bit de erro: [" + linha + "][" + coluna + "]");
		if (linha == 0 || linha == conteudo.length - 1 || coluna == 0 || coluna == conteudo[0].length - 1) {
			System.out.println("Bit na borda");
			if (linha == 0) {
				System.out.println("Bit na borda lateral esquerda");
			} else {
				System.out.println("Bit na borda lateral direita");
			}
			
			if (coluna == 0) {
				System.out.println("Bit na borda superior");
			} else {
				System.out.println("Bit na borda inferior");
			}
		} else {
			System.out.println("Bit dentro da borda");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(conteudo);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntradaMemoriaCache other = (EntradaMemoriaCache) obj;
		if (!Arrays.deepEquals(conteudo, other.conteudo))
			return false;
		return true;
	}

}
