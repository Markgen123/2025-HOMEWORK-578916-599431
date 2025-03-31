package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;
public class Giocatore {
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;


	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa=new Borsa();

	}
	public void setCfu(int cfu) {
		this.cfu=cfu;
	}
	public int getCfu() {
		return this.cfu;
	}

	public void togliUnCfu() {
		this.cfu--;
	}

	public boolean hasZeroCfu() {
		return this.cfu <= 0;
	}
	public Borsa getBorsa() {
		return this.borsa;
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		return this.borsa.addAttrezzo(attrezzo);
	}
	public Attrezzo removeAttrezzo(String nomeattrezzo ) {
		return this.borsa.removeAttrezzo(nomeattrezzo);
	}
	public 	Attrezzo getAttrezzo(String nomeattrezzo) {
		return this.borsa.getAttrezzo(nomeattrezzo);
	}
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.borsa.hasAttrezzo(nomeAttrezzo);
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Giocatore con " + this.cfu + " cfu");
		s.append("\n");
		s.append(borsa);
		return s.toString();
	}
}

