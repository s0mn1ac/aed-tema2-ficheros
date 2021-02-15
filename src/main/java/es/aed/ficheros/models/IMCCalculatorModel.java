package es.aed.ficheros.models;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class IMCCalculatorModel {
	
	public IMCCalculatorModel() {
		
	}
	
	public void calculateIMC(Integer age, Float size, Float weight) {
		
		System.out.println(weight);
		System.out.println(size);

		Float result = (float) weight / (size * size);
		
		this.displayInfoWindow(result);
		
	}
	
	private void displayInfoWindow(Float result) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Resultado");
		alert.setHeaderText(null);
		alert.setContentText("Tu índice de masa corporal es: " + Math.round(result * 100.0) / 100d);
		alert.showAndWait();
	}

}
