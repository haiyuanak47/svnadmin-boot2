package org.svnadmin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.svnadmin.common.util.HttpUtils;
import org.svnadmin.util.interceptor.AdminInterceptor;

import javax.servlet.ServletContext;
@PropertySource("classpath:setting.properties")
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {

    @Value("${setting.sys_name}")
    private String sys_name;

    @Value("${setting.assetsPath}")
    private String assetsPath;

    @Value("${setting.framePath}")
    private String framePath;

    @Value("${setting.appPath}")
    private String appPath;

    @Autowired
    AdminInterceptor adminInterceptor;

    /**
     * 表示这些配置的表示静态文件所处路径， 不用拦截
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/assets/");
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/favicon.ico");
       /* registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");*/
       /*
        registry.addResourceHandler("/templates/**")
                .addResourceLocations("classpath:/templates/");*/
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //后台拦截器
        InterceptorRegistration registration = registry.addInterceptor(adminInterceptor);     //拦截的对象会进入这个类中进行判断
        registration.addPathPatterns("/**");                    //所有路径都被拦截
        registration.excludePathPatterns("/assets/**","/login","/favicon.ico");       //添加不拦截路径
    }

    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
        ServletContext context = getServletContext();
        //String sysPath = PropUtils.get("setting.sys_path");
        String sysName = HttpUtils.urlDecode(sys_name, "utf-8");
        context.setAttribute("sysName", sysName);
        //设置系统应用根目录
        context.setAttribute("assetsPath",  assetsPath);
        context.setAttribute("framePath", framePath);
        context.setAttribute("appPath", appPath);
    }
}
