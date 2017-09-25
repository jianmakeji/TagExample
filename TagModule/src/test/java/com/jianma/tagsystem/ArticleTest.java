package com.jianma.tagsystem;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jianma.tagsystem.module.Article;
import com.jianma.tagsystem.module.ArticleTag;
import com.jianma.tagsystem.module.Tag;
import com.jianma.tagsystem.service.ArticleService;
import com.jianma.tagsystem.util.SpringBeanUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ArticleTest {

	@Autowired
	@Qualifier("articleServiceImpl")
	private ArticleService articleServiceImpl;
	
	@Test
	public void createArticle1(){
		/*
		Executor executor = Executors.newFixedThreadPool(5);
	    for (int i = 0; i < 5; i++){
	    	executor.execute(new Task());
	    }
		
	    */
		Random random = new Random();
    	for (int i = 0; i < (Integer.MAX_VALUE); i++){
			Article article = new Article();
			article.setArticleName("ϰ��ƽָ������Эͬ��չ�⼸��");
			article.setArticleAbstract("ʮ�˴�������ϰ��ƽʼ�չ��Ĳ�ָ���ž���Эͬ��չ��������ǵ�ı��ָ���£����ص�Эͬ��չ�ӹ滮һ�����䵽��ʵ�ء��»��硶ѧϰ����ʱ��ԭ��Ʒ����Ŀ����ϰ���������Ƴ����£������˽�ϰ��ƽָ���µľ���Эͬ��չ�⼸�ꡣ�ںӱ��۰���������������һ�������������ǣ��۰������¡������ǣ�����δ����������ǧ���ơ�ǧ���ͼ������һ���С�����Эͬ��չ��������һ�������������֦��ѿ������ͼ��Ϊ��ʵ��");
			article.setArticleContent("ʮ�˴�������ϰ��ƽʼ�չ��Ĳ�ָ���ž���Эͬ��չ��������ǵ�ı��ָ���£����ص�Эͬ��չ�ӹ滮һ�����䵽��ʵ�ء��»��硶ѧϰ����ʱ��ԭ��Ʒ����Ŀ����ϰ���������Ƴ����£������˽�ϰ��ƽָ���µľ���Эͬ��չ�⼸�ꡣ"
					+ "�ںӱ��۰���������������һ�������������ǣ��۰������¡������ǣ�����δ����������ǧ���ơ�ǧ���ͼ������һ���С�����Эͬ��չ��������һ�������������֦��ѿ������ͼ��Ϊ��ʵ��");
			article.setCreateTime(new Date());
			
			Set<ArticleTag> articleTags = new HashSet<>();
			IntStream intStream = random.ints(11, 414);
			intStream.limit(10).forEach((value)->{
				ArticleTag articleTag = new ArticleTag();
				articleTag.setArticle(article);
				articleTag.setCreateTime(new Date());
				Tag tag = new Tag();
				tag.setId(value);
				articleTag.setTag(tag);
				articleTags.add(articleTag);
				article.setArticleTags(articleTags);
			});
			articleServiceImpl.createArticle(article);
		}
	}
	
	
	
	public void updateArticle(){
		
	}
	
	public void deleteArticle(){
		
	}
	
	
}

class Task implements Runnable {
	
	private ArticleService articleServiceImpl;
	
	public Task(){
		this.articleServiceImpl = (ArticleService)SpringBeanUtil.getBeanByName("articleServiceImpl");;
	}
	
    @Override
    public void run() {
    	Random random = new Random();
    	for (int i = 0; i < (Integer.MAX_VALUE / 5); i++){
			Article article = new Article();
			article.setArticleName("ϰ��ƽָ������Эͬ��չ�⼸��");
			article.setArticleAbstract("ʮ�˴�������ϰ��ƽʼ�չ��Ĳ�ָ���ž���Эͬ��չ��������ǵ�ı��ָ���£����ص�Эͬ��չ�ӹ滮һ�����䵽��ʵ�ء��»��硶ѧϰ����ʱ��ԭ��Ʒ����Ŀ����ϰ���������Ƴ����£������˽�ϰ��ƽָ���µľ���Эͬ��չ�⼸�ꡣ�ںӱ��۰���������������һ�������������ǣ��۰������¡������ǣ�����δ����������ǧ���ơ�ǧ���ͼ������һ���С�����Эͬ��չ��������һ�������������֦��ѿ������ͼ��Ϊ��ʵ��");
			article.setArticleContent("ʮ�˴�������ϰ��ƽʼ�չ��Ĳ�ָ���ž���Эͬ��չ��������ǵ�ı��ָ���£����ص�Эͬ��չ�ӹ滮һ�����䵽��ʵ�ء��»��硶ѧϰ����ʱ��ԭ��Ʒ����Ŀ����ϰ���������Ƴ����£������˽�ϰ��ƽָ���µľ���Эͬ��չ�⼸�ꡣ"
					+ "�ںӱ��۰���������������һ�������������ǣ��۰������¡������ǣ�����δ����������ǧ���ơ�ǧ���ͼ������һ���С�����Эͬ��չ��������һ�������������֦��ѿ������ͼ��Ϊ��ʵ��");
			article.setCreateTime(new Date());
			
			Set<ArticleTag> articleTags = new HashSet<>();
			IntStream intStream = random.ints(1, 276);
			intStream.limit(10).forEach((value)->{
				ArticleTag articleTag = new ArticleTag();
				articleTag.setArticle(article);
				articleTag.setCreateTime(new Date());
				Tag tag = new Tag();
				tag.setId(value);
				articleTag.setTag(tag);
				articleTags.add(articleTag);
				article.setArticleTags(articleTags);
			});
			articleServiceImpl.createArticle(article);
		}
    }
}


