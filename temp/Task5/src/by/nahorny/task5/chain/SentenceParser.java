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
 * Created by Dmitri_Nahorny on 3/17/2017.
 */
public class SentenceParser implements AbstractParser {

    private AbstractParser childParser;
    private final String LEXEME_REG_EXP = "([\\w\\-\'\"]+)([,;:\\s.?!])";

    static final Logger LOGGER = LogManager.getLogger(SentenceParser.class);

    @Override
    public void setHandler(AbstractParser childParser){
        this.childParser = childParser;
    }

    @Override
    public void parseText(String text, Component sentenceComposite) throws TextParsingException{

        if (childParser != null) {
            Pattern lexemePattern = Pattern.compile(LEXEME_REG_EXP);
            Matcher lexemeMatcher = lexemePattern.matcher(text);

            while(lexemeMatcher.find()) {
                String lexeme = lexemeMatcher.group();
                Composite lexemeComposite = new Composite();
                sentenceComposite.addComponent(lexemeComposite);
                childParser.parseText(lexeme, lexemeComposite);

                Punctuation space = new Punctuation(" ");
                sentenceComposite.addComponent(space);
            }

            try {
                sentenceComposite.removeComponent(sentenceComposite.getComponent(sentenceComposite.componentSize() - 1));
            } catch (LeafComponentOperationException e) {
                LOGGER.log(Level.ERROR, "Attempt to get components of leaf component");
            }
        }
        else {
            throw new TextParsingException("Child parser has not been instantiated for " + this.getClass().getSimpleName());
        }
    }
}
