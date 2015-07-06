package com.svc32.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class FileFunctions {
	
	private static BufferedReader reader;

	public static List<String> readFileRows(File file, Charset cs) throws IOException {
	    if (!file.exists()){
	        throw new FileNotFoundException(file.getName());
	    }
	    
	    ArrayList<String> fileRows = new ArrayList<String>();
	    try {
	    	try {
	    		FileInputStream in = new FileInputStream(file);
	    		reader = new BufferedReader(new InputStreamReader(in, cs));
	    		String line = null;
	    		String newline = System.getProperty("line.separator");
	    		
	    		while ((line = reader.readLine()) != null) {
	    			fileRows.add(line);
	    			fileRows.add(newline);
	    		}
	    	} finally {
	    		reader.close();
	    	}
	    	
	    } catch(IOException e) {
	    	throw new RuntimeException(e);
	    }
		return fileRows;
	}
	
	public static void writeFile(File file, String text) {
	 
	    try {
	        if(!file.exists()){
	            file.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
	        try {
	            out.print(text);
	        } finally {
	            out.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	}

}
