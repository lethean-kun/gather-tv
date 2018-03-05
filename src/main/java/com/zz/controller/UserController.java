package com.zz.controller;

import com.zz.model.Parameter;
import com.zz.model.Result;
import com.zz.model.User;
import com.zz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dzk
 * Created by lethean on 2017/12/31.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 文件上传根目录(在Spring的application.yml的配置文件中配置):<br>
     * web:
     * upload-path: （jar包所在目录）/resources/static/
     */
    @Value("${web.upload-path}")
    private String webUploadPath;

    @ResponseBody
    @RequestMapping("register")
    public Result register(User user,HttpServletRequest request){
        Result result = new Result();
        String username = request.getParameter("username");
        int status = userService.register(user);
        if(status>0){
            result.setStatus(1);
            result.setMessage("注册成功");
            request.getSession().setAttribute("user",user);
        }else {
            result.setStatus(0);
            result.setMessage("注册失败");
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("login")
    public Result login(User user,HttpServletRequest request){
        Result result = new Result();
        int status = userService.getUser(user);
        if(status==1){
            result.setStatus(1);
            result.setMessage("登陆成功");
            User zUser = userService.togetUser(user);
            request.getSession().setAttribute("user",zUser);
        }else if(status==-1){
            result.setStatus(-1);
            result.setMessage("密码错误");
        }else if(status==0){
            result.setStatus(0);
            result.setMessage("用户不存在");
        }
        return result;
    }

    @RequestMapping("logout")
    public void logout(HttpServletRequest request,HttpServletResponse response) throws IOException {

        request.getSession().invalidate();
        response.sendRedirect("/toLiveList");
    }

    @ResponseBody
    @RequestMapping("check")
    public Result check(String username,HttpServletRequest request){
        Result result = new Result();

        User user = userService.getUserName(username);
        if(user==null){
            result.setStatus(1);
            result.setMessage("昵称可用");
        }else {
            result.setStatus(0);
            result.setMessage("昵称不可用");
        }
        return result;
    }

    @RequestMapping("toUserInfo")
    public String toUserInfo(HttpServletRequest request,
                               Parameter parameter){

        return "users-twitter/userIngo";
    }

    /**
     * 基于用户标识的头像上传
     * @param file 图片
     * @param userId 用户标识
     * @return
     */
    @RequestMapping(value = "headUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result headUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") Integer userId) {
        Result resultVo = new Result();
        if (!file.isEmpty()) {
            if (file.getContentType().contains("image")) {
                try {
                    String temp = "images" + File.separator + "upload" + File.separator;
                    // 获取图片的文件名
                    String fileName = file.getOriginalFilename();
                    // 获取图片的扩展名
                    String extensionName = StringUtils.substringAfter(fileName, ".");
                    // 新的图片文件名 = 获取时间戳+"."图片扩展名
                    String newFileName = String.valueOf(System.currentTimeMillis()) + "." + extensionName;
                    // 数据库保存的目录
                    String datdDirectory = temp.concat(String.valueOf(userId)).concat(File.separator);
                    // 文件路径
                    String filePath = webUploadPath.concat(datdDirectory);

                    File dest = new File(filePath, newFileName);
                    if (!dest.getParentFile().exists()) {
                        dest.getParentFile().mkdirs();
                    }
                    // 判断是否有旧头像，如果有就先删除旧头像，再上传
//                    User userInfo = sUserService.findUserInfo(userId.toString());
//                    if (StringUtils.isNotBlank(userInfo.getUserHead())) {
//                        String oldFilePath = webUploadPath.concat(userInfo.getHeadPic());
//                        File oldFile = new File(oldFilePath);
//                        if (oldFile.exists()) {
//                            oldFile.delete();
//                        }
//                    }
                    // 上传到指定目录
                    file.transferTo(dest);

//
//                    {
//                        "code": 0,
//                        "msg": "",
//                        "data": {
//                            "src": "http://cdn.layui.com/123.jpg"
//                        }
//                    }


                    // 将反斜杠转换为正斜杠
                    String data = datdDirectory.replaceAll("\\\\", "/") + newFileName;
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("file", data);
                    resultVo.setStatus(0);
                    resultVo.setData(resultMap);
                    resultVo.setMessage("上传成功!");
                } catch (IOException e) {
                    resultVo.setStatus(1);
                    resultVo.setMessage("上传失败!");
                }
            } else {
                resultVo.setMessage("上传的文件不是图片类型，请重新上传!");
            }
            return resultVo;
        } else {
            resultVo.setMessage("上传失败，请选择要上传的图片!");
            return resultVo;
        }
    }
}
