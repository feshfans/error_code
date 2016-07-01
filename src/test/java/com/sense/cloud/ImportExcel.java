package com.sense.cloud;

import com.sense.cloud.service.ImportService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2016/7/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml","classpath:spring-mybatis.xml"})
public class ImportExcel {

    @Autowired
    private ImportService importService;

    @org.junit.Test
    public void importExcel(){

        String excel="";
        String terminalId="";

        int count=importService.importExcel(excel,terminalId);

        System.out.println("count:"+count);




    }
}
