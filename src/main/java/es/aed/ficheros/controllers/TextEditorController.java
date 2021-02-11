package es.aed.ficheros.controllers;

import es.aed.ficheros.models.TextEditorModel;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;

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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class TextEditorController implements Initializable {

	private TextEditorModel textEditorModel = new TextEditorModel();
	
	private Path pathFile;
	private String fileName;

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
		
		textEditorTextArea.clear();
		textEditorTextArea.setDisable(false);
		textEditorTextArea.requestFocus();
		textEditorDeleteButton.setDisable(false);
		textEditorSaveButton.setDisable(false);
		textEditorTextField.setDisable(false);
		
	}
	
	@FXML
	private void onClickDeleteButton(ActionEvent e) {
		
		textEditorTextArea.clear();
		textEditorTextArea.setDisable(true);
		textEditorDeleteButton.setDisable(true);
		textEditorTextField.setDisable(true);

	}
	
	@FXML
	private void onClickLoadButton(ActionEvent e) {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Seleccionar archivo");
		
		File selectedFile = fileChooser.showOpenDialog(null);
				
		if (selectedFile != null) {
			this.pathFile = selectedFile.toPath();
			this.fileName = this.pathFile.getFileName().toString();
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
		
		String newPath = this.pathFile.toString();
		String fileContent = textEditorTextArea.getText();

		if (!this.fileName.equals(textEditorTextField.getText())) {
			DirectoryChooser fileChooser = new DirectoryChooser();
			fileChooser.setTitle("Seleccionar directorio");
			File selectedDirectory = fileChooser.showDialog(null);
			if (selectedDirectory != null) {				
				this.pathFile = selectedDirectory.toPath();
				newPath = this.pathFile.toString() + "/" + textEditorTextField.getText();
				System.out.println(newPath);
			}
		}
		
		
		textEditorModel.saveFile(newPath, fileContent);
		
		textEditorTextArea.clear();
		textEditorTextArea.setDisable(true);
		textEditorDeleteButton.setDisable(true);
		textEditorSaveButton.setDisable(true);
		textEditorTextField.clear();
		this.fileName = null;
		this.pathFile = null;
		
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
