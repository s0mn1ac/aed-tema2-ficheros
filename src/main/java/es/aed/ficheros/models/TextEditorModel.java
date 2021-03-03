package es.aed.ficheros.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;

import javafx.stage.DirectoryChooser;

public class TextEditorModel {
	
	public TextEditorModel() {
		
	}
	
	public String openFile(String path) {
		
		String currentLine;
		String fileContent = "";
		
		try {
			File file  = new File(path);
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			while ((currentLine = bufferedReader.readLine()) != null) {
				fileContent += currentLine;
			}
			bufferedReader.close();
		} catch (FileNotFoundException x) {
			x.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return fileContent;
		
	}
	
	public void saveFile(String path, String fileContent) {
		
		try {
			File file  = new File(path);
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
			bufferedWriter.write(fileContent);
			bufferedWriter.close();			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String selectDirectory(String fileName) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Seleccionar directorio");
		File selectedDirectory = directoryChooser.showDialog(null);
		if (selectedDirectory != null) {				
			Path directoryPath = selectedDirectory.toPath();
			return directoryPath.toString() + "/" + fileName;
		} else {
			return null;
		}
	}

}
