package com.svc32.xml.main;

import com.svc32.xml.Xslt20Transformer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Extracts parameter values from "multipart; form-data"
 * contained in incoming HTTP servlet requests. For further
 * details concerning multipart requests see RFC 1867.
 *
 * @author <a href="mailto:yanik@cs.bgu.ac.il">Yanik</a>
 * @version 1.0
 */
public class MainServlet extends HttpServlet {
    public static final String EOLN = "\r\n";
    public static final String EMPTY_LINE = EOLN + EOLN;
    public static final String DOULE_DASH = "--";

    protected byte[] dataBytes;
    protected StringBuffer dataString;
    protected String boundary;
    protected Vector names;
    protected Map parts, part_names;

    private Xslt20Transformer transformer;

    public MainServlet() {
        this.transformer = Xslt20Transformer.getInstance();

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.html");
        if (dispatcher != null) {
            dispatcher.forward(request, response);
        }
    }

    protected String getValue(String key, Map map) {
        String result = null;
        Object[] object = (Object[])map.get(key);
        if (object != null)
            result = (String) object[0];
        return result;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        part_names = getPartNames(request);

        String command = getValue("command", part_names);
        System.out.println(command);

//        command = getValue("command2", part_names);
//        System.out.println(command);

        String xmlStr = getValue("xml", part_names);
        String xslStr = getValue("xsl", part_names);
        String resXml = null;
        try {
            resXml = transformer.transform(xmlStr, xslStr);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        response.getWriter().append(resXml);
    }

    protected Map getPartNames(HttpServletRequest request) throws ServletException, IOException {
        if (request.getContentType().indexOf("multipart") == -1) {
            throw new ServletException("ServletException: content-type should contain \"multipart\"\n"
                    + "Instead content-type is: " +request.getContentType());
        }
        DataInputStream in = new DataInputStream(request.getInputStream());

        //get length of content data
        int formDataLength = request.getContentLength();

        //allocate a byte array to store content data
        dataBytes = new byte[formDataLength];
        //read file into byte array
        int bytesRead = 0;
        int totalBytesRead = 0;
        while(totalBytesRead<formDataLength){
            bytesRead = in.read(dataBytes,totalBytesRead,formDataLength-totalBytesRead);
            totalBytesRead += bytesRead;
        }

        dataString = new StringBuffer(new String(dataBytes));

        String contentType = request.getContentType();
        int lastIndex = contentType.lastIndexOf("=");
        boundary = contentType.substring(lastIndex+1,contentType.length());

        names = new Vector();
        parts = new HashMap();

        int start = 0;
        String find = boundary + EOLN + "Content-Disposition: form-data; name=\"";
        // initialization of parts
        while(start >= 0 && start < dataBytes.length){
            start = dataString.indexOf(find, start);
            if(start >= 0){
                int index = dataString.indexOf("\"",start + find.length());
                if(index >= 0){
                    String name = dataString.substring(start + find.length(), index);
                    names.add(name);
                    Part p = getPart(name, start);
                    if(p != null){
                        if(parts.containsKey(name)){
                            ArrayList arr = (ArrayList)parts.get(name);
                            arr.add(p);
                        }
                        else{
                            ArrayList arr = new ArrayList();
                            arr.add(p);
                            parts.put(name,arr);
                        }
                    }
                    start += find.length();
                }
            }
        }

        part_names = new HashMap();
        for(Iterator n = names.iterator(); n.hasNext();){
            String name = (String)n.next();
            String[] ret = null;
            ArrayList arr = (ArrayList)parts.get(name);
            if(arr != null && arr.size() > 0){
                ret = new String[arr.size()];
                for(int i=0;i<arr.size();i++){
                    Part p = (Part)arr.get(i);
                    if(p.getContentType().equalsIgnoreCase("text/plain") &&
                            p.getData() != null)
                        ret[i] = new String(p.getData());
                    else
                        ret[i] = null;
                }
            }
            part_names.put(name,ret);
        }
        return part_names;
    }

    /**
     * Returns all <code>Part[]</code> with some name.
     *
     * @param name a <code>String</code> value
     * @return a <code>Part[]</code> value
     */
    public Part getPart(String name) {
        ArrayList arr = (ArrayList)parts.get(name);
        if(arr != null && arr.size() > 0)
            return (Part)arr.get(0);
        return null;
    }

    /**
     * Returns all <code>Part[]</code> with some name.
     *
     * @param name a <code>String</code> value
     * @return a <code>Part[]</code> value
     */
    public Part[] getParts(String name) {
        ArrayList arr = (ArrayList)parts.get(name);
        if(arr != null && arr.size() > 0){
            Part[]ps = new Part[arr.size()];
            for(int i=0;i<arr.size();i++)
                ps[i] = (Part)arr.get(i);
            return ps;
        }
        return null;
    }

    /**
     * Getting {@link Part} with some <code>name</code> starts from
     * some index in global buffer of incomming data.
     * @param name a <code>String</code> value
     * @param start an <code>int</code> value
     * @return a <code>Part</code> value
     */
    protected Part getPart(String name, int start){
        String find = boundary+EOLN+"Content-Disposition: form-data; name=\""+name+"\"";
        int block_start = dataString.indexOf(find,start);
        if(block_start == -1)
            throw new IllegalArgumentException("Part named "+name+" not found");

        // Data block ends here:
        int block_end = dataString.indexOf(EOLN+DOULE_DASH+boundary,block_start+EOLN.length());
        if (block_end == -1)
            throw new IllegalArgumentException("Part named "+name+" not found");

        // Parameter block should contain an empty line between index and index1
        int data_start = dataString.indexOf(EMPTY_LINE,block_start) + EMPTY_LINE.length();
        if (data_start == -1 || data_start > block_end)
            throw new IllegalArgumentException("Part named "+name+" not found");


        if(data_start-block_start < 0)
            throw new IllegalArgumentException("Part named "+name+" not found");

        String parameter = dataString.substring(block_start,data_start);
        // check if has filename
        String filename = "filename=\"";
        String uploadedFileName = "";

        int index = parameter.indexOf(filename);
        if(index >= 0){
            int index1 = parameter.indexOf("\"" + EOLN, index);
            if(index1 >= 0)
                uploadedFileName = parameter.substring(index+filename.length(),index1);
        }

        String contentType = EOLN+"Content-Type: ";
        String uploadedContentType = "text/plain";
        index = parameter.indexOf(contentType);
        if(index != -1) {
            int index1 = parameter.indexOf("\n",index+contentType.length());
            if(index1 != -1)
                uploadedContentType = parameter.substring(index+contentType.length(),index1);
        }

        return new Part(uploadedFileName,uploadedContentType,data_start,block_end);
    }

    /**
     * This class is parts of some multipart
     */
    public final class Part{
        private String filename,contenttype;
        private byte[]data=null;
        private int from,to;
        /**
         * Creates a new <code>Part</code> instance that has no file name.
         * @param contenttype a content-type of part
         * @param from a start index in global data buffer
         * @param to an end index in global data buffer
         */
        public Part(String contenttype,int from,int to){
            this("",contenttype,from,to);
        }
        /**
         * Creates a new <code>Part</code> instance that has file name.
         * @param filename a file name
         * @param contenttype a content-type of part
         * @param from a start index in global data buffer
         * @param to an end index in global data buffer
         */
        public Part(String filename,String contenttype,int from,int to){
            this.filename = filename;
            this.contenttype = contenttype;
            this.from = from;
            this.to = to;
        }
        /**
         * Gets file name (if any)
         * @return a <code>String</code> value
         */
        public String getFileName(){
            return filename;
        }
        /**
         * Gets only file name (if any)
         * @return a <code>String</code> value
         */
        public String getShortFileName(){
            if(filename == null)
                return null;
            int index = filename.lastIndexOf("/");
            if (index == -1) {
                index = filename.lastIndexOf("\\");
                if (index == -1)
                    return filename;
            }
            if (index == filename.length()-1)
                return null;

            return filename.substring(index+1,filename.length());
        }
        /**
         * Gets content type
         * @return a <code>String</code> value
         */
        public String getContentType(){
            return contenttype;
        }
        /**
         * Gets binary data of part
         * @return a <code>byte[]</code> value
         */
        public byte[] getData(){
            if(!isEmpty()){
                if(data == null){
                    data = new byte[to-from];
                    System.arraycopy(dataBytes,from,data,0,to-from);
                }
            }
            return data;
        }
        /**
         * Checking if part is empty
         * @return a <code>boolean</code> value
         */
        public boolean isEmpty(){
            return from>=to;
        }
    }
}
