package com.sx.individual.Util;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ShellComponent
public class PhraseDo {

    @ShellMethod(key = "wf.exe -p" ,value="查询短语个数")
    public static void outPhrase(String fileName, int number){
        Long startTime = System.currentTimeMillis();

        Map<String, Integer> map = new HashMap<>();
        List<String> stringList = IODemoByNIO.readFileByChannel(fileName);
        for(int i = 0; i<stringList.size(); i++){
            String s = stringList.get(i);
            s = s.toLowerCase();
            String regex = "\\W+";
            Pattern pat = Pattern.compile(regex);
            Matcher matcher = pat.matcher(s);
            s = matcher.replaceAll(" ");
            String[] ss = s.split("\\s+");
            for(int j = 0; j<ss.length; j+=number){
                    int end = (j+number-1)<=ss.length?(j+number-1):ss.length;
                    if(end < ss.length){
                        String str = "";
                        for(int k = j; k<=end; k++){
                            str += (ss[k] + " ");
                        }
                       // System.out.println(str);
                        int value = map.getOrDefault(str, 0);
                        map.put(str, value+1);
                    }
            }
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                if(o1.getValue().compareTo(o2.getValue()) == 0){
                    return o1.getKey().compareTo(o2.getKey());
                }
                return -(o1.getValue().compareTo(o2.getValue()));
            }

        });

        System.out.println("短语"+ "                   短语数");
        for(Map.Entry<String,Integer> mapping:list){
                System.out.printf("%-25s", mapping.getKey());
                System.out.println(mapping.getValue());
        }

        System.out.println("耗时" + (System.currentTimeMillis() - startTime));
    }

    @ShellMethod(key = "wf.exe -v" ,value="查询动词形态个数")
    public static void verbDo(String fileName){
        Map<String, Integer> map = new HashMap<>();
        List<String> stringList = IODemoByNIO.readFileByChannel(fileName);
        for(int i = 0; i<stringList.size(); i++) {
            String s = stringList.get(i);
            s = s.toLowerCase();
            String regex = "\\W+";
            Pattern pat = Pattern.compile(regex);
            Matcher matcher = pat.matcher(s);
            s = matcher.replaceAll(" ");
            String[] ss = s.split("\\s+");
            int value = map.getOrDefault(ss[0], 0);
            map.put(ss[0] ,value + ss.length);
        }
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
            //排序
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                if(o1.getValue().compareTo(o2.getValue()) == 0){
                    return o1.getKey().compareTo(o2.getKey());
                }
                return -(o1.getValue().compareTo(o2.getValue()));
            }

        });

        System.out.println("动词"+ "                   动词数");
        for(Map.Entry<String,Integer> mapping:list){
            System.out.printf("%-25s", mapping.getKey());
            System.out.println(mapping.getValue());
        }
    }
}
