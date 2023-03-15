package br.edu.ifce.helio.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.edu.ifce.helio.phicc.implementacao.EntradaCodificada;
import br.edu.ifce.helio.phicc.implementacao.EntradaCodificadaLinear;
import br.edu.ifce.helio.phicc.implementacao.EntradaCodificadaMatricial;

class TesteEntradaCodificadaLinear {

	private static EntradaCodificadaLinear entradaLinear;

	@BeforeAll
	static void setupTest() {
		entradaLinear = new EntradaCodificadaLinear("1101100010110001");
	}

	@Test
	void testGetValor() {
		String valorEsperado = "1101100010110001";
		assertEquals(valorEsperado, entradaLinear.getValor());
	}

	@Test
	void testSetValor() {
		String valorEsperado = "1000100110101011";
		entradaLinear.setValor(valorEsperado);
		assertEquals(valorEsperado, entradaLinear.getValor());
	}

	@Test
	void testThrowsRuntimeExceptionWhenSettingWrongType() {
		assertThrows(RuntimeException.class, () -> entradaLinear.setValor(null));
	}

	@Test
	void testInserirErro() {
		String valorInesperado = "1101100010110001";
		entradaLinear.setValor(valorInesperado);
		entradaLinear.inserirErro(3);
		assertNotSame(valorInesperado, entradaLinear.getValor());
	}
	
	@Test
	void testEquals() {
		String valorIgual = "1101100010110001";
		entradaLinear.setValor(valorIgual);
		EntradaCodificadaLinear novaEntrada = new EntradaCodificadaLinear(valorIgual);
		assertEquals(novaEntrada, entradaLinear);
	}
	
	@Test
	void testNotEqualValor() {
		String valorIgual = "1101100010110001";
		entradaLinear.setValor(valorIgual);
		EntradaCodificada novaEntrada = new EntradaCodificadaLinear("1101100010110000");
		assertNotSame(novaEntrada, entradaLinear);
	}
	
	@Test
	void testNotEqualType() {
		String valorIgual = "1101100010110001";
		entradaLinear.setValor(valorIgual);
		EntradaCodificada novaEntrada = new EntradaCodificadaMatricial(new String[][] { {"1", "1", "0", "1"}, {"1", "1", "0", "0"} });
		assertNotSame(novaEntrada, entradaLinear);
	}
}
