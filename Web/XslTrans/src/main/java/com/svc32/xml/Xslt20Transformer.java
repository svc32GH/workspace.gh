package com.svc32.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

public class Xslt20Transformer {
    private final static TransformerFactory TRANSFORMER_FACTORY = TransformerFactory.newInstance();
    private final static String INDENT_YES = "indent=\"yes\"";

    private final static DocumentBuilderFactory DOCUMENT_BUILDER_FACTORY = DocumentBuilderFactory.newInstance();
    static {
        DOCUMENT_BUILDER_FACTORY.setValidating(false);
        DOCUMENT_BUILDER_FACTORY.setNamespaceAware(true);
    }

    private class SimpleErrorHandler implements ErrorHandler {
        public void warning(SAXParseException e) throws SAXException {
            System.out.println(e.getMessage());
        }

        public void error(SAXParseException e) throws SAXException {
            System.out.println(e.getMessage());
        }

        public void fatalError(SAXParseException e) throws SAXException {
            System.out.println(e.getMessage());
        }
    }

    private static class Loader {
        static final Xslt20Transformer INSTANCE = new Xslt20Transformer();
    }

    public static Xslt20Transformer getInstance() {
        return Loader.INSTANCE;
    }

    private Xslt20Transformer() {
    }

//    public static TransformerFactory getTransformerFactory() {
//        return TRANSFORMER_FACTORY;
//    }

//    public String transform(String xml, String xsl) throws TransformerException, UnsupportedEncodingException {
//        Reader rXsl = new StringReader(xsl);
//        Source srcXsl = new StreamSource(rXsl);
//        Transformer transformer = TRANSFORMER_FACTORY.newTransformer(srcXsl);
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
//
//        Reader rXml = new StringReader(xml);
//        Source srcXml = new StreamSource(rXml);
//
//        OutputStream os = new ByteArrayOutputStream();
//        StreamResult result = new StreamResult(os);
//        transformer.transform(srcXml, result);
//        String res = ((ByteArrayOutputStream) os).toString("UTF-8");
//        return res;
//    }

    public String transform(String xml, String xsl) throws TransformerException, UnsupportedEncodingException {
        Transformer transformer = TRANSFORMER_FACTORY.newTransformer(
                new StreamSource(
                        new StringReader(xsl)
                )
        );
        String res;
        if (xsl.contains(INDENT_YES))
            res = transformFormat(xml, transformer);
        else
            res = transformUnformat(xml, transformer);
        return res;
    }

    public String transformUnformat(String xml, Transformer transformer) throws TransformerException, UnsupportedEncodingException {
        OutputStream os = new ByteArrayOutputStream();
        transformer.transform(
                new StreamSource(
                        new StringReader(xml)),
                new StreamResult(os)
        );
        String res = ((ByteArrayOutputStream) os).toString("UTF-8");
        return res;

    }

    public String transformFormat(String xml, Transformer transformer) throws TransformerException, UnsupportedEncodingException {
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        return transformUnformat(unFormatXml(xml), transformer);
    }

    public String unFormatXml(String xml) {
        String regex = "(.*)>[\\s]*<";
        String replacement = "$1><";
        return xml
                .replaceAll(regex, replacement);
    }

}
