package br.edu.ifce.helio.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.edu.ifce.helio.phicc.modelo.NovaMemoriaCache;
import br.edu.ifce.helio.phicc.modelo.PHICC;
import br.edu.ifce.helio.phicc.modelo.Paridade;

class TesteMemoriaCache {

	@Test
	void testNovaMemoriaCacheThrowsExceptionWhenParidadeHasMoreThanThreeInvertedBits() {
		assertThrows(RuntimeException.class, () -> new NovaMemoriaCache(Paridade.SEM_PARIDADE, 8, 4));
	}

	@Test
	void testNovaMemoriaCacheThrowsExceptionWhenPHICCWordHas32Bits() {
		assertThrows(RuntimeException.class, () -> new NovaMemoriaCache(PHICC.T40, 8, 2, 32));
	}
	
	@Test
	void testNovaMemoriaCacheThrowsExceptionWhenPHICCHasZeroErrors() {
		assertThrows(RuntimeException.class, () -> new NovaMemoriaCache(PHICC.T40, 8, 0));
	}
	
	@Test
	void testNovaMemoriaCacheCreatesWhenParidadeHasZeroErrors() {
		assertNotNull(new NovaMemoriaCache(Paridade.SEM_PARIDADE, 8, 0));
	}

}
