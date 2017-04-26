package test.by.nahorny.task5;

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
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by Dmitri_Nahorny on 4/26/2017.
 */
public class SentenceSortingTest {

    private TextParser customTextParser;
    private ParagraphParser customParagraphParser;
    private SentenceParser customSentenceParser;
    private LexemeParser customLexemeParser;

    private final String TEXT_FILE_PATH = "./resource/text.txt";

    static final Logger LOGGER = LogManager.getLogger(SentenceSortingTest.class);

    @Before
    public void parserInit() {
        customTextParser = new TextParser();
        customParagraphParser = new ParagraphParser();
        customSentenceParser = new SentenceParser();
        customLexemeParser = new LexemeParser();

        customTextParser.setHandler(customParagraphParser);
        customParagraphParser.setHandler(customSentenceParser);
        customSentenceParser.setHandler(customLexemeParser);
    }

    @Test
    public void testLexemeSorting() {

        TextLoader loader = new TextLoader();
        String textToParse;
        try {
            textToParse = loader.readTextFromFile(TEXT_FILE_PATH);
        } catch (IOException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        Composite textComposite = new Composite();

        try {
            customTextParser.parseText(textToParse, textComposite);
        } catch (TextParsingException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            Assert.fail();
        }

        SentenceSorting sentenceSorting = new SentenceSorting();
        sentenceSorting.sortSentence(textComposite);

    }

    @After
    public void parserDeinit() {
        customTextParser = null;
        customParagraphParser = null;
        customSentenceParser = null;
        customLexemeParser = null;
    }
}
