package vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: LLT
 * @description:
 * @date: 2021/7/20 10:56 上午
 * @modified By
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CodeStatisticResponse {
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 代码行数
     */
    private int codeLines;
    /**
     * 注释行数
     */
    private int noteLines;

    /**
     * 空白行数
     */
    private int blackLines;


    /**
     * 待搜索字符
     */
    private String key;
    /**
     * 字符出现次数
     */
    private int keyAppearCount;

    @Override
    public String toString(){
        return "统计文件：" + this.fileName
                +"\r\n"+"代码行数：" + this.codeLines
                +"\r\n"+"注释行数：" + this.noteLines
                +"\r\n"+"空白行数：" + this.blackLines
                +"\r\n"+"查找字符：" + this.key
                +"\r\n"+"字符出现次数：" + this.keyAppearCount;
    }
}
