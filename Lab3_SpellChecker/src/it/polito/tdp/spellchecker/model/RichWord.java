package it.polito.tdp.spellchecker.model;

/*nuova classe (Java Bean) denominata RichWord. Ogni istanza di questa classe conterrà una parola del testo in input, e
l’indicazione se tale parola è presente nel dizionario(true) o meno(false) (utilizzare un boolean)*/

public class RichWord {

	//javabean salva solo parola corrente e boolean
	//memorizzo in una sola variabile la parola e l'etichetta se giusta o sbagliato
	
	private String word;
	private boolean giusta;
	
	
	
	public RichWord(String word, boolean giusta) {
		super();
		this.word = word;
		this.giusta = giusta;
	}
	
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public boolean isGiusta() {
		return giusta;
	}
	
	
}
