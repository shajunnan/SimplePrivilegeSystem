package org.taru.lanqiao.util;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class IdUtil {
    private static DateTimeFormatter
            formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * 获取UUID
     * @return
     */
    public static String   getUuid(){
        String  uuid =UUID.randomUUID().toString();
        return uuid;
    }

    /**
     * 检验是否为UUID 8-4-4-4-12
     * @param uuid
     * @return
     */
    public static boolean isValidUUID(String uuid) {
        if(uuid == null) {
            return false;
        }
        String regex = "^[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}$";
        if(uuid.matches(regex)) {
            return true;
        }
        return false;
    }

    /**
     * 获取DateId 当前时间年月日时分秒
     * @return
     */
    public static String  getDateId(){
        LocalDateTime dateTime =LocalDateTime.now();
        String str =formatter.format(dateTime);
        return  str;

    }

    /**
     * 获取LongTime 当前时间的毫秒数
     * @return
     */
    public static String  getLongTimeId(){
        long  longValue =System.currentTimeMillis();
        return  String.valueOf(longValue);

    }

    // 测试
    public static void main(String[] args) {
        System.out.println(IdUtil.getDateId());
        System.out.println(IdUtil.getLongTimeId());
    }

}
