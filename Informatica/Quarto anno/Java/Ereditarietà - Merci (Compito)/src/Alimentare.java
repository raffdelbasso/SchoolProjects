
public class Alimentare extends Merce{
	private String descrizione;
	private float peso;
	private float calorieUnitarie;
	public Alimentare(String codice, float prezzoUnitario, String descrizione, float peso, float calorieUnitarie) {
		super(codice, prezzoUnitario);
		this.descrizione = descrizione;
		this.peso = peso;
		this.calorieUnitarie = calorieUnitarie;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public float getPeso() {
		return peso;
	}
	
	public void setPeso(float peso) {
		this.peso = peso;
	}
	
	public float getCalorieUnitarie() {
		return calorieUnitarie;
	}
	
	public void setCalorieUnitarie(float calorieUnitarie) {
		this.calorieUnitarie = calorieUnitarie;
	}
	
	public float calcolaImporto(int quantita) {
		return super.calcolaImporto(quantita)*peso;
	}
}
