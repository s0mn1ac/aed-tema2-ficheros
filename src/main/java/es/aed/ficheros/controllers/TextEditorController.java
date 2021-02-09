package es.aed.ficheros.controllers;

import es.aed.ficheros.models.TextEditorModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class TextEditorController implements Initializable {

	TextEditorModel textEditorModel = new TextEditorModel();

//	@FXML
//	private VBox textEditorVBox;
//
//	@FXML
//	private Button textEditorSaveButton;
//
//	@FXML
//	private Button textEditorLoadButton;
//
//	public TextEditorController() throws IOException {
//
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TextEditorView.fxml"));
//		loader.setController(this);
//		loader.load();
//
//	}
//
//	public VBox getView() {
//		return textEditorVBox;
//	}
//	
//	public void initialize(URL location, ResourceBundle resources) {
//		// TODO Auto-generated method stub
//		
//	}
	
	@FXML
	private GridPane grid;
	
	@FXML
	private Button button1;

	@FXML
	private Button button2;

	@FXML
	private Button button3;

	@FXML
	private Button button4;
	
	@FXML
	private Button button5;
	
	@FXML
	private Button button6;
	
	public TextEditorController() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TextEditorView.fxml"));
		loader.setController(this);
		loader.load();

	}

	public GridPane getView() {
		return grid;
	}

	public void initialize(URL location, ResourceBundle resources) {
		

	}

}
