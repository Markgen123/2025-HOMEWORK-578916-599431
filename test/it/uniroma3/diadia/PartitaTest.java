package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

class PartitaTest {
	private Partita partita;
	private Stanza atrio;
	private Stanza stanzatest1;
	private Stanza stanzatest2;
	
	
	Giocatore giocatore;
	@BeforeEach
	public void setUp() {
		partita=new Partita();
		giocatore = new Giocatore();
		stanzatest1 = new Stanza("stanzaTest1");
		stanzatest2 = new Stanza("stanzaTest2");
		partita.setStanzaCorrente(stanzatest1);
	}
	@Test
	public void testStanzaCorrenteNonVincente() {
		assertFalse(partita.vinta());
	}

	@Test
	public void testVerificaStsanzaCorrenteUgualeAVincente() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(partita.vinta());
	}
	@Test
	public void testStanzaCorrenteNull() {
		partita.setStanzaCorrente(null);
		assertFalse(partita.vinta());
	}
	@Test
	public void testVerificaIsFinitaPrimaDiIniziare() {
		assertFalse(partita.isFinita());
	}
	@Test
	public void testIsFinitaEPartitaVinta() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		assertTrue(partita.isFinita());
	}
	@Test
	public void testSetFinita() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}
	@Test
	public void testVerificaGetCfu() {
		assertEquals(20,partita.getCfu());
	}
	@Test
	public void testIsFinitaCfuZero() {
		partita.setCfu(0);
		assertTrue(partita.isFinita());
	}
	@Test
	public void testPartitaNonVintaCfuZero(){
		partita.setCfu(0);
		assertFalse(partita.vinta());
	}

}