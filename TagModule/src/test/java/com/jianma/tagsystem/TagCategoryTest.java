package com.jianma.tagsystem;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jianma.tagsystem.module.TagCategory;
import com.jianma.tagsystem.service.TagCategoryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TagCategoryTest {

	@Autowired
	@Qualifier("tagCategoryServiceImpl")
	private TagCategoryService tagCategoryServiceImpl;
	
	//@Test
	public void createTagCategory(){
		
		List<String> collected = Stream.of("汽车车型","汽车价格","汽车品牌","维修保养","汽车装饰","车载电器","美容清晰","安全自驾","汽车服务","汽车附件","改装","其它").collect(Collectors.toList());
		
		collected.stream().forEach((name)->{
			TagCategory tagCategory = new TagCategory();
			tagCategory.setCategoryName(name);
			tagCategory.setCreateTime(new Date());
			tagCategoryServiceImpl.createTagCategory(tagCategory);
		});
		
		
	}
	
	//@Test
	public void updateTagCategory(){
		TagCategory tagCategory = new TagCategory();
		tagCategory.setId(12);
		tagCategory.setCategoryName("其它");
		tagCategory.setCreateTime(new Date());
		tagCategoryServiceImpl.updateTagCategory(tagCategory);
	}
	
	//@Test
	public void deleteTagCategory(){
		tagCategoryServiceImpl.deleteTagCategoryById(13);
	}
	
	//@Test
	public void getTagCategoryList(){
		List<TagCategory> list = tagCategoryServiceImpl.getTagCategoryByPage(0, 13);
		list.stream().forEach((tagCategory)->{
			System.out.println(tagCategory.getId() + "  " +tagCategory.getCategoryName() + "  " + tagCategory.getCreateTime());
		});;
	}
	
	@Test
	public void getTagCategoryById(){
		TagCategory tagCategory = tagCategoryServiceImpl.getTagCategoryById(10);
		System.out.println(tagCategory.getCategoryName() + "  " + tagCategory.getCreateTime());
	}
}
