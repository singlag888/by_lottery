package com.hash.by_lottery.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName lotteryUtils
 * @Descritption 彩票数据算法工具
 * @Author Hash
 * @Date 2019/3/20 20:30
 * @Version 1.0
 **/

public class lotteryUtils {

    /**
     * @Description: 返回龙虎数据
     * @Param: [code]
     * @return: java.lang.String[]
     * @Author: Hash
     * @Date: 2019/3/20
     */
    public static List<Integer> DragonTiger(int[] code) {
        List<Integer> list = new ArrayList<Integer>();
        switch (code.length) {

            case 10:

                for (int i = 0; i < 5; i++) {
                    list.add(i, Dragon_Tiger(code[i], code[code.length - i - 1]));
                }
                break;
            case 8:
                for (int i = 0; i < 4; i++) {
                    list.add(i, Dragon_Tiger(code[i], code[code.length - i - 1]));
                }
                break;
            case 5:
                list.add(0, Dragon_Tiger(code[0], code[code.length - 1]));
                break;
        }
        return list;
    }

    /**
     * @Description: 返回龙虎list
     * @Param: [dragon, tiger]
     * @return: java.lang.String
     * @Author: Hash
     * @Date: 2019/3/21
     */
    private static int Dragon_Tiger(int dragon, int tiger) {
            return  (dragon == tiger) ? 2 : dragon > tiger ? 0 : 1;
    }

    /**
     * @Description: 返回总和
     * @Param: []
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int sum_All(int[] code) {
        int sum = 0;
        for (int i :
                code) {
            sum += i;
        }
        return sum;
    }

    /**
     * @Description: 返回冠亚和
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/20
     */
    public static int sum_FS(int[] code) {
        return code[0] + code[1];
    }

    /**
     * @Description: 返回总和大小 {0：大，1：小 2:和 4：通吃}
     * @Param: [code, type]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/28
     */
    public static int sum_BS(int[] code, int type) {

        int sum = sum_All(code);

        switch (type) {
            case 1:

                return (code[0] + code[1]) > 11 ? 0 : 1;
            case 2:

                return sum > 22 ? 0 : 1;

            case 3:

                return sum == 30 ? 2 : (sum > 30 ? 0 : 1);

            case 4:

                return sum == 84 ? 2 : (sum > 84 ? 0 : 1);

            case 6:

                return sum == 84 ? 2 : (sum > 84 ? 0 : 1);

            case 5:

                return (code[0] == code[1]) && (code[1] == code[2]) ? 4 : (sum > 10 ? 0 : 1);

        }
        return sum;
    }

    /**
     * @Description: 返回总和单双 {0：单，1：双}
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/20
     */
    public static int sum_SD(int[] code) {

        return (sum_All(code) % 2) != 0 ? 0 : 1;
    }

    public static int FS_sum_SD(int[] code) {
        return (code[0]+code[1])% 2 != 0 ? 0 : 1;
    }

    /**
     * @Description: 返回 前三，中三，后三 数组 (0-杂六，1-半顺，2-顺子，3-对子，4-豹子)
     * @Param: [code]
     * @return: java.lang.String[]
     * @Author: Hash
     * @Date: 2019/3/21
     */
    public static int[] threeFMB(int[] code) {
        int[] str = new int[3];
        str[0] = FMB(code[0], code[1], code[2]);
        str[1] = FMB(code[1], code[2], code[3]);
        str[2] = FMB(code[2], code[3], code[4]);
        return str;
    }

    /**
     * @Description: 返回 (0-杂六，1-半顺，2-顺子，3-对子，4-豹子)
     * @Param: [one, two, three]
     * @return: java.lang.String
     * @Author: Hash
     * @Date: 2019/3/21
     */
    private static int FMB(int one, int two, int three) {
        List<Integer> list = new ArrayList<>();
        list.add(one);
        list.add(two);
        list.add(three);
        Collections.sort(list);
        if (list.contains(0)) {
            if (list.contains(9)) {
                if (simpleFun(one, two, three) == 3) {
                    return 3;
                }
                if (list.contains(8) || list.contains(1)) {
                    return 2;
                } else {
                    return 1;
                }
            } else {
                simpleFun(one, two, three);
            }

        } else {
            simpleFun(one, two, three);
        }
        return simpleFun(one, two, three);
    }


    /**
     * @Description: 不带0 的前中后三比较
     * @Param: [one, two, three]
     * @return: java.lang.String
     * @Author: Hash
     * @Date: 2019/3/21
     */
    private static int simpleFun(int one, int two, int three) {
        if (Math.abs(one - two) == 0 && Math.abs(one - three) == 0) {
            return 4;
        }
        if (Math.abs(one - two) == 0 || Math.abs(one - three) == 0 || Math.abs(two - three) == 0) {
            return 3;
        }
        if (Math.abs(one - two) == 1 && Math.abs(one - three) == 1) {
            return 2;
        }
        if ((Math.abs(one - two) == 1 || Math.abs(one - three) == 1 || Math.abs(two - three) == 1) || (Math.abs(one - two) == 9 || Math.abs(one - three) == 9 || Math.abs(two - three) == 9)) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * @Description: 判断尾数大小(0 - 大 ， 1 - 小)
     * @Param: [code]
     * @return: java.lang.String
     * @Author: Hash
     * @Date: 2019/3/22
     */
    public static int tail_BS(int[] code) {

        return (sum_All(code) % 10) < 5 ? 1:0;
    }


    /**
     * @Description: 百十和
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int hundredTenSum(int[] code) {
        return code[0] + code[1];
    }

    /**
     * @Description: 百个和
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int hundredOneSum(int[] code) {

        return code[0] + code[2];
    }

    /**
     * @Description: 个十和
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int TenOneSum(int[] code) {

        return code[2] + code[1];
    }

    /**
     * @Description: 百十单双
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int hundredTenSingleEven(int[] code) {

        return (hundredTenSum(code) % 2 == 0) ? 1 : 0;
    }

    /**
     * @Description: 百个单双
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int hundredOneSingleEven(int[] code) {

        return (hundredOneSum(code) % 2 == 0) ? 1 : 0;
    }

    /**
     * @Description: 十个单双
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int TenOneSingleEven(int[] code) {
       return  (TenOneSum(code) % 2 == 0) ? 1 : 0;
    }

    /**
     * @Description: 百十大小
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int hundredTenBigSmall(int[] code) {

        return (hundredTenSum(code) % 10 < 5) ?  1: 0;
    }

    /**
     * @Description: 百个大小
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int hundredOneBigSmall(int[] code) {
        return (hundredOneSum(code) % 10 < 5) ? 1 : 0 ;
    }

    /**
     * @Description: 十个大小
     * @Param: [code]
     * @return: int
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static int TenOneBigSmall(int[] code) {

        return (TenOneSum(code) % 10 < 5) ? 1 : 0;
    }

    /**
     * @Description:返回下期时间
     * @Param: [time, s]
     * @return: java.lang.String
     * @Author: Hash
     * @Date: 2019/3/22
     */
    public String next_time(String time, int s) {
        return String.valueOf(Integer.parseInt(time) + s);
    }

    /**
     * @Description: String类型datetime转时间戳
     * @Param: [date_str]
     * @return: java.lang.String
     * @Author: Hash
     * @Date: 2019/3/22
     */
    public static String date2TimeStamp(String date_str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return String.valueOf(sdf.parse(date_str).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @Description: 判断是否为数字
     * @Param: [code, issue]
     * @return: boolean
     * @Author: Hash
     * @Date: 2019/3/23
     */
    public static boolean CodeVerification(String code, String issue) {
        if (code.matches("[0-9]+") && issue.matches("[0-9]+")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean CodeVerification(String code) {
        if (code.matches("[0-9]+")) {
            return false;
        } else {
            return true;
        }
    }


}
