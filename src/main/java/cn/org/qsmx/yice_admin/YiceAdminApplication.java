package cn.org.qsmx.yice_admin;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.org.qsmx.yice_admin.mapper")
public class YiceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiceAdminApplication.class, args);
    }

}
