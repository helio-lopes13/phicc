package br.edu.ifce.helio.phicc.implementacao;

public class PHICC {

	public static String[][] codificaPHICC40(String dados) {
		String[][] dadosCodificados = new String[5][8];
		int tamanhoMatrizDados = 4;
		char[] palavra = dados.toCharArray();

		// palavra == data_in
		// dadosCodificados == word

		for (int i = 0; i < tamanhoMatrizDados; i++) {
//			CB(i + 1, 1) = xor(entrada(i + 1, 2), xor(entrada(i + 1, 3), entrada(i + 1, 4)));
			dadosCodificados[i][5] = Character.toString(palavra[i * 4 + 1] ^ palavra[i * 4 + 2] ^ palavra[i * 4 + 3]);
//			CB(i + 1, 2) = xor(entrada(i + 1, 1), xor(entrada(i + 1, 3), entrada(i + 1, 4)));
			dadosCodificados[i][6] = Character.toString(palavra[i * 4] ^ palavra[i * 4 + 2] ^ palavra[i * 4 + 3]);
//			CB(i + 1, 3) = xor(entrada(i + 1, 1), xor(entrada(i + 1, 2), entrada(i + 1, 4)));
			dadosCodificados[i][7] = Character.toString(palavra[i * 4] ^ palavra[i * 4 + 1] ^ palavra[i * 4 + 3]);
		}

//		word(0):=data_in(0);
		dadosCodificados[0][0] = String.valueOf(palavra[0]);
//		word(1):=data_in(12);
		dadosCodificados[0][1] = String.valueOf(palavra[12]);
//		word(2):=data_in(2);
		dadosCodificados[0][2] = String.valueOf(palavra[2]);
//		word(3):=data_in(14);
		dadosCodificados[0][3] = String.valueOf(palavra[14]);

//		word(8):=data_in(8);
		dadosCodificados[1][0] = String.valueOf(palavra[8]);
//		word(9):=data_in(4);
		dadosCodificados[1][1] = String.valueOf(palavra[4]);
//		word(10):=data_in(10);
		dadosCodificados[1][2] = String.valueOf(palavra[10]);
//		word(11):=data_in(6);
		dadosCodificados[1][3] = String.valueOf(palavra[6]);

//		word(16):=data_in(1);
		dadosCodificados[2][0] = String.valueOf(palavra[1]);
//		word(17):=data_in(13);
		dadosCodificados[2][1] = String.valueOf(palavra[13]);
//		word(18):=data_in(3);
		dadosCodificados[2][2] = String.valueOf(palavra[3]);
//		word(19):=data_in(15);
		dadosCodificados[2][3] = String.valueOf(palavra[15]);

//		word(24):=data_in(9);
		dadosCodificados[3][0] = String.valueOf(palavra[9]);
//		word(25):=data_in(5);
		dadosCodificados[3][1] = String.valueOf(palavra[5]);
//		word(26):=data_in(11);
		dadosCodificados[3][2] = String.valueOf(palavra[11]);
//		word(27):=data_in(7);
		dadosCodificados[3][3] = String.valueOf(palavra[7]);

		for (int i = 0; i < tamanhoMatrizDados; i++) {
//			pa(i+1,1)=xor(datai(i+1,1),xor(datai(i+1,2),xor(datai(i+1,3),datai(i+1,4)))); %Paridade de linha dos dados embaralhados
			dadosCodificados[i][4] = String
					.valueOf(Integer.valueOf(dadosCodificados[i][0]) ^ Integer.valueOf(dadosCodificados[i][1])
							^ Integer.valueOf(dadosCodificados[i][2]) ^ Integer.valueOf(dadosCodificados[i][3]));

//			p(1,i+1)=xor(datai(1,i+1),xor(datai(2,i+1),xor(datai(3,i+1),datai(4,i+1)))); %Paridade de coluna dos dados embaralhados
			dadosCodificados[4][i] = String
					.valueOf(Integer.valueOf(dadosCodificados[0][i]) ^ Integer.valueOf(dadosCodificados[1][i])
							^ Integer.valueOf(dadosCodificados[2][i]) ^ Integer.valueOf(dadosCodificados[3][i]));

//			pd(1,i+1)=xor(entrada(i+1,1),xor(entrada(i+1,2),xor(entrada(i+1,3),entrada(i+1,4))));
			dadosCodificados[4][i + 4] = String
					.valueOf(palavra[i * 4] ^ palavra[i * 4 + 1] ^ palavra[i * 4 + 2] ^ palavra[i * 4 + 3]);
		}

		return dadosCodificados;
	}

	public static String[][] codificaPHICC44(String dados) {
		return null;
	}

	public static String[][] codificaPHICC32(String dados) {
		return null;
	}

	public static String[][] codificaPHICC36(String dados) {
		return null;
	}

	public static String decodificaPHICC40(String[][] dados) {
		char[] palavraDecodificada = new char[16];
		int tamanhoMatrizDados = 4, somaSindromeParidadeLinha = 0, somaSindromeParidadeColuna = 0,
				somaSindromeParidadeDados = 0, somaSindromeCheckbits = 0, errosTotais = 0;
		String[] sindromeParidadeLinha = new String[tamanhoMatrizDados],
				sindromeParidadeColuna = new String[tamanhoMatrizDados],
				sindromeParidadeDados = new String[tamanhoMatrizDados];
		String[][] sindromeCheckbits = new String[tamanhoMatrizDados][3];

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
			sindromeParidadeLinha[i] = String.valueOf(Integer.valueOf(dados[i][0]) ^ Integer.valueOf(dados[i][1])
					^ Integer.valueOf(dados[i][2]) ^ Integer.valueOf(dados[i][3]) ^ Integer.valueOf(dados[i][4]));
// 			%paridade de coluna
//	        sp(1,i+1)=xor(word(5,i+1),xor(word(1,i+1),xor(word(2,i+1),xor(word(3,i+1),word(4,i+1)))));
			sindromeParidadeColuna[i] = String.valueOf(Integer.valueOf(dados[0][i]) ^ Integer.valueOf(dados[1][i])
					^ Integer.valueOf(dados[2][i]) ^ Integer.valueOf(dados[3][i]) ^ Integer.valueOf(dados[4][i]));

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
						dados[i][j] = String.valueOf(Integer.valueOf(dados[i][j]) ^ 1);
					}
				}
			}
		}

//		data(0):=c_word(0);
		palavraDecodificada[0] = dados[0][0].charAt(0);
//		data(1):=c_word(16);
		palavraDecodificada[1] = dados[2][0].charAt(0);
//		data(2):=c_word(2);
		palavraDecodificada[2] = dados[0][2].charAt(0);
//		data(3):=c_word(18);
		palavraDecodificada[3] = dados[2][2].charAt(0);

//		data(4):=c_word(9);
		palavraDecodificada[4] = dados[1][1].charAt(0);
//		data(5):=c_word(25);
		palavraDecodificada[5] = dados[3][1].charAt(0);
//		data(6):=c_word(11);
		palavraDecodificada[6] = dados[1][3].charAt(0);
//		data(7):=c_word(27);
		palavraDecodificada[7] = dados[3][3].charAt(0);

//		data(8):=c_word(1);
		palavraDecodificada[8] = dados[1][0].charAt(0);
//		data(9):=c_word(17);
		palavraDecodificada[9] = dados[3][0].charAt(0);
//		data(10):=c_word(3);
		palavraDecodificada[10] = dados[1][2].charAt(0);
//		data(11):=c_word(19);
		palavraDecodificada[11] = dados[3][2].charAt(0);

//		data(12):=c_word(10);
		palavraDecodificada[12] = dados[0][1].charAt(0);
//		data(13):=c_word(26);
		palavraDecodificada[13] = dados[2][1].charAt(0);
//		data(14):=c_word(12);
		palavraDecodificada[14] = dados[0][3].charAt(0);
//		data(15):=c_word(28);
		palavraDecodificada[15] = dados[2][3].charAt(0);

		System.out.println("Dados corrigidos");
		for (int i = 0; i < dados.length; i++) {
			for (int j = 0; j < dados[i].length; j++) {
				System.out.print(dados[i][j] + "\t");
			}
			System.out.println();
		}

//		for i=0:3
		for (int i = 0; i < tamanhoMatrizDados; i++) {
//			%paridade de dados
//			spd(1,i+1)=xor(word(5,i+5),xor(data(i+1,1),xor(data(i+1,2),xor(data(i+1,3),data(i+1,4)))));
			sindromeParidadeDados[i] = String.valueOf(
					palavraDecodificada[i * 4] ^ palavraDecodificada[i * 4 + 1] ^ palavraDecodificada[i * 4 + 2]
							^ palavraDecodificada[i * 4 + 3] ^ Integer.parseInt(dados[4][i + 4]));
//			%sindrome de checkbits
//			scb(i+1,1)= xor(word(i+1,6),xor(data(i+1,2),xor(data(i+1,3),data(i+1,4))));
			sindromeCheckbits[i][0] = String.valueOf(palavraDecodificada[i * 4 + 1] ^ palavraDecodificada[i * 4 + 2]
					^ palavraDecodificada[i * 4 + 3] ^ Integer.parseInt(dados[i][5]));
//			scb(i+1,2)= xor(word(i+1,7),xor(data(i+1,1),xor(data(i+1,3),data(i+1,4))));
			sindromeCheckbits[i][1] = String.valueOf(palavraDecodificada[i * 4] ^ palavraDecodificada[i * 4 + 2]
					^ palavraDecodificada[i * 4 + 3] ^ Integer.parseInt(dados[i][6]));
//			scb(i+1,3)= xor(word(i+1,8),xor(data(i+1,1),xor(data(i+1,2),data(i+1,4))));
			sindromeCheckbits[i][2] = String.valueOf(palavraDecodificada[i * 4] ^ palavraDecodificada[i * 4 + 1]
					^ palavraDecodificada[i * 4 + 3] ^ Integer.parseInt(dados[i][7]));

//			soma_spd=spd(1,i+1)+soma_spd;
			somaSindromeParidadeDados += Integer.parseInt(sindromeParidadeDados[i]);
//		end
		}

		if (somaSindromeParidadeDados == 0) {
			for (int i = 0; i < tamanhoMatrizDados; i++) {
//				if scb(i+1,1)==0 && scb(i+1,2)==1 && scb(i+1,3)==1
				if (sindromeCheckbits[i][0].equals("0") && sindromeCheckbits[i][1].equals("1")
						&& sindromeCheckbits[i][2].equals("1")) {
//					data(i+1,1)=~data(i+1,1);
					palavraDecodificada[i * 4] = String.valueOf(Integer.valueOf(palavraDecodificada[i * 4]) ^ 1)
							.charAt(0);
//					soma_scb=soma_scb+1;
					somaSindromeCheckbits++;

//	            elseif scb(i+1,1)==1 && scb(i+1,2)==0 && scb(i+1,3)==1 
				} else if (sindromeCheckbits[i][0].equals("1") && sindromeCheckbits[i][1].equals("0")
						&& sindromeCheckbits[i][2].equals("1")) {
//		        	data(i+1,2)=~data(i+1,2);
					palavraDecodificada[(i * 4) + 1] = String
							.valueOf(Integer.valueOf(palavraDecodificada[(i * 4) + 1]) ^ 1).charAt(0);
//		        	soma_scb=soma_scb+1;
					somaSindromeCheckbits++;

//	            elseif scb(i+1,1)==1 && scb(i+1,2)==1 && scb(i+1,3)==0
				} else if (sindromeCheckbits[i][0].equals("1") && sindromeCheckbits[i][1].equals("1")
						&& sindromeCheckbits[i][2].equals("0")) {
//		        	data(i+1,3)=~data(i+1,3);
					palavraDecodificada[(i * 4) + 2] = String
							.valueOf(Integer.valueOf(palavraDecodificada[(i * 4) + 2]) ^ 1).charAt(0);
//		       		soma_scb=soma_scb+1;
					somaSindromeCheckbits++;
//	            elseif scb(i+1,1)==1 && scb(i+1,2)==1 && scb(i+1,3)==1
				} else if (sindromeCheckbits[i][0].equals("1") && sindromeCheckbits[i][1].equals("1")
						&& sindromeCheckbits[i][2].equals("1")) {
//		        	data(i+1,4)=~data(i+1,4);
					palavraDecodificada[(i * 4) + 3] = String
							.valueOf(Integer.valueOf(palavraDecodificada[(i * 4) + 3]) ^ 1).charAt(0);
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

		return String.valueOf(palavraDecodificada);
	}

	public static String decodificaPHICC44(String[][] dados) {
		return null;
	}

	public static String decodificaPHICC32(String[][] dados) {
		return null;
	}

	public static String decodificaPHICC36(String[][] dados) {
		return null;
	}
}
