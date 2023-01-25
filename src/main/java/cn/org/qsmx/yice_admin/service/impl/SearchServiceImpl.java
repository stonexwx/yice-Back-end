package cn.org.qsmx.yice_admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.qsmx.yice_admin.domain.Search;
import cn.org.qsmx.yice_admin.service.SearchService;
import cn.org.qsmx.yice_admin.mapper.SearchMapper;
import org.springframework.stereotype.Service;

/**
* @author xwx
* @description 针对表【search】的数据库操作Service实现
* @createDate 2022-04-28 21:30:40
*/
@Service
public class SearchServiceImpl extends ServiceImpl<SearchMapper, Search>
    implements SearchService{

}




