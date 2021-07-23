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
    private static String JAVA = "java";

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
        //判断是否为java文件
        if (!JAVA.equals(FileUtil.getType(file))) {
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
        //记录缓冲行
        String tempLine = StringUtils.EMPTY;
        //多行注释的标志 以/*开头
        boolean multilineCommentFlag = false;
        try {
            //文件读入流
            BufferedReader in = new BufferedReader(new FileReader(uri));
            while (in.ready()) {
                tempLine = in.readLine();
                //统计总行数
                result.setCodeLines(result.getCodeLines() + 1);
                //统计字符出现次数z
                FileContentStatisticUtil.getCodeAppearCount(code, result, tempLine);
                if (StringUtils.isNotBlank(tempLine)) {
                    //统计注释行数
                    multilineCommentFlag = FileContentStatisticUtil.getNotesLines(result, tempLine, multilineCommentFlag);
                } else {
                    //统计空白行
                    result.setBlackLines(result.getBlackLines() + 1);
                }
            }
            in.close();
        } catch (IOException e) {
            System.err.println("文件读取异常");
        }
        return result;
    }


}