package com.svc32.xml.main.com.svc32.xml;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class Xslt20Transformer {
    private static TransformerFactory factory = TransformerFactory.newInstance();

    private static class Loader {
        static final Xslt20Transformer INSTANCE = new Xslt20Transformer();
    }

    public static Xslt20Transformer getInstance() {
        return Loader.INSTANCE;
    }

    private Xslt20Transformer(){
    }

    public static TransformerFactory getFactory() {
        return factory;
    }

    public String transform(String xml, String xsl) throws TransformerException, UnsupportedEncodingException {
        Reader rXsl = new StringReader(xsl);
        Source srcXsl = new StreamSource(rXsl);
        Transformer transformer = factory.newTransformer(srcXsl);
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        Reader rXml = new StringReader(xml);
        Source srcXml = new StreamSource(rXml);

        OutputStream os = new ByteArrayOutputStream();
        StreamResult result = new StreamResult(os);
        transformer.transform(srcXml, result);
        String res = ((ByteArrayOutputStream) os).toString("UTF-8");
        return res;
    }

}
