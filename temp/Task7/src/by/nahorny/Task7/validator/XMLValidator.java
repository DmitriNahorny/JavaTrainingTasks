package by.nahorny.Task7.validator;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Dmitri_Nahorny on 3/23/2017.
 */
public class XMLValidator {
    public static void validateXML() {
        String filename = "resource/tariffs.xml";
        String schemaname = "resource/tariff.xsd";
        Schema schema;
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);

        try {

            schema = factory.newSchema(new File(schemaname));

            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setSchema(schema);

            SAXParser parser = spf.newSAXParser();
            parser.parse(filename, new CustomErrorHandler());

            System.out.println(filename + " is valid");

        } catch (ParserConfigurationException e){
            System.err.print("XML file config error: " + e.getMessage());
        } catch (SAXException e) {
            System.err.print("SAX parser exception " + e.getMessage());
        } catch (IOException e) {
            System.err.print("I/O stream error " + e.getMessage());
        }
    }
}

class CustomErrorHandler extends DefaultHandler {

    public void warning(SAXParseException e) {
        System.out.println(getLineAddress(e) + "-" + e.getMessage());
    }
    public void error(SAXParseException e) {
        System.out.println(getLineAddress(e) + " - " + e.getMessage());
    }
    public void fatalError(SAXParseException e) {
        System.out.println(getLineAddress(e) + " - " + e.getMessage());
    }
    private String getLineAddress(SAXParseException e) {
        return e.getLineNumber() + " : " + e.getColumnNumber();
    }
}