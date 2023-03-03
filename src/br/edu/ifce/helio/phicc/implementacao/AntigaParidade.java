package br.edu.ifce.helio.phicc.implementacao;

import br.edu.ifce.helio.phicc.modelo.Paridade;

public class AntigaParidade {
	public String codificaParidade(String dados, Paridade tipo) {
		switch (tipo) {
		case SEM_PARIDADE:
			return codificaParidadeSimples(dados);
		case PARIDADE_MSB:
			return codificaParidadeMSB(dados);
		case PARIDADE_MSB_4:
			return codificaParidadeMSB4(dados);
		case PARIDADE_MSB_8:
			return codificaParidadeMSB8(dados);
		case PARIDADE_MSB_12:
			return codificaParidadeMSB12(dados);
		case PARIDADE_MSB_16:
			return codificaParidadeMSB16(dados);
		case PARIDADE_2_MSB:
			return codificaParidade2MSB(dados);
		case PARIDADE_2_MSB_4:
			return codificaParidade2MSB4(dados);
		case PARIDADE_2_MSB_8:
			return codificaParidade2MSB8(dados);
		case PARIDADE_2_MSB_12:
			return codificaParidade2MSB12(dados);
		case PARIDADE_2_MSB_16:
			return codificaParidade2MSB16(dados);
		default:
			return null;
		}
	}

	public String decodificaParidade(String dados, Paridade tipo) {
		switch (tipo) {
		case SEM_PARIDADE:
			return decodificaParidadeSimples(dados);
		case PARIDADE_MSB:
			return decodificaParidadeMSB(dados);
		case PARIDADE_MSB_4:
			return decodificaParidadeMSB4(dados);
		case PARIDADE_MSB_8:
			return decodificaParidadeMSB8(dados);
		case PARIDADE_MSB_12:
			return decodificaParidadeMSB12(dados);
		case PARIDADE_MSB_16:
			return decodificaParidadeMSB16(dados);
		case PARIDADE_2_MSB:
			return decodificaParidade2MSB(dados);
		case PARIDADE_2_MSB_4:
			return decodificaParidade2MSB4(dados);
		case PARIDADE_2_MSB_8:
			return decodificaParidade2MSB8(dados);
		case PARIDADE_2_MSB_12:
			return decodificaParidade2MSB12(dados);
		case PARIDADE_2_MSB_16:
			return decodificaParidade2MSB16(dados);
		default:
			return null;
		}
	}

	private static String codificaParidadeSimples(String dados) {
		return "";
	}

	private static String codificaParidadeMSB(String dados) {
		return "";
	}

	private static String codificaParidadeMSB4(String dados) {
		return "";
	}

	private static String codificaParidadeMSB8(String dados) {
		return "";
	}

	private static String codificaParidadeMSB12(String dados) {
		return "";
	}

	private static String codificaParidadeMSB16(String dados) {
		return "";
	}

	private static String codificaParidade2MSB(String dados) {
		return "";
	}

	private static String codificaParidade2MSB4(String dados) {
		return "";
	}

	private static String codificaParidade2MSB8(String dados) {
		return "";
	}

	private static String codificaParidade2MSB12(String dados) {
		return "";
	}

	private static String codificaParidade2MSB16(String dados) {
		return "";
	}

	private static String decodificaParidadeSimples(String dados) {
		return "";
	}

	private static String decodificaParidadeMSB(String dados) {
		return "";
	}

	private static String decodificaParidadeMSB4(String dados) {
		return "";
	}

	private static String decodificaParidadeMSB8(String dados) {
		return "";
	}

	private static String decodificaParidadeMSB12(String dados) {
		return "";
	}

	private static String decodificaParidadeMSB16(String dados) {
		return "";
	}

	private static String decodificaParidade2MSB(String dados) {
		return "";
	}

	private static String decodificaParidade2MSB4(String dados) {
		return "";
	}

	private static String decodificaParidade2MSB8(String dados) {
		return "";
	}

	private static String decodificaParidade2MSB12(String dados) {
		return "";
	}

	private static String decodificaParidade2MSB16(String dados) {
		return "";
	}
}
