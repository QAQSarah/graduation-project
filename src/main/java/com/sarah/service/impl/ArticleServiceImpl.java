package com.sarah.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarah.dao.ArticleImgMapper;
import com.sarah.dao.ArticleMapper;
import com.sarah.model.Article;
import com.sarah.model.ArticleImg;
import com.sarah.model.Comment;
import com.sarah.model.CommentModel;
import com.sarah.service.ArticleService;
import com.sarah.service.CommentService;
import com.sarah.util.PageBean;

@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private ArticleImgMapper articleImgMapper;
	
	@Autowired
	private CommentService commentService;


	public int deleteByPrimaryKey(Integer id) {
		ArticleImg record=new ArticleImg();
		record.setArticleid(id);
		List<ArticleImg> list1=articleImgMapper.selectByArticleImg(record);
		int val=articleMapper.deleteByPrimaryKey(id);
		if(val!=0) {
			for (ArticleImg articleImg : list1) {
				articleImgMapper.deleteByPrimaryKey(articleImg.getId());
			}
			Comment record1=new Comment();
			record1.setFromUid(id);
			List<CommentModel> comments=commentService.seleteByComment(record1);
			for (CommentModel commentModel : comments) {
				commentService.deleteByPrimaryKey(commentModel.getId());
			}		
		}
		return val;
	}

	public int insert(Article record) {
		return articleMapper.insert(record);
	}

	public int insertSelective(Article record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Article selectByPrimaryKey(Integer id) {
		Article article=null;
		if(id!=null&&!("".equals(id))) {
			article=articleMapper.selectByPrimaryKey(id);
		}
		return article;
	}

	public int updateByPrimaryKeySelective(Article record) {
		int r=0;
		if(record!=null) {
			r=articleMapper.updateByPrimaryKeySelective(record);
		}
		return r;
	}

	public int updateByPrimaryKeyWithBLOBs(Article record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Article record) {
		return articleMapper.updateByPrimaryKey(record);
	}

	public List<Article> showArticles() {
		
		Article article=new Article();
		List<Article> articles=articleMapper.selectAll(article);
		if(articles.size()!=0) {
			return articles;
		}
		return null;
	}

	public List<Article> selectRand() {
		List<Article> articles=articleMapper.selectRand();
		if(articles.size()!=0) {
			return articles;
		}
		return null;
	}

	public List<Article> selectHotArticle() {
	List<Article> articles=articleMapper.selectHotArticle();
	if(articles.size()!=0) {
		return articles;
	}
	return null;
	}

	public PageBean<Article> seleteByCategory(int pageNum, int rows, int id) {
		Article article=new Article();
		article.setCategoryid(id);
		List<Article> all = articleMapper.selectAll(article);
		int totalRecord = 0;
		if(all!=null) {
			totalRecord=all.size();
		}
		PageBean<Article> pb = new PageBean<Article>(pageNum, rows, totalRecord);

		int startIndex = pb.getStartIndex();
		List<Article> list=new ArrayList<Article>();
		list=articleMapper.seleteListByCategorySelective(startIndex ,id, rows);

		if(list==null) {
			return null;
		}else {
			pb.setList(list);
			return pb;
		}
	}

	public List<Article> selectByTitle(String title) {
		Article article=new Article();
		List<Article> articles=new ArrayList<Article>();
		if(title!=null&&!("".equals(title))) {
			article.setTitle(title);
			articles=articleMapper.selectByMH(article);
		}
		if(articles.size()!=0) {
			return articles;
		}
		return null;
	}

	public PageBean<Article> seleteArticles(int pageNum, int rows, Article article) {
		List<Article> all = articleMapper.selectAll(article);
		int totalRecord = 0;
		if(all!=null) {
			totalRecord=all.size();
		}
		PageBean<Article> pb = new PageBean<Article>(pageNum, rows, totalRecord);

		int startIndex = pb.getStartIndex();
		List<Article> list=new ArrayList<Article>();
		list=articleMapper.seleteList(startIndex ,article.getTitle(),article.getAuther(), rows);
		if(list==null) {
			return null;
		}else {
			pb.setList(list);
			return pb;
		}
	}

	

}
