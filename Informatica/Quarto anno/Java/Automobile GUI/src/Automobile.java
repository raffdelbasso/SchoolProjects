
public class Automobile {
	private int v1, v2, v3, velMax, benzina;
	private char mod;
	private boolean cambio=false;
	public Automobile() {
		benzina=100;
		v1=0;
		v2=0;
		v3=0;
		velMax=80;
		mod='C';
	}
	public int getV1() {
		return v1;
	}
	
	public int getBenzina() {
		return benzina;
	}
	public void setBenzina(int benzina) {
		this.benzina = benzina;
	}
	public void setV1(int v1) {
		this.v1 = v1;
	}
	public int getV2() {
		return v2;
	}
	public void setV2(int v2) {
		this.v2 = v2;
	}
	public int getV3() {
		return v3;
	}
	public void setV3(int v3) {
		this.v3 = v3;
	}
	
	public boolean isCambio() {
		return cambio;
	}
	public void setCambio(boolean cambio) {
		this.cambio = cambio;
	}
	public int getVelMax() {
		return velMax;
	}
	public void setVelMax(int velMax) {
		this.velMax = velMax;
	}
	public char getMod() {
		return mod;
	}
	public void setMod(char mod) {
		this.mod = mod;
	}
	public boolean controlloVel() {
		switch (mod) {
		case 'C':
			if (v1>=0) {
				if (v1>=1||v2>=8) {
					return false;
				}
			}
			break;
		case 'E':
			if (v1>=1) {
				if (v1>=2||v2>=8) {
					return false;
				}
			}
			break;
		case 'S':
			if (v1==4) {
				return false;
			}
		}
		return true;
	}

	public void accelera() {
		switch(mod) {
		case 'E':
			v3+=1;
			break;
		case 'C':
			v3+=3;
			break;
		case 'S':
			v2+=1;
			break;
		}
		if (v3>=10) {
			v3-=10;
			v2++;
		}
		if (v2>=10) {
			v2-=10;
			v1++;
		}
	}
	public void frena() {
		int p=v3;
		int p2=v2;
		v3-=5;
		if (v3<0) {
			if (v2!=0 || v1!=0) {
				v3=(10+p)-5;
				v2--;
			} else {
				v3=0;
				v2=0;
				v1=0;
			}
			if (v2<0) {
				v1--;
				v2=(10+p2)-1;
			}
		}
	}
}
