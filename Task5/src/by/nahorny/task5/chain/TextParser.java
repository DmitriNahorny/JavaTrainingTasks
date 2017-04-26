package by.nahorny.task5.chain;

import by.nahorny.task5.composite.Component;
import by.nahorny.task5.composite.Composite;
import by.nahorny.task5.composite.Punctuation;
import by.nahorny.task5.exception.LeafComponentOperationException;
import by.nahorny.task5.exception.TextParsingException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dmitri_Nahorny on 3/16/2017.
 */
public class TextParser implements AbstractParser {

    private AbstractParser childParser;
    private final String PARAGRAPH_REG_EXP = "(\\t)(.+)(\\r\\n)";

    static final Logger LOGGER = LogManager.getLogger(TextParser.class);

    @Override
    public void setHandler(AbstractParser childParser){
        this.childParser = childParser;
    }

    @Override
    public void parseText(String text, Component textComposite) throws TextParsingException{

        if (childParser != null) {

            Pattern paragraphPattern = Pattern.compile(PARAGRAPH_REG_EXP);
            Matcher paragraphMatcher = paragraphPattern.matcher(text);

            while (paragraphMatcher.find()){
                /*Punctuation tab = new Punctuation("\t");
                textComposite.addComponent(tab);*/

                String paragraph = paragraphMatcher.group();

                Composite paragraphComposite = new Composite();
                textComposite.addComponent(paragraphComposite);
                childParser.parseText(paragraph, paragraphComposite);

                Punctuation lineSeparator = new Punctuation(System.lineSeparator());
                textComposite.addComponent(lineSeparator);
            }

            try {
                textComposite.removeComponent(textComposite.getComponent(textComposite.componentSize() - 1));
            } catch (LeafComponentOperationException e) {
                LOGGER.log(Level.ERROR, "Attempt to get components of leaf component");
            }

        }
        else {
            throw new TextParsingException("Child parser has not been instantiated for " + this.getClass().getSimpleName());
        }
    }
}
