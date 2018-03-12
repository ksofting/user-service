package com.zeus.userservice;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests{
    
    @Resource
    RestTemplate restTemplate;
    
    @Resource
    HttpMessageConverters fastJsonHttpMessageConverters;
    
    @Test
    public void contextLoads(){
        try{
            List<Long> ids= Lists.newArrayList();
            ids.add(12480l);
            ids.add(21986l);
            Object map= restTemplate.postForObject("http://user-data/emp/get-employee",
                ids,
                Object.class);
            System.out.println(map);
        }
        catch(RestClientException e){
            e.printStackTrace();
        }
    }
    
    // @Test
    public void testConverter(){
        Iterator<HttpMessageConverter<?>> iter= fastJsonHttpMessageConverters.iterator();
        while(iter.hasNext()){
            HttpMessageConverter<?> con= iter.next();
            System.out.println(con.toString()+ ":");
            List<MediaType> types= con.getSupportedMediaTypes();
            for(MediaType mediaType : types){
                System.out.println("  "+ mediaType.toString());
            }
        }
    }
}
