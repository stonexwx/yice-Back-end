package cn.org.qsmx.yice_admin.service;

import cn.org.qsmx.yice_admin.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author xwx
* @description 针对表【user】的数据库操作Service
* @createDate 2022-04-28 21:30:40
*/
public interface UserService extends IService<User> {
    Map<String,Object> UserMapperCeremony(Integer current , Integer size);
}
