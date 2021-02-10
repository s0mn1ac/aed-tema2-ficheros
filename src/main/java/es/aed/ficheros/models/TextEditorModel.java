package es.aed.ficheros.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

}
