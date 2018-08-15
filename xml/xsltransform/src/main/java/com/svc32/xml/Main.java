package com.svc32.xml;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {

    public static final String SOURCE_XML_PATH = "src\\main\\resources\\inputXml.xml";
    public static final String TARGET_XML_PATH = "src\\main\\resources\\outputXml.xml";
    public static final String XSL_TRANSFORM_PATH = "src\\main\\resources\\transform.xsl";

    public static void main(String[] args) throws TransformerException, FileNotFoundException {
        StreamSource source = new StreamSource(SOURCE_XML_PATH);
        StreamSource stylesource = new StreamSource(XSL_TRANSFORM_PATH);

        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer(stylesource);

        StreamResult result = new StreamResult( new FileOutputStream(TARGET_XML_PATH));
        transformer.transform(source, result);

    }
}
