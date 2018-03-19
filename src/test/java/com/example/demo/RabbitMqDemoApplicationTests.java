package com.example.demo;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import com.rabbit.RabbitMqDemoApplication;
import com.rabbit.message.cache.MessageCacheStorage;
import com.rabbit.model.Message;
import com.rabbit.service.RabbitService;

import junit.framework.Assert;

/**
 * @author Justin Mathew
 *
 * Created On 19-Mar-2018
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT,
classes = RabbitMqDemoApplication.class)
public class RabbitMqDemoApplicationTests {
	@Autowired
	RabbitService rabbitService;
	
	@Autowired
	MessageCacheStorage messageCacheStorage;
	 
	static final String QUEUE_NAME="testQueue";
	
	/*Test for checking the pushing of message 
	 * to guava cache is 
	 * happening as expected*/
	@Test
	public void testCachePushing()
	{
	 messageCacheStorage.pushMessage(QUEUE_NAME, dummyMessage());
     Message message=messageCacheStorage.getMessage("testQueue");
     Assert.assertTrue("TestMessage".equals(message.getMessage()));
     messageCacheStorage.invalidate(QUEUE_NAME);
     message=messageCacheStorage.getMessage(QUEUE_NAME);
     Assert.assertNull(message);     
	}
	
	private static Message dummyMessage()
	{
		return Message.builder()
		  .message("TestMessage")
		  .createdOn(new Date())
		  .build();
		
	}
} 
