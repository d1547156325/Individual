package Util;

import java.util.*;

public class WordsDo {
    public static void outWords(String fileName){
        List<Word> wordList = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        List<String> stringList = IODemoByNIO.readFileByChannel(fileName);
        String s = "";
        for(int i = 0; i<stringList.size(); i++){
            s+=stringList.get(i);
        }
        s = s.toLowerCase();
        s = s.replace(".", " ");
        String[] ss = s.split(" ");
        for(int j = 0; j<ss.length; j++){
            if(ss[j].matches("[a-z]+[0-9]*")){
                if(map.containsKey(ss[j])){
                    int k = map.get(ss[j]);
                    k++;
                    map.put(ss[j], k);
                }
                else
                    map.put(ss[j], 1);
            }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                if(o1.getValue().compareTo(o2.getValue()) == 0){
                    return o1.getKey().compareTo(o2.getKey());
                }
                return -(o1.getValue().compareTo(o2.getValue()));
            }

        });

        System.out.println("单词"+ "         单词数");
        for(Map.Entry<String,Integer> mapping:list){
            System.out.printf("%-13s",mapping.getKey());
            System.out.println(mapping.getValue());
            //System.out.println(mapping.getKey()+"   "+mapping.getValue());
        }

        /*
        Set set = map.entrySet();
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            Map.Entry me = (Map.Entry) iterator.next();
            //System.out.println(me.getKey() + " " + me.getValue());
        }
        */
    }

    public static class Word{
        private String word;
        private int count;

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
