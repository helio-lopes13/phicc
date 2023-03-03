package br.edu.ifce.helio.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.edu.ifce.helio.phicc.implementacao.EntradaCodificada;
import br.edu.ifce.helio.phicc.modelo.Paridade;

class TestParidade {

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
		
		EntradaCodificada entrada = paridadeMSB.codificar("00010110101001010001011010100111");
		assertEquals("10010110101001010001011010100111", entrada.getValor());
	}
	
	@Test
	void testDecodificaParidadeMSB() {
		Paridade paridadeMSB = Paridade.PARIDADE_MSB;
		
		EntradaCodificada entrada = paridadeMSB.codificar("00010110101001010001011010100111");
		assertEquals("00010110101001010001011010100111", paridadeMSB.decodificar(entrada));
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
		
		EntradaCodificada entrada = paridadeMSB4.codificar("0001011010100111");
		assertEquals("0001011010100111", paridadeMSB4.decodificar(entrada));
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
		
		EntradaCodificada entrada = paridadeMSB8.codificar("0001011010100111");
		assertEquals("0001011010100111", paridadeMSB8.decodificar(entrada));
	}
	
	@Test
	void testCodificaParidadeMSB12() {
		Paridade paridadeMSB12 = Paridade.PARIDADE_MSB_12;
		
		EntradaCodificada entrada = paridadeMSB12.codificar("0001011010100111");
		assertEquals("1001011010100111", entrada.getValor());
	}
	
	@Test
	void testDecodificaParidadeMSB12() {
		Paridade paridadeMSB12 = Paridade.PARIDADE_MSB_12;
		
		EntradaCodificada entrada = paridadeMSB12.codificar("0001011010100111");
		assertEquals("0001011010100111", paridadeMSB12.decodificar(entrada));
	}
	
	@Test
	void testCodificaParidadeMSB16() {
		Paridade paridadeMSB16 = Paridade.PARIDADE_MSB_16;
		
		EntradaCodificada entrada = paridadeMSB16.codificar("0001011010100111");
		assertEquals("1001011010100101", entrada.getValor());
	}
	
	@Test
	void testDecodificaParidadeMSB16() {
		Paridade paridadeMSB16 = Paridade.PARIDADE_MSB_16;
		
		EntradaCodificada entrada = paridadeMSB16.codificar("0001011010100111");
		assertEquals("0001011010100111", paridadeMSB16.decodificar(entrada));
	}
	
	@Test
	void testCodificaParidadeMSB16ComPalavraDe32Bits() {
		Paridade paridadeMSB16 = Paridade.PARIDADE_MSB_16;
		
		EntradaCodificada entrada = paridadeMSB16.codificar("00010110101001110001011010101111");
		assertEquals("10010110101001110001011010101111", entrada.getValor());
	}
	
	@Test
	void testDecodificaParidadeMSB16ComPalavraDe32Bits() {
		Paridade paridadeMSB16 = Paridade.PARIDADE_MSB_16;
		
		EntradaCodificada entrada = paridadeMSB16.codificar("00010110101001110001011010101111");
		assertEquals("00010110101001110001011010101111", paridadeMSB16.decodificar(entrada));
	}
}
