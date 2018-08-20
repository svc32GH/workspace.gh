package com.svc32.xml;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class Xslt20Transformer {
    private final static TransformerFactory factory = TransformerFactory.newInstance();
    private final static String INDENT_YES = "indent=\"yes\"";

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

    public String transform(String xml, String xsl) throws TransformerException, UnsupportedEncodingException {
        Transformer transformer = factory.newTransformer(new StreamSource(xsl));
        String res;
        if (xsl.contains(INDENT_YES))
            res = transformFormat(xml, transformer);
        else
            res = transformUnformat(xml, transformer);
        return res;
    }

    public String transformUnformat(String xml, Transformer transformer) throws TransformerException, UnsupportedEncodingException {

    }

    public String transformFormat(String xml, Transformer transformer) throws TransformerException, UnsupportedEncodingException {
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.transform(srcXml, result);
    }

    public String unFormatXml(String xml) {
        String regex = "(.*)>[\\s]*<";
        String replacement = "$1><";
        return xml
                .replaceAll(regex, replacement);
    }

}
