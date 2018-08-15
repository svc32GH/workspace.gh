package com.svc32.xml.main;

import com.svc32.xml.main.com.svc32.xml.Xslt20Transformer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class MainServlet extends HttpServlet {
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sep = "qwerty0123456789";
        StringBuffer sb = new StringBuffer("");
        String line;
        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }
        String[] xmls = sb.toString().split(sep);
        String xmlStr = xmls[0];
        String xslStr = xmls[1];
//        System.out.println("xml = " + xml);
//        System.out.println("xsl = " + xsl);
        String resXml = null;
        try {
            resXml = transformer.transform(xmlStr, xslStr);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

//        BufferedReader r = new BufferedReader(new StringReader(resXml));
//        line = r.readLine();
//        response.getWriter().append(line);
//        while ( (line = r.readLine()) != null ) {
//            response.getWriter().append("\n" + line);
//        }

        response.getWriter().append(resXml);
    }
}
