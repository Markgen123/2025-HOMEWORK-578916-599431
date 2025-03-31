package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza stanza1;
	private Stanza stanza2;
	private Attrezzo attrezzo;

	@BeforeEach
	public void setUp() {
		stanza1 = new Stanza("stanza1");
		stanza2 = new Stanza("stanza 2");
		attrezzo = new Attrezzo("attrezzoProva",20);
	}
	@Test
	public void testImpostaStanzaAdiacente_StanzaEsistente() {
		stanza1.impostaStanzaAdiacente("nord", stanza2);
		assertEquals(stanza2, stanza1.getStanzaAdiacente("nord"));
	}
	@Test
	public void testGetStanzaAdiacente_StanzaNonEsistente() {
		assertNull(stanza1.getStanzaAdiacente("sud"));
	}
	@Test
	public void testGetStanzaAdiacente_StanzaDirezioneNull() {
		assertNull(stanza1.getStanzaAdiacente(null));
	}
	@Test
	public void testAddAttrezzo() {
		assertTrue(stanza1.addAttrezzo(attrezzo));
	}
	@Test
	public void testAddAttrezzo_StanzaPiena() {
		for(int i=0;i<10;i++) {
			stanza1.addAttrezzo(attrezzo);
		}
		assertFalse(stanza1.addAttrezzo(attrezzo));
	}
	@Test
	public void testAddAttrezzo_AttrezzoNull() {
		assertFalse(stanza1.addAttrezzo(null));
	}
	@Test
	public void testHasAttrezzo_StanzaConAttrezzo() {
		stanza1.addAttrezzo(attrezzo);
		assertTrue(stanza1.hasAttrezzo("attrezzoProva"));
	}
	@Test
	public void testHasAttrezzo_StanzaVuota() {
		assertFalse(stanza1.hasAttrezzo("attrezzoProva"));
	}
	@Test
	public void testHasAttrezzo_AttrezzoDiversoDalContenuto() {
		Attrezzo attrezzo2 = new Attrezzo("attrezzoProva2",20);
		stanza1.addAttrezzo(attrezzo);
		assertFalse(stanza1.hasAttrezzo("attrezzoProva2"));
	}




}

