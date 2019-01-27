
public class Anagrafica {
	private String nome;
	private String cognome;
	private String email;
	private boolean registrata;
	public Anagrafica(String nome, String cognome, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		if (email==null) {
			registrata=false;
		} else {
			registrata=true;
		}
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isRegistrata() {
		return registrata;
	}

	public void setRegistrata(boolean registrata) {
		this.registrata = registrata;
	}

	public void registraEmail(String p_email) {
		email = p_email;
		registrata = true;
	}
	public String stampaDati() {
		String msg;
		msg = "Nome: "+nome+"\n";
		msg += "Cognome: "+cognome+"\n";
		if (registrata) {
			msg += "Email: "+email;
		} else {
			msg += "Email non registrata.";
		}
		return msg+"\n";
	}
}
