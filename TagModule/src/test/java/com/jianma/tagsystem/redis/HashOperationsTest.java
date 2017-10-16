package com.jianma.tagsystem.redis;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jianma.tagsystem.redis.SetOperationsTest.IntSerializer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class HashOperationsTest {

	private RedisTemplate<String, String> template;
	
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	public static enum StringSerializer implements RedisSerializer<String> {  
        INSTANCE;  
  
        @Override  
        public byte[] serialize(String s) throws SerializationException {  
            return (null != s ? s.getBytes() : new byte[0]);  
        }  
  
        @Override  
        public String deserialize(byte[] bytes) throws SerializationException {  
            if (bytes.length > 0) {  
                return new String(bytes);  
            } else {  
                return null;  
            }  
        }  
    }  
	
	//@Test
	public void addHash(){
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setHashKeySerializer(StringSerializer.INSTANCE);
		template.setHashValueSerializer(template.getStringSerializer());
		template.afterPropertiesSet();
		
		HashOperations<String,String,String> hOps = template.opsForHash();
		
		hOps.put("articles", "article:1", "{'title:'1','abstract':'dadas込込込込亜込込'}");
		hOps.put("articles", "article:2", "{'title:'2','abstract':'dadas込込込込亜込込'}");
		hOps.put("articles", "article:3", "{'title:'3','abstract':'dadas込込込込亜込込'}");
		hOps.put("articles", "article:4", "{'title:'4','abstract':'dadas込込込込亜込込'}");
		hOps.put("articles", "article:5", "{'title:'5','abstract':'dadas込込込込亜込込'}");
		
		
	}
	
	@Test
	public void dataOperation(){
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setHashKeySerializer(StringSerializer.INSTANCE);
		template.setHashValueSerializer(template.getStringSerializer());
		template.afterPropertiesSet();
		
		HashOperations<String,String,String> hOps = template.opsForHash();
		Map<String,String> maps = hOps.entries("articles");
		
		maps.forEach((key,value)->{
			System.out.println(key+"  "+value);
		});
	}
}
