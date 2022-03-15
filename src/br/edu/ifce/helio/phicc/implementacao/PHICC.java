package br.edu.ifce.helio.phicc.implementacao;

public class PHICC {

	public static String[][] codificaPHICC40(String dados) {
		String[][] dadosCodificados = new String[5][8];
		int tamanhoMatrizDados = 4, i;
		String[] palavra = dados.split("");

		// palavra == data_in
		// dadosCodificados == word

		for (i = 0; i < tamanhoMatrizDados; i++) {
//			CB(i + 1, 1) = xor(entrada(i + 1, 2), xor(entrada(i + 1, 3), entrada(i + 1, 4)));
			dadosCodificados[i][5] = String.valueOf(Integer.parseInt(palavra[i * tamanhoMatrizDados + 1])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 2])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 3]));
//			CB(i + 1, 2) = xor(entrada(i + 1, 1), xor(entrada(i + 1, 3), entrada(i + 1, 4)));
			dadosCodificados[i][6] = String.valueOf(Integer.parseInt(palavra[i * tamanhoMatrizDados])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 2])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 3]));
//			CB(i + 1, 3) = xor(entrada(i + 1, 1), xor(entrada(i + 1, 2), entrada(i + 1, 4)));
			dadosCodificados[i][7] = String.valueOf(Integer.parseInt(palavra[i * tamanhoMatrizDados])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 1])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 3]));
		}

//		word(0):=data_in(0);
		dadosCodificados[0][0] = palavra[0];
//		word(1):=data_in(12);
		dadosCodificados[0][1] = palavra[12];
//		word(2):=data_in(2);
		dadosCodificados[0][2] = palavra[2];
//		word(3):=data_in(14);
		dadosCodificados[0][3] = palavra[14];

//		word(8):=data_in(8);
		dadosCodificados[1][0] = palavra[8];
//		word(9):=data_in(4);
		dadosCodificados[1][1] = palavra[4];
//		word(10):=data_in(10);
		dadosCodificados[1][2] = palavra[10];
//		word(11):=data_in(6);
		dadosCodificados[1][3] = palavra[6];

//		word(16):=data_in(1);
		dadosCodificados[2][0] = palavra[1];
//		word(17):=data_in(13);
		dadosCodificados[2][1] = palavra[13];
//		word(18):=data_in(3);
		dadosCodificados[2][2] = palavra[3];
//		word(19):=data_in(15);
		dadosCodificados[2][3] = palavra[15];

//		word(24):=data_in(9);
		dadosCodificados[3][0] = palavra[9];
//		word(25):=data_in(5);
		dadosCodificados[3][1] = palavra[5];
//		word(26):=data_in(11);
		dadosCodificados[3][2] = palavra[11];
//		word(27):=data_in(7);
		dadosCodificados[3][3] = palavra[7];

		for (i = 0; i < tamanhoMatrizDados; i++) {
//			pa(i+1,1)=xor(datai(i+1,1),xor(datai(i+1,2),xor(datai(i+1,3),datai(i+1,4)))); %Paridade de linha dos dados embaralhados
			dadosCodificados[i][4] = String
					.valueOf(Integer.parseInt(dadosCodificados[i][0]) ^ Integer.parseInt(dadosCodificados[i][1])
							^ Integer.parseInt(dadosCodificados[i][2]) ^ Integer.parseInt(dadosCodificados[i][3]));

//			p(1,i+1)=xor(datai(1,i+1),xor(datai(2,i+1),xor(datai(3,i+1),datai(4,i+1)))); %Paridade de coluna dos dados embaralhados
			dadosCodificados[4][i] = String
					.valueOf(Integer.parseInt(dadosCodificados[0][i]) ^ Integer.parseInt(dadosCodificados[1][i])
							^ Integer.parseInt(dadosCodificados[2][i]) ^ Integer.parseInt(dadosCodificados[3][i]));

//			pd(1,i+1)=xor(entrada(i+1,1),xor(entrada(i+1,2),xor(entrada(i+1,3),entrada(i+1,4))));
			dadosCodificados[4][i + 4] = String.valueOf(Integer.parseInt(palavra[i * tamanhoMatrizDados])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 1])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 2])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 3]));
		}

		return dadosCodificados;
	}

	public static String[][] codificaPHICC44(String dados) {
		String[][] dadosCodificados = new String[4][11], checkbits = new String[4][4];
		String[] palavra = dados.split("");
		int tamanhoMatrizDados = 4, i;

		// Montagem da matriz de checkbits
		for (i = 0; i < tamanhoMatrizDados; i++) {
			checkbits[i][0] = String.valueOf(Integer.parseInt(palavra[i * tamanhoMatrizDados])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 1])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 2]));
			checkbits[i][1] = String.valueOf(Integer.parseInt(palavra[i * tamanhoMatrizDados])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 1])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 3]));
			checkbits[i][2] = String.valueOf(Integer.parseInt(palavra[i * tamanhoMatrizDados])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 2])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 3]));
			checkbits[i][3] = String.valueOf(Integer.parseInt(palavra[i * tamanhoMatrizDados + 1])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 2])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 3]));
		}

		// Interleaving dos dados
		dadosCodificados[0][0] = palavra[0];
		dadosCodificados[0][1] = palavra[8];
		dadosCodificados[0][2] = palavra[2];
		dadosCodificados[0][3] = palavra[10];

		dadosCodificados[1][0] = palavra[4];
		dadosCodificados[1][1] = palavra[12];
		dadosCodificados[1][2] = palavra[6];
		dadosCodificados[1][3] = palavra[14];

		dadosCodificados[2][0] = palavra[9];
		dadosCodificados[2][1] = palavra[1];
		dadosCodificados[2][2] = palavra[11];
		dadosCodificados[2][3] = palavra[3];

		dadosCodificados[3][0] = palavra[13];
		dadosCodificados[3][1] = palavra[5];
		dadosCodificados[3][2] = palavra[15];
		dadosCodificados[3][3] = palavra[7];

		// Interleaving dos checkbits
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
			// Paridade por linha dos dados com interleaving
			dadosCodificados[i][4] = String
					.valueOf(Integer.parseInt(dadosCodificados[i][0]) ^ Integer.parseInt(dadosCodificados[i][1])
							^ Integer.parseInt(dadosCodificados[i][2]) ^ Integer.parseInt(dadosCodificados[i][3]));
			// Paridade por coluna dos dados com interleaving
			dadosCodificados[i][5] = String
					.valueOf(Integer.parseInt(dadosCodificados[0][i]) ^ Integer.parseInt(dadosCodificados[1][i])
							^ Integer.parseInt(dadosCodificados[2][i]) ^ Integer.parseInt(dadosCodificados[3][i]));
			// Paridade por linha dos checkbits sem interleaving
			dadosCodificados[i][6] = String
					.valueOf(Integer.parseInt(checkbits[i][0]) ^ Integer.parseInt(checkbits[i][1])
							^ Integer.parseInt(checkbits[i][2]) ^ Integer.parseInt(checkbits[i][3]));
		}

		return dadosCodificados;
	}

	public static String[][] codificaPHICC32(String dados) {
		String[][] dadosCodificados = new String[4][8], checkbits = new String[4][2];
		int tamanhoMatrizDados = 4, i;
		String[] palavra = dados.split(""), paridades = new String[tamanhoMatrizDados];

		for (i = 0; i < tamanhoMatrizDados; i++) {
			checkbits[i][0] = String.valueOf(Integer.parseInt(palavra[i * tamanhoMatrizDados])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 1]));
			checkbits[i][1] = String.valueOf(Integer.parseInt(palavra[i * tamanhoMatrizDados])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 2]));
		}

		dadosCodificados[0][5] = checkbits[0][0];
		dadosCodificados[0][6] = checkbits[2][1];
		dadosCodificados[1][5] = checkbits[1][0];
		dadosCodificados[1][6] = checkbits[3][1];
		dadosCodificados[2][5] = checkbits[2][0];
		dadosCodificados[2][6] = checkbits[0][1];
		dadosCodificados[3][5] = checkbits[3][0];
		dadosCodificados[3][6] = checkbits[1][1];

		dadosCodificados[0][0] = palavra[8];
		dadosCodificados[0][1] = palavra[0];
		dadosCodificados[0][2] = palavra[10];
		dadosCodificados[0][3] = palavra[2];

		dadosCodificados[1][0] = palavra[12];
		dadosCodificados[1][1] = palavra[4];
		dadosCodificados[1][2] = palavra[14];
		dadosCodificados[1][3] = palavra[6];

		dadosCodificados[2][0] = palavra[1];
		dadosCodificados[2][1] = palavra[9];
		dadosCodificados[2][2] = palavra[3];
		dadosCodificados[2][3] = palavra[11];

		dadosCodificados[3][0] = palavra[5];
		dadosCodificados[3][1] = palavra[13];
		dadosCodificados[3][2] = palavra[7];
		dadosCodificados[3][3] = palavra[15];

		for (i = 0; i < tamanhoMatrizDados; i++) {
			paridades[i] = String.valueOf(Integer.parseInt(palavra[i * tamanhoMatrizDados])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 1])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 2])
					^ Integer.parseInt(palavra[i * tamanhoMatrizDados + 3]));
		}

		dadosCodificados[0][4] = paridades[2];
		dadosCodificados[1][4] = paridades[3];
		dadosCodificados[2][4] = paridades[0];
		dadosCodificados[3][4] = paridades[1];

		for (i = 0; i < tamanhoMatrizDados; i++) {
			dadosCodificados[i][7] = String.valueOf(Integer.parseInt(paridades[i]) ^ Integer.parseInt(checkbits[i][0])
					^ Integer.parseInt(checkbits[i][1]));
		}

		return dadosCodificados;
	}

	public static String[][] codificaPHICC36(String dados) {
		return null;
	}

	public static String decodificaPHICC40(String[][] dados) {
		String[] palavraDecodificada = new String[16];
		int tamanhoMatrizDados = 4, somaSindromeParidadeLinha = 0, somaSindromeParidadeColuna = 0,
				somaSindromeParidadeDados = 0, somaSindromeCheckbits = 0, errosTotais = 0;
		String[] sindromeParidadeLinha = new String[tamanhoMatrizDados],
				sindromeParidadeColuna = new String[tamanhoMatrizDados],
				sindromeParidadeDados = new String[tamanhoMatrizDados];
		String[][] sindromeCheckbits = new String[tamanhoMatrizDados][3];

		dados[0][1] = "0";
		dados[0][2] = "1";

		System.out.println("Dados modificados");
		for (int i = 0; i < dados.length; i++) {
			for (int j = 0; j < dados[i].length; j++) {
				System.out.print(dados[i][j] + "\t");
			}
			System.out.println();
		}

//		SPa == sindromeParidadeLinha
//		SP == sindromeParidadeColuna

//		for i=0:3
		for (int i = 0; i < tamanhoMatrizDados; i++) {
//	        %paridade de palavra
//	        spa(1,i+1)=xor(word(i+1,5),xor(word(i+1,1),xor(word(i+1,2),xor(word(i+1,3),word(i+1,4)))));
			sindromeParidadeLinha[i] = String.valueOf(Integer.parseInt(dados[i][0]) ^ Integer.parseInt(dados[i][1])
					^ Integer.parseInt(dados[i][2]) ^ Integer.parseInt(dados[i][3]) ^ Integer.parseInt(dados[i][4]));
// 			%paridade de coluna
//	        sp(1,i+1)=xor(word(5,i+1),xor(word(1,i+1),xor(word(2,i+1),xor(word(3,i+1),word(4,i+1)))));
			sindromeParidadeColuna[i] = String.valueOf(Integer.parseInt(dados[0][i]) ^ Integer.parseInt(dados[1][i])
					^ Integer.parseInt(dados[2][i]) ^ Integer.parseInt(dados[3][i]) ^ Integer.parseInt(dados[4][i]));

//	        soma_spa=spa(1,i+1)+soma_spa;
			somaSindromeParidadeLinha += Integer.parseInt(sindromeParidadeLinha[i]);
//	        soma_sp=sp(1,i+1)+soma_sp;
			somaSindromeParidadeColuna += Integer.parseInt(sindromeParidadeColuna[i]);
//		end
		}

//		FOR i IN 3 DOWNTO 0 LOOP
//		IF (SPa(i)= '1') THEN
//			FOR j IN 3 DOWNTO 0 LOOP
//				IF (SP(j)='1') THEN
//					c_word(i*8+j):=NOT c_word(i*8+j);
//				END IF;
//			END LOOP;
//		END IF;
//		END LOOP;

		// Correção pelas síndromes de paridade de linha e coluna
		for (int i = 0; i < sindromeParidadeLinha.length; i++) {
			if (sindromeParidadeLinha[i].equals("1")) {
				for (int j = 0; j < sindromeParidadeColuna.length; j++) {
					if (sindromeParidadeColuna[j].equals("1")) {
						dados[i][j] = String.valueOf(Integer.parseInt(dados[i][j]) ^ 1);
					}
				}
			}
		}

//		data(0):=c_word(0);
		palavraDecodificada[0] = dados[0][0];
//		data(1):=c_word(16);
		palavraDecodificada[1] = dados[2][0];
//		data(2):=c_word(2);
		palavraDecodificada[2] = dados[0][2];
//		data(3):=c_word(18);
		palavraDecodificada[3] = dados[2][2];

//		data(4):=c_word(9);
		palavraDecodificada[4] = dados[1][1];
//		data(5):=c_word(25);
		palavraDecodificada[5] = dados[3][1];
//		data(6):=c_word(11);
		palavraDecodificada[6] = dados[1][3];
//		data(7):=c_word(27);
		palavraDecodificada[7] = dados[3][3];

//		data(8):=c_word(1);
		palavraDecodificada[8] = dados[1][0];
//		data(9):=c_word(17);
		palavraDecodificada[9] = dados[3][0];
//		data(10):=c_word(3);
		palavraDecodificada[10] = dados[1][2];
//		data(11):=c_word(19);
		palavraDecodificada[11] = dados[3][2];

//		data(12):=c_word(10);
		palavraDecodificada[12] = dados[0][1];
//		data(13):=c_word(26);
		palavraDecodificada[13] = dados[2][1];
//		data(14):=c_word(12);
		palavraDecodificada[14] = dados[0][3];
//		data(15):=c_word(28);
		palavraDecodificada[15] = dados[2][3];

//		for i=0:3
		for (int i = 0; i < tamanhoMatrizDados; i++) {
//			%paridade de dados
//			spd(1,i+1)=xor(word(5,i+5),xor(data(i+1,1),xor(data(i+1,2),xor(data(i+1,3),data(i+1,4)))));
			sindromeParidadeDados[i] = String.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados])
					^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1])
					^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2])
					^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3])
					^ Integer.parseInt(dados[4][i + 4]));
//			%sindrome de checkbits
//			scb(i+1,1)= xor(word(i+1,6),xor(data(i+1,2),xor(data(i+1,3),data(i+1,4))));
			sindromeCheckbits[i][0] = String.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1])
					^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2])
					^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3])
					^ Integer.parseInt(dados[i][5]));
//			scb(i+1,2)= xor(word(i+1,7),xor(data(i+1,1),xor(data(i+1,3),data(i+1,4))));
			sindromeCheckbits[i][1] = String.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados])
					^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2])
					^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3])
					^ Integer.parseInt(dados[i][6]));
//			scb(i+1,3)= xor(word(i+1,8),xor(data(i+1,1),xor(data(i+1,2),data(i+1,4))));
			sindromeCheckbits[i][2] = String.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados])
					^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1])
					^ Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3])
					^ Integer.parseInt(dados[i][7]));

//			soma_spd=spd(1,i+1)+soma_spd;
			somaSindromeParidadeDados += Integer.parseInt(sindromeParidadeDados[i]);
//		end
		}

		if (somaSindromeParidadeDados != 0) {
			for (int i = 0; i < tamanhoMatrizDados; i++) {
//				if scb(i+1,1)==0 && scb(i+1,2)==1 && scb(i+1,3)==1
				if (sindromeCheckbits[i][0].equals("0") && sindromeCheckbits[i][1].equals("1")
						&& sindromeCheckbits[i][2].equals("1")) {
//					data(i+1,1)=~data(i+1,1);
					palavraDecodificada[i * tamanhoMatrizDados] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados]) ^ 1);
//					soma_scb=soma_scb+1;
					somaSindromeCheckbits++;

//	            elseif scb(i+1,1)==1 && scb(i+1,2)==0 && scb(i+1,3)==1 
				} else if (sindromeCheckbits[i][0].equals("1") && sindromeCheckbits[i][1].equals("0")
						&& sindromeCheckbits[i][2].equals("1")) {
//		        	data(i+1,2)=~data(i+1,2);
					palavraDecodificada[(i * tamanhoMatrizDados) + 1] = String
							.valueOf(Integer.parseInt(palavraDecodificada[(i * tamanhoMatrizDados) + 1]) ^ 1);
//		        	soma_scb=soma_scb+1;
					somaSindromeCheckbits++;

//	            elseif scb(i+1,1)==1 && scb(i+1,2)==1 && scb(i+1,3)==0
				} else if (sindromeCheckbits[i][0].equals("1") && sindromeCheckbits[i][1].equals("1")
						&& sindromeCheckbits[i][2].equals("0")) {
//		        	data(i+1,3)=~data(i+1,3);
					palavraDecodificada[(i * tamanhoMatrizDados) + 2] = String
							.valueOf(Integer.parseInt(palavraDecodificada[(i * tamanhoMatrizDados) + 2]) ^ 1);
//		       		soma_scb=soma_scb+1;
					somaSindromeCheckbits++;
//	            elseif scb(i+1,1)==1 && scb(i+1,2)==1 && scb(i+1,3)==1
				} else if (sindromeCheckbits[i][0].equals("1") && sindromeCheckbits[i][1].equals("1")
						&& sindromeCheckbits[i][2].equals("1")) {
//		        	data(i+1,4)=~data(i+1,4);
					palavraDecodificada[(i * tamanhoMatrizDados) + 3] = String
							.valueOf(Integer.parseInt(palavraDecodificada[(i * tamanhoMatrizDados) + 3]) ^ 1);
//		        	soma_scb=soma_scb+1;           
					somaSindromeCheckbits++;
				}
//	            end
			}
		}

//		if(soma_spa==1 && soma_sp==1 && soma_spd==0 && soma_scb==0)
		if (somaSindromeParidadeLinha == 1 && somaSindromeParidadeColuna == 1 && somaSindromeParidadeDados == 0
				&& somaSindromeCheckbits == 0) {
//	        y=1;
			errosTotais = 1;
//	    elseif (soma_spd==2 && soma_scb==2)
		} else if (somaSindromeParidadeDados == 2 && somaSindromeCheckbits == 2) {
//	        y=2;
			errosTotais = 2;
//	    elseif(soma_spa==3 || soma_sp==3)
		} else if (somaSindromeParidadeLinha == 3 || somaSindromeParidadeColuna == 3) {
//	        y=3;
			errosTotais = 3;
//	    elseif ((soma_spa==0 && soma_sp==0 && soma_spd==4 && soma_scb==4) || (soma_spd==2 && soma_scb==3))
		} else if ((somaSindromeParidadeLinha == 0 && somaSindromeParidadeColuna == 0 && somaSindromeParidadeDados == 4
				&& somaSindromeCheckbits == 4) || (somaSindromeParidadeDados == 2 && somaSindromeCheckbits == 3)) {
//	        y=4;
			errosTotais = 4;
//	    elseif((soma_spa==1 && soma_sp==1 && soma_spd==4 && soma_scb==4)||(soma_spd==0 && soma_scb==3))
		} else if ((somaSindromeParidadeLinha == 1 && somaSindromeParidadeColuna == 1 && somaSindromeParidadeDados == 4
				&& somaSindromeCheckbits == 4) || (somaSindromeParidadeDados == 0 && somaSindromeCheckbits == 3)) {
//	        y=5;
			errosTotais = 5;
//	    elseif((soma_spa==2 || soma_sp==2) && soma_spd==4 && soma_scb==4)
		} else if ((somaSindromeParidadeLinha == 2 || somaSindromeParidadeColuna == 2) && somaSindromeParidadeDados == 4
				&& somaSindromeCheckbits == 4) {
//	        y=6;
			errosTotais = 6;
//	    end
		}

		System.out.println("Quantidade total de erros: " + errosTotais);

		return String.join("", palavraDecodificada);
	}

	public static String decodificaPHICC44(String[][] dados) {
		String[] palavraDecodificada = new String[16];
		int i, j, errosTotais = 0, tamanhoMatrizDados = 4, somaSindromeParidadeLinha = 0,
				somaSindromeParidadeColuna = 0, somaSindromeParidadeCheckbits = 0, somaSindromeCheckbits = 0;

		String[] sindromeParidadeLinha = new String[tamanhoMatrizDados],
				sindromeParidadeColuna = new String[tamanhoMatrizDados],
				sindromeParidadeCheckbits = new String[tamanhoMatrizDados];
		String[][] sindromeCheckbits = new String[tamanhoMatrizDados][tamanhoMatrizDados],
				checkbits = new String[tamanhoMatrizDados][tamanhoMatrizDados];

		dados[0][1] = "0";
		dados[0][2] = "1";
		dados[1][2] = "1";

		for (i = 0; i < tamanhoMatrizDados; i++) {
			sindromeParidadeLinha[i] = String.valueOf(Integer.parseInt(dados[i][0]) ^ Integer.parseInt(dados[i][1])
					^ Integer.parseInt(dados[i][2]) ^ Integer.parseInt(dados[i][3]) ^ Integer.parseInt(dados[i][4]));
			sindromeParidadeColuna[i] = String.valueOf(Integer.parseInt(dados[0][i]) ^ Integer.parseInt(dados[1][i])
					^ Integer.parseInt(dados[2][i]) ^ Integer.parseInt(dados[3][i]) ^ Integer.parseInt(dados[i][5]));

			somaSindromeParidadeLinha += Integer.parseInt(sindromeParidadeLinha[i]);
			somaSindromeParidadeColuna += Integer.parseInt(sindromeParidadeColuna[i]);
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
				if (sindromeCheckbits[i][0].equals("1") && sindromeCheckbits[i][1].equals("1")
						&& sindromeCheckbits[i][2].equals("1") && sindromeCheckbits[i][3].equals("0")) {
					palavraDecodificada[i * tamanhoMatrizDados] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados]) ^ 1);

					somaSindromeCheckbits++;
				} else if (sindromeCheckbits[i][0].equals("1") && sindromeCheckbits[i][1].equals("1")
						&& sindromeCheckbits[i][2].equals("0") && sindromeCheckbits[i][3].equals("1")) {
					palavraDecodificada[i * tamanhoMatrizDados + 1] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1]) ^ 1);

					somaSindromeCheckbits++;
				} else if (sindromeCheckbits[i][0].equals("1") && sindromeCheckbits[i][1].equals("0")
						&& sindromeCheckbits[i][2].equals("1") && sindromeCheckbits[i][3].equals("1")) {
					palavraDecodificada[i * tamanhoMatrizDados + 2] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2]) ^ 1);

					somaSindromeCheckbits++;
				} else if (sindromeCheckbits[i][0].equals("0") && sindromeCheckbits[i][1].equals("1")
						&& sindromeCheckbits[i][2].equals("1") && sindromeCheckbits[i][3].equals("1")) {
					palavraDecodificada[i * tamanhoMatrizDados + 3] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3]) ^ 1);

					somaSindromeCheckbits++;
				} else if (sindromeCheckbits[i][0].equals("0") && sindromeCheckbits[i][1].equals("0")
						&& sindromeCheckbits[i][2].equals("1") && sindromeCheckbits[i][3].equals("1")) {
					palavraDecodificada[i * tamanhoMatrizDados] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados]) ^ 1);
					palavraDecodificada[i * tamanhoMatrizDados + 1] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1]) ^ 1);

					somaSindromeCheckbits++;
				} else if (sindromeCheckbits[i][0].equals("0") && sindromeCheckbits[i][1].equals("1")
						&& sindromeCheckbits[i][2].equals("0") && sindromeCheckbits[i][3].equals("1")) {
					palavraDecodificada[i * tamanhoMatrizDados] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados]) ^ 1);
					palavraDecodificada[i * tamanhoMatrizDados + 2] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2]) ^ 1);

					somaSindromeCheckbits++;
				} else if (sindromeCheckbits[i][0].equals("1") && sindromeCheckbits[i][1].equals("0")
						&& sindromeCheckbits[i][2].equals("0") && sindromeCheckbits[i][3].equals("1")) {
					palavraDecodificada[i * tamanhoMatrizDados] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados]) ^ 1);
					palavraDecodificada[i * tamanhoMatrizDados + 3] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3]) ^ 1);

					somaSindromeCheckbits++;
				} else if (sindromeCheckbits[i][0].equals("0") && sindromeCheckbits[i][1].equals("1")
						&& sindromeCheckbits[i][2].equals("1") && sindromeCheckbits[i][3].equals("0")) {
					palavraDecodificada[i * tamanhoMatrizDados + 1] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1]) ^ 1);
					palavraDecodificada[i * tamanhoMatrizDados + 2] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2]) ^ 1);

					somaSindromeCheckbits++;
				} else if (sindromeCheckbits[i][0].equals("1") && sindromeCheckbits[i][1].equals("0")
						&& sindromeCheckbits[i][2].equals("1") && sindromeCheckbits[i][3].equals("0")) {
					palavraDecodificada[i * tamanhoMatrizDados + 1] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 1]) ^ 1);
					palavraDecodificada[i * tamanhoMatrizDados + 3] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3]) ^ 1);

					somaSindromeCheckbits++;
				} else if (sindromeCheckbits[i][0].equals("1") && sindromeCheckbits[i][1].equals("1")
						&& sindromeCheckbits[i][2].equals("0") && sindromeCheckbits[i][3].equals("0")) {
					palavraDecodificada[i * tamanhoMatrizDados + 2] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 2]) ^ 1);
					palavraDecodificada[i * tamanhoMatrizDados + 3] = String
							.valueOf(Integer.parseInt(palavraDecodificada[i * tamanhoMatrizDados + 3]) ^ 1);

					somaSindromeCheckbits++;
				}
			}
		}

		// soma_spa == sindromeLinha
		// soma_sp == sindromeColuna
		// soma_spd == sindromeParidadeCheckbits
		// soma_scb == sindromeCheckbits

		String erros = "Soma das paridades de linha: %d\nSoma das paridades de coluna: %d\nSoma das paridades de checkbits: %d\nSoma dos checkbits: %d";
		System.out.println(String.format(erros, somaSindromeParidadeLinha, somaSindromeParidadeColuna,
				somaSindromeParidadeCheckbits, somaSindromeCheckbits));

		if (somaSindromeParidadeLinha == 1 && somaSindromeParidadeColuna == 1 && somaSindromeParidadeCheckbits == 0
				&& somaSindromeCheckbits == 0) {
			errosTotais = 1;
		} else if (somaSindromeParidadeCheckbits == 2 && somaSindromeCheckbits == 2) {
			errosTotais = 2;
		} else if (somaSindromeParidadeLinha == 3 || somaSindromeParidadeColuna == 3) {
			errosTotais = 3;
		} else if ((somaSindromeParidadeLinha == 0 && somaSindromeParidadeColuna == 0
				&& somaSindromeParidadeCheckbits == 4 && somaSindromeCheckbits == 4)
				|| (somaSindromeParidadeCheckbits == 2 && somaSindromeCheckbits == 3)) {
			errosTotais = 4;
		} else if ((somaSindromeParidadeLinha == 1 && somaSindromeParidadeColuna == 1
				&& somaSindromeParidadeCheckbits == 4 && somaSindromeCheckbits == 4)
				|| (somaSindromeParidadeCheckbits == 0 && somaSindromeCheckbits == 3)) {
			errosTotais = 5;
		} else if ((somaSindromeParidadeLinha == 2 || somaSindromeParidadeColuna == 2)
				&& somaSindromeParidadeCheckbits == 4 && somaSindromeCheckbits == 4) {
			errosTotais = 6;
		}

		System.out.println("Quantidade total de erros: " + errosTotais);

		return String.join("", palavraDecodificada);
	}

	public static String decodificaPHICC32(String[][] dados) {
		int i, j, tamanhoMatrizDados = 4, sindromeVerificacaoFlag = 0;

		String[] sindromeParidadeLinha = new String[tamanhoMatrizDados],
				sindromeParidadeColuna = new String[tamanhoMatrizDados], paridadeDados = new String[tamanhoMatrizDados],
				sindromeParidadeDados = new String[tamanhoMatrizDados],
				sindromeVerificacao = new String[tamanhoMatrizDados], palavraDecodificada = new String[16];

		String[][] sindromeCheckbits = new String[tamanhoMatrizDados][tamanhoMatrizDados],
				checkbits = new String[tamanhoMatrizDados][tamanhoMatrizDados];

		String[][] dadosTemp = new String[dados.length][dados[0].length];

		for (i = 0; i < dados.length; i++) {
			for (j = 0; j < dados[i].length; j++) {
				dadosTemp[i][j] = dados[i][j];
			}
		}

		dados[0][1] = "0";
		dados[0][2] = "1";
		dados[1][1] = "0";
		dados[0][2] = "0";

		for (i = 0; i < tamanhoMatrizDados / 2; i++) {
			dados[i][0] = dadosTemp[i][1];
			dados[i][1] = dadosTemp[i][0];
			System.out.println("dados[" + i + "][2]: " + dados[i][2]);
			System.out.println("dadosTemp[" + i + "][2]: " + dadosTemp[i][2]);
			dados[i][2] = dadosTemp[i][3];
			System.out.println("dados[" + i + "][2]: " + dados[i][2]);
			System.out.println("dadosTemp[" + i + "][2]: " + dadosTemp[i][2]);
			dados[i][3] = dadosTemp[i][2];
		}

		System.out.println("Dados após o pré interleaving: ");
		printMatriz(dados);

		sindromeVerificacao[0] = String.valueOf(Integer.parseInt(dados[0][7]) ^ Integer.parseInt(dados[2][4])
				^ Integer.parseInt(dados[0][5]) ^ Integer.parseInt(dados[2][6]));
		sindromeVerificacao[1] = String.valueOf(Integer.parseInt(dados[1][7]) ^ Integer.parseInt(dados[3][4])
				^ Integer.parseInt(dados[1][5]) ^ Integer.parseInt(dados[3][6]));
		sindromeVerificacao[2] = String.valueOf(Integer.parseInt(dados[2][7]) ^ Integer.parseInt(dados[0][4])
				^ Integer.parseInt(dados[2][5]) ^ Integer.parseInt(dados[0][6]));
		sindromeVerificacao[3] = String.valueOf(Integer.parseInt(dados[3][7]) ^ Integer.parseInt(dados[1][4])
				^ Integer.parseInt(dados[3][5]) ^ Integer.parseInt(dados[1][6]));

		sindromeVerificacaoFlag = Integer.parseInt(sindromeVerificacao[0]) | Integer.parseInt(sindromeVerificacao[1])
				| Integer.parseInt(sindromeVerificacao[2]) | Integer.parseInt(sindromeVerificacao[3]);

		sindromeParidadeLinha[0] = String.valueOf(Integer.parseInt(dados[2][6]) ^ Integer.parseInt(dados[0][6])
				^ Integer.parseInt(dados[0][0]) ^ Integer.parseInt(dados[0][1]) ^ Integer.parseInt(dados[0][2])
				^ Integer.parseInt(dados[0][3]));

		sindromeParidadeLinha[1] = String.valueOf(Integer.parseInt(dados[1][6]) ^ Integer.parseInt(dados[3][6])
				^ Integer.parseInt(dados[1][0]) ^ Integer.parseInt(dados[1][1]) ^ Integer.parseInt(dados[1][2])
				^ Integer.parseInt(dados[1][3]));

		sindromeParidadeLinha[2] = String.valueOf(Integer.parseInt(dados[2][4]) ^ Integer.parseInt(dados[0][4])
				^ Integer.parseInt(dados[2][6]) ^ Integer.parseInt(dados[0][6]) ^ Integer.parseInt(dados[2][0])
				^ Integer.parseInt(dados[2][1]) ^ Integer.parseInt(dados[2][2]) ^ Integer.parseInt(dados[2][3]));

		sindromeParidadeLinha[3] = String.valueOf(Integer.parseInt(dados[1][4]) ^ Integer.parseInt(dados[3][4])
				^ Integer.parseInt(dados[1][6]) ^ Integer.parseInt(dados[3][6]) ^ Integer.parseInt(dados[3][0])
				^ Integer.parseInt(dados[3][1]) ^ Integer.parseInt(dados[3][2]) ^ Integer.parseInt(dados[3][3]));

		sindromeParidadeColuna[0] = String.valueOf(Integer.parseInt(dados[1][5]) ^ Integer.parseInt(dados[0][5])
				^ Integer.parseInt(dados[0][0]) ^ Integer.parseInt(dados[1][0]) ^ Integer.parseInt(dados[2][0])
				^ Integer.parseInt(dados[3][0]));

		sindromeParidadeColuna[1] = String.valueOf(Integer.parseInt(dados[3][5]) ^ Integer.parseInt(dados[2][5])
				^ Integer.parseInt(dados[0][1]) ^ Integer.parseInt(dados[1][1]) ^ Integer.parseInt(dados[2][1])
				^ Integer.parseInt(dados[3][1]));

		sindromeParidadeColuna[2] = String.valueOf(Integer.parseInt(dados[3][4]) ^ Integer.parseInt(dados[2][4])
				^ Integer.parseInt(dados[1][5]) ^ Integer.parseInt(dados[0][5]) ^ Integer.parseInt(dados[0][2])
				^ Integer.parseInt(dados[1][2]) ^ Integer.parseInt(dados[2][2]) ^ Integer.parseInt(dados[3][2]));

		sindromeParidadeColuna[3] = String.valueOf(Integer.parseInt(dados[1][4]) ^ Integer.parseInt(dados[0][4])
				^ Integer.parseInt(dados[3][5]) ^ Integer.parseInt(dados[2][5]) ^ Integer.parseInt(dados[0][3])
				^ Integer.parseInt(dados[1][3]) ^ Integer.parseInt(dados[2][3]) ^ Integer.parseInt(dados[3][3]));

		if (sindromeVerificacaoFlag == 0) {
			for (i = 0; i < tamanhoMatrizDados; i++) {
				if (sindromeParidadeLinha[i].equals("1")) {
					for (j = 0; j < tamanhoMatrizDados; j++) {
						if (sindromeParidadeColuna[j].equals("1")) {
							dados[i][j] = String.valueOf(Integer.parseInt(dados[i][j]) ^ 1);
						}
					}
				}
			}
		}

		palavraDecodificada[0] = dados[0][0];
		palavraDecodificada[1] = dados[2][0];
		palavraDecodificada[2] = dados[0][2];
		palavraDecodificada[3] = dados[2][2];

		palavraDecodificada[4] = dados[1][0];
		palavraDecodificada[5] = dados[3][0];
		palavraDecodificada[6] = dados[1][2];
		palavraDecodificada[7] = dados[3][2];

		palavraDecodificada[8] = dados[0][1];
		palavraDecodificada[9] = dados[2][1];
		palavraDecodificada[10] = dados[0][3];
		palavraDecodificada[11] = dados[2][3];

		palavraDecodificada[12] = dados[1][1];
		palavraDecodificada[13] = dados[3][1];
		palavraDecodificada[14] = dados[1][3];
		palavraDecodificada[15] = dados[3][3];

		checkbits[0][0] = dados[0][5];
		checkbits[0][1] = dados[2][6];

		checkbits[1][0] = dados[1][5];
		checkbits[1][1] = dados[3][6];

		checkbits[2][0] = dados[2][5];
		checkbits[2][1] = dados[0][6];

		checkbits[3][0] = dados[3][5];
		checkbits[3][1] = dados[1][6];

		paridadeDados[0] = dados[2][4];
		paridadeDados[1] = dados[3][4];
		paridadeDados[2] = dados[0][4];
		paridadeDados[3] = dados[1][4];

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

	public static String decodificaPHICC36(String[][] dados) {
		return null;
	}

	public static void printMatriz(String[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
