package it.rdev.rubrica.model.impl.rdbms;

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
		List<Contact> contacts = new ArrayList<>();
		try {
			ResultSet rs = this.executeQuery("SELECT id, name, surname FROM " + TABLE_NAME);
			while(rs.next()) {
				contacts.add(
						new Contact()
						.setId(rs.getInt("id"))
						.setName(rs.getString("name"))
						.setSurname(rs.getString("surname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contacts;
	}

	@Override
	public boolean persist(Contact o) throws SQLException {
		
		boolean done = false;
		try {
			
			//il metodo restituisce l'ID della tupla aggiunta
			Long id = this.executeInsert("INSERT INTO " + TABLE_NAME + "(name, surname) VALUES (?, ?)", o.getName(), o.getSurname());
			
			if(id!=null) {
				System.out.println("Aggiunto contatto con ID " + id);
				o.setId(id.intValue());		//Imposto l'ID assegnato sul DB all'oggetto per eventuali modifiche ed eliminazione della tupla
				done=true;
			}
			
			
			//Utilizzo l'id per inserire email e numtelefono se ci sono
			
			if(o.getEmails()!=null && !o.getEmails().isEmpty()) {
				//aggiungo le email alla tabella con l'ID dell'utente appena aggiunto
				for(String mail: o.getEmails()) {
					
					try {	//L'aggiunta di una mail già esistente lancia un'eccezione. La catturo e continuo l'esecuzione per aggiungere eventuali mail corrette
					this.executeUpdate("INSERT INTO " + EMAIL_TABLE_NAME + "(email, ID_contatto) VALUES (?, ?)", mail, id);
					} catch (SQLIntegrityConstraintViolationException e) {
						System.out.println(e.getMessage());
					}
				}
			}
			
			if(o.getPhoneNumbers()!=null && !o.getPhoneNumbers().isEmpty()) {
				//aggiungo i numeri di telefono alla tabella con l'ID dell'utente appena aggiunto
				for(String telnum: o.getPhoneNumbers()) {
					
					try {
					this.executeUpdate("INSERT INTO " + TEL_TABLE_NAME + "(telefono, ID_contatto) VALUES (?, ?)", telnum, id);
					} catch (SQLIntegrityConstraintViolationException e) {
						System.out.println(e.getMessage());
					}
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return done;
	}

	@Override
	public boolean delete(Contact t) throws SQLException {
		boolean done = false;
		
		try {
			int del = this.executeUpdate("DELETE FROM " + TABLE_NAME + " WHERE id = ?", t.getId()); //L'ID viene impostato durante l'aggiunta
			
			if(del>0) {
				System.out.println("Eliminato ID " + t.getId());
				done=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return done;
	}

	@Override
	public boolean update(Contact t) throws SQLException {
		boolean done = false;
		
		try {
			int upd = this.executeUpdate("UPDATE " + TABLE_NAME + 
					" SET name=?, surname=? WHERE id=?", 
					t.getName(), t.getSurname(), t.getId());

			
			if(upd>0) {
				System.out.println("Aggiornato ID " + t.getId());
				done=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return done;
	}

}
