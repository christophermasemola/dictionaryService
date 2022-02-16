package com.chris.masemola.dictionaryService.service;

import com.chris.masemola.dictionaryService.EMode;
import org.springframework.stereotype.Service;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is used to translate words based on mode provided
 * mode type on EMode enum
 * author: CHRISTOPHER MAEMOLA
 * */

@Service
public class TranslatorService
{
    private final DictionaryDbService dictionaryDbService;
    private  ConcurrentHashMap<String, Object> dictionary;

    TranslatorService(DictionaryDbService dictionaryDbService)
    {
        this.dictionaryDbService = dictionaryDbService;
        this.dictionary = dictionaryDbService.getDictionary();
    }

    /**
     * this method translates the provided sentence using
     * the provided translation mode
     * @param sentence String
     * @param mode EMode
     * @return String value of the translated sentence
     * */
    public  String translateString(String sentence, EMode mode)
    {
        if (mode.is(EMode.NO_QUOTES))
        {
            sentence = getSimpleTranslation(sentence);
        }
        else if (mode.is(EMode.WITH_QUOTES))
        {
            sentence = getQuotedTranslation(sentence);
        }
        return sentence;
    }

    /**
     * this method will translate the provided sentence with no quotation marks
     * @param sentence String
     * @return String value of the translated sentence with no quotation marks
     * */
    public String getSimpleTranslation(String sentence)
    {
        String[] sentenceArray = sentence.split(" ");
        String translatedString ="";
        for (String word : sentenceArray)
        {
            dictionaryDbService.setRankings(word);
            translatedString = translatedString + " " +  this.dictionary.get(word);
        }
        return translatedString;
    }

    /**
     * this method will translate the provided sentence with quotation marks
     * @param sentence String
     * @return String value of the translated sentence with quotation marks
     * */
    public String getQuotedTranslation(String sentence)
    {
        String[] sentenceArray = sentence.split(" ");
        String translatedString ="";
        for (String word : sentenceArray)
        {
            dictionaryDbService.setRankings(word);
            translatedString = translatedString + " \"" +  this.dictionary .get(word) + "\"";
        }
        return translatedString;
    }
}
