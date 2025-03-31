package it.uniroma3.diadia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ambienti.Stanza;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.*;
/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

	private Partita partita;
	private IOConsole ioconsole;
	private Giocatore giocatore;
	private int cfu;

	public DiaDia() {
		this.partita = new Partita();
		this.giocatore=new Giocatore();
	}

	public void gioca() {
		String istruzione; 
		this.ioconsole = new IOConsole();

		this.ioconsole.mostraMessaggio(MESSAGGIO_BENVENUTO);

		do		
			istruzione = this.ioconsole.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} 
		else if (comandoDaEseguire.getNome().equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
		}

		else if  (comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		}

		else if  (comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		}
		
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto(); 
		else
			this.ioconsole.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.ioconsole.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			this.ioconsole.mostraMessaggio(elencoComandi[i]+" ");
		this.ioconsole.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.ioconsole.mostraMessaggio("Dove vuoi andare?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			this.ioconsole.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getCfu();
			this.partita.setCfu(cfu--);
		}
		this.ioconsole.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.ioconsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}

	private void prendi(String nomeAttrezzo) {
		Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		if (attrezzo != null) {
			if (this.giocatore.addAttrezzo(attrezzo)) {
				this.partita.getStanzaCorrente().removeAttrezzo(attrezzo);
				this.ioconsole.mostraMessaggio("Hai preso l'attrezzo: " + nomeAttrezzo);
			} else {
				this.ioconsole.mostraMessaggio("La borsa è piena, non puoi prendere l'attrezzo.");
			}
		} else if(nomeAttrezzo != null){
			this.ioconsole.mostraMessaggio("Non c'è nessun attrezzo con il nome " + nomeAttrezzo + " nella stanza.");
		}
		else {
			this.ioconsole.mostraMessaggio("Seleziona un attrezzo!");
		}
	}

	private void posa(String nomeAttrezzo) {
		// Controlla se l'attrezzo è nella borsa del giocatore
		if (this.giocatore.hasAttrezzo(nomeAttrezzo)) {
			// Rimuovi l'attrezzo dalla borsa e aggiungilo alla stanza
			Attrezzo attrezzo = this.giocatore.removeAttrezzo(nomeAttrezzo);
			this.partita.getStanzaCorrente().addAttrezzo(attrezzo);
			this.ioconsole.mostraMessaggio("Hai posato l'attrezzo: " + nomeAttrezzo);
		} else {
			this.ioconsole.mostraMessaggio("Non hai questo attrezzo nella tua borsa.");
		}
	}


}