package net.chenlin.dp.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateRandomCode {

    private static final String STR = "0123456789abcdefghijklmnopqrstuvwxyz";

    /**
     *
     * @param passLength 数据书长度
     * @param strCount  生成多少个
     * @return
     */
    public static List<String> getRandomCode(int passLength, int strCount) {
        Random random = new Random();
        List<String> listString = new ArrayList<>();
        //生成10条长度为1-10的随机字符串
        for (int i = 0; i < strCount ; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            //确定字符串长度
//            int stringLength = (int) (Math.random()* (passLength+1));
            for (int j = 0; j < passLength; j++) {
                //先随机生成初始定义的字符串 str 的某个索引，以获取相应的字符
                int index = random.nextInt(STR.length());
                char c = STR.charAt(index);
                stringBuffer.append(c);
            }
            //判断当前的list容器中是否已有刚生成的字符串，满足每条字符串不可重复性
            if (!(listString.contains(stringBuffer.toString()))) {
                listString.add(stringBuffer.toString());
                System.out.println(stringBuffer +".xyz");
            }else {
                i--;
            }
        }
        return listString;
    }

    public static void main(String[] args) {
        List<String> listString = getRandomCode(6,200);

    }

}
