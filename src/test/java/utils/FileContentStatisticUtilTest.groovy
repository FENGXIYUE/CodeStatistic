package utils

import spock.lang.Specification
import spock.lang.Unroll
import vo.CodeStatisticResponse

/**
 * @author: LLT* @description:
 * @date: 2021/7/27 11:26 上午
 * @modified By
 */
class FileContentStatisticUtilTest extends Specification {

    @Unroll
    def "GetCodeAppearCount"() {
        given:
        CodeStatisticResponse codeStatisticResponse = CodeStatisticResponse.builder().keyAppearCount(0).codeLines(1).build()
        when:
        FileContentStatisticUtil.getCodeAppearCount(code,codeStatisticResponse,line)
        then:
        appearCount == codeStatisticResponse.getKeyAppearCount()
        where:
        code|line|appearCount
        "llt"|"fafljfkajsflltfaifhaillt"|2
    }
}
