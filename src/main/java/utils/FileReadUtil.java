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
        CodeStatisticResponse result = CodeStatisticResponse.builder().fileName(FileUtil.getName(uri)).key(code).build();
        //记录文件内容
        StringBuilder content = new StringBuilder();
        //记录缓冲行
        String tempLine = StringUtils.EMPTY;
        //多行注释的标志 以/*开头
        boolean multilineCommentFlag = false;
        try {
            //文件读入流
            BufferedReader in = new BufferedReader(new FileReader(uri));
            while (in.ready()) {
                tempLine = in.readLine();
                //总行数加1
                result.setCodeLines(result.getCodeLines() + 1);
                content.append(tempLine);
                // 除去注释前的空格
                tempLine = tempLine.trim();
                // 匹配空行
                if (StringUtils.isBlank(tempLine)) {
                    System.out.println("空白行为：" + result.getCodeLines());
                    result.setBlackLines(result.getBlackLines() + 1);
                    //匹配单行注释
                } else if (tempLine.startsWith("//")) {
                    result.setNoteLines(result.getNoteLines() + 1);
                    //匹配多行注释，注释内容在多行
                } else if (tempLine.startsWith("/*") && !tempLine.endsWith("*/")) {
                    result.setNoteLines(result.getNoteLines() + 1);
                    multilineCommentFlag = true;
                    //匹配多行注释，注释内容在一行
                } else if (tempLine.startsWith("/*") && tempLine.endsWith("*/")) {
                    result.setNoteLines(result.getNoteLines() + 1);
                    //多行注释，注释行数加1
                } else if (multilineCommentFlag) {
                    result.setNoteLines(result.getNoteLines() + 1);
                    //多行解释结尾
                    if (tempLine.endsWith("*/")) {
                        multilineCommentFlag = false;
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("文件读取异常");
        }
        int countAppear = getCountAppear(code, content);
        result.setKeyAppearCount(countAppear);
        return result;
    }


    /**
     * 计算String中,指定字符出现次数
     *
     * @param code
     * @param content
     * @return
     */
    private static int getCountAppear(String code, StringBuilder content) {
        int oldLength = content.toString().length();
        int newLength = content.toString().replaceAll(code, code + "@").length();
        int countAppear = newLength - oldLength;
        return countAppear;
    }
}