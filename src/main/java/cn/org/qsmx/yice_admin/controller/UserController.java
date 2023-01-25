package cn.org.qsmx.yice_admin.controller;

import cn.org.qsmx.yice_admin.domain.User;
import cn.org.qsmx.yice_admin.mapper.UserMapper;
import cn.org.qsmx.yice_admin.service.UserService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("admin/user/")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户根据ID删除
     * @param id
     * @return
     */
    @GetMapping("delete")
    public String delete(Integer id){
        Map<String,Object> map = new HashMap<>();
        map.put("flag",userService.removeById(id));
        return JSON.toJSONString(map);
    }

    /**
     * 用户-分页查询
     * @param current
     * @param size
     * @return
     */
    @GetMapping("selectAll")
    public String select(int current,int size){
        return JSON.toJSONString(userService.UserMapperCeremony(current,size));
    }

}