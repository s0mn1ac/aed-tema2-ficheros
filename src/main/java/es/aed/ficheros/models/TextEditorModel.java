package es.aed.ficheros.models;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
