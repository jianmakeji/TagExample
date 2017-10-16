package com.jianma.tagsystem.redis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jianma.tagsystem.redis.SetOperationsTest.IntSerializer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ZSetOperationsTest {

	private RedisTemplate<String, String> template;
	
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	//@Test
	public void addData(){
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(template.getStringSerializer());
		template.setValueSerializer(template.getStringSerializer());
		template.afterPropertiesSet();
		
		template.opsForZSet().add("zSetExample", "jack", 100);
		template.opsForZSet().add("zSetExample", "mark", 90);
		template.opsForZSet().add("zSetExample", "lily", 96);
		template.opsForZSet().add("zSetExample", "brain", 70);
		
	}
	
	
	//@Test
	public void updateData(){
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(template.getStringSerializer());
		template.setValueSerializer(template.getStringSerializer());
		template.afterPropertiesSet();
		
		Set<TypedTuple<String>> tuples = new HashSet<>();
	    TypedTuple<String> tuple0 = new DefaultTypedTuple<String>("c", 92d);  
	    tuples.add(tuple0);
	    TypedTuple<String> tuple1 = new DefaultTypedTuple<String>("d", 91d);  
	    tuples.add(tuple1);
	    TypedTuple<String> tuple2 = new DefaultTypedTuple<String>("e", 85d);  
	    tuples.add(tuple2);
	        
		template.opsForZSet().add("zSetExample", tuples);
	}
	
	@Test
	public void baseCommand(){
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(template.getStringSerializer());
		template.setValueSerializer(template.getStringSerializer());
		template.afterPropertiesSet();
		
		System.out.println(template.opsForZSet().count("zSetExample", 80d, 90d));

		System.out.println(template.opsForZSet().zCard("zSetExample"));
		
	}
}
