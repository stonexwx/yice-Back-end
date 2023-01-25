package cn.org.qsmx.yice_admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.qsmx.yice_admin.domain.Advertising;
import cn.org.qsmx.yice_admin.service.AdvertisingService;
import cn.org.qsmx.yice_admin.mapper.AdvertisingMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author xwx
* @description 针对表【advertising】的数据库操作Service实现
* @createDate 2022-04-28 21:30:40
*/
@Service
public class AdvertisingServiceImpl extends ServiceImpl<AdvertisingMapper, Advertising>
    implements AdvertisingService{

    @Resource
    private AdvertisingMapper advertisingMapper;

    @Override
    public Map<String, Object> AdvertisingMapperCeremony(Integer current, Integer size) {
        Page<Advertising> page = new Page<>();
        QueryWrapper<Advertising> queryWrapper = new QueryWrapper<>();
        IPage<Advertising> iPage = advertisingMapper.AdvertisingMapperConfig(page,queryWrapper);
        List<Advertising> advertisingList = iPage.getRecords();
        Map<String,Object> map = new HashMap<>();
        if (advertisingList.size()>0){
            map.put("data",advertisingList);
            map.put("flag",true);
            return map;
        }else{
            map.put("flag",false);
            return map;
        }
    }
}




