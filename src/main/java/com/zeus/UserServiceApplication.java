package com.zeus;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;

@SpringCloudApplication
@EnableFeignClients
public class UserServiceApplication{
    
    public static void main(String[] args){
        
        SpringApplication.run(UserServiceApplication.class,args);
    }
    
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(FastJsonHttpMessageConverter fastJsonHttpMessageConverter){
        RestTemplate restTemplate= new RestTemplate();
        List<HttpMessageConverter<?>> converters= restTemplate.getMessageConverters();
        List<HttpMessageConverter<?>> newConverters= Lists.newArrayList();
        for(HttpMessageConverter<?> converter : converters){
            if(converter instanceof MappingJackson2HttpMessageConverter
                || converter instanceof MappingJackson2XmlHttpMessageConverter){
                continue;
            }
            else{
                newConverters.add(converter);
            }
        }
        newConverters.add(fastJsonHttpMessageConverter);
        restTemplate.setMessageConverters(newConverters);
        return restTemplate;
    }
}
