package com.svc32.common.crosswordassistant;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrossSet extends TreeMap<Integer, Set<String>> {

    public void addWord(String word) {
        word = word.replaceAll("ё", "е");
        int wLen = new Integer(word.length());
        Set set = get(wLen);
        if (set == null) {
            set = new TreeSet();
            set.add(word.toLowerCase());
            put(wLen, set);
        } else {
            set.add(word.toLowerCase());
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder("\n");
        for (Integer key : keySet()) {
            if (key.intValue() < 10) {
                res.append("\n " + key.toString() + " --> " + get(key).size());
            } else {
                res.append("\n" + key.toString() + " --> " + get(key).size());
            }
        }
        return res.toString();
    }

    public List<String> getSuitableWords(String allowedSymbols, String pattern) {
        List<String> res = new ArrayList();
        Integer wLen = new Integer(pattern.length());
        Set<String> wSet = get(wLen);
        for (String word : wSet) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(word);
            if (m.matches() && checkWord(allowedSymbols, word))
                res.add(word);
        }
        return res;
    }

    public static boolean checkWord(String allowedSymbols, String word) {
        String as = allowedSymbols;
        String[] wordArray = word.split("(?!^)");
        for (String s : wordArray) {
            if (!as.contains(s))
                return false;
            as = as.replaceFirst(s, "");
        }
        return true;
    }
}
