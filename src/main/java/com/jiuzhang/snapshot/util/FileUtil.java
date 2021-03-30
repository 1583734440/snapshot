package com.jiuzhang.snapshot.util;

import java.io.*;

/**
 * @Description 读取文件内容
 * @Date 2021/1/28 14:58
 * @Author FU
 */
public class FileUtil {

    public static String readFile(File file) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return content.toString();
    }
}
