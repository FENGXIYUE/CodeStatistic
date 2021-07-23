package utils;

import vo.CodeStatisticResponse;

/**
 * @author: LLT
 * @description:
 * @date: 2021/7/23 5:59 下午
 * @modified By
 */
public class FileContentStatisticUtil {

    /**
     * 统计注释行数
     *
     * @param result
     * @param tempLine
     * @param multilineCommentFlag
     * @return
     */
    public static boolean getNotesLines(CodeStatisticResponse result, String tempLine, boolean multilineCommentFlag) {
        // 除去注释前的空格
        tempLine = tempLine.trim();
        //匹配单行注释 "//" 或 多行注释在一行
        if (tempLine.startsWith("//") || (tempLine.startsWith("/*") && tempLine.endsWith("*/"))) {
            result.setNoteLines(result.getNoteLines() + 1);
            //匹配多行注释，注释内容在多行
        } else if (tempLine.startsWith("/*") && !tempLine.endsWith("*/")) {
            result.setNoteLines(result.getNoteLines() + 1);
            multilineCommentFlag = true;
            //匹配多行注释，每行注释加1
        } else if (multilineCommentFlag) {
            result.setNoteLines(result.getNoteLines() + 1);
            //多行解释结尾
            if (tempLine.endsWith("*/")) {
                multilineCommentFlag = false;
            }
        }
        return multilineCommentFlag;
    }

    /**
     * 统计字符出现次数
     *
     * @param code
     * @param result
     * @param tempLine
     */
    public static void getCodeAppearCount(String code, CodeStatisticResponse result, String tempLine) {
        if (tempLine.contains(code)) {
            //字符出现次数加1
            result.setKeyAppearCount(result.getKeyAppearCount() + 1);
            //字符所在位置更新
            result.getPositionRecord().add("java:" + result.getCodeLines());
        }
    }

}
