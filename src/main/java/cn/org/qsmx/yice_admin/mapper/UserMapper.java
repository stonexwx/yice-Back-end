package cn.org.qsmx.yice_admin.mapper;

import cn.org.qsmx.yice_admin.domain.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;


/**
* @author xwx
* @description 针对表【user】的数据库操作Mapper
* @createDate 2022-04-28 21:30:40
* @Entity cn.org.qsmx.yice_admin.domain.User
*/
public interface UserMapper extends BaseMapper<User> {
    IPage<User> UserMapperConfig(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

}




