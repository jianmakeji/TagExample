package com.jianma.tagsystem.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jianma.tagsystem.redis.HashOperationsTest.StringSerializer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class HyperLogLogOperationsTest {

	private RedisTemplate<String, String> template;
	
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	@Test
	public void addData(){
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setHashKeySerializer(StringSerializer.INSTANCE);
		template.setHashValueSerializer(StringSerializer.INSTANCE);
		template.afterPropertiesSet();
		
		//template.opsForHyperLogLog().add("hyperLogLog", 1,2,3,3,4,5,6,6,7,7);
		
		template.opsForHyperLogLog().add("unique::ip::counter", "202.16.36.25","139.26.36.129","139.26.36.129","139.26.36.129","139.26.36.129","139.26.36.121");
	}
	
	@Test
	public void getData(){
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setHashKeySerializer(StringSerializer.INSTANCE);
		template.setHashValueSerializer(IntSerializer.INSTANCE);
		template.afterPropertiesSet();
		
		//System.out.println(template.opsForHyperLogLog().size("hyperLogLog"));
		
		System.out.println(template.opsForHyperLogLog().size("unique::ip::counter"));
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
