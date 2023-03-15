package br.edu.ifce.helio.phicc.modelo;

import br.edu.ifce.helio.phicc.implementacao.Codificador;
import br.edu.ifce.helio.phicc.implementacao.EntradaCodificada;
import br.edu.ifce.helio.phicc.implementacao.EntradaCodificadaLinear;

public enum Paridade implements Codificador {
	SEM_PARIDADE {
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
		
		public String toString() {
			return "Sem Paridade";
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

			for (int i = 0; i < palavraSeparada.length; i++) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
		
		public String toString() {
			return "Paridade MSB";
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
		
		public String toString() {
			return "Paridade MSB com 4 LSB";
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
		
		public String toString() {
			return "Paridade MSB com 8 LSB";
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
		
		public String toString() {
			return "Paridade MSB com 12 LSB";
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
			palavraSeparada[0] = String.valueOf(Integer.valueOf(palavraSeparada[0]) ^ Integer.valueOf(calcularParidade(palavraSeparada)));

			return String.join("", palavraSeparada);
		}
		
		private String calcularParidade(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = palavraSeparada.length - 16; i < palavraSeparada.length; i++) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
		
		public String toString() {
			return "Paridade MSB com 16 LSB";
		}
	},
	PARIDADE_2_MSB {
		@Override
		public EntradaCodificada codificar(String palavra) {
			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = calcularParidadePar(palavraSeparada);
			palavraSeparada[1] = calcularParidadeImpar(palavraSeparada);
			return new EntradaCodificadaLinear(String.join("", palavraSeparada));
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String palavra = (String) entrada.getValor();

			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = calcularParidadePar(palavraSeparada);
			palavraSeparada[1] = calcularParidadeImpar(palavraSeparada);

			return String.join("", palavraSeparada);
		}
		
		private String calcularParidadePar(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = 0; i < palavraSeparada.length; i += 2) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
		
		private String calcularParidadeImpar(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = 1; i < palavraSeparada.length; i += 2) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
		
		public String toString() {
			return "Paridade 2 MSB";
		}
	},
	PARIDADE_2_MSB_4 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = calcularParidadePar(palavraSeparada);
			palavraSeparada[1] = calcularParidadeImpar(palavraSeparada);
			return new EntradaCodificadaLinear(String.join("", palavraSeparada));
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String palavra = (String) entrada.getValor();

			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = String.valueOf(Integer.valueOf(palavraSeparada[0]) ^ Integer.valueOf(calcularParidadePar(palavraSeparada)));
			palavraSeparada[1] = String.valueOf(Integer.valueOf(palavraSeparada[1]) ^ Integer.valueOf(calcularParidadeImpar(palavraSeparada)));

			return String.join("", palavraSeparada);
		}
		
		private String calcularParidadePar(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = palavraSeparada.length - 4; i < palavraSeparada.length; i += 2) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
		
		private String calcularParidadeImpar(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = palavraSeparada.length - 3; i < palavraSeparada.length; i += 2) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
		
		public String toString() {
			return "Paridade 2 MSB com 4 LSB";
		}
	},
	PARIDADE_2_MSB_8 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = calcularParidadePar(palavraSeparada);
			palavraSeparada[1] = calcularParidadeImpar(palavraSeparada);
			return new EntradaCodificadaLinear(String.join("", palavraSeparada));
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String palavra = (String) entrada.getValor();

			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = String.valueOf(Integer.valueOf(palavraSeparada[0]) ^ Integer.valueOf(calcularParidadePar(palavraSeparada)));
			palavraSeparada[1] = String.valueOf(Integer.valueOf(palavraSeparada[1]) ^ Integer.valueOf(calcularParidadeImpar(palavraSeparada)));

			return String.join("", palavraSeparada);
		}
		
		private String calcularParidadePar(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = palavraSeparada.length - 8; i < palavraSeparada.length; i += 2) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
		
		private String calcularParidadeImpar(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = palavraSeparada.length - 7; i < palavraSeparada.length; i += 2) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
		
		public String toString() {
			return "Paridade 2 MSB com 8 LSB";
		}
	},
	PARIDADE_2_MSB_12 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = calcularParidadePar(palavraSeparada);
			palavraSeparada[1] = calcularParidadeImpar(palavraSeparada);
			return new EntradaCodificadaLinear(String.join("", palavraSeparada));
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String palavra = (String) entrada.getValor();

			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = String.valueOf(Integer.valueOf(palavraSeparada[0]) ^ Integer.valueOf(calcularParidadePar(palavraSeparada)));
			palavraSeparada[1] = String.valueOf(Integer.valueOf(palavraSeparada[1]) ^ Integer.valueOf(calcularParidadeImpar(palavraSeparada)));

			return String.join("", palavraSeparada);
		}
		
		private String calcularParidadePar(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = palavraSeparada.length - 12; i < palavraSeparada.length; i += 2) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
		
		private String calcularParidadeImpar(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = palavraSeparada.length - 11; i < palavraSeparada.length; i += 2) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
		
		public String toString() {
			return "Paridade 2 MSB com 12 LSB";
		}
	},
	PARIDADE_2_MSB_16 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = calcularParidadePar(palavraSeparada);
			palavraSeparada[1] = calcularParidadeImpar(palavraSeparada);
			return new EntradaCodificadaLinear(String.join("", palavraSeparada));
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String palavra = (String) entrada.getValor();

			String[] palavraSeparada = palavra.split("");
			palavraSeparada[0] = String.valueOf(Integer.valueOf(palavraSeparada[0]) ^ Integer.valueOf(calcularParidadePar(palavraSeparada)));
			palavraSeparada[1] = String.valueOf(Integer.valueOf(palavraSeparada[1]) ^ Integer.valueOf(calcularParidadeImpar(palavraSeparada)));

			return String.join("", palavraSeparada);
		}
		
		private String calcularParidadePar(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = palavraSeparada.length - 16; i < palavraSeparada.length; i += 2) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
		
		private String calcularParidadeImpar(String[] palavraSeparada) {
			int paridade = 0;

			for (int i = palavraSeparada.length - 15; i < palavraSeparada.length; i += 2) {
				paridade = paridade ^ Integer.parseInt(palavraSeparada[i]);
			}

			return String.valueOf(paridade);
		}
		
		public String toString() {
			return "Paridade 2 MSB com 16 LSB";
		}
	};

	@Override
	public abstract EntradaCodificada codificar(String palavra);

	@Override
	public abstract String decodificar(EntradaCodificada entrada);
}
