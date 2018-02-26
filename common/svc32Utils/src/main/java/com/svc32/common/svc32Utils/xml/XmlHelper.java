package com.svc32.common.svc32Utils.xml;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;

public class XmlHelper {
    private static final DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
    private static final XPathFactory xpathFactory = XPathFactory.newInstance();
    private static final XPath xPath = xpathFactory.newXPath();

    private final DocumentBuilder domBuilder;

    static {
        domFactory.setNamespaceAware(true);
    }

    public XmlHelper() throws ParserConfigurationException {
        domBuilder = domFactory.newDocumentBuilder();
    }

    public Document getDocument(String xml) throws IOException, SAXException {
        ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());
        return domBuilder.parse(bais);
    }

    public Object GetXPathResultAsNodeSet(Document doc, String xmlPath) throws XPathExpressionException {
        XPathExpression expression = xPath.compile(xmlPath);
        Object result = expression.evaluate(doc, XPathConstants.NODESET);
        return result;
    }

    public Object GetXPathResultAsNodeSet(String xml, String xmlPath) throws XPathExpressionException, IOException, SAXException {
        Document doc = getDocument(xml);
        Object result = GetXPathResultAsNodeSet(doc, xmlPath);
        return result;
    }

    public List<String> getXPathValus(Document doc, String xmlPath) throws XPathExpressionException {
        ArrayList<String> list = new ArrayList();
        NodeList nodeList = (NodeList) GetXPathResultAsNodeSet(doc, xmlPath);
        for (int i = 0; i < nodeList.getLength(); i++) {
            String value = nodeList.item(i).getTextContent();
            list.add(value);
        }

        return list;
    }

    public Collection<String> getXPathSortedValues(Document doc, String xmlPath) throws XPathExpressionException {
        Set<String> set = new TreeSet();
        NodeList nodeList = (NodeList) GetXPathResultAsNodeSet(doc, xmlPath);
        for (int i = 0; i < nodeList.getLength(); i++) {
            String value = nodeList.item(i).getTextContent();
            set.add(value);
        }

        return set;
    }

    public Collection<String> getXPathSortedValues(String xml, String xmlPath) throws XPathExpressionException, IOException, SAXException {
        Document dom = getDocument(xml);
        return getXPathSortedValues(dom, xmlPath);
    }

}
