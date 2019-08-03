package org.taru.lanqiao.vo;

import java.io.Serializable;

/**
 * 返回Json结果
 * vo 即 value object
 * 使用网络传输的对象应该实现序列化接口
 */
public class JsonResult implements Serializable {
    String status;
    String message;
    Object data;

    // 构造函数
    public JsonResult(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // get set 方法
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
