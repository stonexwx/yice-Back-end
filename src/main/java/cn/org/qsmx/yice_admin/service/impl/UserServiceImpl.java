package cn.org.qsmx.yice_admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.qsmx.yice_admin.domain.User;
import cn.org.qsmx.yice_admin.service.UserService;
import cn.org.qsmx.yice_admin.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
* @author xwx
* @description 针对表【user】的数据库操作Service实现
* @createDate 2022-04-28 21:30:40
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
    @Resource
    private UserMapper userMapper;
    @Override
    public Map<String,Object> UserMapperCeremony(Integer current, Integer size) {
        Page<User> page = new Page<>(current,size);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        IPage<User> iPage = userMapper.UserMapperConfig(page,queryWrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("data",iPage.getRecords());
        map.put("count",iPage.getTotal());
        return map;
    }
}




