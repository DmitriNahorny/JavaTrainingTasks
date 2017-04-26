package by.nahorny.task5.chain;

import by.nahorny.task5.composite.Component;
import by.nahorny.task5.composite.Letter;
import by.nahorny.task5.composite.Punctuation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.nahorny.task5.exception.SetHandlerException;
import by.nahorny.task5.exception.TextParsingException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Dmitri_Nahorny on 20.03.2017.
 */
    public class LexemeParser implements AbstractParser{

    static final Logger LOGGER = LogManager.getLogger(LexemeParser.class);

    private final String SYMBOL_REG_EXP = "[\\w]|[\\p{Punct}]";
    private final String LETTER_REG_EXP = "[\\w]";

    @Override
    public void setHandler(AbstractParser childParser) throws SetHandlerException{
        LOGGER.log(Level.WARN, "Attempt to set handler for leaf parser");
        throw new SetHandlerException("Attempt to set handler for leaf parser");
    }

    @Override
    public void parseText(String text, Component lexemeComposite){

        Pattern symbolPattern = Pattern.compile(SYMBOL_REG_EXP);
        Matcher symbolMatcher = symbolPattern.matcher(text);

        Pattern letterPattern = Pattern.compile(LETTER_REG_EXP);

        Component symbolComponent;

        while (symbolMatcher.find()){
            String sym = symbolMatcher.group();
            if (letterPattern.matcher(sym).find()) {
                symbolComponent = new Letter(sym);
            }
            else {
                symbolComponent = new Punctuation(sym);
            }
            lexemeComposite.addComponent(symbolComponent);
        }
    }
}
