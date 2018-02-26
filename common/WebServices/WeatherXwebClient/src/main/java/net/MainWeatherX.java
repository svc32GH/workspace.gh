package net;

import com.svc32.common.svc32Utils.string.StringFunctions;
import com.svc32.common.svc32Utils.xml.XmlHelper;
import net.webservicex.country.Country;
import net.webservicex.country.CountrySoap;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Collection;

public class MainWeatherX {
    public static void main(String[] args) throws ParserConfigurationException, XPathExpressionException, IOException, SAXException {
        Country country = new Country();
        CountrySoap countrySoap = country.getCountrySoap();
        String countriesXml = countrySoap.getCountries();

//        System.out.println("countriesXml: \n" + countriesXml);

        XmlHelper xmlHelper = new XmlHelper();

        Collection<String> coll = xmlHelper.getXPathSortedValues(countriesXml, "/NewDataSet/Table/Name");
        String collString = StringFunctions.getSeparatedList(coll, "\n");

        System.out.println("\ncollString: \n" + collString);

    }
}
