package by.nahorny.task5.chain;

import by.nahorny.task5.composite.Component;
import by.nahorny.task5.composite.Composite;
import by.nahorny.task5.composite.Punctuation;
import by.nahorny.task5.exception.TextParsingException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dmitri_Nahorny on 3/17/2017.
 */
public class SentenceParser implements AbstractParser {

    private AbstractParser childParser;

    @Override
    public void setHandler(AbstractParser childParser){
        this.childParser = childParser;
    }

    @Override
    public void parseText(String text, Component sentenceComposite) throws TextParsingException{

        if (childParser != null) {
            String lexemeRegExp = "([\\w\\-\'\"]+)([,;:\\s.?!])";
            Pattern lexemePattern = Pattern.compile(lexemeRegExp);
            Matcher lexemeMatcher = lexemePattern.matcher(text);

            while(lexemeMatcher.find()) {
                String lexeme = lexemeMatcher.group();
                Composite lexemeComposite = new Composite();
                sentenceComposite.addComponent(lexemeComposite);
                childParser.parseText(lexeme, lexemeComposite);

                Punctuation space = new Punctuation(" ");
                sentenceComposite.addComponent(space);
            }

            sentenceComposite.remove(sentenceComposite.getChild(sentenceComposite.componentSize() - 1));
        }
        else {
            throw new TextParsingException("Child parser has not been instantiated for " + this.getClass().getSimpleName());
        }
    }
}
