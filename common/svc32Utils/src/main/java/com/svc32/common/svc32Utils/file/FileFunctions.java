package com.svc32.common.svc32Utils.file;

import java.io.*;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileFunctions {

    public static final String defaultLogFileName = "WorkLog.txt";
    public static final String separator = System.getProperty("file.separator");

    private File file;
    private String charsetName;
    private FileInputStream fis;
    private BufferedReader bReader;

    public FileFunctions() {

    }

    public FileFunctions(File file, String csName, boolean createIfNotExist) throws IOException {
        _FileFunctions(file, csName, createIfNotExist);
    }

    public FileFunctions(File file) throws IOException {
        _FileFunctions(file, Charset.defaultCharset().name(), false);
    }

    private void _FileFunctions(File file, String csName, boolean createIfNotExist) throws IOException {
        if (!file.exists()) {
            if (createIfNotExist) {
                File parent = file.getParentFile();
                parent.mkdirs();
                file.createNewFile();
            } else
                throw new FileNotFoundException();
        }
        this.file = file;
        this.charsetName = csName;
        this.fis = new FileInputStream(this.file);
        this.bReader = new BufferedReader(new InputStreamReader(fis, this.charsetName));
    }

    public String readFileLine() throws IOException {
        String line = bReader.readLine();
        return line;
    }

    public List<String> readFileLines() throws IOException {
        List<String> res = new ArrayList();
        String line;
        while ((line = readFileLine()) != null)
            res.add(line);

        return res;
    }

    public void reopenReader() throws IOException {
        bReader.close();
        FileInputStream fis = new FileInputStream(this.file);
        this.bReader = new BufferedReader(new InputStreamReader(fis, this.charsetName));
    }

    private static String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private static String readFromInputStreamAndRevert(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringBuilder lineBuilder = new StringBuilder(line);
                resultStringBuilder.append(lineBuilder.reverse()).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    /**
     * Reads and concatenates lines from file represented by path
     * If reverse = true: each line is reversed.
     * @param   path        file location.
     * @param   reverse     boolean condition to reverse lines in the file content.
     * @return  file content as a string
     */
    public String readFileRowsFromClassPath(String path, boolean reverse) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(path);

        if (inputStream == null) {
            throw new FileNotFoundException(path);
        }

        String data;
        if (reverse)
            data = readFromInputStreamAndRevert(inputStream);
        else
            data = readFromInputStream(inputStream);
        return data;
    }

    public static List<String> readFileRows(File file) throws IOException {
        return readFileRows(file, Charset.defaultCharset());
    }


    public static List<String> readFileRows(File file, Charset cs) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }

        FileInputStream in = null;
        BufferedReader reader = null;
        ArrayList<String> fileRows = new ArrayList<String>();
        try {
            try {
                in = new FileInputStream(file);
                reader = new BufferedReader(new InputStreamReader(in, cs));
                String line = null;

                while ((line = reader.readLine()) != null) {
                    fileRows.add(line);
                    fileRows.add(separator);
                }
            } finally {
                reader.close();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fileRows;
    }

    public static void writeFile(File file, String line) {
        PrintWriter out = null;

        try {
            if (!file.exists())
                file.createNewFile();
            out = new PrintWriter(file.getAbsoluteFile());
            try {
                out.print(line);
            } finally {
                out.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Write String to the end of File
    public static void writeToFile(File file, String line) {
        FileWriter fWriter = null;

        try {
            if (!file.exists())
                file.createNewFile();
            fWriter = new FileWriter(file, true);
            fWriter.write(line + "\n");
            fWriter.flush();
            fWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String rewriteLastLine(File file, String _line) {
        String line = null;
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");

            long currentLineStart = file.length();
            long currentLineEnd = file.length();
            long currentPos = currentLineEnd;
            long lastPosInFile = currentLineStart - 1;
            long filePointer = currentLineStart - 1;
            while (true) {
                filePointer--;

                // we are at start of file so this is the first line in the file.
                if (filePointer < 0) {
                    break;
                }

                raf.seek(filePointer);
                int readByte = raf.readByte();

                // We ignore last LF in file. search back to find the previous LF.
                if (readByte == 0xA && filePointer != lastPosInFile) {
                    break;
                }
            }
            // we want to start at pointer +1 so we are after the LF we found or at 0 the start of the file.
            currentLineStart = filePointer + 1;
            currentPos = currentLineStart;
            if (filePointer >= 0)
                line = raf.readLine();
            raf.seek(currentLineStart);
            raf.writeBytes(_line);
//            raf.writeBytes(separator);
//            raf.setLength(raf.getFilePointer()); // TRUNCATE
            raf.close();

//            long length = raf.length();
//            String line=null;
//            boolean find=false;
//
//            for(long rPos=0L, wPos=0L; rPos<length; raf.seek(rPos)){
//
//                // READ
//                String str="max_files_per_process";
//                line = raf.readLine();
//                if(line.startsWith(str)){
//                    find=true;
//                }
//
//                rPos = raf.getFilePointer();
//                if (find){
//                    line = line.replace("17","30");
//                    find=false;
//                }
//                raf.seek(wPos);
//                raf.writeBytes(line + separator);
//                wPos = raf.getFilePointer();
//            }
//            raf.setLength(raf.getFilePointer()); // TRUNCATE
//            raf.close(); // duh...

        } catch (IOException x) {
            System.err.println("wrong");
        }
        return line;
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

    public static boolean isFileNameCorrect(String name) {
        Pattern pattern = Pattern.compile("([a-zA-Z]\\:)?(\\\\[a-zA-Z\\d\\.!#$%&()+,\\-=@\\[\\]^`{}~]+)+");
        Matcher matcher = pattern.matcher(name);
        boolean res = matcher.matches();
        return res;
    }

    //	public static final Pattern patt = Pattern.compile("([a-zA-Z]\\:)?(\\\\[a-zA-Z\\d]+)+");
    protected static final Pattern patt = Pattern.compile("([a-zA-Z]\\:)?(\\\\[a-zA-Z\\d\\.!#$%&()+,\\-=@\\[\\]^`{}~]+)+");
    protected static final String correctPath = "D:";
    protected static final String correctPath1 = "\\111\\222\\3:33.log";
    protected static final String correctPath2 = correctPath + correctPath1;

    protected static boolean isFileNameCorrectFind(String name) {
        Matcher matcher = patt.matcher(name);
        boolean res = matcher.find();
        return res;
    }

    protected static boolean isFileNameCorrectMatches(String name) {
        Matcher matcher = patt.matcher(name);
        boolean res = matcher.matches();
        return res;
    }

    public static void fillFileList(File file, List files) {
        if (file.isFile()) {
            files.add(file);
            if (file.getName().endsWith(".lnk")) {
                WindowsShortcut ws = null;
                try {
                    ws = new WindowsShortcut(file);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String rfn = ws.getRealFilename();
                File rfnFile = new File(rfn);
                if (rfnFile.exists())
                    files.add(rfnFile);
            }
        }
        else
            for (File f : file.listFiles())
                fillFileList(f, files);
    }

    public static String fileListToString(List<File> fList) {
        StringBuilder sb = new StringBuilder("");
        if (fList.size() > 0) {
            sb.append(fList.get(0).getAbsolutePath());
            for (int i=1; i<fList.size(); i++)
                sb.append("\n" + fList.get(i).getAbsolutePath());
        }
        return sb.toString();
    }
}
