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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class TextEditorController implements Initializable {

	private TextEditorModel textEditorModel = new TextEditorModel();
	
	private Path pathFile;
	
	private String fileName;
	
	private boolean newDocument;

	@FXML
	private VBox textEditorVBox;
	
	@FXML
	private HBox textEditorHBox;
	
	@FXML
	private TextArea textEditorTextArea;
	
	@FXML
	private TextField textEditorTextField;
	
	@FXML
	private Button textEditorNewButton;
	
	@FXML
	private ImageView textEditorNewImage;
	
	@FXML
	private Button textEditorDeleteButton;
	
	@FXML
	private ImageView textEditorDeleteImage;

	@FXML
	private Button textEditorSaveButton;
	
	@FXML
	private ImageView textEditorSaveImage;

	@FXML
	private Button textEditorLoadButton;
	
	@FXML
	private ImageView textEditorLoadImage;
	
	@FXML
	private void onClickNewButton(ActionEvent e) {
		this.pathFile = null;
		this.fileName = null;
		this.newDocument = true;
		
		textEditorTextArea.clear();
		textEditorTextArea.setDisable(false);
		textEditorTextArea.requestFocus();
		textEditorDeleteButton.setDisable(false);
		textEditorSaveButton.setDisable(false);
		textEditorTextField.setDisable(false);
		textEditorTextField.setText("NuevoDocumentoDeTexto.txt");
	}
	
	@FXML
	private void onClickDeleteButton(ActionEvent e) {
		this.newDocument = false;
		
		textEditorTextArea.clear();
		textEditorTextArea.setDisable(true);
		textEditorDeleteButton.setDisable(true);
		textEditorSaveButton.setDisable(true);
		textEditorTextField.setDisable(true);
		textEditorTextField.clear();
	}
	
	@FXML
	private void onClickLoadButton(ActionEvent e) {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Seleccionar archivo");
		
		File selectedFile = fileChooser.showOpenDialog(null);
				
		if (selectedFile != null) {
			this.pathFile = selectedFile.toPath();
			this.fileName = this.pathFile.getFileName().toString();
			this.newDocument = false;

			ArrayList<String> fileContent = (ArrayList<String>) textEditorModel.openFile(pathFile.toString());
			textEditorTextArea.clear();
			for (String line : fileContent) {
				textEditorTextArea.appendText(line);
			}
			textEditorTextArea.setDisable(false);
			textEditorTextArea.requestFocus();
			textEditorDeleteButton.setDisable(false);
			textEditorSaveButton.setDisable(false);
			textEditorTextField.setText(this.fileName);
			textEditorTextField.setDisable(false);
		}
		
	}
	
	@FXML
	private void onClickSaveButton(ActionEvent e) {
		
		String finalPath = "";
		String fileContent = textEditorTextArea.getText();
		
		if (this.newDocument) {
			this.fileName = this.textEditorTextField.getText();
			finalPath = this.textEditorModel.selectDirectory(this.fileName);
		} else {
			if (!this.fileName.equals(textEditorTextField.getText())) {
				finalPath = this.textEditorModel.selectDirectory(this.fileName);
			} else {
				finalPath = this.pathFile.toString();
			}
		}
		
		if (finalPath != null) {
			textEditorModel.saveFile(finalPath, fileContent);
			
			textEditorTextArea.clear();
			textEditorTextArea.setDisable(true);
			textEditorDeleteButton.setDisable(true);
			textEditorSaveButton.setDisable(true);
			textEditorTextField.clear();
			textEditorTextField.setDisable(true);
			this.fileName = null;
			this.pathFile = null;
			this.newDocument = false;
		}
		
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
		textEditorTextArea.setWrapText(true);
		textEditorTextArea.setDisable(true);
		textEditorDeleteButton.setDisable(true);
		textEditorSaveButton.setDisable(true);
		textEditorTextField.setDisable(true);
	}

}
