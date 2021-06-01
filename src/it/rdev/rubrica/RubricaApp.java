package it.rdev.rubrica;

import java.util.ArrayList;
import java.util.List;

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
		
		List<String> mails = new ArrayList<>();
		mails.add("mail@test.it");
		mails.add("mail2@test.it");
		mails.add("mail3@test.it");
		mails.add("mail4@test.it");
		mails.add("altra@mail.it");
		con.setEmails(mails);
		
		List<String> nums = new ArrayList<>();
		nums.add("123");
		nums.add("124");
		nums.add("125");
		con.setPhoneNumbers(nums);
		
		System.out.println(con.toString());
		List<Contact> contatti = controller.getContactList();
		for(Contact c: contatti) {
			System.out.println(c.getId() + ") " + c.getName() + " " + c.getSurname());
		}
		controller.addContact(con);
		
		contatti = controller.getContactList();
		for(Contact c: contatti) {
			System.out.println(c.getId() + ") " + c.getName() + " " + c.getSurname());
		}
		
		con.setName("Modificato").setSurname("Test");
		
		controller.updateContact(con);
		
		contatti = controller.getContactList();
		for(Contact c: contatti) {
			System.out.println(c.getId() + ") " + c.getName() + " " + c.getSurname());
		}
		
//		controller.removeContact(con);
//		
//		contatti = controller.getContactList();
//		for(Contact c: contatti) {
//			System.out.println(c.getId() + ") " + c.getName() + " " + c.getSurname());
//		}
	}
	
}
