package org.example;

import java.util.*;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Main {
    public static void main(String[] args) {
        System.out.println(bell(1));
        System.out.println(bell(2));
        System.out.println(bell(3));

        System.out.println(translateWord("flag"));
        System.out.println(translateWord("Apple"));
        System.out.println(translateWord("button"));
        System.out.println(translateWord(""));

        System.out.println(translateSentence("I like to eat honey waffles."));
        System.out.println(translateSentence("Do you think it is going to rain today?"));

        System.out.println(validColor("rgb(0,0,0)"));
        System.out.println(validColor("rgb(0,,0)"));
        System.out.println(validColor("rgb(255,256,255)"));
        System.out.println(validColor("rgba(0,0,0,0.123456789)"));

        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"}));
        System.out.println(stripUrlParams("https://edabit.com", new String[]{"b"}));

        System.out.println(getHashTags("How the Avocado Became the Fruit of the Global Trade?"));
        System.out.println(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year"));
        System.out.println(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit"));
        System.out.println(getHashTags("Visualizing Science"));

        System.out.println(ulam(4));
        System.out.println(ulam(9));
        System.out.println(ulam(206));

        System.out.println(longestNonrepeatingSubstring("abcabcbb"));
        System.out.println(longestNonrepeatingSubstring("aaaaaa"));
        System.out.println(longestNonrepeatingSubstring("abcde"));
        System.out.println(longestNonrepeatingSubstring("abcda"));

        System.out.println(convertToRoman(2));
        System.out.println(convertToRoman(12));
        System.out.println(convertToRoman(16));

        System.out.println(formula("6 * 4 = 24"));
        System.out.println(formula("18 / 17 = 2"));
        System.out.println(formula("16 * 10 = 160 = 14 + 120"));

        System.out.println(palindromeDescendant(11211230));
        System.out.println(palindromeDescendant(13001120));
        System.out.println(palindromeDescendant(23336014));
        System.out.println(palindromeDescendant(11));
        System.out.println(palindromeDescendant(12));
    }

    public static int bell(int n) {
        int[][] bell = new int[n + 1][n + 1];
        bell[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            bell[i][0] = bell[i - 1][i - 1];
            for (int j = 1; j <= i; j++)
                bell[i][j] = bell[i - 1][j - 1] + bell[i][j - 1];
        }
        return bell[n][0];
    }

    public static String translateWord(String word) {
        if (word.length() == 0) {
            return word;
        }

        String firstLetter = word.substring(0, 1);

        if (!firstLetter.matches("(?i)[aiyueo]")) {
            String firstVowel = String.valueOf(word.replaceAll("(?i)[^aiyueo]", "").charAt(0));
            if (firstLetter.matches("^[A-Z]")) {
                return firstVowel.toUpperCase() +
                        word.substring(word.indexOf(firstVowel) + 1) +
                        word.substring(0, word.indexOf(firstVowel)).toLowerCase() + "ay";
            }
            return word.substring(word.indexOf(firstVowel)) + word.substring(0, word.indexOf(firstVowel)) + "ay";
        }
        return word + "yay";
    }

    public static String translateSentence(String s) {
        String[] sentence = s.split(" ");

        List<String> sentenceWords = new ArrayList<>(Arrays.asList(s.split("\\s|\\.|,|\\?|!")));
        sentenceWords.removeIf(String::isEmpty);

        for (int i = 0; i < sentenceWords.size(); i++) {
            sentence[i] = sentence[i].replace(sentenceWords.get(i), translateWord(sentenceWords.get(i)));
        }

        return String.join(" ", sentence);
    }

    public static boolean validColor(String rgb) {
        return rgb.matches("rgba?" +
                "\\(" +
                "(0|([1-9]\\d?|2[0-5][0-5]|1\\d{1,2}))," +
                "(0|([1-9]\\d?|2[0-5][0-5]|1\\d{1,2}))," +
                "(0|([1-9]\\d?|2[0-5][0-5]|1\\d{1,2}))" +
                "(,((0.\\d*)|1))?\\)" +
                "");
    }

    public static String stripUrlParams(String url) {
        boolean paramsNotStated = String.join("", url.split("[^?]")).isEmpty();
        if (paramsNotStated) {
            return url;
        }

        StringBuilder result = new StringBuilder(url.substring(0, url.indexOf("?") + 1));
        HashMap<String, String> unicParams = new HashMap<>();
        String[] params = String.join("", url.substring(url.indexOf("?") + 1).split("[=&0-9]")).split("");
        String[] values = String.join("", url.substring(url.indexOf("?") + 1).split("[=&a-z]")).split("");

        for (int i = 0; i < params.length; i++) {
            unicParams.put(params[i], values[i]);
        }

        for (String key : unicParams.keySet()) {
            result.append(key).append("=").append(unicParams.get(key));
        }

        return result.toString();
    }

    public static String stripUrlParams(String url, String[] paramsToStrip) {
        boolean paramsNotStated = String.join("", url.split("[^?]")).isEmpty();
        if (paramsNotStated) {
            return url;
        }

        StringBuilder result = new StringBuilder(url.substring(0, url.indexOf("?") + 1));
        HashMap<String, String> unicParams = new HashMap<>();
        String[] params = String.join("", url.substring(url.indexOf("?") + 1).split("[=&0-9]")).split("");
        String[] values = String.join("", url.substring(url.indexOf("?") + 1).split("[=&a-z]")).split("");

        for (int i = 0; i < params.length; i++) {
            if (String.join("", paramsToStrip).contains(params[i])) {
                continue;
            }
            unicParams.put(params[i], values[i]);
        }

        for (String key : unicParams.keySet()) {
            result.append(key).append("=").append(unicParams.get(key));
        }

        return result.toString();
    }

    public static String getHashTags(String title) {
        String[] titleWords = String.join(" ", title.split("(?i)[^a-z]")).split("\\s+");
        ArrayList<String> hashTags = new ArrayList<>();

        if (titleWords.length <= 3) {
            String longestWord;
            while (hashTags.size() < titleWords.length) {
                longestWord = "";

                for (String titleWord : titleWords) {
                    if (hashTags.contains("#" + titleWord.toLowerCase())) {
                        continue;
                    }
                    longestWord = longestWord.length() <= titleWord.length() ? titleWord : longestWord;
                }
                hashTags.add("#" + longestWord.toLowerCase());
            }
            return hashTags.toString();
        }

        HashMap<Integer, String> sortedWordsWithLength = new HashMap<>();

        for (int i = titleWords.length - 1; i >= 0; i--) {
            sortedWordsWithLength.put(titleWords[i].length(), titleWords[i]);
        }

        Object[] sortedWords = sortedWordsWithLength.values().toArray();

        int i = sortedWords.length - 1;
        int wordsNeeded = 3;
        while (wordsNeeded > 0 && i >= 0) {
            hashTags.add("#" + ((String) sortedWords[i]).toLowerCase());
            i--;
            wordsNeeded--;
        }

        return hashTags.toString();
    }

    public static int ulam(int n) {
        ArrayList<Integer> ulamNumbers = new ArrayList<>(Arrays.asList(1, 2));


        if (n == 1) {
            return ulamNumbers.get(0);
        }
        if (n == 2) {
            return ulamNumbers.get(1);
        }

        int nextUlam = 3;
        while (true){
            if (n == ulamNumbers.size()) {
                return ulamNumbers.get(n-1);
            }
            int count = 0;

            for (int j = 0; j < ulamNumbers.size() - 1; j++) {
                for (int k = j + 1; k < ulamNumbers.size(); k++) {
                    if (ulamNumbers.get(j) + ulamNumbers.get(k) == nextUlam) {
                        count++;
                    }
                    if (count > 1)
                        break;
                }
                if (count > 1)
                    break;
            }

            if (count == 1) {
                ulamNumbers.add(nextUlam);
            }

            nextUlam++;
        }
    }

    public static String longestNonrepeatingSubstring(String s) {
        ArrayList<String> digits = new ArrayList<>(List.of(s.split("")));
        LinkedHashSet<String> totalLongest = new LinkedHashSet<>();
        LinkedHashSet<String> currentLongest = new LinkedHashSet<>();

        for (String item: digits) {
            if (!currentLongest.contains(item)){
                currentLongest.add(item);
                continue;
            }
            if (currentLongest.size() > totalLongest.size()){
                totalLongest.clear();
                totalLongest.addAll(currentLongest);
            }
            currentLongest.clear();
            currentLongest.add(item);
        }
        if (currentLongest.size() > totalLongest.size()){
            totalLongest.clear();
            totalLongest.addAll(currentLongest);
        }

        return totalLongest.toString();
    }

    public static String convertToRoman(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return thousands[num / 1000] + hundreds[(num % 1000) / 100] + tens[(num % 100) / 10] + units[num % 10];
    }

    public static boolean formula(String f) {
        String[] formulaParts = f.split("\s=\s");
        HashSet<Double> calculations = new HashSet<>();

        for (String part : formulaParts) {
            Expression e = new ExpressionBuilder(part).build();
            calculations.add(e.evaluate());
        }

        return calculations.size() == 1;
    }

    public static boolean palindromeDescendant(long num) {
        String s = String.valueOf(num);
        if (s.length() <= 1) {
            return false;
        }
        if(isPalindrome(s)) {
            return true;
        }
        return palindromeDescendant(
                Long.parseLong(
                        makeSumOfPairs(s)
                )
        );
    }

    public static boolean isPalindrome(String s) {
        return s.equals(new StringBuilder(s).reverse().toString());
    }

    public static String makeSumOfPairs(String num) {
        String[] digits = num.split("");
        ArrayList<String> newNum = new ArrayList<>();

        for(int i = 0; i < digits.length-1; i+=2){
            newNum.add(
                    String.valueOf(
                    Integer.parseInt(digits[i]) +
                            Integer.parseInt(digits[i+1])
                )
            );
        }

        return String.join("", newNum);
    }
}