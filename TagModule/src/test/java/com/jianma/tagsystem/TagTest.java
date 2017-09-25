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
		List<String> list1 = Stream.of("微型车","小型车","紧凑型车","中型车","中大型车","豪华车","MPV","SUV","跑车").collect(Collectors.toList());
		List<String> list2 = Stream.of("5万以下","5-8万","8-10万","10-15万","15-25万","25-40万","40万以上").collect(Collectors.toList());
		List<String> list3 = Stream.of("宝马","一汽-大众","上汽大众","五菱","宝骏","奔腾","广汽传祺","比亚迪").collect(Collectors.toList());
		List<String> list4 = Stream.of("机油","轮胎","添加剂","防冻液","滤清器","蓄电池","雨刷","刹车片","盘火花","塞车灯","轮毂","维修配件","汽车玻璃","减震器","正时皮带","汽车喇叭","汽修工具","改装配件","原厂件").collect(Collectors.toList());
		List<String> list5 = Stream.of("座垫","座套","脚垫","头枕","腰靠","方向盘套","后备箱垫","车载支架","车钥匙扣","香水","炭包","净化剂","扶手箱","挂件","摆件","车用收纳袋","盒遮阳","雪挡","车衣","车贴","踏板","行李架","箱雨眉","装饰条","装饰灯","功能小件","车身装饰件").collect(Collectors.toList());
		List<String> list6 = Stream.of("行车记录仪","车载充电器","车机导航","车载蓝牙","智能驾驶对讲电台","倒车雷达","导航仪","安全预警仪","车载净化器","车载吸尘器","汽车音响","车载冰箱","应急电源","逆变器","车载影音","车载生活电器","车载电器配件").collect(Collectors.toList());
		List<String> list7 = Stream.of("洗车机","洗车水枪","玻璃水","清洁剂","镀晶镀膜","车蜡","汽车贴膜","底盘装甲","补漆笔","毛巾掸子","洗车配件").collect(Collectors.toList());
		List<String> list8 = Stream.of("胎压监测","充气泵","灭火器","车载床","应急救援","防盗设备","自驾野营","摩托车","摩托周边","保温箱","储物箱").collect(Collectors.toList());
		List<String> list9 = Stream.of("保养维修","清洗美容","功能升级","改装服务","ETC","驾驶培训","油卡充值","加油卡").collect(Collectors.toList());
		List<String> list10 = Stream.of("汽车附件1","汽车附件2","汽车附件3","汽车附件4","汽车附件5","汽车附件6","汽车附件7").collect(Collectors.toList());
		List<String> list11 = Stream.of("改装1","改装2","改装3","改装4","改装5","改装6","改装7").collect(Collectors.toList());
		List<String> list12 = Stream.of("其它1","其它2","其它3","其它4","其它5","其它6","其它7").collect(Collectors.toList());
		
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
		tag.setTagName("汽车附件1X");
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
