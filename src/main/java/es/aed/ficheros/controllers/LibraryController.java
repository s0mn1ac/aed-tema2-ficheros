package es.aed.ficheros.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import es.aed.ficheros.entities.AuthorEntity;
import es.aed.ficheros.entities.BookEntity;
import es.aed.ficheros.entities.LibraryEntity;
import es.aed.ficheros.models.LibraryModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LibraryController implements Initializable {
	
	private LibraryModel libraryModel = new LibraryModel();
	
	private NewAuthorController newAuthorController = new NewAuthorController();
	
	private Stage newAuthorStage;
	
	private Scene newAuthorScene;
	
	@FXML
	VBox librarySceneVbox;
	
	@FXML
	Button libraryAddButton;
	
	@FXML
	TableView<BookEntity> libraryTableView;
	
	@FXML
	TableColumn<BookEntity, String> title;
	
	@FXML
	TableColumn<BookEntity, Integer> pages;
	
	@FXML
	public void onClickAddButton(ActionEvent e) {
		this.openNewAuthorWindow();
	}
	
	public LibraryController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/LibraryView.fxml"));
		loader.setController(this);
		loader.load();
	}
	
	public VBox getView() {
		return librarySceneVbox;
	}
	
	private void initTableColumns() {
		this.title.setCellValueFactory(new PropertyValueFactory<BookEntity, String>("title"));
		this.title.setStyle( "-fx-alignment: CENTER;");
		this.title.prefWidthProperty().bind(this.libraryTableView.widthProperty().multiply(0.8));
		this.pages.setCellValueFactory(new PropertyValueFactory<BookEntity, Integer>("pages"));
		this.pages.setStyle( "-fx-alignment: CENTER;");
		this.pages.prefWidthProperty().bind(this.libraryTableView.widthProperty().multiply(0.2));	
	}

	public void initialize(URL location, ResourceBundle resources) {
		this.initTableColumns();
		this.createNewAuthorWindow();
		this.loadTable();
	}
	
	private void loadTable() {
		LibraryEntity libraryEntity = this.libraryModel.getLibraryData();
		this.libraryTableView.getItems().clear();
		libraryEntity.getAuthor().forEach((AuthorEntity authorEntity) -> {
			authorEntity.getBooks().forEach((BookEntity bookEntity) -> this.libraryTableView.getItems().add(bookEntity));
		});
	}
	
	private void createNewAuthorWindow() {
		this.newAuthorStage = new Stage();
		this.newAuthorScene = new Scene(this.newAuthorController.getView(), 640, 640);
		this.newAuthorStage.setTitle("Crear autor");
		this.newAuthorStage.setScene(this.newAuthorScene);
		this.newAuthorStage.setOnHiding(event -> this.loadTable());
	}
	
	private void openNewAuthorWindow() {
		this.newAuthorStage.show();
	}

}
