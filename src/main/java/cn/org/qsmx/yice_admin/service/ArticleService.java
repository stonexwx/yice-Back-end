package cn.org.qsmx.yice_admin.service;

import cn.org.qsmx.yice_admin.domain.Article;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author xwx
* @description 针对表【article】的数据库操作Service
* @createDate 2022-04-28 21:30:40
*/
public interface ArticleService extends IService<Article> {
    Map<String,Object> ArticleMaapperCeremony(Integer current , Integer size);
}
