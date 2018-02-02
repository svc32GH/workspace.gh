package com.svc32.common.svc32Utils.file;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Properties;

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
	
	public static void writeFile(File file, String line) {
	 
	    try {
	        if(!file.exists()){
	            file.createNewFile();
	        }
	 
	        PrintWriter out = new PrintWriter(file.getAbsoluteFile());
	        try {
	            out.print(line);
	        } finally {
	            out.close();
	        }
	    } catch(IOException e) {
	        throw new RuntimeException(e);
	    }
	}

	// Write String to the end of File
	public static void writeToFile(File file, String line) {
		FileWriter fWriter = null;

		try {
			fWriter = new FileWriter(file, true);
			fWriter.write(line + "\n");
			fWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Properties readProperties(String propertyPath, String charsetName) {
		File propertyFile = new File(propertyPath);
		return readProperties(propertyFile, charsetName);
	}

	public static Properties readProperties(File propertyFile, String charsetName) {
		Properties properties = new Properties();
		InputStream is = null;

		try {
			InputStream inputStream = is = new FileInputStream(propertyFile);
			properties.load(new InputStreamReader(is, charsetName));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return properties;
	}

	public static void writeProperties(Properties properties, String propertyPath) {
		File propertyFile = new File(propertyPath);
		writeProperties(properties, propertyFile);
	}

		public static void writeProperties(Properties properties, File propertyFile) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(propertyFile);
			properties.store(out, null);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String escapeUnicode(String input) {
		StringBuilder b = new StringBuilder(input.length());
		Formatter f = new Formatter(b);
		for (char c : input.toCharArray()) {
			if (c < 128) {
				b.append(c);
			} else {
				f.format("\\u%04x", (int) c);
			}
		}
		return b.toString();
	}
}
