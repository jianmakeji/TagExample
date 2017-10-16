package com.jianma.tagsystem.redis;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.ObjectHashMapper;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jianma.tagsystem.model.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class HashMappingTest {

	@Autowired
	private RedisTemplate<String, String> template;

	// inject the template as HashOperations
	@Resource(name = "redisTemplate")
	HashOperations<String, byte[], byte[]> hashOperations;

	HashMapper<Object, byte[], byte[]> mapper = new ObjectHashMapper();

	private RedisTemplate<String, Map<String, Person>> peopleTemplate;
	
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	/*
	 * public void writeHash(String key, Person person) {
	 * 
	 * Map<byte[], byte[]> mappedHash = mapper.toHash(person);
	 * hashOperations.putAll(key, mappedHash); }
	 * 
	 * public Person loadHash(String key) {
	 * 
	 * Map<byte[], byte[]> loadedHash = hashOperations.entries("key"); return
	 * (Person) mapper.fromHash(loadedHash); }
	 */
	public <T> void writeHash(String key, T obj) {
		
		Map<byte[], byte[]> mappedHash = mapper.toHash(obj);
		hashOperations.putAll(key, mappedHash);
	}

	@SuppressWarnings("unchecked")
	public <T> T loadHash(String key) {

		Map<byte[], byte[]> loadedHash = hashOperations.entries(key);
		return (T) mapper.fromHash(loadedHash);
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
	
	@Test
	public void HashMappingTest() {
		/*
		template.setHashKeySerializer(StringSerializer.INSTANCE);
		template.setHashValueSerializer(StringSerializer.INSTANCE);
		
		Person person = new Person();
		person.setFirstName("jack");
		person.setLastName("bauer");
		
		writeHash("person:1", person);

		Person personObj = loadHash("person:1");
		System.out.println(personObj.getFirstName() + "  " + personObj.getLastName());
		
		*/
		peopleTemplate = new RedisTemplate<>();
		peopleTemplate.setConnectionFactory(jedisConnectionFactory);
		peopleTemplate.setKeySerializer(peopleTemplate.getStringSerializer());
		peopleTemplate.setHashKeySerializer(peopleTemplate.getStringSerializer());
		peopleTemplate.setHashValueSerializer(new Jackson2JsonRedisSerializer<Person>(Person.class));
		peopleTemplate.afterPropertiesSet();
		/*
		Person person = new Person();
		person.setFirstName("jack hhhh");
		person.setLastName("bauer");
		peopleTemplate.opsForHash().put("aa", "dd", person);
		*/
		Map<String, Person> persons = new HashMap<>();
		for (int i = 1; i <= 25; i++) {
			Person person1 = new Person();
			person1.setFirstName("jack hhhh");
			person1.setLastName("bauer");
			persons.put(""+i, person1);
		}
		
		peopleTemplate.opsForHash().putAll("personHash", persons);
	}
}
