package domain;

import Util.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[] ) throws FileNotFoundException {
        //Long staet=System.currentTimeMillis();
        //String fileName = "D:\\\\course\\\\软件工程\\ddffg.txt";
        String fileName = "D:\\course\\软件工程";
        //String fileName = "D:\\course\\软件工程\\Love in the Time of Cholera\u0083\u0085.txt";
        //WordsDo.outWords(fileName);

        //WordsDo.directoryTxt(fileName);

        //WordsDo.directory(fileName);

        //WordsDoN.directoryTxt(fileName, 3);

        WordsDoN.directory(fileName, 3);

       // LetterDo.letters(fileName);
    }

}
