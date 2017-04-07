package by.nahorny.task5.runner;

import by.nahorny.task5.calculation.SentenceSorting;
import by.nahorny.task5.chain.LexemeParser;
import by.nahorny.task5.chain.ParagraphParser;
import by.nahorny.task5.chain.SentenceParser;
import by.nahorny.task5.chain.TextParser;
import by.nahorny.task5.composite.Composite;
import by.nahorny.task5.exception.TextParsingException;

import by.nahorny.task5.reader.TextLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

/**
 * Created by Dmitri_Nahorny on 3/16/2017.
 */
public class Main {

    static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args){

        TextLoader loader = new TextLoader();
        String textToParse;
        try {
            textToParse = loader.readTextFromFile("./resource/text.txt");
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        TextParser customTextParser = new TextParser();
        ParagraphParser customParagraphParser = new ParagraphParser();
        SentenceParser customSentenceParser = new SentenceParser();
        LexemeParser customLexemeParser = new LexemeParser();

        customTextParser.setHandler(customParagraphParser);
        customParagraphParser.setHandler(customSentenceParser);
        customSentenceParser.setHandler(customLexemeParser);

        Composite textComposite = new Composite();

        try {
            customTextParser.parseText(textToParse, textComposite);
        } catch (TextParsingException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        System.out.println(textComposite.toString());

        /*LexemeSorting ls = new LexemeSorting();
        ls.sortText(textComposite);*/

        SentenceSorting ss = new SentenceSorting();
        ss.sortSentence(textComposite);
    }
}
