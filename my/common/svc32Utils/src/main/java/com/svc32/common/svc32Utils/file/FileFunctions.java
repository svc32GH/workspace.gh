package com.svc32.common.svc32Utils.file;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFunctions {

	public static final String defaultLogFileName = "WorkLog.txt";
	public static final String separator = System.getProperty("file.separator");
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
			if(!file.exists())
				file.createNewFile();
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
//		Writer wr = null;
		BufferedWriter wr = null;
		try {
			out = new FileOutputStream(propertyFile);
			wr = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
//			properties.store(out, null);
//			properties.store(wr, null);
			properties.store(wr, null);
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
			if (wr != null) {
				try {
					wr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// %[argument_index$][flags][width][.precision]conversion
	// %[argument_index$][flags][width]conversion
	// %[flags][width]conversion
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

	public static File getLogFile(String logFilePath) {

		if (logFilePath == null)
			return getDefaultLogFile();
		else if (logFilePath.length() == 0)
			return getDefaultLogFile();
		else if (!isFileNameCorrect(logFilePath))
			return getDefaultLogFile();
		else {
			File logFile = new File(logFilePath);
			try {
				if (hasExtension(logFile)) {
					File parentDir = logFile.getParentFile();
					parentDir.mkdirs();
					logFile.createNewFile();
					return logFile;
				} else {
					logFile.mkdirs();
					File logFIleDefaultName = new File(logFile.getAbsolutePath()
							+ separator
							+ defaultLogFileName);
					logFIleDefaultName.createNewFile();
					return logFIleDefaultName;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return null;
	}

	private static File getDefaultLogFile() {
		File file = new File(System.getProperty("user.home")
				+ separator
				+ "WorkLog.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	public static boolean hasExtension(File file) {
		return file.getName().contains(".");
	}

	public static boolean isFileNameCorrect(String name){
		Pattern pattern = Pattern.compile("([a-zA-Z]\\:)?(\\\\[a-zA-Z\\d\\.!#$%&()+,\\-=@\\[\\]^`{}~]+)+");
		Matcher matcher = pattern.matcher(name);
		boolean res = matcher.matches();
		return res;
	}

	//	public static final Pattern patt = Pattern.compile("([a-zA-Z]\\:)?(\\\\[a-zA-Z\\d]+)+");
	protected static final Pattern patt = Pattern.compile("([a-zA-Z]\\:)?(\\\\[a-zA-Z\\d\\.!#$%&()+,\\-=@\\[\\]^`{}~]+)+");
	protected static final String correctPath  = "D:";
	protected static final String correctPath1 = "\\111\\222\\3:33.log";
	protected static final String correctPath2 = correctPath + correctPath1;

	protected static boolean isFileNameCorrectFind(String name){
		Matcher matcher = patt.matcher(name);
		boolean res = matcher.find();
		return res;
	}

	protected static boolean isFileNameCorrectMatches(String name){
		Matcher matcher = patt.matcher(name);
		boolean res = matcher.matches();
		return res;
	}

}
