package it.rdev.rubrica.model.impl.file;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import it.rdev.rubrica.model.Contact;
import it.rdev.rubrica.model.ContactDAO;

public class ContactDAOImpl extends AbstractDAO<Contact> implements ContactDAO {
	
	private final String TABLE_NAME = "contacts";
	private final String EMAIL_TABLE_NAME = "email";
	private final String TEL_TABLE_NAME = "num_telefono";

	public List<Contact> getAll() {
		
//		System.out.println("getAll() da file");
		
		List<Contact> contacts = new ArrayList<>();
		
		List<String> fileValues = this.executeRead();
		
		//Test lettura
		for(String s: fileValues) {
			System.out.println(s);
			
		}
		//TODO Implementare lettura del file creazione del contatto dal file e aggiunta a contacts.

		return contacts;
	}

	@Override
	public boolean persist(Contact o) throws SQLException {
		
		boolean done = false;
		
		//TODO implementare aggiunta in append dei valori di Contact o sul file
		System.out.println("persist() da file");
		List<String> toAdd = new ArrayList<>();
		
		toAdd.add(""+o.getId());
		toAdd.add(""+o.getName());
		toAdd.add(""+o.getSurname());
		
		toAdd.add("-"); //il carattere - delimita nel file la fine del contatto
		
		this.executeWriteAppend(toAdd);

		return done;
	}

	@Override
	public boolean delete(Contact t) throws SQLException {
		boolean done = false;
		
		//TODO prelevare i record dal file e aggiungerli ad una List<Contact>, eliminare dalla list quel contatto e scrivere tutti gli altri su file dall'inizio
		
//		try {
//			int del = this.executeUpdate("DELETE FROM " + TABLE_NAME + " WHERE id = ?", t.getId()); //L'ID viene impostato durante l'aggiunta
//			
//			if(del>0) {
//				System.out.println("Eliminato ID " + t.getId());
//				done=true;
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		return done;
	}

	@Override
	public boolean update(Contact t) throws SQLException {
		boolean done = false;
		
		//TODO prelevare i record dal file e aggiungerli ad una List<Contact>, sostituire dalla list il contatto con t e scrivere tutto su file dall'inizio
		
		
//		try {
//			int upd = this.executeUpdate("UPDATE " + TABLE_NAME + 
//					" SET name=?, surname=? WHERE id=?", 
//					t.getName(), t.getSurname(), t.getId());
//
//			
//			if(upd>0) {
//				System.out.println("Aggiornato ID " + t.getId());
//				done=true;
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		return done;
	}

}
