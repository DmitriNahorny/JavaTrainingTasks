package by.nahorny.task5.chain;

import by.nahorny.task5.composite.Component;
import by.nahorny.task5.composite.Composite;
import by.nahorny.task5.composite.Punctuation;

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
    public void parseText(String text, Component textComposite){
        String[] paragraphs = text.replace("\n","").replace("\r","").split("\t");

        for (String paragraph : paragraphs){
            /*Punctuation tab = new Punctuation("\t");
            textComposite.addComponent(tab);*/

            Composite paragraphComposite = new Composite();
            textComposite.addComponent(paragraphComposite);
            childParser.parseText(paragraph, paragraphComposite);

            Punctuation lineSeparator = new Punctuation(System.lineSeparator());
            textComposite.addComponent(lineSeparator);
        }
    }
}
