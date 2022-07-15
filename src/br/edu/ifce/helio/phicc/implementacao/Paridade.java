package br.edu.ifce.helio.phicc.implementacao;

import br.edu.ifce.helio.phicc.modelo.TipoParidade;

public class Paridade {
	public String codificaParidade(String dados, TipoParidade tipo) {
		switch (tipo) {
		case PS:
			return codificaParidadeSimples(dados);
		case PMSB:
			return codificaParidadeMSB(dados);
		case PMSB4:
			return codificaParidadeMSB4(dados);
		case PMSB8:
			return codificaParidadeMSB8(dados);
		case PMSB12:
			return codificaParidadeMSB12(dados);
		case PMSB16:
			return codificaParidadeMSB16(dados);
		case P2MSB:
			return codificaParidade2MSB(dados);
		case P2MSB4:
			return codificaParidade2MSB4(dados);
		case P2MSB8:
			return codificaParidade2MSB8(dados);
		case P2MSB12:
			return codificaParidade2MSB12(dados);
		case P2MSB16:
			return codificaParidade2MSB16(dados);
		default:
			return null;
		}
	}

	public String decodificaParidade(String dados, TipoParidade tipo) {
		switch (tipo) {
		case PS:
			return decodificaParidadeSimples(dados);
		case PMSB:
			return decodificaParidadeMSB(dados);
		case PMSB4:
			return decodificaParidadeMSB4(dados);
		case PMSB8:
			return decodificaParidadeMSB8(dados);
		case PMSB12:
			return decodificaParidadeMSB12(dados);
		case PMSB16:
			return decodificaParidadeMSB16(dados);
		case P2MSB:
			return decodificaParidade2MSB(dados);
		case P2MSB4:
			return decodificaParidade2MSB4(dados);
		case P2MSB8:
			return decodificaParidade2MSB8(dados);
		case P2MSB12:
			return decodificaParidade2MSB12(dados);
		case P2MSB16:
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
