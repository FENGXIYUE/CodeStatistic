import org.apache.commons.lang3.StringUtils;
import utils.FileReadUtil;

import java.util.Scanner;

/**
 * @author: LLT
 * @description:
 * @date: 2021/7/20 9:54 上午
 * @modified By
 */
public class APPStarter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("欢迎使用lsqdzc代码统计工具："
                + "\r\n" + "请输入对应编码"
                + "\r\n" + "1 统计代码"
                + "\r\n" + "2 需要退出程序");
        while (true) {
            String inputLine = scanner.nextLine();
            if ("2".equals(inputLine)) {
                break;
            } else if ("1".equals(inputLine)) {
                System.out.println("请输入文件路径");
                String fileUri = scanner.nextLine();
                while (!FileReadUtil.checkUri(fileUri)) {
                    System.out.println("文件路径错误,请输入正确文件路径");
                    fileUri = scanner.nextLine();
                }

                System.out.println("请输入待统计的字符");
                String code = scanner.nextLine();
                while (StringUtils.isBlank(fileUri)) {
                    System.out.println("待统计字符为空,请输入正确字符");
                    code = scanner.nextLine();
                }
                System.out.println("统计结果为:");
                System.out.println(FileReadUtil.readFileContent(fileUri, code).toString());
                //休眠1秒，等待用户下次选择
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("\r\n" + "请输入对应编码"
                        + "\r\n" + "1 统计代码"
                        + "\r\n" + "2 需要退出程序");
            } else {
                System.out.println("\r\n" + "请输入对应编码"
                        + "\r\n" + "1 统计代码"
                        + "\r\n" + "2 需要退出程序");
            }
        }
    }
}
