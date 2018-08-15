import com.svc32.xml.main.com.svc32.xml.Xslt20Transformer;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleTests {

    @Test
    public void testString() {
        String sep = "qwerty0123456789";
        System.out.println("sep = " + sep);
        String a = "123";
        String b = "456";
        String c = a + sep + b;
        String arr[] = c.split(sep);

        System.out.println("arr = " + Arrays.toString(arr));

    }

    @Test
    public void testXslTransform() throws IOException, TransformerException {
        String xmlStr = readFile("simple.xml");
        String regex = "(.*)>[\\s]*<(.*)";
        String replacement = "$1><$2";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(xmlStr);
        while (matcher.find()) {
            int count = matcher.groupCount();
            String replaced = matcher.replaceAll(replacement);
            System.out.println("count    = " + count);
            System.out.println("replaced = " + replaced);
        }


        String xmlStr2 = xmlStr
//                .replaceAll("\r", "")
//                .replaceAll("\n", "")
//                .replaceAll("\t", "")
                .replaceAll(regex, replacement);
        String xslStr = readFile("simple.xsl");
//        System.out.println("xmlStr = " + xmlStr);
//        System.out.println("xslStr = " + xslStr);
        Xslt20Transformer transformer = Xslt20Transformer.getInstance();
        String resXml = transformer.transform(xmlStr2, xslStr);
        System.out.println("resXml = " + resXml);
    }

    private String readFile(String path) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        String result = null;
        try (FileInputStream inputStream = new FileInputStream(file)) {
            result = IOUtils.toString(inputStream, "UTF-8");
        }
        return result;
    }

    @Test
    public void testRegexp() {
        String str = ""
                + "<A>\r\n"
                + "   <b>asd123cvb</b>\r\n"
                + "   <c>asd123cvb</c>\r\n"
                + "   <d>asd123cvb</d>\r\n"
                + "   <e>asd123cvb</e>\r\n"
                + "   <f>asd123cvb</f>\r\n"
                + "   <g>asd123cvb</g>\r\n"
                + "   <h>asd123cvb</h>\r\n"
                + "   <i>asd123cvb</i>\r\n"
                + "<\\A>"
                ;

//        String str2 = "121212121<b>asd123cvb</b>      <b>asd123cvb</b>";
        String regex = "(.*)>[\\s]*<(.*)";
        String res = str.replaceAll(regex, "$1><$2");
        System.out.println("str   = \n" + str);
        System.out.println("regex = " + regex);
        System.out.println("res   = \n" + res);

        res = res.replaceAll(regex, "$1><$2");
        System.out.println("\n\nres   = \n" + res);

        res = res.replaceAll(regex, "$1><$2");
        System.out.println("\n\nres   = \n" + res);

        res = res.replaceAll(regex, "$1><$2");
        System.out.println("\n\nres   = \n" + res);

        res = res.replaceAll(regex, "$1><$2");
        System.out.println("\n\nres   = \n" + res);
    }
}
