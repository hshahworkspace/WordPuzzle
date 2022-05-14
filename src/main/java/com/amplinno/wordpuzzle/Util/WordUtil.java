package com.amplinno.wordpuzzle.Util;

import lombok.SneakyThrows;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

public final class WordUtil {
    public static String maskWord(String word) {
        Random rand = new Random();
        var firstRandomPosition = rand.nextInt(word.length() - 1);
        var secondRandomPosition = rand.nextInt(word.length() - 1);
        while (firstRandomPosition == secondRandomPosition) {
            secondRandomPosition = rand.nextInt(word.length() - 1);
        }

        String markWord = replaceCharAt(word, firstRandomPosition, secondRandomPosition, '*');

        return markWord;
    }

    private static String replaceCharAt(String s, int firstPos, int secondPos, char c) {
        String firstReplacement = s.substring(0, firstPos) + c + s.substring(firstPos + 1);
        String secondReplacement = firstReplacement.substring(0, secondPos) + c + firstReplacement.substring(secondPos + 1);
        return secondReplacement;
    }

    @SneakyThrows
    public static String getRandomWord(File wordFile) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(wordFile, "r");

        final long randomLocation = (long) (Math.random() * wordFile.length() - 1);
        randomAccessFile.seek(randomLocation);
        randomAccessFile.readLine();
        String selectedWord = randomAccessFile.readLine();

        randomAccessFile.close();

        return selectedWord;
    }

    @SneakyThrows
    public static Boolean findWordInFile(File sourceFile, String word) {
        Scanner inputFile = new Scanner(sourceFile);
        Boolean foundFlag = false;
        while(inputFile.hasNextLine()) {
            String wordFromFile = inputFile.nextLine();
            if (wordFromFile.equals(word)) {
                foundFlag = true;
                break;
            }
        }
        return foundFlag;
    }
}
