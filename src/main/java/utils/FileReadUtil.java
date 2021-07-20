package utils;

import cn.hutool.core.io.FileUtil;
import org.apache.commons.lang3.StringUtils;
import vo.CodeStatisticResponse;

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
        //返回文件大小
        return true;
    }

    /**
     * 读取文件内容
     *
     * @param uri
     * @return
     */
    public static CodeStatisticResponse readFileContent(String uri, String code) {
        try {
            CodeStatisticResponse result = CodeStatisticResponse.builder().fileName(FileUtil.getName(uri)).key(code).build();
            boolean multilineCommentFlag = false;
            BufferedReader in = new BufferedReader(new FileReader(uri));
            StringBuffer content = new StringBuffer();
            String temp = "";
            while (in.ready()) {
                temp = in.readLine();
                result.setCodeLines(result.getCodeLines() + 1);
                content.append(temp);
                // 除去注释前的空格
                temp = temp.trim();
                // 匹配空行
                if (StringUtils.isBlank(temp)) {
                    result.setBlackLines(result.getBlackLines() + 1);
                } else if (temp.startsWith("//")) {
                    result.setNoteLines(result.getNoteLines() + 1);
                } else if (temp.startsWith("/*") && !temp.endsWith("*/")) {
                    result.setNoteLines(result.getNoteLines() + 1);
                    multilineCommentFlag = true;
                } else if (temp.startsWith("/*") && temp.endsWith("*/")) {
                    result.setNoteLines(result.getNoteLines() + 1);
                } else if (multilineCommentFlag == true) {
                    result.setNoteLines(result.getNoteLines() + 1);
                    if (temp.endsWith("*/")) {
                        multilineCommentFlag = false;
                    }
                }
            }
            in.close();
            int oldLength = content.toString().length();
            int newLength = content.toString().replaceAll(code, "EMPTY").length();
            int countAppear = (newLength - oldLength) / (5 - code.length());
            result.setKeyAppearCount(countAppear);

            return result;
        } catch (IOException e) {
            System.out.println("文件读取异常");
        }
        return null;
    }
}