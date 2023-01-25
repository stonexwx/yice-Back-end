package cn.org.qsmx.yice_admin.controller;

import cn.org.qsmx.yice_admin.domain.Admin;
import cn.org.qsmx.yice_admin.domain.User;
import cn.org.qsmx.yice_admin.service.AdminService;
import cn.org.qsmx.yice_admin.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @PostMapping("login")
    public String login(String phone,String password){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone",phone);
        queryWrapper.eq("password",password);
        Admin admin = this.adminService.getOne(queryWrapper);
        Map<String,Object> map = new HashMap<>();
        if(admin!=null){
            map.put("data",admin);
            map.put("flag",true);
            return JSON.toJSONString(map);
        }else {
            map.put("flag",false);
            return JSON.toJSONString(map);
        }
    }
    /**
     * 获取微信用户唯一标识ID
     *
     * @param wxId 也就是wx.login生成的code，有效时间五分钟，只能使用一次
     * @return 验证成功则返回openid，失败则为null
     */
    @GetMapping("user/login")
    public String getOpenId(String wxId) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + "" +
                "&secret=" + "" +
                "&js_code=" + wxId + "&grant_type=authorization_code";
        JSONObject jsonObject = null;
        URLConnection urlConnection = null;
        try {
            URL urlObject = new URL(url);
            urlConnection = urlObject.openConnection();
            jsonObject= JSON.parseObject(inputStreamProcess(urlConnection));
            User user = new User();
            user.setWxid((String) jsonObject.get("openid"));
            user.setAddtime(new Date());
            QueryWrapper<User> queryWrapper =new QueryWrapper<>();
            queryWrapper.eq("wxid",user.getWxid());
            if(userService.getOne(queryWrapper)==null){
                userService.save(user);
                jsonObject.put("data",userService.getOne(queryWrapper));
            }else {
                jsonObject.put("data",userService.getOne(queryWrapper));
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("data",jsonObject.get("data"));
        return JSON.toJSONString(map);
    }
    /**
     * 获取响应数据.
     *
     * @param urlConnection Http连接对象
     * @return 响应数据
     */
    public static String inputStreamProcess(URLConnection urlConnection) {
        try (InputStream inputStream = urlConnection.getInputStream(); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            return response.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
