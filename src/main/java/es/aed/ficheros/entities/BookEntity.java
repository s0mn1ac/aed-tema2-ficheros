package es.aed.ficheros.entities;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "book")
@XmlType(propOrder = { "title", "pages" })
public class BookEntity {
	
	private String title;
	private Integer pages;

	public BookEntity() { }
	
	public BookEntity(String title, Integer pages) {
		this.title = title;
		this.pages = pages;
	}
	
	@XmlElement(name = "title")
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@XmlElement(name = "pages")
	public Integer getPages() {
		return this.pages;
	}
	
	public void setPages(Integer pages) {
		this.pages = pages;
	}

}
