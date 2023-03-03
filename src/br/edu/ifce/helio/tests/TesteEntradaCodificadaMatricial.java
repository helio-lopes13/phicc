package br.edu.ifce.helio.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.edu.ifce.helio.phicc.implementacao.EntradaCodificadaMatricial;

class TesteEntradaCodificadaMatricial {
	
	private static EntradaCodificadaMatricial entradaMatricial;
	
	@BeforeAll
	static void setupTest() {
		entradaMatricial = new EntradaCodificadaMatricial(new String[][] { {"1", "1", "0", "1"},
															{"1", "0", "0", "0"},
															{"1", "0", "1", "1"},
															{"0", "0", "0", "1"} });
	}

	@Test
	void testGetValor() {
		String[][] valorEsperado = new String[][] { {"1", "1", "0", "1"},
			{"1", "0", "0", "0"},
			{"1", "0", "1", "1"},
			{"0", "0", "0", "1"} };
		assertTrue(Arrays.deepEquals(valorEsperado, ((String[][]) entradaMatricial.getValor())));
	}

	@Test
	void testSetValor() {
		String[][] valorEsperado = new String[][] { {"1", "0", "0", "0"},
			{"1", "0", "0", "1"},
			{"1", "0", "1", "1"},
			{"1", "0", "1", "1"} };
		entradaMatricial.setValor(valorEsperado);
		assertEquals(valorEsperado, entradaMatricial.getValor());
	}
	
	@Test
	void testThrowsRuntimeExceptionWhenSettingWrongType() {
		assertThrows(RuntimeException.class, () -> entradaMatricial.setValor(null));
	}

	@Test
	void testInserirErro() {
		for (int i = 1; i <= 8; i++) {
			String[][] valorInesperado = new String[][] { {"1", "1", "0", "1"},
				{"1", "0", "0", "0"},
				{"1", "0", "1", "1"},
				{"0", "0", "0", "1"} };
			entradaMatricial.setValor(new String[][] { {"1", "1", "0", "1"},
				{"1", "0", "0", "0"},
				{"1", "0", "1", "1"},
				{"0", "0", "0", "1"} });
			entradaMatricial.inserirErro(i);
			assertFalse(Arrays.deepEquals(valorInesperado, ((String[][]) entradaMatricial.getValor())));
		}
	}
}
