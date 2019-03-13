package com.maugrift.godtitles;

import squidpony.FakeLanguageGen;
import squidpony.squidmath.RNG;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Reminder: Use the .mix method of a FLG instance to combine languages
        RNG rng = new RNG();
        FakeLanguageGen language = FakeLanguageGen.randomLanguage(rng).removeAccents();

        List<String> formatStrings   = readFileToList("data/format_strings.txt");
        List<String> adjectives      = readFileToList("data/adjectives.txt");
        List<String> domains         = readFileToList("data/domains.txt");
        List<String> titlesGeneral   = readFileToList("data/titles/general.txt");
        List<String> titlesAdjective = readFileToList("data/titles/adjective.txt");
        List<String> titlesDomain    = readFileToList("data/titles/domain.txt");

        titlesAdjective.addAll(titlesGeneral);
        titlesDomain.addAll(titlesGeneral);

        for (int i = 0; i < 20; i++) {
            System.out.println(generateGodTitle(rng, language, formatStrings, adjectives, domains, titlesAdjective, titlesDomain));
        }
    }

    public static String generateGodTitle(RNG rng,
                                          FakeLanguageGen language,
                                          List<String> formatStrings,
                                          List<String> adjectives,
                                          List<String> domains,
                                          List<String> titlesAdjective,
                                          List<String> titlesDomain) {
        String name;
        do {
            name = language.word(rng, true);
        } while (name.length() <= 2);

        return String.format(rng.getRandomElement(formatStrings),
                name,
                rng.getRandomElement(adjectives),
                rng.getRandomElement(domains),
                rng.getRandomElement(titlesAdjective),
                rng.getRandomElement(titlesDomain));
    }

    public static List<String> readFileToList(String path) {
        List<String> list = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()){
                list.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}
