package utils

import spock.lang.Specification

/**
 * @author: LLT* @description:
 * @date: 2021/7/23 3:28 下午
 * @modified By
 */
class FileReadUtilTest extends Specification {
    def "CheckUri"() {
        expect:
        result == FileReadUtil.checkUri(uri)
        where:
        uri                                                                                    | result
        "/Users/liulongtao/IdeaProjects/CodeStatistic/src/main/java/APPStarter.java"           | true
        ""                                                                                     | false
        "/Users/liulongtao/IdeaProjects/CodeStatistic/src/main/java"                           | false
        "/Users/liulongtao/IdeaProjects/CodeStatistic/target/classes/utils/FileReadUtil.class" | false
        "/Users/liulongtao/IdeaProjects/CodeStatistic/target/classes/utils/FileReadUtil.class" | false


    }

    def "ReadFileContent"() {
    }
}
