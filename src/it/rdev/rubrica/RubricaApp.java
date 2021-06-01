package it.rdev.rubrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import it.rdev.rubrica.controller.RubricaController;
import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.ui.AppFrame;

public class RubricaApp {

	// Main application
	public static void main(String[] strings) {
//		new AppFrame().setVisible(true);
		
		//TEST FUNZIONALITA' AGGIUNTIVE
		
		RubricaController controller = new RubricaController();
		
		Contact con = new Contact().setName("Nuovo").setSurname("Utente");
		con.addEmail("mail1@prova.it").addEmail("mail3@prova.it");
		con.addPhoneNumber("123").addPhoneNumber("124").addPhoneNumber("125");

		
		System.out.println(con.toString());
		List<Contact> contatti = controller.getContactList();
		for(Contact c: contatti) {
			System.out.println(c.getId() + ") " + c.getName() + " " + c.getSurname() + ", email:" + c.getEmails().toString() + ", telefono:" + c.getPhoneNumbers().toString());
		}
		controller.addContact(con);
		
		contatti = controller.getContactList();
		for(Contact c: contatti) {
			System.out.println(c.getId() + ") " + c.getName() + " " + c.getSurname() + ", email:" + c.getEmails().toString() + ", telefono:" + c.getPhoneNumbers().toString());
		}
		
		con.setName("Modificato").setSurname("Test");
		
		controller.updateContact(con);
		
		contatti = controller.getContactList();
		for(Contact c: contatti) {
			System.out.println(c.getId() + ") " + c.getName() + " " + c.getSurname() + ", email:" + c.getEmails().toString() + ", telefono:" + c.getPhoneNumbers().toString());
		}
		
		controller.removeContact(con);
		
		contatti = controller.getContactList();
		for(Contact c: contatti) {
			System.out.println(c.getId() + ") " + c.getName() + " " + c.getSurname() + ", email:" + c.getEmails().toString() + ", telefono:" + c.getPhoneNumbers().toString());
		}
	}
	
}
