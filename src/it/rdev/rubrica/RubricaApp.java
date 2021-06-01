package it.rdev.rubrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import it.rdev.rubrica.controller.RubricaController;
import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.ui.AppFrame;

public class RubricaApp {

	
	private static void print(RubricaController contr) {
		
		List<Contact> contatti = contr.getContactList();
		for(Contact c: contatti) {
			System.out.println(c.getId() + ") " + c.getName() + " " + c.getSurname() + ", email:" + c.getEmails().toString() + ", telefono:" + c.getPhoneNumbers().toString());
		}
		
	}
	
	// Main application
	public static void main(String[] strings) {
//		new AppFrame().setVisible(true);
		
		
		//TEST FUNZIONALITA' AGGIUNTIVE
		
		RubricaController controller = new RubricaController();
		
//		controller.addContact(new Contact().setId(1).setName("Luca").setSurname("Pangaro"));
//		controller.getContactList();
//		
//		controller.addContact(new Contact().setId(2).setName("Danilo").setSurname("Di Nuzzo"));
//		controller.getContactList();
		
		
		
		Contact con = new Contact().setName("Nuovo").setSurname("Utente");
		con.addEmail("mail1@prova.it").addEmail("mail3@prova.it");
		con.addPhoneNumber("123").addPhoneNumber("124").addPhoneNumber("125");

		
		System.out.println(con.toString());
		
		
		print(controller);
		
		controller.addContact(con);
		
		print(controller);
		
		con.setName("Modificato").setSurname("Test");
		con.addEmail("altra@mail.it");
		con.addPhoneNumber("0");
		
		controller.updateContact(con);
		
		print(controller);
//		
//		controller.removeContact(con);
//		
//		print(controller);
	}
	
}
