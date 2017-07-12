package org.staffManage.Configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by K550jk on 2017/6/20.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3000)
public class SessionConfiguration{
    @Bean
    public static ConfigureRedisAction configureRedisAction(){
                return ConfigureRedisAction.NO_OP;
    }
}
