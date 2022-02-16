package com.chris.masemola.dictionaryService.controller;

import com.chris.masemola.dictionaryService.EMode;
import com.chris.masemola.dictionaryService.service.DictionaryDbService;
import com.chris.masemola.dictionaryService.service.TranslatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * This class is used as a controller for the dictionary service
 * author: CHRISTOPHER MAEMOLA
 * */

@RestController
public class DictionaryController
{
    private final TranslatorService translatorService;
    private  final DictionaryDbService dictionaryDbService;

    DictionaryController(TranslatorService translatorService, DictionaryDbService dictionaryDbService)
    {
        this.translatorService = translatorService;
        this.dictionaryDbService = dictionaryDbService;
    }

    @GetMapping("/translate")
    public String translate(@RequestParam String sentence,  @RequestParam String mode )
    {
        return translatorService.translateString(sentence, EMode.of(Integer.valueOf(mode)));
    }

    @GetMapping("/getRanking")
    public List<Map.Entry<String, Integer>>  getRanking()
    {
        Map<String, Integer> rankMap = new HashMap<>(dictionaryDbService.getRankings());
        List<Map.Entry<String, Integer>> sortedRankList = new ArrayList<>(rankMap.entrySet());
        sortedRankList.sort(Map.Entry.comparingByValue());
        return  sortedRankList;
    }
}
