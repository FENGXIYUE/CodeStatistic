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
    private int rowCount;
    private int noteRowCount;
    private int keyAppearCount;
}
