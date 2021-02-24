package es.aed.ficheros.controllers;

import es.aed.ficheros.enums.Genre;
import es.aed.ficheros.exceptions.Messages;
import es.aed.ficheros.models.IMCCalculatorModel;
import es.aed.ficheros.models.Person;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class IMCCalculatorController implements Initializable {

	private IMCCalculatorModel imcCalculatorModel = new IMCCalculatorModel();
	
	private ToggleGroup genreToggleGroup = new ToggleGroup();
	
	@FXML
	private VBox imcCalculatorVBox;
	
	@FXML
	private TextField imcCalculatorDniTextField;
	
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
	private Button imcCalculatorClearButton;
	
	@FXML
	private Button imcCalculatorLoadButton;
	
	@FXML
	private TableView<Person> imcCalculatorTableView;
	
	@FXML
	private TableColumn<Person, String> dni;
	
	@FXML
	private TableColumn<Person, String> firstname;
	
	@FXML
	private TableColumn<Person, String> lastname;
	
	@FXML
	private TableColumn<Person, Genre> genre;
	
	@FXML
	private TableColumn<Person, Integer> age;
	
	@FXML
	private TableColumn<Person, Float> size;
	
	@FXML
	private TableColumn<Person, Float> weight;
	
	@FXML
	private TableColumn<Person, Float> imc;
	
	@FXML
	private void onClickCalculateButton(ActionEvent e) {

		RadioButton radioButtonValue = (RadioButton) this.genreToggleGroup.getSelectedToggle();
		
		try {
			
			String dni = this.imcCalculatorDniTextField.getText();
			String firstname = this.imcCalculatorNameTextField.getText();
			String lastname = this.imcCalculatorLastnameTextField.getText();
			String genre = radioButtonValue.getText();
			Integer age = Integer.parseInt(this.imcCalculatorAgeTextField.getText());
			Float size = Float.parseFloat(this.imcCalculatorSizeTextField.getText()) / 100;
			Float weight = Float.parseFloat(this.imcCalculatorWeightTextField.getText());
			
			Float imc = this.imcCalculatorModel.calculate(dni, firstname, lastname, genre, age, size, weight);
			this.displayAlert(Messages.TITLE_RESULT, Messages.MESSAGE_IMC_RESULT + imc.toString(), AlertType.INFORMATION);

		} catch (Exception error) {
			this.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_NO_NUMERIC_VALUE, AlertType.ERROR);
		}

	}
	
	@FXML
	private void onClickSaveButton(ActionEvent e) {
		
		RadioButton radioButtonValue = (RadioButton) this.genreToggleGroup.getSelectedToggle();
		
		try {
		
			String dni = this.imcCalculatorDniTextField.getText();
			String firstname = this.imcCalculatorNameTextField.getText();
			String lastname = this.imcCalculatorLastnameTextField.getText();
			String genre = radioButtonValue.getText();
			Integer age = Integer.parseInt(this.imcCalculatorAgeTextField.getText());
			Float size = Float.parseFloat(this.imcCalculatorSizeTextField.getText()) / 100;
			Float weight = Float.parseFloat(this.imcCalculatorWeightTextField.getText());
			
			this.imcCalculatorModel.save(dni, firstname, lastname, genre, age, size, weight);
			this.clearAll();
			
		} catch (Exception error) {
			this.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_NO_NUMERIC_VALUE, AlertType.ERROR);
		}
		
	}
	
	@FXML
	private void onClickLoadButton(ActionEvent e) {
		ArrayList<Person> people = this.imcCalculatorModel.load();
		this.imcCalculatorTableView.getItems().clear();
		this.imcCalculatorTableView.getItems().addAll(people);
	}
	
	@FXML
	private void onClickClearButton(ActionEvent e) {
		this.clearAll();
	}
	
	private void clearAll() {
		this.imcCalculatorAgeTextField.clear();
		this.imcCalculatorSizeTextField.clear();
		this.imcCalculatorWeightTextField.clear();
		this.imcCalculatorDniTextField.clear();
		this.imcCalculatorNameTextField.clear();
		this.imcCalculatorLastnameTextField.clear();
		this.imcCalculatorMRadioButton.setSelected(true);
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
			.or(imcCalculatorDniTextField.textProperty().isEmpty())
		    .or(imcCalculatorLastnameTextField.textProperty().isEmpty())
		    .or(imcCalculatorAgeTextField.textProperty().isEmpty())
		    .or(imcCalculatorAgeTextField.textProperty().isEqualTo("0"))
		    .or(imcCalculatorSizeTextField.textProperty().isEmpty())
		    .or(imcCalculatorSizeTextField.textProperty().isEqualTo("0"))
		    .or(imcCalculatorWeightTextField.textProperty().isEmpty())
		    .or(imcCalculatorWeightTextField.textProperty().isEqualTo("0"))
		);
		
		imcCalculatorSaveButton.disableProperty().bind(
				imcCalculatorNameTextField.textProperty().isEmpty()
			.or(imcCalculatorDniTextField.textProperty().isEmpty())
		    .or(imcCalculatorLastnameTextField.textProperty().isEmpty())
		    .or(imcCalculatorAgeTextField.textProperty().isEmpty())
		    .or(imcCalculatorAgeTextField.textProperty().isEqualTo("0"))
		    .or(imcCalculatorSizeTextField.textProperty().isEmpty())
		    .or(imcCalculatorSizeTextField.textProperty().isEqualTo("0"))
		    .or(imcCalculatorWeightTextField.textProperty().isEmpty())
		    .or(imcCalculatorWeightTextField.textProperty().isEqualTo("0"))
		);

	}
	
	private void initTableColumns() {
		this.dni.setCellValueFactory(new PropertyValueFactory<Person, String>("dni"));
		this.firstname.setCellValueFactory(new PropertyValueFactory<Person, String>("firstname"));
		this.lastname.setCellValueFactory(new PropertyValueFactory<Person, String>("lastname"));
		this.genre.setCellValueFactory(new PropertyValueFactory<Person, Genre>("genre"));
		this.age.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
		this.size.setCellValueFactory(new PropertyValueFactory<Person, Float>("size"));
		this.weight.setCellValueFactory(new PropertyValueFactory<Person, Float>("weight"));
		this.imc.setCellValueFactory(new PropertyValueFactory<Person, Float>("imc"));
		
		this.dni.setStyle( "-fx-alignment: CENTER;");
		this.genre.setStyle( "-fx-alignment: CENTER;");
		this.age.setStyle( "-fx-alignment: CENTER;");
		this.size.setStyle( "-fx-alignment: CENTER;");
		this.weight.setStyle( "-fx-alignment: CENTER;");
		this.imc.setStyle( "-fx-alignment: CENTER;");
		
		this.dni.prefWidthProperty().bind(this.imcCalculatorTableView.widthProperty().multiply(0.15));
		this.firstname.prefWidthProperty().bind(this.imcCalculatorTableView.widthProperty().multiply(0.2));
		this.lastname.prefWidthProperty().bind(this.imcCalculatorTableView.widthProperty().multiply(0.2));
		this.genre.prefWidthProperty().bind(this.imcCalculatorTableView.widthProperty().multiply(0.09));
		this.age.prefWidthProperty().bind(this.imcCalculatorTableView.widthProperty().multiply(0.09));
		this.size.prefWidthProperty().bind(this.imcCalculatorTableView.widthProperty().multiply(0.09));
		this.weight.prefWidthProperty().bind(this.imcCalculatorTableView.widthProperty().multiply(0.09));
		this.imc.prefWidthProperty().bind(this.imcCalculatorTableView.widthProperty().multiply(0.09));
	}
	
	private void displayAlert(String title, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		
		this.initButtonBindings();
		this.initTableColumns();
		
		this.imcCalculatorMRadioButton.setToggleGroup(genreToggleGroup);
		this.imcCalculatorFRadioButton.setToggleGroup(genreToggleGroup);
		this.imcCalculatorMRadioButton.setSelected(true);
	}

}
