package es.aed.ficheros.entities;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "author")
@XmlType(propOrder = { "firstname", "lastname", "books" })
public class AuthorEntity {
	
	String firstname;
	String lastname;
	private List<BookEntity> books = new ArrayList<BookEntity>();	
	
	public AuthorEntity() { }
	
	public AuthorEntity(String firstname, String lastname, List<BookEntity> books) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.books = books;
	}
	
	@XmlElement(name = "firstname")
	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	@XmlElement(name = "lastname")
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	@XmlElementWrapper(name = "books")
	@XmlElement(name = "book")
	public List<BookEntity> getBooks() {
		return this.books;
	}

}
