package it.rdev.rubrica.model.impl.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import it.rdev.rubrica.config.ConfigKeys;
import it.rdev.rubrica.config.Configuration;

public class DataSource {

	private static DataSource ds;
	private File file;
	private FileWriter fwAppend; 	
	private BufferedWriter bwAppend;
	private FileWriter fwNew; 	
	private BufferedWriter bwNew;
	private FileReader fr;
	private BufferedReader br;
	
	public static DataSource getInstance() {
		if(ds == null) {
			ds = new DataSource();
		}
		return ds;
	}
	
//	private Connection conn;
	
	private DataSource() {
		
		try {
			
			file = new File( Configuration.getInstance().getValue(ConfigKeys.FILE_NAME) );
			if(!file.exists())
				file.createNewFile();

		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Restituisce un BufferedReader da utilizzare per leggere il file
	 * @return BufferedReader su file in config
	 */
	protected BufferedReader getReader() {
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			return br;
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Restituisce un BufferedWriter da utilizzare per scrivere il file in append
	 * @return BufferedWriter su file in config
	 */
	protected BufferedWriter getAppendWriter() {
		try {
			fwAppend = new FileWriter(file, true); //true per scrittura in append
			bwAppend = new BufferedWriter(fwAppend);
			return bwAppend;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Restituisce un BufferedWriter da utilizzare per scrivere il file dall'inizio
	 * @return BufferedWriter su file in config
	 */
	protected BufferedWriter getTopWriter() {
		
		try {
			fwNew = new FileWriter(file);
			bwNew = new BufferedWriter(fwNew);
			return bwNew;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public Connection getConnection() {
//		return conn;
//	}
	
	@Override
	protected void finalize() {
//		if( conn != null ) {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
		
		
	}

}
