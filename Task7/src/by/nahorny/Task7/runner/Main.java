package by.nahorny.Task7.runner;

import by.nahorny.Task7.parser.TariffsParser;
import by.nahorny.Task7.tariff.Tariff;

import java.util.Set;

public class Main {
    public static void main(String args[]) {
        String filePath = "resource/tariffs.xml";
        TariffsParser parserInst = new TariffsParser();

        parserInst.buildSetTariffs(filePath);

        Set<Tariff> importedTariffs = parserInst.getTariffs();
        for (Tariff t : importedTariffs) {
            System.out.println(t.toString());
        }
    }
}