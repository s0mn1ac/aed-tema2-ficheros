package es.aed.ficheros.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import es.aed.ficheros.dtos.Person;
import es.aed.ficheros.enums.Genre;
import es.aed.ficheros.exceptions.Alerts;
import es.aed.ficheros.exceptions.Messages;
import javafx.scene.control.Alert.AlertType;

public class IMCCalculatorModel {
	
	public IMCCalculatorModel() { }
	
	private Alerts alert = new Alerts();
	
	// CALCULATE
	// Calcula y devuelve el IMC en formato Float
	public Float calculate(String dni, String firstname, String lastname, String genre, Integer age, Float size, Float weight) {
		return this.calculateImc(weight, size);
	}
	
	// SAVE
	// Guarda una persona en una base de datos en formato csv
	public void save(String dni, String firstname, String lastname, String genre, Integer age, Float size, Float weight) {
		
		String comma = ",";

		Person person = new Person(dni, firstname, lastname, this.convertGenreFromString(genre), age, size, weight, this.calculateImc(weight, size));
		
		ArrayList<String> personInfo = new ArrayList<String>();
		
		File csvFile = new File("src/main/resources/bbdd/bdImcCalculator.data");
		
		personInfo.add(person.getDni());
		personInfo.add(comma);
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
		personInfo.add(comma);
		personInfo.add(person.getImc().toString());
		personInfo.add("\n");
		
		try {
			
			FileWriter csvWriter = new FileWriter(csvFile, true);
						
			for (String string : personInfo) {
				csvWriter.write(string);
			}
			
			csvWriter.flush();
			csvWriter.close();
			
		} catch (Exception e) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_SAVE_PERSON, AlertType.ERROR);
		}
		
	}
	
	// LOAD
	// Carga una persona desde una base de datos en formato csv
	public ArrayList<Person> load() {
		ArrayList<Person> people = new ArrayList<Person>();
		
		File csvFile = new File("src/main/resources/bbdd/bdImcCalculator.data");
		
		try {
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
			String line = bufferedReader.readLine();
			while (line != null) {
				String[] parsedLine = line.split(",");
				Person person = new Person(parsedLine[0], parsedLine[1], parsedLine[2], Genre.valueOf(parsedLine[3]), Integer.parseInt(parsedLine[4]), Float.parseFloat(parsedLine[5]), Float.parseFloat(parsedLine[6]), Float.parseFloat(parsedLine[7]));
				people.add(person);
				line = bufferedReader.readLine();
			}

			bufferedReader.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_LOAD_PERSON, AlertType.ERROR);
		}
		
		return people;
	}
	
	public String convertGenreToString(Genre genre) {
		return genre.equals(Genre.M) ? "Masculino" : "Femenino";
	}
	
	public Genre convertGenreFromString(String genre) {
		return genre.equals("Masculino") ? Genre.M : Genre.F;
	}
	
	private float calculateImc(Float weight, Float size) {
		return (float) (Math.round((weight / (size * size)) * 100.0) / 100d);
	}
	
}
