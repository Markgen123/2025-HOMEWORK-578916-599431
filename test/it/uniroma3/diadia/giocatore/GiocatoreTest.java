package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {

	private Giocatore giocatore;

	@BeforeEach
	void setUp() {
		giocatore = new Giocatore();
	}

	@Test
	void testGetCfuIniziale() {
		assertEquals(20,giocatore.getCfu());
	}
	@Test
	void testTogliUnCfu() {
		giocatore.togliUnCfu();
		assertEquals(19,giocatore.getCfu());
	}
	@Test
	void testHasZeroCfu_False() {
		assertFalse(giocatore.hasZeroCfu());
	}
	@Test
	void testHasZeroCfu_True() {
		giocatore.setCfu(0);
		assertTrue(giocatore.hasZeroCfu());
	}


}
