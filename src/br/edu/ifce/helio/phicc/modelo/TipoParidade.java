package br.edu.ifce.helio.phicc.modelo;

import br.edu.ifce.helio.phicc.implementacao.Codificador;
import br.edu.ifce.helio.phicc.implementacao.EntradaCodificada;
import br.edu.ifce.helio.phicc.implementacao.EntradaCodificadaLinear;

public enum TipoParidade implements Codificador {
	PS {
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}
		
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	PMSB {

		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
		
	},
	PMSB4 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	PMSB8 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	PMSB12 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	PMSB16 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	P2MSB {
		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	P2MSB4 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	P2MSB8 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	P2MSB12 {
		@Override
		public EntradaCodificada codificar(String palavra) {
			return new EntradaCodificadaLinear(palavra);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			return (String) entrada.getValor();
		}
	},
	P2MSB16 {
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
