package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dictionary {
	
	//nuova classe, è il modello dell’applicazione, model implementa tutta la logica dello spell checking
	
	List<String> dizionario;
	
	public void loadDictionary(String language) {
		//permette di caricare in memoria il dizionario della lingua desiderata.

		dizionario = new ArrayList<String>();
		
		//codice per leggere le parole da file:
		try {
			FileReader fr = new FileReader("rsc/"+language+".txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while ((word = br.readLine()) != null) {
			   dizionario.add(word);
			}
			br.close();
			System.out.println("Dizionario " + language + " loaded. Found " + dizionario.size() + " words.");
			} catch (IOException e){
			System.out.println("Errore nella lettura del file");
			}
		
		
	}
	
	
	
	/*spellCheckText controlla se la parola è presente nel dizionario. 
	 * In caso affermativo, la RichWord corrispondente sarà corretta, 
	 * altrimenti sarà errata. La lista delle RichWord viene restituita in output.*/
	
	
	//1- CASO NORMALE
	public List<RichWord> spellCheckText(List<String> inputTextList){
	
		/*esegue il controllo ortografico sul testo in input (rappresentato da una lista di parole)
		e restituisce una lista di RichWord. Per ogni elemento di inputTextList, spellCheckText
		controlla se la parola è presente nel dizionario, se presente creo RichWord con true,
		 altrimenti sarà errata e la creo con false. La lista delle RichWord viene restituita in output.*/
		
		List<RichWord> listaRichWord= new LinkedList<RichWord>();
	
		
		for(String s: inputTextList) {
			if(dizionario.contains(s))
				listaRichWord.add(new RichWord(s, true)); //creo richword true perche trovata nel dizionario
			else {
				listaRichWord.add(new RichWord(s, false)); //creo richword false perche non trovata nel dizionario
			}
			
		}
		
			return listaRichWord;
		
	}
	
	
	
	/*2- caso lineare
	public List<RichWord> spellCheckText(List<String> inputTextList){
	
		
		List<RichWord> RichWord= new LinkedList<RichWord>();
	
		boolean trovato=false;
		
		for(String s: inputTextList) {
			
				for(int i=0; i<dizionario.size(); i++) {
					
				if(s.compareTo(dizionario.get(i))==0)
					trovato=true;
				}
			
			if(trovato==true)
				RichWord.add(new RichWord(s, true));
			else {
				RichWord.add(new RichWord(s, false));
			}
		
	   }
		
		return RichWord;
	}*/
	
	
	
	/*3- caso dicotomico
	public List<RichWord> spellCheckText(List<String> inputTextList){
		
		
		List<RichWord> RichWord= new LinkedList<RichWord>();
	
		
		for(String s: inputTextList) {
			
			int intermedio=dizionario.size()/2;
		
				while(dizionario.get(intermedio).compareTo(s)!=0) {
					
					if(dizionario.get(intermedio).compareTo(s)==-1) {
						for(int i=intermedio; i<=dizionario.size(); i++)
						dizionario.remove(i);
					}

					if(dizionario.get(intermedio).compareTo(s)==+1) {
						for(int i=0; i<=intermedio; i++)
						dizionario.remove(i);
					}
					
					if(dizionario.get(intermedio).compareTo(s)==0) {
						RichWord.add(new RichWord(s, true));
						break;
					}
						
					intermedio=dizionario.size()/2;
				}
				
		
			
		}
		
			return RichWord;
		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	

}
