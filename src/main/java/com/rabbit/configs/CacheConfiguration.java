package com.rabbit.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.rabbit.model.Message;

@Configuration
public class CacheConfiguration {

  @Bean
  public Cache<String, Message> cache() {
    return   CacheBuilder.newBuilder().softValues().build();
  }

}
