package cn.org.qsmx.yice_admin.controller;

import cn.org.qsmx.yice_admin.domain.Article;
import cn.org.qsmx.yice_admin.domain.TimeRecord;
import cn.org.qsmx.yice_admin.service.ArticleService;
import cn.org.qsmx.yice_admin.service.TimeRecordService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("admin/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private TimeRecordService timeRecordService;
    /**
     * 文章-添加and修改
     * @param article
     * @return
     */
    @PostMapping("insert_update")
    public String insert_update(Article article){
        Map<String,Object> map = new HashMap<>();
        map.put("flag",articleService.saveOrUpdate(article));
        return JSON.toJSONString(map);
    }

    /**
     * 文章-删除
     * @param id
     * @return
     */
    @GetMapping("delete")
    public String delete(Integer id){
        Map<String,Object> map = new HashMap<>();
        map.put("flag",articleService.removeById(id));
        return JSON.toJSONString(map);
    }

    /**
     * 文章-分页查询
     * @param page
     * @param limit
     * @return
     */
    @GetMapping("select")
    public String select(int page,int limit){
        return JSON.toJSONString(articleService.ArticleMaapperCeremony(page, limit));
    }

    @GetMapping("getOne")
    public String getOne(long id){
        QueryWrapper<TimeRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",id);
        List<TimeRecord> timeRecordList = timeRecordService.list(queryWrapper);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Map<String, Integer> map = new HashMap<>();
        map.put("健康",0);
        map.put("便秘",0);
        map.put("窜稀",0);
        map.put("上火",0);
        for(TimeRecord timeRecord:timeRecordList){
            String old = dateFormat.format(timeRecord.getAddtime());
            String newTime = dateFormat.format(new Date());
            if(old.equals(newTime)){
                switch (timeRecord.getState()){
                    case "健康":map.put("健康",map.get("健康")+1);break;
                    case "便秘":map.put("便秘",map.get("便秘")+1);break;
                    case "窜稀":map.put("窜稀",map.get("窜稀")+1);break;
                    case "上火":map.put("上火",map.get("上火")+1);break;
                }
            }
        }
        int length =map.size();
        Collection<Integer> c = map.values();
        Object[] obj = c.toArray();
        Arrays.sort(obj);
        int i = (int) obj[length-1];
        if(map.get("健康")==i){
            QueryWrapper<Article> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("type","健康");
            return JSON.toJSONString(articleService.getOne(queryWrapper1));
        }else if(map.get("便秘")==i){
            QueryWrapper<Article> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("type","便秘");
            return JSON.toJSONString(articleService.getOne(queryWrapper1));
        }else if (map.get("窜稀")== i){
            QueryWrapper<Article> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("type","窜稀");
            return JSON.toJSONString(articleService.getOne(queryWrapper1));
        }else {
            QueryWrapper<Article> queryWrapper1 = new QueryWrapper<>();
            queryWrapper1.eq("type","上火");
            return JSON.toJSONString(articleService.getOne(queryWrapper1));
        }
    }
}
