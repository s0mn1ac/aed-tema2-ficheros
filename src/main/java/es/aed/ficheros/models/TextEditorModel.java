package es.aed.ficheros.models;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.stage.DirectoryChooser;

public class TextEditorModel {
	
	public TextEditorModel() {
		
	}
	
	public ArrayList<String> openFile(String path) {
		
		File file  = new File(path);
		ArrayList<String> fileContent = new ArrayList<String>();
		
		try (Scanner sc = new Scanner(file)) {
			while (sc.hasNextLine()) {
				fileContent.add(sc.nextLine() + "\n");
			}
		} catch (FileNotFoundException x) {
			x.printStackTrace();
		}
		
		return fileContent;
		
	}
	
	public void saveFile(String path, String fileContent) {
		
		File file  = new File(path);
		
		try {
			
			FileOutputStream fileOutputStream = new FileOutputStream(file);
		    DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(fileOutputStream));
		    dataOutputStream.writeUTF(fileContent);
		    dataOutputStream.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
