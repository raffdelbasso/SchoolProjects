
public class Terrestre {
	private String orientamento;
	private int nVite;
	private int vita;
	private int att;
	private int dif;
	private int col;
	private char spada;
	
	public Terrestre(String orientamento, int nVite, int vita, int att, int dif, int col, char spada) {
		this.orientamento = orientamento;
		this.nVite = nVite;
		this.vita = vita;
		this.att = att;
		this.dif = dif;
		this.col=col;
		this.spada=spada;
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
	public char getSpada() {
		return spada;
	}
	public void setSpada(char spada) {
		this.spada = spada;
	}
	
	public void attaccaMarziano(Marziano m) {
		int casuale;
		casuale = (int) (Math.random()*16);
		if (att>m.getDif()) {
			m.setVita(0);
			if (casuale < 10) {
				vita=vita-25;
			} else {
				vita=vita-50;
			}
		} else {
			if (casuale < 10) {
				m.setVita(m.getVita()-50);
				vita=vita-50;
			} else {
				m.setVita(m.getVita()-25);
				vita=vita-75;
			}
		}
	}
}