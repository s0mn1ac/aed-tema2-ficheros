package es.aed.ficheros.controllers;

import es.aed.ficheros.models.TextEditorModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

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
	
	@FXML
	private void onClickLoadButton(ActionEvent e) {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Seleccionar archivo");
		
		File selectedFile = fileChooser.showOpenDialog(null);
		
		Path pathFile=null;
		
		if (selectedFile != null) {
			pathFile = selectedFile.toPath();
			ArrayList<String> fileContent = (ArrayList<String>) textEditorModel.openFile(pathFile.toString());
			textEditorTextArea.clear();
			for (String line : fileContent) {
				textEditorTextArea.appendText(line);
			}
		}

	}
	
	@FXML
	private void onClickSaveButton(ActionEvent e) {
		
		
	}

	public TextEditorController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TextEditorView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public VBox getView() {
		return textEditorVBox;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		
		// TODO: Implementar
		textEditorTextArea.setWrapText(true);		
	}

}
