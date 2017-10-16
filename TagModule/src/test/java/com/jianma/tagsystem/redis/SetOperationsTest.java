package com.jianma.tagsystem.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jianma.tagsystem.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SetOperationsTest {
	
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	private RedisTemplate<String, Integer> template;
	
	private SetOperations<String,Integer> setOps;
	
	@Autowired
	private RedisTemplate<String,Integer> opTemplate;
	
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
	
	@Test
	public void addSet(){
		
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(template.getStringSerializer());
		template.setValueSerializer(IntSerializer.INSTANCE);
		template.afterPropertiesSet();
		
		setOps = template.opsForSet();
		setOps.add("set1", 1,2,3,4,5,6,7,8,9,10);
		setOps.add("set2", 10,2,31,45,5,61,17,48,59,610);
		setOps.add("set3", 11,2,3,14,15,6,17,38,59,610);
	}
	
	@Test
	public void baseCommand(){
		System.out.println(opTemplate.opsForSet().size("set1"));
		System.out.println(opTemplate.opsForSet().size("set2"));
		System.out.println(opTemplate.opsForSet().size("set3"));
		
		System.out.println(opTemplate.opsForSet().members("set3"));
		
		
		
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(template.getStringSerializer());
		template.setValueSerializer(IntSerializer.INSTANCE);
		template.afterPropertiesSet();
		
		setOps = template.opsForSet();
		setOps.move("set1", 10, "set3");
		
		
		ScanOptions scanOption = ScanOptions.scanOptions().match("*"+1+"*").count(5).build();
		Cursor c = opTemplate.opsForSet().scan("set1", scanOption);
		while (c.hasNext()){
			System.out.println(c.next());
		}
		
		
		
		System.out.println(opTemplate.opsForSet().pop("set1"));
		
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(template.getStringSerializer());
		template.setValueSerializer(IntSerializer.INSTANCE);
		template.afterPropertiesSet();
		
		setOps = template.opsForSet();
		System.out.println(setOps.remove("set1",8));
		
	}
	
	@Test 
	public void sInner(){
		
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(template.getStringSerializer());
		template.setValueSerializer(IntSerializer.INSTANCE);
		template.afterPropertiesSet();
		
		setOps = template.opsForSet();
		
		Set<Integer> resultSet = setOps.intersect("set1", "set2");
		
		System.out.println("----------------------------------");
		for (Integer data : resultSet){
			System.out.println(data);
		}
		System.out.println("----------------------------------");
		
		List<String> keyList = new ArrayList<>();
		keyList.add("set2");
		keyList.add("set3");
		Set<Integer> resultSet2 = setOps.intersect("set1", keyList);
		for (Integer data : resultSet2){
			System.out.println(data);
		}
		
	}
	
	@Test
	public void sInnerStore(){
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(template.getStringSerializer());
		template.setValueSerializer(IntSerializer.INSTANCE);
		template.afterPropertiesSet();
		
		setOps = template.opsForSet();
		
		Long result = setOps.intersectAndStore("set1", "set2","set1_2");
		
		System.out.println("----------------------------------");
		System.out.println(result);
		System.out.println("----------------------------------");
	}
	
	@Test
	public void sUnion(){
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(template.getStringSerializer());
		template.setValueSerializer(IntSerializer.INSTANCE);
		template.afterPropertiesSet();
		
		setOps = template.opsForSet();
		
		Set<Integer> resultData = setOps.union("set1", "set2");
		System.out.println("----------------------------------");
		for (Integer data : resultData){
			System.out.println(data);
		}
		System.out.println("----------------------------------");
		
		List<String> keyList = new ArrayList<>();
		keyList.add("set2");
		keyList.add("set3");
		Set<Integer> resultSet2 = setOps.union("set1", keyList);
		for (Integer data : resultSet2){
			System.out.println(data);
		}
	}
	
	@Test 
	public void sUnionStore(){
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(template.getStringSerializer());
		template.setValueSerializer(IntSerializer.INSTANCE);
		template.afterPropertiesSet();
		
		setOps = template.opsForSet();
		
		Long result = setOps.unionAndStore("set1", "set2","union1_2");
		
		System.out.println("----------------------------------");
		System.out.println(result);
		System.out.println("----------------------------------");
	}
	
	@Test
	public void sDiff(){
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(template.getStringSerializer());
		template.setValueSerializer(IntSerializer.INSTANCE);
		template.afterPropertiesSet();
		
		setOps = template.opsForSet();
		
		Set<Integer> resultData = setOps.difference("set1", "set2");
		System.out.println("----------------------------------");
		for (Integer data : resultData){
			System.out.println(data);
		}
		System.out.println("----------------------------------");
		
		List<String> keyList = new ArrayList<>();
		keyList.add("set2");
		keyList.add("set3");
		Set<Integer> resultSet2 = setOps.difference("set1", keyList);
		for (Integer data : resultSet2){
			System.out.println(data);
		}
	}
	
	@Test
	public void sDiffStore(){
		template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory);
		template.setKeySerializer(template.getStringSerializer());
		template.setValueSerializer(IntSerializer.INSTANCE);
		template.afterPropertiesSet();
		
		setOps = template.opsForSet();
		
		Long result = setOps.differenceAndStore("set1", "set2","diff1_2");
		
		System.out.println("----------------------------------");
		System.out.println(result);
		System.out.println("----------------------------------");
	}
}
