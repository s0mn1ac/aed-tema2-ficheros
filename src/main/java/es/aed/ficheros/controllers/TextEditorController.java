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
import javafx.scene.web.HTMLEditor;

public class TextEditorController implements Initializable {

	private TextEditorModel textEditorModel = new TextEditorModel();
	
	private Path pathFile;
	
	private String fileName;
	
	private boolean newDocument;

	@FXML
	private VBox view;
	
	@FXML
	private HTMLEditor editorHtmlEditor;
	
	@FXML
	private TextField fileNameTextField;
	
	@FXML
	private Button newButton;
	
	@FXML
	private Button clearButton;

	@FXML
	private Button saveButton;

	@FXML
	private Button loadButton;
	
	@FXML
	private void onClickNewButton(ActionEvent e) {
		this.pathFile = null;
		this.fileName = null;
		this.newDocument = true;
		
		editorHtmlEditor.setHtmlText("<html><head></head><body contenteditable=\"true\"></body></html>");;
		editorHtmlEditor.setDisable(false);
		editorHtmlEditor.requestFocus();
		clearButton.setDisable(false);
		saveButton.setDisable(false);
		fileNameTextField.setDisable(false);
		fileNameTextField.setText("index.html");
	}
	
	@FXML
	private void onClickClearButton(ActionEvent e) {
		this.newDocument = false;
		
		editorHtmlEditor.setHtmlText("<html><head></head><body contenteditable=\"true\"></body></html>");;
		editorHtmlEditor.setDisable(true);
		clearButton.setDisable(true);
		saveButton.setDisable(true);
		fileNameTextField.setDisable(true);
		fileNameTextField.clear();
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

			String fileContent = textEditorModel.openFile(pathFile.toString());
			editorHtmlEditor.setHtmlText("<html><head></head><body contenteditable=\"true\"></body></html>");
//			System.out.println(fileContent);
			this.editorHtmlEditor.setHtmlText(fileContent);
			editorHtmlEditor.setDisable(false);
			editorHtmlEditor.requestFocus();
			clearButton.setDisable(false);
			saveButton.setDisable(false);
			fileNameTextField.setText(this.fileName);
			fileNameTextField.setDisable(false);
		}
		
	}
	
	@FXML
	private void onClickSaveButton(ActionEvent e) {
		
		String finalPath = "";
		String fileContent = editorHtmlEditor.getHtmlText();
		System.out.println(fileContent);
		
		if (this.newDocument) {
			this.fileName = this.fileNameTextField.getText();
			finalPath = this.textEditorModel.selectDirectory(this.fileName);
		} else {
			if (!this.fileName.equals(fileNameTextField.getText())) {
				finalPath = this.textEditorModel.selectDirectory(this.fileName);
			} else {
				finalPath = this.pathFile.toString();
			}
		}
		
		if (finalPath != null) {
			textEditorModel.saveFile(finalPath, fileContent);
			
			editorHtmlEditor.setHtmlText("<html><head></head><body contenteditable=\"true\"></body></html>");;
			editorHtmlEditor.setDisable(true);
			clearButton.setDisable(true);
			saveButton.setDisable(true);
			fileNameTextField.clear();
			fileNameTextField.setDisable(true);
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
		return view;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
//		textEditorTextArea.setWrapText(true);
		editorHtmlEditor.setDisable(true);
		clearButton.setDisable(true);
		saveButton.setDisable(true);
		fileNameTextField.setDisable(true);
	}

}
