package it.rdev.rubrica.controller;

import java.sql.SQLException;
import java.util.List;

import it.rdev.rubrica.config.ConfigKeys;
import it.rdev.rubrica.config.Configuration;
import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;

public class RubricaController {
	
	private ContactDAO dao;
	
	public RubricaController() {
		//AGGIUNGERE DESIGN PATTERN DYNAMIC LINK
		
		if(Configuration.getInstance().getValue(ConfigKeys.PERSISTENCE_TYPE).equals("RDBMS")) {
			dao = new it.rdev.rubrica.model.impl.rdbms.ContactDAOImpl();
		} else if(Configuration.getInstance().getValue(ConfigKeys.PERSISTENCE_TYPE).equals("FILE")) {
			dao = new it.rdev.rubrica.model.impl.file.ContactDAOImpl();
		}
	}

	public List<Contact> getContactList() {
		return dao.getAll();
	}
	
	public String addContact(Contact c) {
		String view = "LIST";
		// Controlli ore lavorate
		// Controlli anagrafica
		// altri controlli
		try {
			dao.persist(c);
		} catch (SQLException e) {
			e.printStackTrace();
			view = "LIST-ERROR";
		}
		return view;
	}
	
	public boolean removeContact(Contact c) {
		try {
			dao.delete(c);
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean updateContact(Contact c) {
		try {
			dao.update(c);
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
