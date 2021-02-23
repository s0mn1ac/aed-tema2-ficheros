package es.aed.ficheros.controllers;

import es.aed.ficheros.enums.Genre;
import es.aed.ficheros.models.IMCCalculatorModel;
import es.aed.ficheros.models.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class IMCCalculatorController implements Initializable {

	private IMCCalculatorModel imcCalculatorModel = new IMCCalculatorModel();
	
	private ToggleGroup genreToggleGroup = new ToggleGroup();

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
	private Button imcCalculatorSaveButton;
	
	@FXML
	private Button imcCalculatorLoadButton;
	
	@FXML
	private ListView<String> imcCalculatorListView;
	
	@FXML
	private void onClickCalculateButton(ActionEvent e) {

		RadioButton radioButtonValue = (RadioButton) this.genreToggleGroup.getSelectedToggle();
		
		String firstname = this.imcCalculatorNameTextField.getText();
		String lastname = this.imcCalculatorLastnameTextField.getText();
		String genre = radioButtonValue.getText();
		Integer age = Integer.parseInt(this.imcCalculatorAgeTextField.getText());
		Float size = Float.parseFloat(this.imcCalculatorSizeTextField.getText()) / 100;
		Float weight = Float.parseFloat(this.imcCalculatorWeightTextField.getText());
		

		this.imcCalculatorModel.calculateIMC(firstname, lastname, genre, age, size, weight);

	}
	
	@FXML
	private void onClickSaveButton(ActionEvent e) {
		
		RadioButton radioButtonValue = (RadioButton) this.genreToggleGroup.getSelectedToggle();
		
		String firstname = this.imcCalculatorNameTextField.getText();
		String lastname = this.imcCalculatorLastnameTextField.getText();
		String genre = radioButtonValue.getText();
		Integer age = Integer.parseInt(this.imcCalculatorAgeTextField.getText());
		Float size = Float.parseFloat(this.imcCalculatorSizeTextField.getText()) / 100;
		Float weight = Float.parseFloat(this.imcCalculatorWeightTextField.getText());
		

		this.imcCalculatorModel.save(firstname, lastname, genre, age, size, weight);
		
	}
	
	@FXML
	private void onClickLoadButton(ActionEvent e) {
		ArrayList<Person> people = this.imcCalculatorModel.load();
		people.forEach((Person person) -> {
			String personToString = person.getFirstname();
			this.imcCalculatorListView.getItems().add(personToString);
		});
	}

	public IMCCalculatorController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/IMCCalculatorView.fxml"));
		loader.setController(this);
		loader.load();
	}

	public VBox getView() {
		return imcCalculatorVBox;
	}
	
	private void initButtonBindings() {
		imcCalculatorCalculateButton.disableProperty().bind(
				imcCalculatorNameTextField.textProperty().isEmpty()
		    .or(imcCalculatorLastnameTextField.textProperty().isEmpty())
		    .or(imcCalculatorAgeTextField.textProperty().isEmpty())
		    .or(imcCalculatorAgeTextField.textProperty().isEqualTo("0"))
		    .or(imcCalculatorSizeTextField.textProperty().isEmpty())
		    .or(imcCalculatorSizeTextField.textProperty().isEqualTo("0"))
		    .or(imcCalculatorWeightTextField.textProperty().isEmpty())
		    .or(imcCalculatorWeightTextField.textProperty().isEqualTo("0"))
		);
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		this.imcCalculatorAgeTextField.setText("0");
		this.imcCalculatorSizeTextField.setText("0");
		this.imcCalculatorWeightTextField.setText("0");
		
		this.initButtonBindings();
		
		imcCalculatorMRadioButton.setToggleGroup(genreToggleGroup);
		imcCalculatorFRadioButton.setToggleGroup(genreToggleGroup);
		imcCalculatorMRadioButton.setSelected(true);
	}

}
