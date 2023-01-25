package cn.org.qsmx.yice_admin.controller;

import cn.org.qsmx.yice_admin.domain.Search;
import cn.org.qsmx.yice_admin.service.SearchService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.*;

@RestController
@RequestMapping("user/search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping("host")
    public String searchHost(@RequestBody String markers){
        JSONObject jsonObject = (JSONObject) JSON.parse(markers);
        JSONArray jsonArray = (JSONArray) JSON.parse((String) jsonObject.get("markers"));
        Map<String,Object> map1 = new HashMap<>();
        List<Search> list =new ArrayList<>();
        for(Object object :jsonArray){
            Map<String,Object> map = (Map<String, Object>) object;
            String latitude = String.valueOf(map.get("latitude")) ;
            String longitude = String.valueOf(map.get("longitude"));
            String latitudeAndLongitude = longitude+","+latitude;
            QueryWrapper<Search> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("latitude_and_longitude",latitudeAndLongitude);
            Search search = searchService.getOne(queryWrapper);
            if(search!=null){
                long time = System.currentTimeMillis();
                int i = (int) ((time-search.getAddtime().getTime())/1000/60);
                if(i>30){
                    search.setChoose(0L);
                    search.setAddtime(new Date());
                    search.setNumber(0L);
                    searchService.updateById(search);
                    list.add(search);
                }else {
                    long number =search.getNumber();
                    long number1 = number+1;
                    search.setNumber(number1);
                    if (searchService.updateById(search)){
                        list.add(search);
                    }
                }

            }else{
                Search search1 = new Search();
                search1.setAddtime(new Date());
                search1.setNumber(1L);
                search1.setLatitudeAndLongitude(latitudeAndLongitude);
                if(searchService.save(search1)){
                   list.add(search1);
                }
            }
        }
        map1.put("flag",true);
        map1.put("data",list);
        return JSON.toJSONString(map1);
    }
    @PostMapping("choose")
    public String searchChoose( String choose){
        QueryWrapper<Search> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("latitude_and_longitude",choose);
        Search search = searchService.getOne(queryWrapper);
        if(search.getChoose()!=null){
            long number = search.getChoose();
            long chooseNumber = number+1;
            search.setChoose(chooseNumber);
            searchService.updateById(search);
        }else{
            search.setChoose(1L);
            searchService.updateById(search);
        }
        return JSON.toJSONString(true);
    }
}
