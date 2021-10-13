package com.gift.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author liuch
 * @title: CorsConfiguration
 * @description: 跨域请求配置
 * @date 2020/9/3 9:14
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    /**
     * 跨域支持
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600 * 24);
    }

    private static List  getDates(int year, int month) {
        List dates = new ArrayList();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DATE, 1);
        while (cal.get(Calendar.YEAR) == year && cal.get(Calendar.MONTH) < month) {
            int day = cal.get(Calendar.DAY_OF_WEEK);
            if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
                Date date = new Date(cal.getTime().getTime());
                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dates.add(LocalDate.parse(sd.format(date)));
            }
            cal.add(Calendar.DATE, 1);
        }
        return dates;
    }

}
