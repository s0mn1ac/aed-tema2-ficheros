package es.aed.ficheros.controllers;

import es.aed.ficheros.models.IMCCalculatorModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class IMCCalculatorController implements Initializable {

	private IMCCalculatorModel imcCalculatorModel = new IMCCalculatorModel();

	@FXML
	private VBox imcCalculatorVBox;
	
	@FXML
	private TextField imcCalculatorNameTextField;
	
	@FXML
	private TextField imcCalculatorLastnameTextField;
	
	@FXML
	private TextField imcCalculatorAgeTextField;
	
	@FXML
	private TextField imcCalculatorSizeTextField;
	
	@FXML
	private TextField imcCalculatorWeightTextField;
	
	@FXML
	private RadioButton imcCalculatorMRadioButton;
	
	@FXML
	private RadioButton imcCalculatorFRadioButton;
	
	@FXML
	private Button imcCalculatorCalculateButton;
	
	@FXML
	private void onClickCalculateButton(ActionEvent e) {
		Integer age = Integer.parseInt(this.imcCalculatorAgeTextField.getText());
		Float size = Float.parseFloat(this.imcCalculatorSizeTextField.getText()) / 100;
		Float weight = Float.parseFloat(this.imcCalculatorWeightTextField.getText());
		this.imcCalculatorModel.calculateIMC(age, size, weight);
	}

	public IMCCalculatorController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/IMCCalculatorView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public VBox getView() {
		return imcCalculatorVBox;
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		this.imcCalculatorAgeTextField.setText("0");
		this.imcCalculatorSizeTextField.setText("0");
		this.imcCalculatorWeightTextField.setText("0");
	}

}
