
public class Marziano {
	private String orientamento;
	private int nVite;
	private int vita;
	private int att;
	private int dif;
	private int col;
	private char ready;
	public Marziano(String orientamento, int nVite, int vita, int att, int dif, int col, char ready) {
		this.orientamento = orientamento;
		this.nVite = nVite;
		this.vita = vita;
		this.att = att;
		this.dif = dif;
		this.col=col;
		this.ready=ready;
	}
	public String getOrientamento() {
		return orientamento;
	}
	public void setOrientamento(String orientamento) {
		this.orientamento = orientamento;
	}
	public int getnVite() {
		return nVite;
	}
	public void setnVite(int nVite) {
		this.nVite = nVite;
	}
	public int getVita() {
		return vita;
	}
	public void setVita(int vita) {
		this.vita = vita;
	}
	public int getAtt() {
		return att;
	}
	public void setAtt(int att) {
		this.att = att;
	}
	public int getDif() {
		return dif;
	}
	public void setDif(int dif) {
		this.dif = dif;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public char getReady() {
		return ready;
	}
	public void setReady(char ready) {
		this.ready = ready;
	}
	
	public void attaccaTerrestre(Terrestre t) {
		int casuale;
		casuale = (int) (Math.random()*16);
		if (att>t.getDif()) {
			t.setVita(0);
			if (casuale < 10) {
				vita=vita-25;
			} else {
				vita=vita-50;
			}
		} else {
			if (casuale < 10) {
				t.setVita(t.getVita()-50);
				vita=vita-50;
			} else {
				t.setVita(t.getVita()-25);
				vita=vita-75;
			}
		}
	}
	
}