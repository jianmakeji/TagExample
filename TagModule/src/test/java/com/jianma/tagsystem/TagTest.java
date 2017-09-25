package com.jianma.tagsystem;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jianma.tagsystem.module.Tag;
import com.jianma.tagsystem.module.TagCategory;
import com.jianma.tagsystem.service.TagService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TagTest {

	@Autowired
	@Qualifier("tagServiceImpl")
	private TagService tagServiceImpl;
	
	//@Test
	public void createTag(){
		List<String> list1 = Stream.of("΢�ͳ�","С�ͳ�","�����ͳ�","���ͳ�","�д��ͳ�","������","MPV","SUV","�ܳ�").collect(Collectors.toList());
		List<String> list2 = Stream.of("5������","5-8��","8-10��","10-15��","15-25��","25-40��","40������").collect(Collectors.toList());
		List<String> list3 = Stream.of("����","һ��-����","��������","����","����","����","��������","���ǵ�").collect(Collectors.toList());
		List<String> list4 = Stream.of("����","��̥","��Ӽ�","����Һ","������","����","��ˢ","ɲ��Ƭ","�̻�","������","���","ά�����","��������","������","��ʱƤ��","��������","���޹���","��װ���","ԭ����").collect(Collectors.toList());
		List<String> list5 = Stream.of("����","����","�ŵ�","ͷ��","����","��������","�����","����֧��","��Կ�׿�","��ˮ","̿��","������","������","�Ҽ�","�ڼ�","�������ɴ�","������","ѩ��","����","����","̤��","�����","����ü","װ����","װ�ε�","����С��","����װ�μ�").collect(Collectors.toList());
		List<String> list6 = Stream.of("�г���¼��","���س����","��������","��������","���ܼ�ʻ�Խ���̨","�����״�","������","��ȫԤ����","���ؾ�����","����������","��������","���ر���","Ӧ����Դ","�����","����Ӱ��","�����������","���ص������").collect(Collectors.toList());
		List<String> list7 = Stream.of("ϴ����","ϴ��ˮǹ","����ˮ","����","�ƾ���Ĥ","����","������Ĥ","����װ��","�����","ë����","ϴ�����").collect(Collectors.toList());
		List<String> list8 = Stream.of("̥ѹ���","������","�����","���ش�","Ӧ����Ԯ","�����豸","�Լ�ҰӪ","Ħ�г�","Ħ���ܱ�","������","������").collect(Collectors.toList());
		List<String> list9 = Stream.of("����ά��","��ϴ����","��������","��װ����","ETC","��ʻ��ѵ","�Ϳ���ֵ","���Ϳ�").collect(Collectors.toList());
		List<String> list10 = Stream.of("��������1","��������2","��������3","��������4","��������5","��������6","��������7").collect(Collectors.toList());
		List<String> list11 = Stream.of("��װ1","��װ2","��װ3","��װ4","��װ5","��װ6","��װ7").collect(Collectors.toList());
		List<String> list12 = Stream.of("����1","����2","����3","����4","����5","����6","����7").collect(Collectors.toList());
		
		Map<Integer, List<String>> map = new HashMap<>();
		map.put(1, list1);
		map.put(2, list2);
		map.put(3, list3);
		map.put(4, list4);
		map.put(5, list5);
		map.put(6, list6);
		map.put(7, list7);
		map.put(8, list8);
		map.put(9, list9);
		map.put(10, list10);
		map.put(11, list11);
		map.put(12, list12);
		
		map.forEach((key,value)->{
			final TagCategory tagCategory = new TagCategory();
			tagCategory.setId(key);
			
			value.stream().forEach((name)->{
				Tag tag = new Tag();
				tag.setTagName(name);
				tag.setTagCategory(tagCategory);
				tag.setCreateTime(new Date());
				
				tagServiceImpl.createTag(tag);
			});
		});
		
	}
	
	//@Test
	public void deleteTag(){
		tagServiceImpl.deleteTagById(10);
	}
	
	//@Test
	public void updateTag(){
		Tag tag = new Tag();
		tag.setId(11);
		tag.setTagName("��������1X");
		tag.setCreateTime(new Date());
		TagCategory tagCategory = new TagCategory();
		tagCategory.setId(10);
		tag.setTagCategory(tagCategory);
		tagServiceImpl.updateTag(tag);
	}
	
	//@Test
	public void getTagList(){
		List<Tag> list = tagServiceImpl.getTagByPage(0, 10);
		
		list.stream().forEach((tag)->{
			System.out.println(tag.getId() +" "+ tag.getTagName() +"  "+tag.getTagCategory().getCategoryName());
		});
	}
	
	@Test
	public void getTagListByCategory(){
		List<Tag> list = tagServiceImpl.getListTagByCategory(6);
		list.stream().forEach((tag)->{
			System.out.println(tag.getId() +" "+ tag.getTagName() +"  "+tag.getTagCategory().getCategoryName());
		});;
	}
}
