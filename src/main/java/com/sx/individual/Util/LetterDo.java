package com.sx.individual.Util;


import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;



@ShellComponent
public class LetterDo{

    @ShellMethod(key = "wf.exe -c", value = "统计字母个数")
    public static void letters(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        List<Letter> letterList = new ArrayList<Letter>();
        for(int i = 0; i<26; i++){
            char a = (char) ('A' + i);
            Letter letter = new Letter();
            letter.setLetter(a);
            letter.setCounts(0);
            letterList.add(letter);
        }
        List<String> lists = IODemoByNIO.readFileByChannel(filename);
        if(lists == null || lists.size() == 0){
            return ;
        }
        int count = 0;
        for (String str : lists) {
            for(int i = 0; i<str.length(); i++){
                String s = str.substring(i, i+1).toUpperCase();
                if(s.matches("[A-Z]")){
                    char c = s.charAt(0);
                    int j = c - 'A';
                    //System.out.println(c);
                    count++;
                    int k = letterList.get(j).getCounts();
                    k++;
                    letterList.get(j).setCounts(k);
                }
            }
        }
        //System.out.println(count);
        Collections.sort(letterList);
        for(int i = 0; i<letterList.size(); i++){
            Letter letter = letterList.get(i);
            double m = (double) letter.getCounts() / (double) count;
            letter.setRate(m);
            //BigDecimal bg = new BigDecimal(m);
            //double f1 = bg.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println("字母:" + letter.getLetter() +
                    "  个数: " + letter.getCounts() +
                    "  百分比: " +  String .format("%.2f",m*100) +"%");
        }
    }

    public static class Letter implements Comparable{
        private char letter;
        private int counts;
        private double rate;

        public char getLetter() {
            return letter;
        }

        public void setLetter(char letter) {
            this.letter = letter;
        }

        public int getCounts() {
            return counts;
        }

        public void setCounts(int counts) {
            this.counts = counts;
        }

        public double getRate() {
            return rate;
        }

        public void setRate(double rate) {
            this.rate = rate;
        }

        @Override
        public int compareTo(Object o) {

            if(o instanceof Letter){
                Letter letter = (Letter) o;
                if(this.counts < ((Letter) o).counts)
                    return 1;
                else if(this.counts == ((Letter) o).counts)
                    return 0;
                else
                    return -1;
            }
            return 0;
        }
    }
}
