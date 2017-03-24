package by.nahorny.task5.chain;

import by.nahorny.task5.composite.Component;
import by.nahorny.task5.composite.Letter;
import by.nahorny.task5.composite.Punctuation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pekarnya on 20.03.2017.
 */
public class LexemeParser implements AbstractParser {
    @Override
    public void setHandler(AbstractParser childParser){
        //custom warning to implement
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
