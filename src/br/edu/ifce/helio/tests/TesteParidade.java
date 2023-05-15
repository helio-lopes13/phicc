package br.edu.ifce.helio.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.edu.ifce.helio.phicc.implementacao.EntradaCodificada;
import br.edu.ifce.helio.phicc.modelo.Paridade;

class TesteParidade {

	@Test
	void testCodificaSemParidade() {
		Paridade semParidade = Paridade.SEM_PARIDADE;

		EntradaCodificada entrada = semParidade.codificar("00010110101001010001011010100101");
		assertEquals("00010110101001010001011010100101", entrada.getValor());
	}

	@Test
	void testDecodificaSemParidade() {
		Paridade semParidade = Paridade.SEM_PARIDADE;

		EntradaCodificada entrada = semParidade.codificar("00010110101001010001011010100101");
		assertEquals("00010110101001010001011010100101", entrada.getValor());
	}

	@Test
	void testCodificaParidadeMSB() {
		Paridade paridadeMSB = Paridade.PARIDADE_MSB;

		EntradaCodificada entrada = paridadeMSB.codificar("10010100000000000000000000000000");
		assertEquals("10010100000000000000000000000000", entrada.getValor());
	}

	@Test
	void testDecodificaParidadeMSB() {
		Paridade paridadeMSB = Paridade.PARIDADE_MSB;

		for (int i = 0; i < 4096 * 1024 * 1024 - 1; i++) {
			String palavra = String.format("%32s", Integer.toBinaryString(i & 0xFFFFFFFF)).replace(" ", "0");
			EntradaCodificada entrada = paridadeMSB.codificar(palavra);
			assertEquals(palavra, paridadeMSB.decodificar(entrada));
		}
	}

	@Test
	void testCodificaParidadeMSB4() {
		Paridade paridadeMSB4 = Paridade.PARIDADE_MSB_4;

		EntradaCodificada entrada = paridadeMSB4.codificar("0001011010100111");
		assertEquals("1001011010100111", entrada.getValor());
	}

	@Test
	void testDecodificaParidadeMSB4() {
		Paridade paridadeMSB4 = Paridade.PARIDADE_MSB_4;

		for (int i = 0; i < 4096 * 1024 * 1024 - 1; i++) {
			String palavra = String.format("%32s", Integer.toBinaryString(i & 0xFFFFFFFF)).replace(" ", "0");
			EntradaCodificada entrada = paridadeMSB4.codificar(palavra);
			assertEquals(palavra, paridadeMSB4.decodificar(entrada));
		}
	}

	@Test
	void testCodificaParidadeMSB8() {
		Paridade paridadeMSB8 = Paridade.PARIDADE_MSB_8;

		EntradaCodificada entrada = paridadeMSB8.codificar("0001011010100111");
		assertEquals("1001011010100111", entrada.getValor());
	}

	@Test
	void testDecodificaParidadeMSB8() {
		Paridade paridadeMSB8 = Paridade.PARIDADE_MSB_8;

		for (int i = 0; i < 4096 * 1024 * 1024 - 1; i++) {
			String palavra = String.format("%32s", Integer.toBinaryString(i & 0xFFFFFFFF)).replace(" ", "0");
			EntradaCodificada entrada = paridadeMSB8.codificar(palavra);
			assertEquals(palavra, paridadeMSB8.decodificar(entrada));
		}
	}

	@Test
	void testCodificaParidadeMSB12() {
		Paridade paridadeMSB12 = Paridade.PARIDADE_MSB_12;

		EntradaCodificada entrada = paridadeMSB12.codificar("0001001010100101");
		assertEquals("1001001010100101", entrada.getValor());
	}

	@Test
	void testDecodificaParidadeMSB12() {
		Paridade paridadeMSB12 = Paridade.PARIDADE_MSB_12;

		for (int i = 0; i < 4096 * 1024 * 1024 - 1; i++) {
			String palavra = String.format("%32s", Integer.toBinaryString(i & 0xFFFFFFFF)).replace(" ", "0");
			EntradaCodificada entrada = paridadeMSB12.codificar(palavra);
			assertEquals(palavra, paridadeMSB12.decodificar(entrada));
		}
	}

	@Test
	void testCodificaParidadeMSB16() {
		Paridade paridadeMSB16 = Paridade.PARIDADE_MSB_16;

		EntradaCodificada entrada = paridadeMSB16.codificar("00010110101001110001011010101111");
		assertEquals("10010110101001110001011010101111", entrada.getValor());
	}

	@Test
	void testDecodificaParidadeMSB16() {
		Paridade paridadeMSB16 = Paridade.PARIDADE_MSB_16;

		for (int i = 0; i < 4096 * 1024 * 1024 - 1; i++) {
			String palavra = String.format("%32s", Integer.toBinaryString(i & 0xFFFFFFFF)).replace(" ", "0");
			EntradaCodificada entrada = paridadeMSB16.codificar(palavra);
			assertEquals(palavra, paridadeMSB16.decodificar(entrada));
		}
	}

	@Test
	void testCodificaParidade2MSB() {
		Paridade paridade2MSB = Paridade.PARIDADE_2_MSB;

		EntradaCodificada entrada = paridade2MSB.codificar("00010110101001110001011010101110");
		assertEquals("11010110101001110001011010101110", entrada.getValor());
	}

	@Test
	void testDecodificaParidade2MSB() {
		Paridade paridade2MSB = Paridade.PARIDADE_2_MSB;

		for (int i = 0; i < 4096 * 1024 * 1024 - 1; i++) {
			String palavra = String.format("%32s", Integer.toBinaryString(i & 0xFFFFFFFF)).replace(" ", "0");
			EntradaCodificada entrada = paridade2MSB.codificar(palavra);
			assertEquals(palavra, paridade2MSB.decodificar(entrada));
		}
	}

	@Test
	void testCodificaParidade2MSB4() {
		Paridade paridade2MSB4 = Paridade.PARIDADE_2_MSB_4;

		EntradaCodificada entrada = paridade2MSB4.codificar("00010110101001110001011010101110");
		assertEquals("01010110101001110001011010101110", entrada.getValor());
	}

	@Test
	void testDecodificaParidade2MSB4() {
		Paridade paridade2MSB4 = Paridade.PARIDADE_2_MSB_4;

		for (int i = 0; i < 4096 * 1024 * 1024 - 1; i++) {
			String palavra = String.format("%32s", Integer.toBinaryString(i & 0xFFFFFFFF)).replace(" ", "0");
			EntradaCodificada entrada = paridade2MSB4.codificar(palavra);
			assertEquals(palavra, paridade2MSB4.decodificar(entrada));
		}
	}

	@Test
	void testCodificaParidade2MSB8() {
		Paridade paridade2MSB8 = Paridade.PARIDADE_2_MSB_8;

		EntradaCodificada entrada = paridade2MSB8.codificar("00010110101001110001011010101110");
		assertEquals("01010110101001110001011010101110", entrada.getValor());
	}

	@Test
	void testDecodificaParidade2MSB8() {
		Paridade paridade2MSB8 = Paridade.PARIDADE_2_MSB_8;

		for (int i = 0; i < 4096 * 1024 * 1024 - 1; i++) {
			String palavra = String.format("%32s", Integer.toBinaryString(i & 0xFFFFFFFF)).replace(" ", "0");
			EntradaCodificada entrada = paridade2MSB8.codificar(palavra);
			assertEquals(palavra, paridade2MSB8.decodificar(entrada));
		}
	}

	@Test
	void testCodificaParidade2MSB12() {
		Paridade paridade2MSB12 = Paridade.PARIDADE_2_MSB_12;

		EntradaCodificada entrada = paridade2MSB12.codificar("00010110101001110001011010101111");
		assertEquals("11010110101001110001011010101111", entrada.getValor());
	}

	@Test
	void testPBitMSB12VerificarValidadeParaSingleError() {
		Paridade pBitMSB12 = Paridade.P_BIT_MSB_12;

		EntradaCodificada entrada = pBitMSB12.codificar("00010110101001110001011010101111");
		
		String valor = (String) entrada.getValor();
		String[] valorSeparado = valor.split("");
		
		valorSeparado[29] = "0";
		entrada.setValor(String.join("", valorSeparado));
		
		assertFalse(entrada.isValida());
	}

	@Test
	void testDecodificaParidade2MSB12() {
		Paridade paridade2MSB12 = Paridade.PARIDADE_2_MSB_12;

		for (int i = 0; i < 4096 * 1024 * 1024 - 1; i++) {
			String palavra = String.format("%32s", Integer.toBinaryString(i & 0xFFFFFFFF)).replace(" ", "0");
			EntradaCodificada entrada = paridade2MSB12.codificar(palavra);
			assertEquals(palavra, paridade2MSB12.decodificar(entrada));
		}
	}

	@Test
	void testCodificaParidade2MSB16() {
		Paridade paridade2MSB16 = Paridade.PARIDADE_2_MSB_16;

		EntradaCodificada entrada = paridade2MSB16.codificar("00010110101001111001011010101110");
		assertEquals("01010110101001111001011010101110", entrada.getValor());
	}

	@Test
	void testDecodificaParidade2MSB16() {
		Paridade paridade2MSB16 = Paridade.PARIDADE_2_MSB_16;

		for (int i = 0; i < 4096 * 1024 * 1024 - 1; i++) {
			String palavra = String.format("%32s", Integer.toBinaryString(i & 0xFFFFFFFF)).replace(" ", "0");
			EntradaCodificada entrada = paridade2MSB16.codificar(palavra);
			assertEquals(palavra, paridade2MSB16.decodificar(entrada));
		}
	}

	@Test
	void testCodificaParidadeBitMSB() {
		Paridade paridadeBitMSB = Paridade.P_BIT_MSB;

		EntradaCodificada entrada = paridadeBitMSB.codificar("10010100000000000000000000000000");
		assertEquals("10010100000000000000000000000000", entrada.getValor());
	}

	@Test
	void testCodificaParidadeBitMSB4() {
		Paridade paridadeBitMSB4 = Paridade.P_BIT_MSB_4;

		EntradaCodificada entrada = paridadeBitMSB4.codificar("10010100000000000000000000000000");
		assertEquals("00010100000000000000000000000000", entrada.getValor());
	}

	@Test
	void testCodificaParidadeBitMSB8() {
		Paridade paridadeBitMSB8 = Paridade.P_BIT_MSB_8;

		EntradaCodificada entrada = paridadeBitMSB8.codificar("10010100000000000000000000000000");
		assertEquals("00010100000000000000000000000000", entrada.getValor());
	}

	@Test
	void testCodificaParidadeBitMSB12() {
		Paridade paridadeBitMSB12 = Paridade.P_BIT_MSB_12;

		EntradaCodificada entrada = paridadeBitMSB12.codificar("10010100000000000000000000000000");
		assertEquals("00010100000000000000000000000000", entrada.getValor());
	}

	@Test
	void testCodificaParidadeBitMSB16() {
		Paridade paridadeBitMSB16 = Paridade.P_BIT_MSB_16;

		EntradaCodificada entrada = paridadeBitMSB16.codificar("10010100000000001000000000000000");
		assertEquals("10010100000000001000000000000000", entrada.getValor());
	}
}
