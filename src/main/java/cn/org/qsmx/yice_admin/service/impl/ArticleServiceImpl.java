package cn.org.qsmx.yice_admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.qsmx.yice_admin.domain.Article;
import cn.org.qsmx.yice_admin.service.ArticleService;
import cn.org.qsmx.yice_admin.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author xwx
* @description 针对表【article】的数据库操作Service实现
* @createDate 2022-04-28 21:30:40
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService{
    @Resource
    private  ArticleMapper articleMapper;

    @Override
    public Map<String, Object> ArticleMaapperCeremony(Integer current, Integer size) {

        Page<Article> page = new Page<>();
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        IPage<Article> iPage = articleMapper.ArticleMapperConfig(page,queryWrapper);
        List<Article> articleList = iPage.getRecords();
        Map<String,Object> map = new HashMap<>();
        if (articleList.size()>0){
            map.put("data",articleList);
            map.put("flag",true);
            return map;
        }else{
            map.put("flag",false);
            return map;
        }
    }
}




