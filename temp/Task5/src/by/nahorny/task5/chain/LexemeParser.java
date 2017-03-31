package by.nahorny.task5.chain;

import by.nahorny.task5.composite.Component;
import by.nahorny.task5.composite.Letter;
import by.nahorny.task5.composite.Punctuation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Dmitri_Nahorny on 20.03.2017.
 */
    public class LexemeParser implements AbstractParser{

    static final Logger logger = LogManager.getLogger(LexemeParser.class);

    @Override
    public void setHandler(AbstractParser childParser){
        logger.log(Level.WARN, "Attempt to set handler for leaf parser");
    }

    @Override
    public void parseText(String text, Component lexemeComposite){

        String symbolRegExp = "[\\w]|[\\p{Punct}]";
        Pattern symbolPattern = Pattern.compile(symbolRegExp);
        Matcher symbolMatcher = symbolPattern.matcher(text);

        String letterRegExp = "[\\w]";
        Pattern letterPattern = Pattern.compile(letterRegExp);

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
