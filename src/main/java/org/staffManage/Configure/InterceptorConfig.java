package org.staffManage.Configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.staffManage.Controller.Interceptor.Admin_interceptor;
import org.staffManage.Controller.Interceptor.Staff_interceptor;

/**
 * Created by K550jk on 2017/6/23.
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(new Admin_interceptor()).addPathPatterns("/admin/**");
        interceptorRegistry.addInterceptor(new Staff_interceptor()).addPathPatterns("/staff/**");
        super.addInterceptors(interceptorRegistry);
    }
}
