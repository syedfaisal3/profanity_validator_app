package com.target.product.validator.impl;

import com.target.product.validator.Result;
import com.target.product.validator.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class ProfanityValidator implements Validator<String> {

    @Value("${profanity.validator.input_file_path:./validation/src/main/resources/Word_Filter.csv}")
    private String inputFilePath;

    @PostConstruct
    public void init() throws IOException {
        loadBadWords();
    }


    private static int LARGEST_WORD_LENGTH = 0;

    private static final Map<String, String[]> BLACK_LISTED_WORDS = new HashMap<>();

    /**
     * Iterates over a String input and checks whether any cuss word was found - and for any/all cuss word found,
     * as long as the cuss word should not be ignored (i.e. check for false positives - e.g. even though "bass"
     * contains the word *ss, bass should not be censored) then (in the String returned) replace the cuss word with asterisks.
     */

    @Override
    public Result<String> validate(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("input is null or empty");
        }

        String modifiedInput = input;
        modifiedInput = removeLeetSpeak(modifiedInput);

        ArrayList<String> badWordsFound = getBadWords(modifiedInput);

        return censor(modifiedInput, badWordsFound, input);

    } // end getCensoredText

    private ArrayList<String> getBadWords(String modifiedInput) {
        ArrayList<String> badWordsFound = new ArrayList<>();

        // iterate over each letter in the word
        for (int start = 0; start < modifiedInput.length(); start++) {
            // from each letter, keep going to find bad words until either the end of
            // the sentence is reached, or the max word length is reached.
            for (int offset = 1; offset < (modifiedInput.length() + 1 - start) && offset < LARGEST_WORD_LENGTH; offset++) {
                String wordToCheck = modifiedInput.substring(start, start + offset);
                if (BLACK_LISTED_WORDS.containsKey(wordToCheck)) {
                    String[] ignoreCheck = BLACK_LISTED_WORDS.get(wordToCheck);
                    boolean ignore = false;
                    for (String s : ignoreCheck) {
                        if (modifiedInput.contains(s)) {
                            ignore = true;
                            break;
                        }
                    }

                    if (!ignore) {
                        badWordsFound.add(wordToCheck);
                    }
                }
            }
        }
        return badWordsFound;
    }

    private Result<String> censor(String modifiedInput, ArrayList<String> badWordsFound, String inputToReturn) {
        boolean isSuccess = true;
        for (String swearWord : badWordsFound) {
            isSuccess = false;
            char[] charsStars = new char[swearWord.length()];
            Arrays.fill(charsStars, '*');
            final String stars = new String(charsStars);

            // The "(?i)" is to make the replacement case insensitive.
            //inputToReturn = inputToReturn.replaceAll("(?i)" + swearWord, stars);
            inputToReturn = modifiedInput.replaceAll("(?i)" + swearWord, stars);
        }

        if(!isSuccess) {
            return new Result<>(false, inputToReturn);
        } else {
            return new Result<>(true);
        }
    }

    private String removeLeetSpeak(String modifiedInput) {
        // remove leetspeak
        modifiedInput = modifiedInput.replaceAll("1", "i").replaceAll("!", "i").replaceAll("3", "e").replaceAll("4", "a")
                .replaceAll("@", "a").replaceAll("5", "s").replaceAll("7", "t").replaceAll("0", "o").replaceAll("9", "g");
        return modifiedInput;
    }

    private void loadBadWords() throws IOException {
        int readCounter = 0;

        try (FileReader fr = new FileReader(inputFilePath); BufferedReader reader = new BufferedReader(fr)) {

            String currentLine = "";
            while ((currentLine = reader.readLine()) != null) {
                readCounter++;
                String[] content;

                if (1 == readCounter) {
                    continue;
                }

                content = currentLine.split(",");
                if (content.length == 0) {
                    continue;
                }

                final String word = content[0];

                if (word.startsWith("-----")) {
                    continue;
                }

                if (word.length() > LARGEST_WORD_LENGTH) {
                    LARGEST_WORD_LENGTH = word.length();
                }

                String[] ignore_in_combination_with_words = new String[]{};
                if (content.length > 1) {
                    ignore_in_combination_with_words = content[1].split("_");
                }

                // Make sure there are no capital letters in the spreadsheet
                BLACK_LISTED_WORDS.put(word.replaceAll(" ", "").toLowerCase(), ignore_in_combination_with_words);
            } // end while
        } // end loadBadWords

    }
}
