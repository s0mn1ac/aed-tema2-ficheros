package es.aed.ficheros.controllers;

import es.aed.ficheros.models.TextEditorModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TextEditorController implements Initializable {

	TextEditorModel textEditorModel = new TextEditorModel();

	@FXML
	private VBox textEditorVBox;
	
	@FXML
	private HBox textEditorHBox;
	
	@FXML
	private TextArea textEditorTextArea;

	@FXML
	private Button textEditorSaveButton;

	@FXML
	private Button textEditorLoadButton;

	public TextEditorController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TextEditorView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public VBox getView() {
		return textEditorVBox;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
