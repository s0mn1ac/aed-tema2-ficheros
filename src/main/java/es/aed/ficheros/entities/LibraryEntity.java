package es.aed.ficheros.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "library")
@XmlType(propOrder = { "author" })
public class LibraryEntity {
	
	private List<AuthorEntity> author = new ArrayList<AuthorEntity>();	
	
	public LibraryEntity() { }
	
	public LibraryEntity(List<AuthorEntity> author) {
		this.author = author;
	}
	
	@XmlElement(name = "author")
	public List<AuthorEntity> getAuthor() {
		return this.author;
	}
	
	public void setAuthor(List<AuthorEntity> author) {
		this.author = author;
	}

}
