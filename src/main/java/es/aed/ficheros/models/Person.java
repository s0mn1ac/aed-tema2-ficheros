package es.aed.ficheros.models;

import es.aed.ficheros.enums.Genre;

public class Person {
	
	private String firstname;
	private String lastname;
	private Integer age;
	private Float size;
	private Float weight;
	private Genre genre;
	
	public Person() { }
	
	public Person(String firstname, String lastname, Integer age, Float size, Float weight, Genre genre) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.size = size;
		this.weight = weight;
		this.genre = genre;
	}
	
	public String getFirstname() {
		return this.firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return this.lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Integer getAge() {
		return this.age;
	}
	
	public void setAge(Integer age) {
		this.age = age;
	}
	
	public Float getSize() {
		return this.size;
	}
	
	public void setSize(Float size) {
		this.size = size;
	}
	
	public Float getWeight() {
		return this.weight;
	}
	
	public void setWeight(Float weight) {
		this.weight = weight;
	}
	
	public Genre getGenre() {
		return this.genre;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
}
