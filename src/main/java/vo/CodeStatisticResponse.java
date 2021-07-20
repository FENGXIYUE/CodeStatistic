package vo;

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
     * 待搜索字符
     */
    private String key;
    /**
     * 字符出现次数
     */
    private int keyAppearCount;
    /**
     * 空白行数
     */
    private int blackLines;
}
