package br.edu.ifce.helio.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import br.edu.ifce.helio.phicc.implementacao.EntradaCodificada;
import br.edu.ifce.helio.phicc.modelo.TipoParidade;

class TestParidade {

	@Test
	void testCodificaSemParidade() {
		TipoParidade semParidade = TipoParidade.SEM_PARIDADE;
		
		EntradaCodificada entrada = semParidade.codificar("0001011010100101");
		assertEquals("0001011010100101", entrada.getValor());
	}

	@Test
	void testDecodificaSemParidade() {
		TipoParidade semParidade = TipoParidade.SEM_PARIDADE;
		
		EntradaCodificada entrada = semParidade.codificar("0001011010100101");
		assertEquals("0001011010100101", entrada.getValor());
	}
	
	@Test
	void testCodificaParidadeMSB() {
		TipoParidade paridadeMSB = TipoParidade.PARIDADE_MSB;
		
		EntradaCodificada entrada = paridadeMSB.codificar("0001011010100101");
		assertEquals("1001011010100101", entrada.getValor());
	}
	
	@Test
	void testDecodificaParidadeMSB() {
		TipoParidade paridadeMSB = TipoParidade.PARIDADE_MSB;
		
		EntradaCodificada entrada = paridadeMSB.codificar("0001011010100101");
		assertEquals("0001011010100101", paridadeMSB.decodificar(entrada));
	}
	
	@Test
	void testCodificaParidadeMSB4() {
		TipoParidade paridadeMSB4 = TipoParidade.PARIDADE_MSB_4;
		
		EntradaCodificada entrada = paridadeMSB4.codificar("0001011010100111");
		assertEquals("1001011010100111", entrada.getValor());
	}
	
	@Test
	void testDecodificaParidadeMSB4() {
		TipoParidade paridadeMSB4 = TipoParidade.PARIDADE_MSB_4;
		
		EntradaCodificada entrada = paridadeMSB4.codificar("0001011010100111");
		assertEquals("0001011010100111", paridadeMSB4.decodificar(entrada));
	}
	
	@Test
	void testCodificaParidadeMSB8() {
		TipoParidade paridadeMSB8 = TipoParidade.PARIDADE_MSB_8;
		
		EntradaCodificada entrada = paridadeMSB8.codificar("0001011010100111");
		assertEquals("1001011010100111", entrada.getValor());
	}
	
	@Test
	void testDecodificaParidadeMSB8() {
		TipoParidade paridadeMSB8 = TipoParidade.PARIDADE_MSB_8;
		
		EntradaCodificada entrada = paridadeMSB8.codificar("0001011010100111");
		assertEquals("0001011010100111", paridadeMSB8.decodificar(entrada));
	}
	
	@Test
	void testCodificaParidadeMSB12() {
		TipoParidade paridadeMSB12 = TipoParidade.PARIDADE_MSB_12;
		
		EntradaCodificada entrada = paridadeMSB12.codificar("0001011010100111");
		assertEquals("1001011010100111", entrada.getValor());
	}
	
	@Test
	void testDecodificaParidadeMSB12() {
		TipoParidade paridadeMSB12 = TipoParidade.PARIDADE_MSB_12;
		
		EntradaCodificada entrada = paridadeMSB12.codificar("0001011010100111");
		assertEquals("0001011010100111", paridadeMSB12.decodificar(entrada));
	}
	
	@Test
	void testCodificaParidadeMSB16() {
		TipoParidade paridadeMSB16 = TipoParidade.PARIDADE_MSB_16;
		
		EntradaCodificada entrada = paridadeMSB16.codificar("0001011010100111");
		assertEquals("1001011010100101", entrada.getValor());
	}
	
	@Test
	void testDecodificaParidadeMSB16() {
		TipoParidade paridadeMSB16 = TipoParidade.PARIDADE_MSB_16;
		
		EntradaCodificada entrada = paridadeMSB16.codificar("0001011010100111");
		assertEquals("0001011010100111", paridadeMSB16.decodificar(entrada));
	}
}
