package it.rdev.rubrica.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.rdev.rubrica.config.ConfigKeys;
import it.rdev.rubrica.config.Configuration;
import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;

public class RubricaController {
	
	private ContactDAO dao;
	private static final Map<String, String> CLASS_MAP;
	
	static {
		CLASS_MAP = new HashMap<>();
		CLASS_MAP.put("FILE", "it.rdev.rubrica.model.impl.file.ContactDAOImpl");
		CLASS_MAP.put("RDBMS", "it.rdev.rubrica.model.impl.rdbms.ContactDAOImpl");
	}
	
	public RubricaController() {
		//AGGIUNGERE DESIGN PATTERN DYNAMIC LINK
		
		
		
			Class<?> clazz;
			try {
				clazz = (Class<?>) Class.forName(
						CLASS_MAP.get( 
								Configuration.getInstance().getValue(
										ConfigKeys.PERSISTENCE_TYPE) 
								)
						);
				try {
					dao = (ContactDAO) clazz.getConstructor().newInstance();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
//		
//		if(Configuration.getInstance().getValue(ConfigKeys.PERSISTENCE_TYPE).equals("RDBMS")) {
//			dao = new it.rdev.rubrica.model.impl.rdbms.ContactDAOImpl();
//		} else if(Configuration.getInstance().getValue(ConfigKeys.PERSISTENCE_TYPE).equals("FILE")) {
//			dao = new it.rdev.rubrica.model.impl.file.ContactDAOImpl();
//		}
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
