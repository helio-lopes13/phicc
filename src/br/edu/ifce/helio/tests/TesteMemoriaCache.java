package br.edu.ifce.helio.tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.edu.ifce.helio.phicc.modelo.NovaMemoriaCache;
import br.edu.ifce.helio.phicc.modelo.TamanhoPHICC;
import br.edu.ifce.helio.phicc.modelo.TipoParidade;

class TesteMemoriaCache {

	@Test
	void testNovaMemoriaCacheThrowsExceptionWhenParidadeHasMoreThanThreeInvertedBits() {
		assertThrows(RuntimeException.class, () -> new NovaMemoriaCache(TipoParidade.PS, 8, 4));
	}

	@Test
	void testNovaMemoriaCacheThrowsExceptionWhenPHICCWordHas32Bits() {
		assertThrows(RuntimeException.class, () -> new NovaMemoriaCache(TamanhoPHICC.T40, 8, 2, 32));
	}
	
	@Test
	void testNovaMemoriaCacheThrowsExceptionWhenPHICCHasZeroErrors() {
		assertThrows(RuntimeException.class, () -> new NovaMemoriaCache(TamanhoPHICC.T40, 8, 0));
	}
	
	@Test
	void testNovaMemoriaCacheCreatesWhenParidadeHasZeroErrors() {
		assertNotNull(new NovaMemoriaCache(TipoParidade.PS, 8, 0));
	}

}
