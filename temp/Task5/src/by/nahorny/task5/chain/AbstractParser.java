package by.nahorny.task5.chain;

import by.nahorny.task5.composite.Component;
import by.nahorny.task5.composite.Composite;
import by.nahorny.task5.exception.TextParsingException;

import java.text.ParseException;

/**
 * Created by Dmitri_Nahorny on 3/16/2017.
 */
interface AbstractParser {
    void setHandler(AbstractParser p);
    void parseText(String textToParse, Component pc) throws TextParsingException;
}