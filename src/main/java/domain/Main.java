package domain;

import Util.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[] ) throws FileNotFoundException {

        String stopwords = "D:\\course\\软件工程\\stopwords.txt";
        //Long staet=System.currentTimeMillis();
        //String fileName = "D:\\course\\\\软件工程\\ddffg.txt";
        String fileName = "D:\\course\\软件工程";
        //String fileName = "D:\\course\\软件工程\\Love in the Time of Cholera\u0083\u0085.txt";
        //WordsDo.outWords(stopwords, fileName, -1);

        WordsDo.directoryTxt(stopwords, fileName, 5);
        //WordsDoN.directoryTxt(stopwords, fileName, 10);

        //PhraseDo.outPhrase(fileName, 2);
       // PhraseDo.verbDo(fileName);

        //WordsDo.directory(fileName);

       // LetterDo.letters(fileName);
    }

}
