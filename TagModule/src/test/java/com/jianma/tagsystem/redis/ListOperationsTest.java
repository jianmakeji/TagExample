package com.jianma.tagsystem.redis;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jianma.tagsystem.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ListOperationsTest {

	@Autowired
	private RedisTemplate<String, Person> template;
	
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	@Autowired
	private RedisTemplate<String, String> templateString;
	
	//@Test
	public void ListTest(){
		
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<Person>(Person.class));
		/*
		String random = template.randomKey();
		System.out.println(random);
		
		template.opsForValue().set("person:3", new Person("John", "Smith"));
		*/
		ListOperations<String, Person> listOps = template.opsForList();
		listOps.rightPush("PersonList", new Person("Jane", "Smith"));
	}
	
	public void addData(){
		
	}
}
