package by.nahorny.task5.chain;

import by.nahorny.task5.composite.Component;
import by.nahorny.task5.composite.Composite;
import by.nahorny.task5.exception.TextParsingException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dmitri_Nahorny on 3/17/2017.
 */
public class ParagraphParser implements AbstractParser  {

    private AbstractParser childParser;

    @Override
    public void setHandler(AbstractParser childParser){
        this.childParser = childParser;
    }

    @Override
    public void parseText(String text, Component paragraphComposite) throws TextParsingException{

        if (childParser != null) {
            String sentenceRegExp = "([A-Z[0-9]])([\\w,;:\\-\\s\'\"]*)(.!?)";
            Pattern sentencePattern = Pattern.compile(sentenceRegExp);
            Matcher sentenceMatcher = sentencePattern.matcher(text);

            while (sentenceMatcher.find()) {
                String sentence = sentenceMatcher.group();
                Composite sentenceComposite = new Composite();
                paragraphComposite.addComponent(sentenceComposite);
                childParser.parseText(sentence, sentenceComposite);
            }
        }
        else {
            throw new TextParsingException("Child parser has not been instantiated for " + this.getClass().getSimpleName());
        }
    }
}
