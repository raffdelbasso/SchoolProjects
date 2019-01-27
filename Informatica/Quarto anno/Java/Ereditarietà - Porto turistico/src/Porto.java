
public class Porto {
	private Barca[] barche;
	private final int DIM=200;
	public Porto() {
		barche = new Barca[DIM];
	}
	
	public int getDIM() {
		return DIM;
	}
	
	public boolean isValida(Barca b, int pos) {
		boolean valida=false;
		if (pos>=0 && pos <=19) {
			if (b.getLunghezza()<=10) {
				barche[pos] = b;
				valida=true;
			} else {
				valida=false;
			}
		}
		if (b.getTipologia()=='V' || b.getTipologia()=='v') {
			if (pos>50) {
				barche[pos] = b;
				valida=true;
			} else {
				valida=false;
			}
		}
		return valida;
	}
	
	public float costoSosta(int nGiorni, int pos) {
		float costo=-1;
		if (barche[pos]!=null) {
			costo = 50*nGiorni*(barche[pos].getStazza());
			if (barche[pos].getTipologia()=='V' || barche[pos].getTipologia()=='v') {
				costo-=(costo*0.1);
			}
			barche[pos]=null;
		}
		return costo;
	}
	
	public Barca info(int pos) {
		Barca b;
		if (barche[pos]==null) {
			b=null;
		} else {
			b=barche[pos];
		}
		return b;
	}
}
