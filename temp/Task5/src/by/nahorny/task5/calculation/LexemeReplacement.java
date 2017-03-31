package by.nahorny.task5.calculation;

import by.nahorny.task5.composite.Component;
import by.nahorny.task5.composite.Composite;

/**
 * Created by Dmitri_Nahorny on 3/29/2017.
 */
public class LexemeReplacement {
    public void replaceLexemeInSentence(Component textComposite) {
        for(int i = 0; i<textComposite.componentSize(); i++) {
            Component paragraphComposite = textComposite.getChild(i);
            for (int j=0; j<paragraphComposite.componentSize(); j++) {
                Component sentenceComposite = paragraphComposite.getChild(j);
                Component firstLexeme = sentenceComposite.getChild(0).getCopy();
            }
        }
    }
}
