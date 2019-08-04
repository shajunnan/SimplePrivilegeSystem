package org.taru.lanqiao.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.taru.lanqiao.dao.CoreDaoImpl;
import org.taru.lanqiao.entity.Menu;
import org.taru.lanqiao.entity.Privilege;
import org.taru.lanqiao.util.IdUtil;
import org.taru.lanqiao.vo.JsonResult;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
    public JsonResult queryUserMenu(@RequestParam("userId") String userId){
        JsonResult result = null;

        try {
            CoreDaoImpl impl = new CoreDaoImpl();
            List<Menu> list = impl.queryUserMenu(userId);
            if(list != null){
                result = new JsonResult("200","查询用户菜单成功",list);
            }else {
                result = new JsonResult("404","查询用户菜单失败",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new JsonResult("500","查询用户菜单异常",null);
        }

        return result;
    }

    /**
     * 查询用户权限
     * @author
     * @return
     */
    @RequestMapping("/api/user/query/priv")
    public JsonResult queryUserPriv(@RequestParam("userId") String userId){
        JsonResult result = null;
        try {
            CoreDaoImpl impl = new CoreDaoImpl();
            List<Privilege> list = impl.queryUserPriv(userId);
            if(list != null){
                result = new JsonResult("200","查询用户权限成功",list);
            }else {
                result = new JsonResult("404","查询用户权限失败",null);
            }
        }catch (Exception e){
            e.printStackTrace();
            result = new JsonResult("500","查询用户权限异常",null);
        }

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
