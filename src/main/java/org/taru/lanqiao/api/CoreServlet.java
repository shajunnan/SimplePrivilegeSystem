package org.taru.lanqiao.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.taru.lanqiao.util.IdUtil;
import org.taru.lanqiao.vo.JsonResult;

import java.io.File;
import java.io.IOException;

/**
 * 核心-交互 部分
 * 此处代码考虑移动分散至其他类中
 */
@RestController
public class CoreServlet {
    /**
     * 图片绝对路径，通过Value注解获得属性配置文件信息
     */
    @Value("${upload.absolutePath}")
    private String uploadAbsolutePath;

    /**
     * 查询用户菜单
     * @author
     * @return
     */
    @RequestMapping("/api/user/query/menu")
    public JsonResult queryUserMenu(){
        JsonResult result = null;
        return result;
    }

    /**
     * 查询用户权限
     * @author
     * @return
     */
    @RequestMapping("/api/user/query/priv")
    public JsonResult queryUserPriv(){
        JsonResult result = null;
        result = new JsonResult("200","测试成功",null);
        return result;
    }

    /**
     * 上传图片
     * @author ShaJunnan
     * @param multipartFile
     * @return
     */
    @RequestMapping("/api/upload")
    public JsonResult uploadImageFile(@RequestParam("imageFile") MultipartFile multipartFile){
        JsonResult result = null;
        if(!multipartFile.isEmpty()){
            try {
                String fileName = "";   // 文件名
                String arr[] = multipartFile.getOriginalFilename().split("\\.");
                if(arr != null && arr.length > 0){
                    String suffix = arr[arr.length - 1];    // 文件后缀
                    fileName = IdUtil.getLongTimeId() + "." + suffix;   // 重命名文件
                    multipartFile.transferTo(new File(uploadAbsolutePath + "/" + fileName));
                    result = new JsonResult("200","上传成功",fileName);
                }else{
                    result = new JsonResult("404","上传失败，格式错误","");
                }
            } catch (IOException e) {
                e.printStackTrace();
                result = new JsonResult("500","上传异常","");
            }
        }else {
            result = new JsonResult("404","上传失败，没有数据","");
        }
        return result;
    }
}
