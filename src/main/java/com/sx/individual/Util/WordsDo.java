package com.sx.individual.Util;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ShellComponent
public class WordsDo {
    /**
     * 输出文件单词数
     */
    @ShellMethod(key = "wf.exe -f", value = "统计单词个数")
    public static void outWords(String file, @ShellOption(defaultValue = "-1")int n){
        Map<String, Integer> map = new HashMap<>();
        List<String> stringList = IODemoByNIO.readFileByChannel(file);


        //读取处理目标文件
        //String s = "";
        for(int i = 0; i<stringList.size(); i++){
            String s = stringList.get(i);
            s = s.toLowerCase();
            String regex = "\\W+";
            Pattern pat = Pattern.compile(regex);
            Matcher matcher = pat.matcher(s);
            s = matcher.replaceAll(" ");
            //s = s.replace(".", " ");
            String[] ss = s.split("\\s+");
            for(int j = 0; j<ss.length; j++){
                if(ss[j].matches("[a-z]+[0-9]*")){
                    int value = map.getOrDefault(ss[j], 0);
                    map.put(ss[j], value+1);
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
        int i = 0;
        if(n == -1)
            n = Integer.MAX_VALUE;
        System.out.println("单词"+ "         单词数");
        for(Map.Entry<String,Integer> mapping:list){
            if(i < n) {
                System.out.printf("%-13s", mapping.getKey());
                System.out.println(mapping.getValue());
            }
            else
                break;
            i++;
        }
    }

    @ShellMethod(key = "wf.exe -d", value = "统计该目录下所有txt单词个数")
    public static void directoryTxt(String path, @ShellOption(defaultValue = "-1")int n){
        File file = new File(path);

        File[] fileArr = file.listFiles();
        for(File f:fileArr){
            String s = f.getName();
            if(f.isFile() && s.substring(s.length()-3, s.length()).equals("txt")){
                System.out.println(f.getName());
                outWords(f.getPath(), n);
                System.out.println();
            }
        }
    }

    @ShellMethod(key = "wf.exe -d -s", value = "统计该目录下所有目录所有txt单词个数")
    public static void directory(String path, @ShellOption(defaultValue = "-1")int n){
        File file = new File(path);

        File[] fileArr = file.listFiles();

        for(File f :fileArr){
            String s = f.getName();
            if(f.isFile() && s.substring(s.length()-3, s.length()).equals("txt")){
                System.out.println(f.getName());
                outWords(f.getPath(), n);
                System.out.println();
            }
            else if(f.isDirectory()){
                directory(f.getPath(), n);
            }
        }
    }

}
