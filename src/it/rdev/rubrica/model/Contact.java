package it.rdev.rubrica.model;

import java.util.List;
import java.util.Set;

public class Contact {

	private Integer id;
	private String name;
	private String surname;
	private Set<String> phoneNumbers;
	private Set<String> emails;
	
	
	public Contact() {}

	public String getName() {
		return name;
	}
	public Contact setName(String name) {
		this.name = name;
		return this;
	}
	public String getSurname() {
		return surname;
	}
	public Contact setSurname(String surname) {
		this.surname = surname;
		return this;
	}
	public Set<String> getPhoneNumbers() {
		return phoneNumbers;
	}
	public Contact setPhoneNumbers(Set<String> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
		return this;
	}
	public Set<String> getEmails() {
		return emails;
	}
	public Contact setEmails(Set<String> emails) {
		this.emails = emails;
		return this;
	}
	public Integer getId() {
		return id;
	}
	public Contact setId(Integer id) {
		this.id = id;
		return this;
	}
	
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", surname=" + surname + ", phoneNumbers=" + phoneNumbers
				+ ", emails=" + emails + "]";
	}
	
}
