package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	private Borsa borsa;
	private Attrezzo attrezzoPesante;
	private Attrezzo attrezzoLeggero;

	@BeforeEach
	void setUp() {
		borsa = new Borsa();
		attrezzoPesante = new Attrezzo("Spada" ,10);
		attrezzoLeggero= new Attrezzo("Osso",1);
	}
	@Test
	void testAddAttrezzoSottoLimitePeso() {
		assertTrue(borsa.addAttrezzo(attrezzoLeggero));
	}
	@Test
	void testAddAttrezzoSopraLimitePeso() {
		borsa.addAttrezzo(attrezzoPesante);
		assertFalse(borsa.addAttrezzo(attrezzoLeggero));
	}
	@Test
	void testAddAttrezzoSopraNumeroMassimo() {
		for(int i=0;i<10;i++) {
			borsa.addAttrezzo(attrezzoLeggero);
		}
		assertFalse(borsa.addAttrezzo(attrezzoLeggero));
	}
	@Test
	void testGetAttrezzoEsistente() {
		borsa.addAttrezzo(attrezzoLeggero);
		assertEquals(attrezzoLeggero,borsa.getAttrezzo("Osso"));
	}
	@Test
	void testGetAttrezzoInesistente() {
		assertNull(borsa.getAttrezzo("Martello"));
	}
	@Test
	void testRemoveAttrezzoEsistente() {
		borsa.addAttrezzo(attrezzoPesante);
		assertNotNull(borsa.removeAttrezzo("Spada"));
		assertFalse(borsa.hasAttrezzo("Spada"));
	}
	@Test
	void testRemoveAttrezzoNonEsistente() {
		assertNull(borsa.removeAttrezzo("Martello"));
	}
	@Test
	void testGetPesoMax() {
		assertEquals(10,borsa.getPesoMax());
	}
	@Test 
	void testGetPeso(){
		borsa.addAttrezzo(attrezzoLeggero);
		assertEquals(1,borsa.getPeso());
	}
	@Test
	void testBorsaVuota_True() {
		assertTrue(borsa.isEmpty());
	}
	@Test
	void testBorsaVuota_False() {
		borsa.addAttrezzo(attrezzoLeggero);
		assertFalse(borsa.isEmpty());
	}
}
