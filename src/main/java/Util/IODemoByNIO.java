package Util;

import org.apache.commons.lang.StringUtils;



import java.io.*;

import java.nio.file.Files;

import java.nio.file.Paths;

import java.util.Collections;
import java.util.List;



/**

 * @Author: shuyizhi @Date: 2018-07-30 14:32 @Description: 通过NIO的方式操作文件

 */

public class IODemoByNIO {



    /**

     * InputStream读取文件(按行读取)

     *

     * @param filePath

     */

    public static void readFileByIO(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return;
        }
        FileInputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = new FileInputStream(filePath);
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String str = null;
            int count = 0;
            while ((str = reader.readLine()) != null) {
                // 输出文件内容
                System.out.println(str);
                count++;
            }
            System.out.println("总共输出: " + count + " 条");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**

     * 通过FileChannel从指定的文件中读取数据(按行读取)

     *

     * @param filePath 文件路径

     */

    public static List<String> readFileByChannel(String filePath) {

        if (StringUtils.isEmpty(filePath)) {
            return Collections.emptyList();
        }
        try {
            List<String> lists = Files.readAllLines(Paths.get(filePath));
            int count = 0;
          //  for (String str : lists) {
                // 输出文件内容
               // System.out.println(str);
               // count++;
          //  }
            return lists;
           // System.out.println("总共输出: " + count + " 条");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
