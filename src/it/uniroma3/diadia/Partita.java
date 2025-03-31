package it.uniroma3.diadia;
import it.uniroma3.diadia.giocatore.*;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Labirinto;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {


	private Giocatore giocatore;
	private Labirinto labirinto;
	private boolean finita;

	
	public Partita(){
		this.giocatore=new Giocatore();
		this.labirinto=new Labirinto();
		this.finita = false;
	}

   

	public Stanza getStanzaVincente() {
		return this.labirinto.StanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.labirinto.StanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.labirinto.StanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.hasZeroCfu());
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	public int getCfu() {
		return this.giocatore.getCfu();
	}

	public void setCfu(int cfu) {
		this.giocatore.setCfu(cfu);
	}	
}
