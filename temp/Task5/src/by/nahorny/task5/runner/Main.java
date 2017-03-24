package by.nahorny.task5.runner;

import by.nahorny.task5.chain.LexemeParser;
import by.nahorny.task5.chain.ParagraphParser;
import by.nahorny.task5.chain.SentenceParser;
import by.nahorny.task5.chain.TextParser;
import by.nahorny.task5.composite.Composite;

/**
 * Created by Dmitri_Nahorny on 3/16/2017.
 */
public class Main {
    public static void main(String[] args){
        String textToParse = "1P1S1, PP1'SS1. P1-S2 p1s2? P1S3.\tP2S1! P2S2 - p2s2.";

        TextParser customTextParser = new TextParser();
        ParagraphParser customParagraphParser = new ParagraphParser();
        SentenceParser customSentenceParser = new SentenceParser();
        LexemeParser customLexemeParser = new LexemeParser();

        customTextParser.setHandler(customParagraphParser);
        customParagraphParser.setHandler(customSentenceParser);
        customSentenceParser.setHandler(customLexemeParser);

        Composite textComposite = new Composite();

        customTextParser.parseText(textToParse, textComposite);

        System.out.println(textComposite.toString());
        System.out.println(textComposite.getChild(0));
    }
}
