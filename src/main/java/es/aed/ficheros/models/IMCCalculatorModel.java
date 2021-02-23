package es.aed.ficheros.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Stream;

import es.aed.ficheros.enums.Genre;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Alert.AlertType;

public class IMCCalculatorModel {
	
	public IMCCalculatorModel() {
		
	}
	
	public void calculateIMC(String firstname, String lastname, String genre, Integer age, Float size, Float weight) {
		
		Person person = this.createPerson(firstname, lastname, genre, age, size, weight);

		Float result = (float) person.getWeight() / (person.getSize() * person.getSize());
		
		this.displayInfoWindow(result);
		
	}
	
	public String convertGenreToString(Genre genre) {
		return genre.equals(Genre.M) ? "Masculino" : "Femenino";
	}
	
	public Genre convertGenreFromString(String genre) {
		return genre.equals("Masculino") ? Genre.M : Genre.F;
	}
	
	public void save(String firstname, String lastname, String genre, Integer age, Float size, Float weight) {
		
		String comma = ",";
		
		Person person = this.createPerson(firstname, lastname, genre, age, size, weight);
		
		ArrayList<String> personInfo = new ArrayList<String>();
		
		File csvFile = new File("src/main/resources/bbdd/bdImcCalculator.data");
		
		personInfo.add(person.getFirstname());
		personInfo.add(comma);
		personInfo.add(person.getLastname());
		personInfo.add(comma);
		personInfo.add(person.getGenre().toString());
		personInfo.add(comma);
		personInfo.add(person.getAge().toString());
		personInfo.add(comma);
		personInfo.add(person.getSize().toString());
		personInfo.add(comma);
		personInfo.add(person.getWeight().toString());
		personInfo.add("\n");
		
		try {
			
			FileWriter csvWriter = new FileWriter(csvFile, true);
						
			for (String string : personInfo) {
				csvWriter.write(string);
			}
			
			csvWriter.flush();
			csvWriter.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public ArrayList<Person> load() {
		ArrayList<Person> people = new ArrayList<Person>();
		
		File csvFile = new File("src/main/resources/bbdd/bdImcCalculator.data");
		
		try {
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
			String[] line = bufferedReader.readLine().split(",");
			while (line != null) {
				Person person = new Person(line[0], line[1], Genre.valueOf(line[2]), Integer.parseInt(line[3]), Float.parseFloat(line[4]), Float.parseFloat(line[5]));
				people.add(person);
				line = bufferedReader.readLine().split(",");
			}

			bufferedReader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return people;
	}
	
	private Person createPerson(String firstname, String lastname, String genre, Integer age, Float size, Float weight) {
		
		Person person = new Person();
		person.setFirstname(firstname);
		person.setLastname(lastname);
		person.setGenre(this.convertGenreFromString(genre));
		person.setAge(age);
		person.setSize(size);
		person.setWeight(weight);
		
		return person;
	}
	
	private void displayInfoWindow(Float result) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Resultado");
		alert.setHeaderText(null);
		alert.setContentText("Tu índice de masa corporal es: " + Math.round(result * 100.0) / 100d);
		alert.showAndWait();
	}

}
