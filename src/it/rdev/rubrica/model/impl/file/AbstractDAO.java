package it.rdev.rubrica.model.impl.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.rdev.rubrica.model.DAO;

/**
 * 
 * Classe astratta che contiene la logica comune per le operazioni di CRUD
 * 
 * @author Danilo Di Nuzzo
 *
 * @param <T> tipo concreto su cui effettuare le operazioni di CRUD
 */
public abstract class AbstractDAO<T> implements DAO<T> {
	
	/**
	 * Restituisce un List<String> contenente ogni riga del file in ogni String della List
	 * @return List<String> contenente il file
	 */
	protected List<String> executeRead() {
		
		BufferedReader br = DataSource.getInstance().getReader();
		List<String> fileStrings = new ArrayList<>();
		
		String buffer;
		try {
			while((buffer = br.readLine()) != null) {
				fileStrings.add(buffer);
			}
		
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return fileStrings;
	}
	
	/**
	 * Inserisce le String contenute in buffer all'interno del file, una stringa per riga.
	 * Da utilizzare per aggiungere il contenuto di buffer alla fine del file 
	 * @param buffer
	 */
	protected void executeWriteAppend(List<String> buffer) {
		
		BufferedWriter bw = DataSource.getInstance().getAppendWriter();
//		System.out.println(buffer.toString());
		
		try {
			for(String s: buffer) {
//				System.out.println(s);
				bw.write(s+"\n");
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Inserisce le String contenute in buffer all'interno del file, una stringa per riga.
	 * Da utilizzare per sostituire il contenuto del file con il contenuto di buffer 
	 * @param buffer
	 */
	protected void executeWriteTop(List<String> buffer) {
		
		BufferedWriter bw = DataSource.getInstance().getTopWriter();
		System.out.println(buffer.toString());
		
		try {
			for(String s: buffer) {
				bw.write(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	protected ResultSet executeQuery(String query) throws SQLException {
//		return DataSource.getInstance().getConnection().createStatement().executeQuery(query);
//	}
//	
//	protected ResultSet executeQuery(String query, Object ... params) throws SQLException {
//		PreparedStatement ps = composePreparedStatement(query, params);
//		return ps.executeQuery();
//	}
//	
//	protected Long executeInsert(String query, Object ... params) throws SQLException {
//		PreparedStatement ps = composePreparedStatement(query, params);
//		int row = ps.executeUpdate();
//		if( row == 0 ) {
//			throw new SQLException("Nessuna riga inserita");
//		}
//		Long generatedId = null;
//		try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
//            if (generatedKeys.next()) {
//            	generatedId = generatedKeys.getLong(1);
//            } else {
//            	throw new SQLException("Errore nell'inserimento, nessun id restituito");
//            }
//        }
//		return generatedId;
//	}
//	
//	protected Integer executeUpdate(String query, Object ... params) throws SQLException {
//		return composePreparedStatement(query, params).executeUpdate();
//	}
//	
//	private PreparedStatement composePreparedStatement(String query, Object ... params) throws SQLException {
//		Connection conn = DataSource.getInstance().getConnection();
//		PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//		for(int i = 1; i <= params.length; i++) {
//			ps.setObject(i, params[i-1]);
//		}
//		return ps;
//}

}
