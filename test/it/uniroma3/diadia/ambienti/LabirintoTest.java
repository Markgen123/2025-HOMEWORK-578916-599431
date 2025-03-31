package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LabirintoTest {


    private Labirinto labirinto;

    @BeforeEach
    void setUp() {
        labirinto = new Labirinto();
    }

    @Test
    void testStanzaCorrente_Atrio() {
        assertEquals("Atrio", labirinto.getStanzaCorrente().getNome());
    }

    @Test
    void testStanzaVincente_Biblioteca() {
        assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
    }

    @Test
    void testCambioStanzaCorrente() {
        Stanza nuovaStanza = new Stanza("Aula Test");
        labirinto.setStanzaCorrente(nuovaStanza);
        assertEquals("Aula Test", labirinto.getStanzaCorrente().getNome());
    }

    @Test
    void testStanzaAtrio_HaAttrezzoOsso() {
        assertTrue(labirinto.getStanzaCorrente().hasAttrezzo("osso"));
    }

    @Test
    void testAulaN10_HaAttrezzoLanterna() {
        Stanza aulaN10 = labirinto.getStanzaCorrente().getStanzaAdiacente("sud");
        assertNotNull(aulaN10);
        assertTrue(aulaN10.hasAttrezzo("lanterna"));
    }

    @Test
    void testLaboratorio_HaAttrezzoSpada() {
        Stanza laboratorio = labirinto.getStanzaCorrente().getStanzaAdiacente("ovest");
        assertNotNull(laboratorio);
        assertTrue(laboratorio.hasAttrezzo("spada"));
    }

    @Test
    void testCollegamenti_StanzaAtrio() {
        Stanza atrio = labirinto.getStanzaCorrente();
        assertNotNull(atrio.getStanzaAdiacente("nord"));
        assertNotNull(atrio.getStanzaAdiacente("est"));
        assertNotNull(atrio.getStanzaAdiacente("sud"));
        assertNotNull(atrio.getStanzaAdiacente("ovest"));
    }
}