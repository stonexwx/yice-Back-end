package cn.org.qsmx.yice_admin.mapper;

import cn.org.qsmx.yice_admin.domain.Advertising;
import cn.org.qsmx.yice_admin.domain.Article;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.Resource;


/**
* @author xwx
* @description 针对表【advertising】的数据库操作Mapper
* @createDate 2022-04-28 21:30:40
* @Entity cn.org.qsmx.yice_admin.domain.Advertising
*/
@Mapper
public interface AdvertisingMapper extends BaseMapper<Advertising> {
    IPage<Advertising> AdvertisingMapperConfig(Page<Advertising> page, @Param(Constants.WRAPPER) Wrapper<Advertising> wrapper);
}




