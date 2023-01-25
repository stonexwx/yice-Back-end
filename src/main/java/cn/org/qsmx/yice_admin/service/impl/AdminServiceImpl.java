package cn.org.qsmx.yice_admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.org.qsmx.yice_admin.domain.Admin;
import cn.org.qsmx.yice_admin.service.AdminService;
import cn.org.qsmx.yice_admin.mapper.AdminMapper;
import org.springframework.stereotype.Service;

/**
* @author xwx
* @description 针对表【admin】的数据库操作Service实现
* @createDate 2022-04-28 21:30:40
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




