package es.aed.ficheros;

import es.aed.ficheros.controllers.IMCCalculatorController;
import es.aed.ficheros.controllers.TextEditorController;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class Ficheros extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		TextEditorController textEditorController = new TextEditorController();
		IMCCalculatorController imcCalculatorController = new IMCCalculatorController();
		
		// Construcción del TabPane
		TabPane tabPane = new TabPane();
		
		// Creación de Tabs
		tabPane.getTabs().add(new Tab("Editor de texto", textEditorController.getView()));
		tabPane.getTabs().add(new Tab("Calculadora I.M.C.", imcCalculatorController.getView()));
		
		// Construcción de escena
		Scene scene = new Scene(tabPane, 640, 640);
		primaryStage.setTitle("Acceso a ficheros");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		
		launch(args);
		
	}

}
