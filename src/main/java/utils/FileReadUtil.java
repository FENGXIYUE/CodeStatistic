package utils;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author: LLT
 * @description:
 * @date: 2021/7/20 9:36 上午
 * @modified By
 */
public class FileReadUtil {
    /**
     * 判断文件路径是否正确
     *
     * @param uri
     * @return
     */
    public static boolean checkUri(String uri) {
        //判断传入的uri路径是否合法
        if (StringUtils.isBlank(uri)) {
            return false;
        }
        File file = new File(uri);
        //判断文件是否存在以及对应的路径下是否为文件而不是文件夹
        if (!file.exists() || !file.isFile()) {
            return false;
        }
        return true;//返回文件大小
    }

    /**
     * 读取文件内容
     *
     * @param uri
     * @return
     */
    public static String readFileContent(String uri) {
        try {
            BufferedReader in = new BufferedReader(new FileReader("runoob.txt"));
            StringBuffer content = new StringBuffer();
            while (in.ready()) {
                content.append(in.readLine());
            }
            in.close();
            return content.toString();
        } catch (IOException e) {
            System.out.println("文件读取异常");
        }
        return null;
    }
}
