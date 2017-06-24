package org.staffManage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by K550jk on 2017/6/20.
 */
@SpringBootApplication
@EnableAutoConfiguration
public class Running{
    public static void main(String[] args){
        SpringApplication.run(Running.class,args);
    }
}
