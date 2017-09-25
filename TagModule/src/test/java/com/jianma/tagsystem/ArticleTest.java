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
			article.setArticleName("习近平指导京津冀协同发展这几年");
			article.setArticleAbstract("十八大以来，习近平始终关心并指导着京津冀协同发展。在总书记的谋划指导下，三地的协同发展从规划一步步落到了实地。新华社《学习进行时》原创品牌栏目“讲习所”今天推出文章，带您了解习近平指导下的京津冀协同发展这几年。在河北雄安新区流传着这样一副对联，上联是：雄安容天下。下联是：京津冀未来。横批：千年大计。千年宏图，尽在一联中。京津冀协同发展，几年来一步步落地生根抽枝发芽，从蓝图变为现实。");
			article.setArticleContent("十八大以来，习近平始终关心并指导着京津冀协同发展。在总书记的谋划指导下，三地的协同发展从规划一步步落到了实地。新华社《学习进行时》原创品牌栏目“讲习所”今天推出文章，带您了解习近平指导下的京津冀协同发展这几年。"
					+ "在河北雄安新区流传着这样一副对联，上联是：雄安容天下。下联是：京津冀未来。横批：千年大计。千年宏图，尽在一联中。京津冀协同发展，几年来一步步落地生根抽枝发芽，从蓝图变为现实。");
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
			article.setArticleName("习近平指导京津冀协同发展这几年");
			article.setArticleAbstract("十八大以来，习近平始终关心并指导着京津冀协同发展。在总书记的谋划指导下，三地的协同发展从规划一步步落到了实地。新华社《学习进行时》原创品牌栏目“讲习所”今天推出文章，带您了解习近平指导下的京津冀协同发展这几年。在河北雄安新区流传着这样一副对联，上联是：雄安容天下。下联是：京津冀未来。横批：千年大计。千年宏图，尽在一联中。京津冀协同发展，几年来一步步落地生根抽枝发芽，从蓝图变为现实。");
			article.setArticleContent("十八大以来，习近平始终关心并指导着京津冀协同发展。在总书记的谋划指导下，三地的协同发展从规划一步步落到了实地。新华社《学习进行时》原创品牌栏目“讲习所”今天推出文章，带您了解习近平指导下的京津冀协同发展这几年。"
					+ "在河北雄安新区流传着这样一副对联，上联是：雄安容天下。下联是：京津冀未来。横批：千年大计。千年宏图，尽在一联中。京津冀协同发展，几年来一步步落地生根抽枝发芽，从蓝图变为现实。");
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


