package es.aed.ficheros.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import es.aed.ficheros.enums.Genre;

public class IMCCalculatorModel {
	
	public IMCCalculatorModel() {
		
	}
	
	// CALCULATE
	public Float calculate(String dni, String firstname, String lastname, String genre, Integer age, Float size, Float weight) {
		return this.calculateImc(weight, size);
	}
	
	// SAVE
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
			System.out.println(e);
		}
		
	}
	
	// LOAD
	public ArrayList<Person> load() {
		ArrayList<Person> people = new ArrayList<Person>();
		
		File csvFile = new File("src/main/resources/bbdd/bdImcCalculator.data");
		
		try {
			
			BufferedReader bufferedReader = new BufferedReader(new FileReader(csvFile));
			String[] line = bufferedReader.readLine().split(",");
			while (line != null) {
				Person person = new Person(line[0], line[1], line[2], Genre.valueOf(line[3]), Integer.parseInt(line[4]), Float.parseFloat(line[5]), Float.parseFloat(line[6]), Float.parseFloat(line[7]));
				people.add(person);
				line = bufferedReader.readLine().split(",");
			}

			bufferedReader.close();
			
		} catch (Exception e) {
			// TODO: handle exception
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
