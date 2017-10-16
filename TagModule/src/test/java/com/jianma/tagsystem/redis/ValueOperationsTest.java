package com.jianma.tagsystem.redis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jianma.tagsystem.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class ValueOperationsTest {

	@Autowired
	private RedisTemplate<String, String> template;

	// inject the template as ValueOperations
	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOps;

	@Resource(name = "jedisConnectionFactory")
	private JedisConnectionFactory jedisConnectionFactory;
	
	@Test
	public void operationTest() {
		//valueOps.set("hello2", "world2");
		
		//template.opsForValue().set("hello3", "world");
		//template.opsForValue().set("id", "1");
		/*
		System.out.println("==================================");
		
		System.out.println(template.opsForValue().get("id"));
		template.opsForValue().increment("id", 10);
		System.out.println(template.opsForValue().get("id"));
		*/
		System.out.println("==================================");
		
		//template.opsForValue().append("hello3", " world");
		
		//template.opsForValue().increment("id", -3);
		//System.out.println(template.opsForValue().get("id"));
		
		//System.out.println(template.opsForValue().getAndSet("hello3", " world"));
		
		/*
		template.execute(new RedisCallback<Object>(){

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				Long size = connection.dbSize();
				System.out.println(size);
			      // Can cast to StringRedisConnection if using a StringRedisTemplate
			    ((StringRedisConnection)connection).set("corporation", "microsoft");
				return size;
			}
			
		});
		
		*/
		
		RedisTemplate<String, Object> redis = new RedisTemplate<String, Object>();  
		redis.setConnectionFactory(jedisConnectionFactory);  
		redis.setKeySerializer(StringSerializer.INSTANCE);  
		redis.setValueSerializer(new Jackson2JsonRedisSerializer<Person>(Person.class));  
		redis.afterPropertiesSet();  
		  
		ValueOperations<String, Object> ops = redis.opsForValue();  
        
		Person person = new Person();
		person.setFirstName("mark");
		person.setFirstName("Micol");
		
		ops.set("pseron:2", person);
		
		//System.out.println(personValueOps.get("person:2").getFirstName());
	}
	
	
	public void TestString(){
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new Jackson2JsonRedisSerializer<Person>(Person.class));
		
		String random = template.randomKey();
		System.out.println(random);
		
		//template.opsForValue().set("person:3", new Person("John", "Smith"));
	}
	
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
  
    public static enum LongSerializer implements RedisSerializer<Long> {  
        INSTANCE;  
  
        @Override  
        public byte[] serialize(Long aLong) throws SerializationException {  
            if (null != aLong) {  
                return aLong.toString().getBytes();  
            } else {  
                return new byte[0];  
            }  
        }  
  
        @Override  
        public Long deserialize(byte[] bytes) throws SerializationException {  
            if (bytes.length > 0) {  
                return Long.parseLong(new String(bytes));  
            } else {  
                return null;  
            }  
        }  
    }  
  
  
    public static enum IntSerializer implements RedisSerializer<Integer> {  
        INSTANCE;  
  
        @Override  
        public byte[] serialize(Integer i) throws SerializationException {  
            if (null != i) {  
                return i.toString().getBytes();  
            } else {  
                return new byte[0];  
            }  
        }  
  
        @Override  
        public Integer deserialize(byte[] bytes) throws SerializationException {  
            if (bytes.length > 0) {  
                return Integer.parseInt(new String(bytes));  
            } else {  
                return null;  
            }  
        }  
    }  
	
}
