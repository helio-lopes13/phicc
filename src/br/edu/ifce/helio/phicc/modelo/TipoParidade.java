package br.edu.ifce.helio.phicc.modelo;

import br.edu.ifce.helio.phicc.implementacao.Codificador;
import br.edu.ifce.helio.phicc.implementacao.EntradaCodificada;
import br.edu.ifce.helio.phicc.implementacao.EntradaCodificadaLinear;

public enum TipoParidade implements Codificador {
	SEM_PARIDADE {
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	PARIDADE_MSB {

		@Override
		public EntradaCodificada codificar(String palavra) {
			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = calcularParidade(palavraSeparada);
			return new EntradaCodificadaLinear(String.join("", palavraSeparada));
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String palavra = (String) entrada.getValor();

			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = calcularParidade(palavraSeparada);

			return String.join("", palavraSeparada);
		}

		private String calcularParidade(String[] palavraSeparada) {
			int paridade = 0;

			for (String bit : palavraSeparada) {
				paridade = paridade ^ Integer.parseInt(bit);
			}

			return String.valueOf(paridade);
		}

	},
	PARIDADE_MSB_4 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = calcularParidade(palavraSeparada);
			return new EntradaCodificadaLinear(String.join("", palavraSeparada));
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String palavra = (String) entrada.getValor();

			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = String.valueOf(Integer.valueOf(palavraSeparada[0]) ^ Integer.valueOf(calcularParidade(palavraSeparada)));

			return String.join("", palavraSeparada);
		}

		private String calcularParidade(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = palavraSeparada.length - 4; i < palavraSeparada.length; i++) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
	},
	PARIDADE_MSB_8 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = calcularParidade(palavraSeparada);
			return new EntradaCodificadaLinear(String.join("", palavraSeparada));
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String palavra = (String) entrada.getValor();

			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = String.valueOf(Integer.valueOf(palavraSeparada[0]) ^ Integer.valueOf(calcularParidade(palavraSeparada)));

			return String.join("", palavraSeparada);
		}
		
		private String calcularParidade(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = palavraSeparada.length - 8; i < palavraSeparada.length; i++) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
	},
	PARIDADE_MSB_12 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = calcularParidade(palavraSeparada);
			return new EntradaCodificadaLinear(String.join("", palavraSeparada));
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String palavra = (String) entrada.getValor();

			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = String.valueOf(Integer.valueOf(palavraSeparada[0]) ^ Integer.valueOf(calcularParidade(palavraSeparada)));

			return String.join("", palavraSeparada);
		}
		
		private String calcularParidade(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = palavraSeparada.length - 12; i < palavraSeparada.length; i++) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
	},
	PARIDADE_MSB_16 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = calcularParidade(palavraSeparada);
			return new EntradaCodificadaLinear(String.join("", palavraSeparada));
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String palavra = (String) entrada.getValor();

			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = calcularParidade(palavraSeparada);

			return String.join("", palavraSeparada);
		}
		
		private String calcularParidade(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = palavraSeparada.length - 16; i < palavraSeparada.length; i++) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
	},
	PARIDADE_2_MSB {
		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	PARIDADE_2_MSB_4 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	PARIDADE_2_MSB_8 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	PARIDADE_2_MSB_12 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	PARIDADE_2_MSB_16 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	};

	@Override
	public abstract EntradaCodificada codificar(String palavra);

	@Override
	public abstract String decodificar(EntradaCodificada entrada);
}
