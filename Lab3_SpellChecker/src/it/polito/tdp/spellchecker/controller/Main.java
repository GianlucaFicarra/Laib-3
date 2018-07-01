package it.polito.tdp.spellchecker.controller;


import it.polito.tdp.spellchecker.model.Dictionary;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


/*FUNZIONAMENTO:
 * seleziono lingua dal menu a tendina
 * nel txtArea1 inserisco le parole che voglio ricercare nel dizionario
 * nel txtArea2 stampo solo quelle non trovate
 * stampo numero di errori e tempo impiegato per trovare le parole nel dizionario*/

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loader= new FXMLLoader(getClass().getResource("SpellChecker.fxml"));
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//finestra cra controller e controller setta il modello
			Dictionary model= new Dictionary();
			((SpellCheckerController)loader.getController()).setModel(model);
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
