package com.chris.masemola.dictionaryService.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class is used for getting word translations and updating word rankings
 * author: CHRISTOPHER MAEMOLA
 * */

@Service
public class DictionaryDbService
{
    private final ConcurrentHashMap<String, Object> words;
    private final Map<String, Integer> wordRanking;

    @PostConstruct
    public void init() throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
        InputStream dictionaryInput =  TypeReference.class.getResourceAsStream("/Dictionary.json");

        try
        {
            HashMap<String, Object>  dictionary = mapper.readValue(dictionaryInput, typeRef);
            this.words.putAll(dictionary);
            initialiseRank();
        }
        catch( IOException exception)
        {
            System.out.println("Error Reading a file " + exception.getMessage());
        }
    }

    public DictionaryDbService()
    {
        this.words = new ConcurrentHashMap<>();
        this.wordRanking =   new ConcurrentHashMap<>();
    }

    public Map<String, Integer> getRankings() {
        return wordRanking;
    }

    /**
     * Increments word ranking
     * @param word String
     * */
    public void incrementRanking(String word)
    {
        Integer rank = this.wordRanking.get(word) + 1;
        this.wordRanking.put(word, rank);
    }

    /**
     * Initializes word rankings
     * */
    public void initialiseRank()
    {
        for ( String word  : this.words.keySet())
        {
            this.wordRanking.put(word, 0);
        }
    }

    /**
     * gets word translation dictionary
     * @return word translation dictionary map
     * */
    public ConcurrentHashMap<String, Object> getDictionary()
    {
        return  this.words;
    }
}
