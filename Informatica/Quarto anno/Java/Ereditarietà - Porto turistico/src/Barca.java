
public class Barca {
	private String nome;
	private char tipologia;
	private float stazza;
	private int lunghezza;
	private String nazione;
	public Barca(String nome, char tipologia, float stazza, int lunghezza, String nazione) {
		this.nome = nome;
		this.tipologia = tipologia;
		this.stazza = stazza;
		this.lunghezza = lunghezza;
		this.nazione = nazione;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public char getTipologia() {
		return tipologia;
	}
	public void setTipologia(char tipologia) {
		this.tipologia = tipologia;
	}
	public float getStazza() {
		return stazza;
	}
	public void setStazza(float stazza) {
		this.stazza = stazza;
	}
	public int getLunghezza() {
		return lunghezza;
	}
	public void setLunghezza(int lunghezza) {
		this.lunghezza = lunghezza;
	}
	public String getNazione() {
		return nazione;
	}
	public void setNazione(String nazione) {
		this.nazione = nazione;
	}
	public String toString() {
		String msg;
		msg = "Nome: "+nome;
		msg+= "\nTipologia: ";
		if (tipologia=='V' || tipologia=='v') {
			msg+= "Barca a vela";
		} else {
			msg+= "Barca a motore";
		}
		msg+= "\nStazza: "+stazza;
		msg+= "\nLunghezza: "+lunghezza+"m";
		msg+= "\nNazione: "+nazione+"\n";
		return msg;
	}
}
