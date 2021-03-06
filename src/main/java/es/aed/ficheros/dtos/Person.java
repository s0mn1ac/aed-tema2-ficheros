package es.aed.ficheros.dtos;

import es.aed.ficheros.enums.Genre;

public class Person {
	
	private String dni;
	private String firstname;
	private String lastname;
	private Genre genre;
	private Integer age;
	private Float size;
	private Float weight;
	private Float imc;
	
	public Person() { }
	
	public Person(String dni, String firstname, String lastname, Genre genre, Integer age, Float size, Float weight, Float imc) {
		this.dni = dni;
		this.firstname = firstname;
		this.lastname = lastname;
		this.genre = genre;
		this.age = age;
		this.size = size;
		this.weight = weight;
		this.imc = imc;
	}
	
	public String getDni() {
		return this.dni;
	}
	
	public void setDni(String dni) {
		this.dni = dni;
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
	
	public Genre getGenre() {
		return this.genre;
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
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

	public Float getImc() {
		return this.imc;
	}
	
	public void setImc(Float imc) {
		this.imc = imc;
	}
	
}
