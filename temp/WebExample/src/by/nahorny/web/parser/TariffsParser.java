package by.nahorny.web.parser;

import by.nahorny.web.tariff.Tariff;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Dmitri_Nahorny on 3/22/2017.
 */
public class TariffsParser {
    private Set<Tariff> tariffs;
    private DocumentBuilder docBuilder;
    public TariffsParser() {
        this.tariffs = new HashSet<Tariff>();
        // create DOM-builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }
    public Set<Tariff> getTariffs() {
        return tariffs;
    }
    public void buildSetTariffs(String fileName) {
        Document doc;
        try {
            //start XML parsing
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            //get Tariffs elements from file
            NodeList tariffsList = root.getElementsByTagName("tariff");
            for (int i = 0; i < tariffsList.getLength(); i++) {
                Element tariffElement = (Element)tariffsList.item(i);
                Tariff importedTariff = buildTariff(tariffElement);
                tariffs.add(importedTariff);
            }
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }
    private Tariff buildTariff(Element tariffElement) {
        Tariff tariff = new Tariff();

        //set Tariff properties
        tariff.setTariffName(tariffElement.getAttribute("name"));
        tariff.setOperatorName(getElementTextContent(tariffElement, "operator-name"));
        tariff.setPayroll(Integer.valueOf(getElementTextContent(tariffElement, "payroll")));

        //set Call Prices properties
        Element callPricesElement = (Element)tariffElement.getElementsByTagName("call-prices").item(0);
        tariff.setWithinNetworkCallPrice(Integer.valueOf(getElementTextContent(callPricesElement, "within-network-call-price")));
        tariff.setOutsideNetworkCallPrice(Integer.valueOf(getElementTextContent(callPricesElement, "outside-network-call-price")));
        tariff.setStationaryCallPrice(Integer.valueOf(getElementTextContent(callPricesElement, "stationary-call-price")));

        //set Tariff properties
        tariff.setSmsPrice(Integer.valueOf(getElementTextContent(tariffElement, "sms-price")));

        //set Tariff Parameters properties
        Element parametersElement = (Element)tariffElement.getElementsByTagName("parameter").item(0);
        tariff.setFavouriteNumber(Integer.valueOf(getElementTextContent(parametersElement, "favourite-number")));
        tariff.setCharging(getElementTextContent(parametersElement, "charging"));
        tariff.setSubscriptionFee(Integer.valueOf(getElementTextContent(parametersElement, "subscription-fee")));

        return tariff;
    }
    //get text content from element
    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
