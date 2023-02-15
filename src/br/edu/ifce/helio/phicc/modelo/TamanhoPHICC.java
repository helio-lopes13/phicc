package br.edu.ifce.helio.phicc.modelo;

import br.edu.ifce.helio.phicc.implementacao.Codificador;
import br.edu.ifce.helio.phicc.implementacao.EntradaCodificada;
import br.edu.ifce.helio.phicc.implementacao.EntradaCodificadaMatricial;

public enum TamanhoPHICC implements Codificador {
	T40(40) {

		@Override
		public EntradaCodificada codificar(String palavra) {
			String[][] dadosCodificados = new String[5][8];
			int tamanhoMatrizDados = 4, i;
			String[] palavraSeparada = palavra.split("");

			for (i = 0; i < tamanhoMatrizDados; i++) {
				dadosCodificados[i][5] = String.valueOf(Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 1])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 2])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 3]));
				dadosCodificados[i][6] = String.valueOf(Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 2])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 3]));
				dadosCodificados[i][7] = String.valueOf(Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 1])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 3]));
			}

			dadosCodificados[0][0] = palavraSeparada[0];
			dadosCodificados[0][1] = palavraSeparada[12];
			dadosCodificados[0][2] = palavraSeparada[2];
			dadosCodificados[0][3] = palavraSeparada[14];

			dadosCodificados[1][0] = palavraSeparada[8];
			dadosCodificados[1][1] = palavraSeparada[4];
			dadosCodificados[1][2] = palavraSeparada[10];
			dadosCodificados[1][3] = palavraSeparada[6];

			dadosCodificados[2][0] = palavraSeparada[1];
			dadosCodificados[2][1] = palavraSeparada[13];
			dadosCodificados[2][2] = palavraSeparada[3];
			dadosCodificados[2][3] = palavraSeparada[15];

			dadosCodificados[3][0] = palavraSeparada[9];
			dadosCodificados[3][1] = palavraSeparada[5];
			dadosCodificados[3][2] = palavraSeparada[11];
			dadosCodificados[3][3] = palavraSeparada[7];

			for (i = 0; i < tamanhoMatrizDados; i++) {
				dadosCodificados[i][4] = String
						.valueOf(Integer.parseInt(dadosCodificados[i][0]) ^ Integer.parseInt(dadosCodificados[i][1])
								^ Integer.parseInt(dadosCodificados[i][2]) ^ Integer.parseInt(dadosCodificados[i][3]));

				dadosCodificados[4][i] = String
						.valueOf(Integer.parseInt(dadosCodificados[0][i]) ^ Integer.parseInt(dadosCodificados[1][i])
								^ Integer.parseInt(dadosCodificados[2][i]) ^ Integer.parseInt(dadosCodificados[3][i]));

				dadosCodificados[4][i + 4] = String.valueOf(Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 1])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 2])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 3]));
			}

			return new EntradaCodificadaMatricial(dadosCodificados);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String[][] dados = (String[][]) entrada.getValor();
			String[] palavraDecodificada = new String[16];
			int tamanhoMatrizDados = 4, somaSindromeParidadeDados = 0;
			String[] sindromeParidadeLinha = new String[tamanhoMatrizDados],
					sindromeParidadeColuna = new String[tamanhoMatrizDados],
					sindromeParidadeDados = new String[tamanhoMatrizDados];
			String[][] sindromeCheckbits = new String[tamanhoMatrizDados][3];


			for (int i = 0; i < tamanhoMatrizDados; i++) {
				sindromeParidadeLinha[i] = String.valueOf(Integer.parseInt(dados[i][0]) ^ Integer.parseInt(dados[i][1])
						^ Integer.parseInt(dados[i][2]) ^ Integer.parseInt(dados[i][3]) ^ Integer.parseInt(dados[i][4]));
				sindromeParidadeColuna[i] = String.valueOf(Integer.parseInt(dados[0][i]) ^ Integer.parseInt(dados[1][i])
						^ Integer.parseInt(dados[2][i]) ^ Integer.parseInt(dados[3][i]) ^ Integer.parseInt(dados[4][i]));
			}

			for (int i = 0; i < sindromeParidadeLinha.length; i++) {
				if (sindromeParidadeLinha[i].equals("1")) {
					for (int j = 0; j < sindromeParidadeColuna.length; j++) {
						if (sindromeParidadeColuna[j].equals("1")) {
							dados[i][j] = String.valueOf(Integer.parseInt(dados[i][j]) ^ 1);
						}
					}
				}
			}

			palavraDecodificada[0] = dados[0][0];
			palavraDecodificada[1] = dados[2][0];
			palavraDecodificada[2] = dados[0][2];
			palavraDecodificada[3] = dados[2][2];

			palavraDecodificada[4] = dados[1][1];
			palavraDecodificada[5] = dados[3][1];
			palavraDecodificada[6] = dados[1][3];
			palavraDecodificada[7] = dados[3][3];

			palavraDecodificada[8] = dados[1][0];
			palavraDecodificada[9] = dados[3][0];
			palavraDecodificada[10] = dados[1][2];
			palavraDecodificada[11] = dados[3][2];

			palavraDecodificada[12] = dados[0][1];
			palavraDecodificada[13] = dados[2][1];
			palavraDecodificada[14] = dados[0][3];
			palavraDecodificada[15] = dados[2][3];

			for (int i = 0; i < tamanhoMatrizDados; i++) {
				sindromeParidadeDados[i] = String.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados])
						^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1])
						^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2])
						^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3])
						^ Integer.parseInt(dados[4][i + 4]));

				sindromeCheckbits[i][0] = String.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1])
						^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2])
						^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3])
						^ Integer.parseInt(dados[i][5]));
				sindromeCheckbits[i][1] = String.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados])
						^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2])
						^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3])
						^ Integer.parseInt(dados[i][6]));
				sindromeCheckbits[i][2] = String.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados])
						^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1])
						^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3])
						^ Integer.parseInt(dados[i][7]));

				somaSindromeParidadeDados += Integer.parseInt(sindromeParidadeDados[i]);
			}

			if (somaSindromeParidadeDados != 0) {
				for (int i = 0; i < tamanhoMatrizDados; i++) {
					String linhaSindromeCheckbits = String.join("", sindromeCheckbits[i]);

					switch (linhaSindromeCheckbits) {
					case "011":
						palavraDecodificada[i * tamanhoMatrizDados] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados]) ^ 1);
						break;
					case "101":
						palavraDecodificada[(i * tamanhoMatrizDados) + 1] = String
								.valueOf(Integer.parseInt(palavraDecodificada[(i * tamanhoMatrizDados) + 1]) ^ 1);
						break;
					case "110":
						palavraDecodificada[(i * tamanhoMatrizDados) + 2] = String
								.valueOf(Integer.parseInt(palavraDecodificada[(i * tamanhoMatrizDados) + 2]) ^ 1);
						break;
					case "111":
						palavraDecodificada[(i * tamanhoMatrizDados) + 3] = String
								.valueOf(Integer.parseInt(palavraDecodificada[(i * tamanhoMatrizDados) + 3]) ^ 1);
						break;
					}
				}
			}

			return String.join("", palavraDecodificada);
		}
		
	},
	T44(44) {

		@Override
		public EntradaCodificada codificar(String palavra) {
			String[][] dadosCodificados = new String[4][11], checkbits = new String[4][4];
			String[] palavraSeparada = palavra.split("");
			int tamanhoMatrizDados = 4, i;

			for (i = 0; i < tamanhoMatrizDados; i++) {
				checkbits[i][0] = String.valueOf(Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 1])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 2]));
				checkbits[i][1] = String.valueOf(Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 1])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 3]));
				checkbits[i][2] = String.valueOf(Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 2])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 3]));
				checkbits[i][3] = String.valueOf(Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 1])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 2])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 3]));
			}

			dadosCodificados[0][0] = palavraSeparada[0];
			dadosCodificados[0][1] = palavraSeparada[8];
			dadosCodificados[0][2] = palavraSeparada[2];
			dadosCodificados[0][3] = palavraSeparada[10];

			dadosCodificados[1][0] = palavraSeparada[4];
			dadosCodificados[1][1] = palavraSeparada[12];
			dadosCodificados[1][2] = palavraSeparada[6];
			dadosCodificados[1][3] = palavraSeparada[14];

			dadosCodificados[2][0] = palavraSeparada[9];
			dadosCodificados[2][1] = palavraSeparada[1];
			dadosCodificados[2][2] = palavraSeparada[11];
			dadosCodificados[2][3] = palavraSeparada[3];

			dadosCodificados[3][0] = palavraSeparada[13];
			dadosCodificados[3][1] = palavraSeparada[5];
			dadosCodificados[3][2] = palavraSeparada[15];
			dadosCodificados[3][3] = palavraSeparada[7];

			dadosCodificados[0][7] = checkbits[0][0];
			dadosCodificados[0][8] = checkbits[2][0];
			dadosCodificados[0][9] = checkbits[0][2];
			dadosCodificados[0][10] = checkbits[2][2];

			dadosCodificados[1][7] = checkbits[1][0];
			dadosCodificados[1][8] = checkbits[3][0];
			dadosCodificados[1][9] = checkbits[1][2];
			dadosCodificados[1][10] = checkbits[3][2];

			dadosCodificados[2][7] = checkbits[2][1];
			dadosCodificados[2][8] = checkbits[0][1];
			dadosCodificados[2][9] = checkbits[2][3];
			dadosCodificados[2][10] = checkbits[0][3];

			dadosCodificados[3][7] = checkbits[3][1];
			dadosCodificados[3][8] = checkbits[1][1];
			dadosCodificados[3][9] = checkbits[3][3];
			dadosCodificados[3][10] = checkbits[1][3];

			for (i = 0; i < tamanhoMatrizDados; i++) {
				dadosCodificados[i][4] = String
						.valueOf(Integer.parseInt(dadosCodificados[i][0]) ^ Integer.parseInt(dadosCodificados[i][1])
								^ Integer.parseInt(dadosCodificados[i][2]) ^ Integer.parseInt(dadosCodificados[i][3]));
				dadosCodificados[i][5] = String
						.valueOf(Integer.parseInt(dadosCodificados[0][i]) ^ Integer.parseInt(dadosCodificados[1][i])
								^ Integer.parseInt(dadosCodificados[2][i]) ^ Integer.parseInt(dadosCodificados[3][i]));
				dadosCodificados[i][6] = String
						.valueOf(Integer.parseInt(checkbits[i][0]) ^ Integer.parseInt(checkbits[i][1])
								^ Integer.parseInt(checkbits[i][2]) ^ Integer.parseInt(checkbits[i][3]));
			}

			return new EntradaCodificadaMatricial(dadosCodificados);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String[][] dados = (String[][]) entrada.getValor();
			String[] palavraDecodificada = new String[16];
			int i, j, tamanhoMatrizDados = 4, somaSindromeParidadeCheckbits = 0;

			String[] sindromeParidadeLinha = new String[tamanhoMatrizDados],
					sindromeParidadeColuna = new String[tamanhoMatrizDados],
					sindromeParidadeCheckbits = new String[tamanhoMatrizDados];
			String[][] sindromeCheckbits = new String[tamanhoMatrizDados][tamanhoMatrizDados],
					checkbits = new String[tamanhoMatrizDados][tamanhoMatrizDados];

			for (i = 0; i < tamanhoMatrizDados; i++) {
				sindromeParidadeLinha[i] = String.valueOf(Integer.parseInt(dados[i][0]) ^ Integer.parseInt(dados[i][1])
						^ Integer.parseInt(dados[i][2]) ^ Integer.parseInt(dados[i][3]) ^ Integer.parseInt(dados[i][4]));
				sindromeParidadeColuna[i] = String.valueOf(Integer.parseInt(dados[0][i]) ^ Integer.parseInt(dados[1][i])
						^ Integer.parseInt(dados[2][i]) ^ Integer.parseInt(dados[3][i]) ^ Integer.parseInt(dados[i][5]));
			}

			for (i = 0; i < tamanhoMatrizDados; i++) {
				if (sindromeParidadeLinha[i].equals("1")) {
					for (j = 0; j < tamanhoMatrizDados; j++) {
						if (sindromeParidadeColuna[j].equals("1")) {
							dados[i][j] = String.valueOf(Integer.parseInt(dados[i][j]) ^ 1);
						}
					}
				}
			}

			palavraDecodificada[0] = dados[0][0];
			palavraDecodificada[1] = dados[2][1];
			palavraDecodificada[2] = dados[0][2];
			palavraDecodificada[3] = dados[2][3];

			palavraDecodificada[4] = dados[1][0];
			palavraDecodificada[5] = dados[3][1];
			palavraDecodificada[6] = dados[1][2];
			palavraDecodificada[7] = dados[3][3];

			palavraDecodificada[8] = dados[0][1];
			palavraDecodificada[9] = dados[2][0];
			palavraDecodificada[10] = dados[0][3];
			palavraDecodificada[11] = dados[2][2];

			palavraDecodificada[12] = dados[1][1];
			palavraDecodificada[13] = dados[3][0];
			palavraDecodificada[14] = dados[1][3];
			palavraDecodificada[15] = dados[3][2];

			checkbits[0][0] = dados[0][7];
			checkbits[0][1] = dados[2][8];
			checkbits[0][2] = dados[0][9];
			checkbits[0][3] = dados[2][10];

			checkbits[1][0] = dados[1][7];
			checkbits[1][1] = dados[3][8];
			checkbits[1][2] = dados[1][9];
			checkbits[1][3] = dados[3][10];

			checkbits[2][0] = dados[0][8];
			checkbits[2][1] = dados[2][7];
			checkbits[2][2] = dados[0][10];
			checkbits[2][3] = dados[2][9];

			checkbits[3][0] = dados[1][8];
			checkbits[3][1] = dados[3][7];
			checkbits[3][2] = dados[1][10];
			checkbits[3][3] = dados[3][9];

			for (i = 0; i < tamanhoMatrizDados; i++) {
				sindromeParidadeCheckbits[i] = String.valueOf(Integer.parseInt(checkbits[i][0])
						^ Integer.parseInt(checkbits[i][1]) ^ Integer.parseInt(checkbits[i][2])
						^ Integer.parseInt(checkbits[i][3]) ^ Integer.parseInt(dados[i][6]));

				sindromeCheckbits[i][0] = String.valueOf(
						Integer.parseInt(checkbits[i][0]) ^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados])
								^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1])
								^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2]));
				sindromeCheckbits[i][1] = String.valueOf(
						Integer.parseInt(checkbits[i][1]) ^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados])
								^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1])
								^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3]));
				sindromeCheckbits[i][2] = String.valueOf(
						Integer.parseInt(checkbits[i][2]) ^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados])
								^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2])
								^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3]));
				sindromeCheckbits[i][3] = String.valueOf(Integer.parseInt(checkbits[i][3])
						^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1])
						^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2])
						^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3]));

				somaSindromeParidadeCheckbits += Integer.parseInt(sindromeParidadeCheckbits[i]);
			}

			if (somaSindromeParidadeCheckbits == 0) {
				for (i = 0; i < tamanhoMatrizDados; i++) {
					String linhaSindromeCheckbits = String.join("", sindromeCheckbits[i]);

					switch (linhaSindromeCheckbits) {
					case "1110":
						palavraDecodificada[i * tamanhoMatrizDados] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados]) ^ 1);
						break;
					case "1101":
						palavraDecodificada[i * tamanhoMatrizDados + 1] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1]) ^ 1);
						break;
					case "1011":
						palavraDecodificada[i * tamanhoMatrizDados + 2] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2]) ^ 1);
						break;
					case "0111":
						palavraDecodificada[i * tamanhoMatrizDados + 3] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3]) ^ 1);
						break;
					case "0011":
						palavraDecodificada[i * tamanhoMatrizDados] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados]) ^ 1);
						palavraDecodificada[i * tamanhoMatrizDados + 1] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1]) ^ 1);
						break;
					case "0101":
						palavraDecodificada[i * tamanhoMatrizDados] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados]) ^ 1);
						palavraDecodificada[i * tamanhoMatrizDados + 2] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2]) ^ 1);
						break;
					case "1001":
						palavraDecodificada[i * tamanhoMatrizDados] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados]) ^ 1);

						palavraDecodificada[i * tamanhoMatrizDados + 3] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3]) ^ 1);
						break;
					case "0110":
						palavraDecodificada[i * tamanhoMatrizDados + 1] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1]) ^ 1);
						palavraDecodificada[i * tamanhoMatrizDados + 2] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2]) ^ 1);
						break;
					case "1010":
						palavraDecodificada[i * tamanhoMatrizDados + 1] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1]) ^ 1);
						palavraDecodificada[i * tamanhoMatrizDados + 3] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3]) ^ 1);
						break;
					case "1100":
						palavraDecodificada[i * tamanhoMatrizDados + 2] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2]) ^ 1);
						palavraDecodificada[i * tamanhoMatrizDados + 3] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3]) ^ 1);
						break;
					}
				}
			}

			return String.join("", palavraDecodificada);
		}
		
	},
	T32(32) {

		@Override
		public EntradaCodificada codificar(String palavra) {
			String[][] dadosCodificados = new String[4][8], checkbits = new String[4][2];
			int tamanhoMatrizDados = 4, i;
			String[] palavraSeparada = palavra.split(""), paridades = new String[tamanhoMatrizDados];

			for (i = 0; i < tamanhoMatrizDados; i++) {
				checkbits[i][0] = String.valueOf(Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 1]));
				checkbits[i][1] = String.valueOf(Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 2]));
			}

			dadosCodificados[0][5] = checkbits[0][0];
			dadosCodificados[0][6] = checkbits[2][1];
			dadosCodificados[1][5] = checkbits[1][0];
			dadosCodificados[1][6] = checkbits[3][1];
			dadosCodificados[2][5] = checkbits[2][0];
			dadosCodificados[2][6] = checkbits[0][1];
			dadosCodificados[3][5] = checkbits[3][0];
			dadosCodificados[3][6] = checkbits[1][1];

			dadosCodificados[0][0] = palavraSeparada[8];
			dadosCodificados[0][1] = palavraSeparada[0];
			dadosCodificados[0][2] = palavraSeparada[10];
			dadosCodificados[0][3] = palavraSeparada[2];

			dadosCodificados[1][0] = palavraSeparada[12];
			dadosCodificados[1][1] = palavraSeparada[4];
			dadosCodificados[1][2] = palavraSeparada[14];
			dadosCodificados[1][3] = palavraSeparada[6];

			dadosCodificados[2][0] = palavraSeparada[1];
			dadosCodificados[2][1] = palavraSeparada[9];
			dadosCodificados[2][2] = palavraSeparada[3];
			dadosCodificados[2][3] = palavraSeparada[11];

			dadosCodificados[3][0] = palavraSeparada[5];
			dadosCodificados[3][1] = palavraSeparada[13];
			dadosCodificados[3][2] = palavraSeparada[7];
			dadosCodificados[3][3] = palavraSeparada[15];

			for (i = 0; i < tamanhoMatrizDados; i++) {
				paridades[i] = String.valueOf(Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 1])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 2])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 3]));
			}

			dadosCodificados[0][4] = paridades[2];
			dadosCodificados[1][4] = paridades[3];
			dadosCodificados[2][4] = paridades[0];
			dadosCodificados[3][4] = paridades[1];

			for (i = 0; i < tamanhoMatrizDados; i++) {
				dadosCodificados[i][7] = String.valueOf(Integer.parseInt(paridades[i]) ^ Integer.parseInt(checkbits[i][0])
						^ Integer.parseInt(checkbits[i][1]));
			}

			return new EntradaCodificadaMatricial(dadosCodificados);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String[][] dados = (String[][]) entrada.getValor();
			int i, j, tamanhoMatrizDados = 4, sindromeVerificacaoFlag = 0;

			String[] sindromeParidadeLinha = new String[tamanhoMatrizDados],
					sindromeParidadeColuna = new String[tamanhoMatrizDados], paridadeDados = new String[tamanhoMatrizDados],
					sindromeParidadeDados = new String[tamanhoMatrizDados],
					sindromeVerificacao = new String[tamanhoMatrizDados], palavraDecodificada = new String[16];

			String[][] sindromeCheckbits = new String[tamanhoMatrizDados][tamanhoMatrizDados / 2],
					checkbits = new String[tamanhoMatrizDados][tamanhoMatrizDados / 2];

			String[][] dadosManipulados = new String[dados.length][dados[0].length], dadosTemp = new String[dadosManipulados.length][dadosManipulados[0].length];

			for (i = 0; i < dados.length; i++) {
				for (j = 0; j < dados[i].length; j++) {
					dadosManipulados[i][j] = dados[i][j];
				}
			}

			for (i = 0; i < dadosManipulados.length; i++) {
				for (j = 0; j < dadosManipulados[i].length; j++) {
					dadosTemp[i][j] = dadosManipulados[i][j];
				}
			}

			for (i = 0; i < tamanhoMatrizDados / 2; i++) {
				dadosManipulados[i][0] = dadosTemp[i][1];
				dadosManipulados[i][1] = dadosTemp[i][0];
				dadosManipulados[i][2] = dadosTemp[i][3];
				dadosManipulados[i][3] = dadosTemp[i][2];
			}

			sindromeVerificacao[0] = String.valueOf(Integer.parseInt(dadosManipulados[0][7]) ^ Integer.parseInt(dadosManipulados[2][4])
					^ Integer.parseInt(dadosManipulados[0][5]) ^ Integer.parseInt(dadosManipulados[2][6]));
			sindromeVerificacao[1] = String.valueOf(Integer.parseInt(dadosManipulados[1][7]) ^ Integer.parseInt(dadosManipulados[3][4])
					^ Integer.parseInt(dadosManipulados[1][5]) ^ Integer.parseInt(dadosManipulados[3][6]));
			sindromeVerificacao[2] = String.valueOf(Integer.parseInt(dadosManipulados[2][7]) ^ Integer.parseInt(dadosManipulados[0][4])
					^ Integer.parseInt(dadosManipulados[2][5]) ^ Integer.parseInt(dadosManipulados[0][6]));
			sindromeVerificacao[3] = String.valueOf(Integer.parseInt(dadosManipulados[3][7]) ^ Integer.parseInt(dadosManipulados[1][4])
					^ Integer.parseInt(dadosManipulados[3][5]) ^ Integer.parseInt(dadosManipulados[1][6]));

			sindromeVerificacaoFlag = Integer.parseInt(sindromeVerificacao[0]) | Integer.parseInt(sindromeVerificacao[1])
					| Integer.parseInt(sindromeVerificacao[2]) | Integer.parseInt(sindromeVerificacao[3]);

			sindromeParidadeLinha[0] = String.valueOf(Integer.parseInt(dadosManipulados[2][6]) ^ Integer.parseInt(dadosManipulados[0][6])
					^ Integer.parseInt(dadosManipulados[0][0]) ^ Integer.parseInt(dadosManipulados[0][1]) ^ Integer.parseInt(dadosManipulados[0][2])
					^ Integer.parseInt(dadosManipulados[0][3]));

			sindromeParidadeLinha[1] = String.valueOf(Integer.parseInt(dadosManipulados[1][6]) ^ Integer.parseInt(dadosManipulados[3][6])
					^ Integer.parseInt(dadosManipulados[1][0]) ^ Integer.parseInt(dadosManipulados[1][1]) ^ Integer.parseInt(dadosManipulados[1][2])
					^ Integer.parseInt(dadosManipulados[1][3]));

			sindromeParidadeLinha[2] = String.valueOf(Integer.parseInt(dadosManipulados[2][4]) ^ Integer.parseInt(dadosManipulados[0][4])
					^ Integer.parseInt(dadosManipulados[2][6]) ^ Integer.parseInt(dadosManipulados[0][6]) ^ Integer.parseInt(dadosManipulados[2][0])
					^ Integer.parseInt(dadosManipulados[2][1]) ^ Integer.parseInt(dadosManipulados[2][2]) ^ Integer.parseInt(dadosManipulados[2][3]));

			sindromeParidadeLinha[3] = String.valueOf(Integer.parseInt(dadosManipulados[1][4]) ^ Integer.parseInt(dadosManipulados[3][4])
					^ Integer.parseInt(dadosManipulados[1][6]) ^ Integer.parseInt(dadosManipulados[3][6]) ^ Integer.parseInt(dadosManipulados[3][0])
					^ Integer.parseInt(dadosManipulados[3][1]) ^ Integer.parseInt(dadosManipulados[3][2]) ^ Integer.parseInt(dadosManipulados[3][3]));

			sindromeParidadeColuna[0] = String.valueOf(Integer.parseInt(dadosManipulados[1][5]) ^ Integer.parseInt(dadosManipulados[0][5])
					^ Integer.parseInt(dadosManipulados[0][0]) ^ Integer.parseInt(dadosManipulados[1][0]) ^ Integer.parseInt(dadosManipulados[2][0])
					^ Integer.parseInt(dadosManipulados[3][0]));

			sindromeParidadeColuna[1] = String.valueOf(Integer.parseInt(dadosManipulados[3][5]) ^ Integer.parseInt(dadosManipulados[2][5])
					^ Integer.parseInt(dadosManipulados[0][1]) ^ Integer.parseInt(dadosManipulados[1][1]) ^ Integer.parseInt(dadosManipulados[2][1])
					^ Integer.parseInt(dadosManipulados[3][1]));

			sindromeParidadeColuna[2] = String.valueOf(Integer.parseInt(dadosManipulados[3][4]) ^ Integer.parseInt(dadosManipulados[2][4])
					^ Integer.parseInt(dadosManipulados[1][5]) ^ Integer.parseInt(dadosManipulados[0][5]) ^ Integer.parseInt(dadosManipulados[0][2])
					^ Integer.parseInt(dadosManipulados[1][2]) ^ Integer.parseInt(dadosManipulados[2][2]) ^ Integer.parseInt(dadosManipulados[3][2]));

			sindromeParidadeColuna[3] = String.valueOf(Integer.parseInt(dadosManipulados[1][4]) ^ Integer.parseInt(dadosManipulados[0][4])
					^ Integer.parseInt(dadosManipulados[3][5]) ^ Integer.parseInt(dadosManipulados[2][5]) ^ Integer.parseInt(dadosManipulados[0][3])
					^ Integer.parseInt(dadosManipulados[1][3]) ^ Integer.parseInt(dadosManipulados[2][3]) ^ Integer.parseInt(dadosManipulados[3][3]));

			if (sindromeVerificacaoFlag == 0) {
				for (i = 0; i < tamanhoMatrizDados; i++) {
					if (sindromeParidadeLinha[i].equals("1")) {
						for (j = 0; j < tamanhoMatrizDados; j++) {
							if (sindromeParidadeColuna[j].equals("1")) {
								dadosManipulados[i][j] = String.valueOf(Integer.parseInt(dadosManipulados[i][j]) ^ 1);
							}
						}
					}
				}
			}

			palavraDecodificada[0] = dadosManipulados[0][0];
			palavraDecodificada[1] = dadosManipulados[2][0];
			palavraDecodificada[2] = dadosManipulados[0][2];
			palavraDecodificada[3] = dadosManipulados[2][2];

			palavraDecodificada[4] = dadosManipulados[1][0];
			palavraDecodificada[5] = dadosManipulados[3][0];
			palavraDecodificada[6] = dadosManipulados[1][2];
			palavraDecodificada[7] = dadosManipulados[3][2];

			palavraDecodificada[8] = dadosManipulados[0][1];
			palavraDecodificada[9] = dadosManipulados[2][1];
			palavraDecodificada[10] = dadosManipulados[0][3];
			palavraDecodificada[11] = dadosManipulados[2][3];

			palavraDecodificada[12] = dadosManipulados[1][1];
			palavraDecodificada[13] = dadosManipulados[3][1];
			palavraDecodificada[14] = dadosManipulados[1][3];
			palavraDecodificada[15] = dadosManipulados[3][3];

			checkbits[0][0] = dadosManipulados[0][5];
			checkbits[0][1] = dadosManipulados[2][6];

			checkbits[1][0] = dadosManipulados[1][5];
			checkbits[1][1] = dadosManipulados[3][6];

			checkbits[2][0] = dadosManipulados[2][5];
			checkbits[2][1] = dadosManipulados[0][6];

			checkbits[3][0] = dadosManipulados[3][5];
			checkbits[3][1] = dadosManipulados[1][6];

			paridadeDados[0] = dadosManipulados[2][4];
			paridadeDados[1] = dadosManipulados[3][4];
			paridadeDados[2] = dadosManipulados[0][4];
			paridadeDados[3] = dadosManipulados[1][4];

			for (i = 0; i < tamanhoMatrizDados; i++) {
				sindromeParidadeDados[i] = String.valueOf(
						Integer.parseInt(paridadeDados[i]) ^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados])
								^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1])
								^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2])
								^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3]));
				sindromeCheckbits[i][0] = String.valueOf(
						Integer.parseInt(checkbits[i][0]) ^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados])
								^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1]));
				sindromeCheckbits[i][1] = String.valueOf(
						Integer.parseInt(checkbits[i][1]) ^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados])
								^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2]));

				if (sindromeParidadeDados[i].equals("1") && sindromeVerificacao[i].equals("0")) {
					String sindromeParidade = String.join("", sindromeCheckbits[i]);

					switch (sindromeParidade) {
					case "11":
						palavraDecodificada[i * tamanhoMatrizDados] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados]) ^ 1);
						break;
					case "10":
						palavraDecodificada[i * tamanhoMatrizDados + 1] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1]) ^ 1);
						break;
					case "01":
						palavraDecodificada[i * tamanhoMatrizDados + 2] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2]) ^ 1);
						break;
					case "00":
						palavraDecodificada[i * tamanhoMatrizDados + 3] = String
								.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3]) ^ 1);
						break;
					}
				}
			}

			return String.join("", palavraDecodificada);
		}
		
	},
	T36(36) {

		@Override
		public EntradaCodificada codificar(String palavra) {
			int i, tamanhoMatrizDados = 4;

			String dadosCodificados[][] = new String[tamanhoMatrizDados][9];
			String[] palavraSeparada = palavra.split("");

			for (i = 0; i < tamanhoMatrizDados; i++) {
				dadosCodificados[i][6] = String
						.valueOf(Integer.parseInt(palavraSeparada[i * 4]) ^ Integer.parseInt(palavraSeparada[i * 4 + 1]));
				dadosCodificados[i][7] = String
						.valueOf(Integer.parseInt(palavraSeparada[i * 4]) ^ Integer.parseInt(palavraSeparada[i * 4 + 2]));
			}

			dadosCodificados[0][0] = palavraSeparada[0];
			dadosCodificados[0][1] = palavraSeparada[12];
			dadosCodificados[0][2] = palavraSeparada[2];
			dadosCodificados[0][3] = palavraSeparada[14];

			dadosCodificados[1][0] = palavraSeparada[8];
			dadosCodificados[1][1] = palavraSeparada[4];
			dadosCodificados[1][2] = palavraSeparada[10];
			dadosCodificados[1][3] = palavraSeparada[6];

			dadosCodificados[2][0] = palavraSeparada[1];
			dadosCodificados[2][1] = palavraSeparada[13];
			dadosCodificados[2][2] = palavraSeparada[3];
			dadosCodificados[2][3] = palavraSeparada[15];

			dadosCodificados[3][0] = palavraSeparada[9];
			dadosCodificados[3][1] = palavraSeparada[5];
			dadosCodificados[3][2] = palavraSeparada[11];
			dadosCodificados[3][3] = palavraSeparada[7];

			for (i = 0; i < tamanhoMatrizDados; i++) {
				dadosCodificados[i][4] = String
						.valueOf(Integer.parseInt(dadosCodificados[i][0]) ^ Integer.parseInt(dadosCodificados[i][1])
								^ Integer.parseInt(dadosCodificados[i][2]) ^ Integer.parseInt(dadosCodificados[i][3]));
				dadosCodificados[i][5] = String
						.valueOf(Integer.parseInt(dadosCodificados[0][i]) ^ Integer.parseInt(dadosCodificados[1][i])
								^ Integer.parseInt(dadosCodificados[2][i]) ^ Integer.parseInt(dadosCodificados[3][i]));
				dadosCodificados[i][8] = String.valueOf(Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 1])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 2])
						^ Integer.parseInt(palavraSeparada[i * tamanhoMatrizDados + 3]));
			}

			return new EntradaCodificadaMatricial(dadosCodificados);
		}

		@Override
		public String decodificar(EntradaCodificada entrada) {
			String[][] dados = (String[][]) entrada.getValor();
			int i, j, tamanhoMatrizDados = 4, somaSindromeParidades = 0;

			String[] palavraDecodificada = new String[16], sindromeParidadeLinha = new String[tamanhoMatrizDados],
					sindromeParidadeColuna = new String[tamanhoMatrizDados],
					sindromeParidadeDados = new String[tamanhoMatrizDados];
			String[][] sindromeCheckbits = new String[tamanhoMatrizDados][2], dadosManipulados = new String[dados.length][dados[0].length];
			
			for (i = 0; i < dados.length; i++) {
				for (j = 0; j < dados[i].length; j++) {
					dadosManipulados[i][j] = dados[i][j];
				}
			}

			for (i = 0; i < tamanhoMatrizDados; i++) {
				sindromeParidadeLinha[i] = String.valueOf(Integer.parseInt(dadosManipulados[i][4]) ^ Integer.parseInt(dadosManipulados[i][0])
						^ Integer.parseInt(dadosManipulados[i][1]) ^ Integer.parseInt(dadosManipulados[i][2]) ^ Integer.parseInt(dadosManipulados[i][3]));
				sindromeParidadeColuna[i] = String.valueOf(Integer.parseInt(dadosManipulados[i][5]) ^ Integer.parseInt(dadosManipulados[0][i])
						^ Integer.parseInt(dadosManipulados[1][i]) ^ Integer.parseInt(dadosManipulados[2][i]) ^ Integer.parseInt(dadosManipulados[3][i]));

				somaSindromeParidades += Integer.parseInt(sindromeParidadeLinha[i]);
				somaSindromeParidades += Integer.parseInt(sindromeParidadeColuna[i]);
			}

			for (i = 0; i < tamanhoMatrizDados; i++) {
				if (sindromeParidadeLinha[i].equals("1")) {
					for (j = 0; j < tamanhoMatrizDados; j++) {
						if (sindromeParidadeColuna[j].equals("1")) {
							dadosManipulados[i][j] = String.valueOf(Integer.parseInt(dadosManipulados[i][j]) ^ 1);
						}
					}
				}
			}

			palavraDecodificada[0] = dadosManipulados[0][0];
			palavraDecodificada[1] = dadosManipulados[2][0];
			palavraDecodificada[2] = dadosManipulados[0][2];
			palavraDecodificada[3] = dadosManipulados[2][2];

			palavraDecodificada[4] = dadosManipulados[1][1];
			palavraDecodificada[5] = dadosManipulados[3][1];
			palavraDecodificada[6] = dadosManipulados[1][3];
			palavraDecodificada[7] = dadosManipulados[3][3];

			palavraDecodificada[8] = dadosManipulados[1][0];
			palavraDecodificada[9] = dadosManipulados[3][0];
			palavraDecodificada[10] = dadosManipulados[1][2];
			palavraDecodificada[11] = dadosManipulados[3][2];

			palavraDecodificada[12] = dadosManipulados[0][1];
			palavraDecodificada[13] = dadosManipulados[2][1];
			palavraDecodificada[14] = dadosManipulados[0][3];
			palavraDecodificada[15] = dadosManipulados[2][3];

			for (i = 0; i < tamanhoMatrizDados; i++) {
				sindromeParidadeDados[i] = String.valueOf(Integer.parseInt(dadosManipulados[i][8])
						^ Integer.parseInt(palavraDecodificada[i * 4]) ^ Integer.parseInt(palavraDecodificada[i * 4 + 1])
						^ Integer.parseInt(palavraDecodificada[i * 4 + 2])
						^ Integer.parseInt(palavraDecodificada[i * 4 + 3]));

				sindromeCheckbits[i][0] = String.valueOf(Integer.parseInt(palavraDecodificada[i * 4])
						^ Integer.parseInt(palavraDecodificada[i * 4 + 1]) ^ Integer.parseInt(dadosManipulados[i][6]));
				sindromeCheckbits[i][1] = String.valueOf(Integer.parseInt(palavraDecodificada[i * 4])
						^ Integer.parseInt(palavraDecodificada[i * 4 + 2]) ^ Integer.parseInt(dadosManipulados[i][7]));
			}

			if (somaSindromeParidades != 0) {
				for (i = 0; i < tamanhoMatrizDados; i++) {
					if (sindromeParidadeDados[i].equals("1")) {
						String linhaSindromeCheckbits = String.join("", sindromeCheckbits[i]);

						switch (linhaSindromeCheckbits) {
						case "11":
							palavraDecodificada[i * 4] = String.valueOf(Integer.parseInt(palavraDecodificada[i * 4]) ^ 1);
							break;
						case "10":
							palavraDecodificada[i * 4 + 1] = String
									.valueOf(Integer.parseInt(palavraDecodificada[i * 4 + 1]) ^ 1);
							break;
						case "01":
							palavraDecodificada[i * 4 + 2] = String
									.valueOf(Integer.parseInt(palavraDecodificada[i * 4 + 2]) ^ 1);
							break;
						case "00":
							palavraDecodificada[i * 4 + 3] = String
									.valueOf(Integer.parseInt(palavraDecodificada[i * 4 + 3]) ^ 1);
							break;
						}
					}
				}
			}

			return String.join("", palavraDecodificada);
		}
		
	};
	
	private int tamanho;
	
	TamanhoPHICC(int tamanho) {
		this.tamanho = tamanho;
	}
	
	public int getTamanho() {
		return tamanho;
	}

	@Override
	public abstract EntradaCodificada codificar(String palavra);

	@Override
	public abstract String decodificar(EntradaCodificada entrada);
}
