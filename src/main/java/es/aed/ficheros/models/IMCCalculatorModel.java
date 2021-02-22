package es.aed.ficheros.models;

import es.aed.ficheros.enums.Genre;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class IMCCalculatorModel {
	
	public IMCCalculatorModel() {
		
	}
	
	public void calculateIMC(Person person) {
		
		System.out.println(person.getWeight());
		System.out.println(person.getSize());

		Float result = (float) person.getWeight() / (person.getSize() * person.getSize());
		
		this.displayInfoWindow(result);
		
	}
	
	public String convertGenreToString(Genre genre) {
		return genre.equals(Genre.M) ? "Masculino" : "Femenino";
	}
	
	public Genre convertGenreFromString(String genre) {
		return genre.equals("Masculino") ? Genre.M : Genre.F;
	}
	
	private void displayInfoWindow(Float result) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Resultado");
		alert.setHeaderText(null);
		alert.setContentText("Tu índice de masa corporal es: " + Math.round(result * 100.0) / 100d);
		alert.showAndWait();
	}

}
