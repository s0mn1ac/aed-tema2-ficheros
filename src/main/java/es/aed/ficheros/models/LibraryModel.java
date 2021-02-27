package es.aed.ficheros.models;

import java.io.File;
import java.io.FileWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import es.aed.ficheros.entities.AuthorEntity;
import es.aed.ficheros.entities.LibraryEntity;
import es.aed.ficheros.exceptions.Alerts;
import es.aed.ficheros.exceptions.Messages;
import javafx.scene.control.Alert.AlertType;

public class LibraryModel {
	
	public LibraryModel() { }
	
	private Alerts alert = new Alerts();
	
	public void addNewAuthor(AuthorEntity authorEntity) {
		
		try {
    		LibraryEntity libraryEntity = this.getLibraryData();
    		libraryEntity.getAuthor().add(authorEntity);
    		JAXBContext context = JAXBContext.newInstance(LibraryEntity.class);
    		Marshaller marshaller = context.createMarshaller();
    		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    		marshaller.marshal(libraryEntity, new FileWriter("src/main/resources/bbdd/bdLibrary.data"));
    			
		} catch (Exception error) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_SAVE_AUTHOR, AlertType.ERROR);
		}
		
	}
	
	public LibraryEntity getLibraryData() {

		LibraryEntity libraryEntity = new LibraryEntity();
		
		try {
			
			JAXBContext context = JAXBContext.newInstance(LibraryEntity.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			libraryEntity =  (LibraryEntity) unmarshaller.unmarshal(new File("src/main/resources/bbdd/bdLibrary.data"));
			
		} catch (Exception error) {
			this.alert.displayAlert(Messages.TITLE_ERROR, Messages.ERROR_LOAD_LIBRARY, AlertType.ERROR);
		}
		
		return libraryEntity;
		
	}

}
