package com.rabbit.message.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.cache.Cache;
import com.rabbit.model.Message;
@Component
public class MessageCacheStorage {
	  @Autowired
	  private Cache<String, Message> cache;

	  public void pushMessage(String queue,Message message)
	  {
		  cache.put(queue, message);
		  
	  }
	  
	  public Message getMessage(String queue)
	  {
  		 return cache.getIfPresent(queue);
	  }
	  
}