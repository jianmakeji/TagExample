package com.jianma.tagsystem.module;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Article generated by hbm2java
 */
@Entity
@Table(name = "article", catalog = "tag_example")
public class Article implements java.io.Serializable {

	private Integer id;
	private String articleName;
	private String articleAbstract;
	private String articleContent;
	private Date createTime;
	private Set<ArticleTag> articleTags = new HashSet<ArticleTag>(0);

	public Article() {
	}

	public Article(String articleName, String articleAbstract, Date createTime) {
		this.articleName = articleName;
		this.articleAbstract = articleAbstract;
		this.createTime = createTime;
	}

	public Article(String articleName, String articleAbstract, String articleContent, Date createTime,
			Set<ArticleTag> articleTags) {
		this.articleName = articleName;
		this.articleAbstract = articleAbstract;
		this.articleContent = articleContent;
		this.createTime = createTime;
		this.articleTags = articleTags;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "article_name", nullable = false, length = 100)
	public String getArticleName() {
		return this.articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	@Column(name = "article_abstract", nullable = false)
	public String getArticleAbstract() {
		return this.articleAbstract;
	}

	public void setArticleAbstract(String articleAbstract) {
		this.articleAbstract = articleAbstract;
	}

	@Column(name = "article_content", length = 65535)
	public String getArticleContent() {
		return this.articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL}, mappedBy = "article")
	public Set<ArticleTag> getArticleTags() {
		return this.articleTags;
	}

	public void setArticleTags(Set<ArticleTag> articleTags) {
		this.articleTags = articleTags;
	}

}
