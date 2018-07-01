package it.polito.tdp.spellchecker.controller;

/**
 * Sample Skeleton for 'SpellChecker.fxml' Controller Class
 */

import it.polito.tdp.spellchecker.model.*;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class SpellCheckerController {

	//controller si limita alla gestione dell’interfaccia e richiama i metodi definiti nel model.
	
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtFinestra"
    private ChoiceBox<String> txtFinestra; // Value injected by FXMLLoader
    /*DEVO DICHIARAE IL TIPO DI ELEMENTI NELLA CHOICE BOX*/

    @FXML // fx:id="txtArea1"
    private TextArea txtArea1; // Value injected by FXMLLoader

    @FXML // fx:id="txtButtonCheck"
    private Button txtButtonCheck; // Value injected by FXMLLoader

    @FXML // fx:id="txtArea2"
    private TextArea txtArea2; // Value injected by FXMLLoader

    @FXML // fx:id="txtErrori"
    private Text txtErrori; // Value injected by FXMLLoader

    @FXML // fx:id="txtButtonClear"
    private Button txtButtonClear; // Value injected by FXMLLoader

    @FXML // fx:id="txtSecondi"
    private Text txtSecondi; // Value injected by FXMLLoader
    
    
    private Dictionary model= new Dictionary(); //creo collegamento al model di tipo dizionario
    int cont=0;// per valutre il numero di errori
    
    
	public void setModel(Dictionary model) {
		this.model = model;
		
		//SETTO ELEMENTI NELLA CHOICE BOX IN MODO MANUALE SE POCHI
        txtFinestra.getItems().addAll("Italian","English");
        
	}
    
	
    @FXML
    void doClearText(ActionEvent event) {

    	txtArea1.clear(); //alta
    	txtArea2.clear(); //bassa
    	
    	cont=0;
    	txtErrori.setText("The text contain "+ cont +" errors");
    	txtSecondi.setText("Spell check completed in "+ 0 + " seconds");
    }


    @FXML
    void doSpellCheck(ActionEvent event) {//fai ricerca nel dizionario

    	txtArea2.clear(); //bassa dove stampo parole sbagliate, la pulisco
    	List<String> inputTextList= new LinkedList<String>(); //lista dove salvare le parole passate
    
    	if (txtFinestra.getValue() == null) { //se non ho selezionato nessuna lingua
    		txtArea2.setText("Seleziona una lingua!");
			return;
		}
    	
    	String inputText = txtArea1.getText(); //prento testo inserito nella prima finestra
		//Caso dove non ho scritto nulla
    	if (inputText.isEmpty()) {    //oppure if(inputText.length()==0 || inputText==null) 
			txtArea2.setText("Inserire un testo da correggere!");
			return;
		}
		
    	//Caso dove ho scritto parole da cercare
    	String rigo=txtArea1.getText().toLowerCase(); //lo rendo caseSensitive
    	rigo = rigo.replaceAll("\n", " ");  //per non far intendere il vai a capo un errore
    	rigo=rigo.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", ""); //elimino segni di punteggiature(.,:;!)
    	
    	String[] array=rigo.split(" "); //scompongo la riga con le parole
    	for(int i=0; i<array.length; i++){
    		inputTextList.add(array[i]); //salvo tutte le parole pasatein una lista
    	}
    	
    	//seleziono lingua e carico dizionario
    	model.loadDictionary(txtFinestra.getValue());
    	
    	//inizio la ricerca:
    	/*spellCheckText controlla se la parola è presente nel dizionario.
    	 *viene restituita la lista delle RichWord, ed avranno attributo true se
    	 *nel dizionario, false se non sono state trovate*/
    	long start=System.currentTimeMillis();
    	List<RichWord> listaRichWord=model.spellCheckText(inputTextList);
    	long finish=System.currentTimeMillis();
    	
    	
    	for(RichWord rw: listaRichWord) {
    		if(rw.isGiusta()==false) { //dato tutto l'elenco stampo a videio solo le false non trovate
    			txtArea2.appendText(rw.getWord()+"\n");
    			cont++; //aumento cont di parole sbagliate
    		}
    	}
    	
    	//MODIFICO I CAMPI TEMPO E NUMERO ERRORI:
    	
    	if(cont==1)
    		txtErrori.setText("The text contain "+ cont +" error");
    	else if(cont>1)
    		txtErrori.setText("The text contain "+ cont +" errors");
    	//txtErrori.setText(txtErrori.getText().replaceAll("-", ""+cont));
    	 
    	 txtSecondi.setText("Spell check completed in "+(finish-start)+ " seconds");
    	 //txtSecondi.setText(txtSecondi.getText().replaceAll("-", ""+(finish-start)));
    	 cont=0;
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtFinestra != null : "fx:id=\"txtFinestra\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtArea1 != null : "fx:id=\"txtArea1\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtButtonCheck != null : "fx:id=\"txtButtonCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtArea2 != null : "fx:id=\"txtArea2\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtButtonClear != null : "fx:id=\"txtButtonClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        assert txtSecondi != null : "fx:id=\"txtSecondi\" was not injected: check your FXML file 'SpellChecker.fxml'.";
        
  
        /*se usassi property
        txtSecondi.setText(txtSecondi.getText().replaceAll("-", ""));
        txtErrori.setText(txtErrori.getText().replaceAll("-", ""+cont));*/

    }

    
    

}

	
