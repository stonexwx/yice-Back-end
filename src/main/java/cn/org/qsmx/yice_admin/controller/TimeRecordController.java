package cn.org.qsmx.yice_admin.controller;

import cn.org.qsmx.yice_admin.domain.TimeRecord;
import cn.org.qsmx.yice_admin.domain.User;
import cn.org.qsmx.yice_admin.service.TimeRecordService;
import cn.org.qsmx.yice_admin.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("time")
public class TimeRecordController {
    @Autowired
    private TimeRecordService timeRecordService;
    @Autowired
    private UserService userService;
    /**
     * 保存厕所记录
     * @param timeRecord
     * @return
     */
    @PostMapping("insert")
    public String insertTime(TimeRecord timeRecord){
        timeRecord.setAddtime(new Date());
        return JSON.toJSONString(timeRecordService.save(timeRecord));
    }

    @GetMapping("select")
    public String selectRecord(String date ,long uid){
        QueryWrapper<TimeRecord> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        List<TimeRecord> timeRecordList = timeRecordService.list(queryWrapper);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        JSONArray jsonArray =new JSONArray();
        int count =0;
        for (TimeRecord timeRecord:timeRecordList){
            String a = dateFormat.format(timeRecord.getAddtime());
            if(date.equals(a)){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("endTime",timeRecord.getTime().split("-")[1]);
                jsonObject.put("startTime",timeRecord.getTime().split("-")[0]);
                jsonObject.put("state",timeRecord.getState());
                jsonArray.add(jsonObject);
                count++;
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",jsonArray);
        jsonObject.put("count",count);
        return JSON.toJSONString(jsonObject);
    }
}
