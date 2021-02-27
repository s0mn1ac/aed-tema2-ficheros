package es.aed.ficheros.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import es.aed.ficheros.entities.AuthorEntity;
import es.aed.ficheros.entities.BookEntity;
import es.aed.ficheros.exceptions.Alerts;
import es.aed.ficheros.exceptions.Messages;
import es.aed.ficheros.models.LibraryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewAuthorController implements Initializable {
	
	private LibraryModel libraryModel = new LibraryModel();
	
	private Alerts alert = new Alerts();

	private List<BookEntity> books;
		
	@FXML
    private VBox newAuthorView;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField pagesTextField;

    @FXML
    private Button addBokkButton;

    @FXML
    private Button addAuthorButton;

    @FXML
    private Button cancelButton;

    @FXML
    private ListView<String> booksListView;
    
    @FXML
    void onClickAddAuthor(ActionEvent event) {
    	
    	String firstname = this.firstnameTextField.getText();
    	String lastname = this.lastnameTextField.getText();    	    	
    	this.libraryModel.addNewAuthor(new AuthorEntity(firstname, lastname, this.books));
    	this.clearAndHide();
    }

    @FXML
    void onClickAddBook(ActionEvent event) {
    	
    	try {
    		
    		String title = this.titleTextField.getText();			
    		Integer pages = Integer.parseInt(this.pagesTextField.getText());
    		this.books.add(new BookEntity(title, pages));
    		this.titleTextField.clear();
    		this.pagesTextField.clear();
    		this.loadListView();
    		
		} catch (Exception error) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_NO_NUMERIC_VALUE, AlertType.ERROR);
		}
    	
    }

    @FXML
    void onClickCancelButton(ActionEvent event) {
    	this.clearAndHide();
    }

	public NewAuthorController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewAuthorView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	public VBox getView() {
		return newAuthorView;
	}
	
	private void clearAndHide() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		this.firstnameTextField.clear();
		this.lastnameTextField.clear();
		this.titleTextField.clear();
		this.pagesTextField.clear();
		this.booksListView.getItems().clear();
		this.books = new ArrayList<BookEntity>();
        stage.hide();
	}
	
	private void loadListView() {
		this.booksListView.getItems().clear();
		this.books.forEach((BookEntity book) -> this.booksListView.getItems().add(book.getTitle()));
	}
	
	private void initButtons() {
		addAuthorButton.disableProperty().bind(
				firstnameTextField.textProperty().isEmpty()
				.or(lastnameTextField.textProperty().isEmpty())
		);
		
		addBokkButton.disableProperty().bind(
				titleTextField.textProperty().isEmpty()
			.or(pagesTextField.textProperty().isEmpty())
		);
	}
	
	public void initialize(URL location, ResourceBundle resources) {
		this.initButtons();
		this.books = new ArrayList<BookEntity>();
	}

}
