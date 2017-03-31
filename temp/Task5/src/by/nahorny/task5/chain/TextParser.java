package by.nahorny.task5.chain;

import by.nahorny.task5.composite.Component;
import by.nahorny.task5.composite.Composite;
import by.nahorny.task5.composite.Punctuation;
import by.nahorny.task5.exception.TextParsingException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dmitri_Nahorny on 3/16/2017.
 */
public class TextParser implements AbstractParser {

    private AbstractParser childParser;

    @Override
    public void setHandler(AbstractParser childParser){
        this.childParser = childParser;
    }

    @Override
    public void parseText(String text, Component textComposite) throws TextParsingException{

        if (childParser != null) {

            String paragraphRegExp = "(\\t)(.+)(\\r\\n)";
            Pattern paragraphPattern = Pattern.compile(paragraphRegExp);
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

            textComposite.remove(textComposite.getChild(textComposite.componentSize() - 1));
        }
        else {
            throw new TextParsingException("Child parser has not been instantiated for " + this.getClass().getSimpleName());
        }
    }
}
