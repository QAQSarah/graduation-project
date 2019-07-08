package com.sarah.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sarah.model.Article;
import com.sarah.model.ArticleModel;
import com.sarah.model.Category;
import com.sarah.model.User;
import com.sarah.model.UserModel;
import com.sarah.service.ArticleImgService;
import com.sarah.service.ArticleService;
import com.sarah.service.CategoryService;
import com.sarah.util.PageBean;

@RequestMapping("/article")
@Controller
public class ArticleController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleImgService articleImgService;
	
	@RequestMapping("/findArticles.do")
	@ResponseBody
	public ModelAndView findArticles(HttpServletRequest request,HttpServletResponse response) {
		String title="";
		title=request.getParameter("title");
		List<Article> articles=articleService.selectByTitle(title);
		List<ArticleModel> articleModels=new ArrayList<ArticleModel>();
		for (Article article : articles) {
			ArticleModel model=new ArticleModel();
			model.setId(article.getId());
			model.setArticleImg(articleImgService.selectByArticle(article));
			model.setAuther(article.getAuther());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setCreatetime(sdf.format(article.getCreatetime()));
			model.setTitle(article.getTitle());
			articleModels.add(model);
		}
		ModelAndView mv=new ModelAndView("context");
		mv.addObject("articleList",articleModels );
		return mv;
	}
	
	@RequestMapping("articleList.do")
	public ModelAndView artileList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/view/article/articleList");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		String articleName=request.getParameter("articleName");
		String articleAuthor=request.getParameter("articleAuthor");
		Article article=new Article();
		article.setAuther(articleAuthor);
		article.setTitle(articleName);
		PageBean<Article> pb=articleService.seleteArticles(Integer.parseInt(pageNum),10,article);
		mv.addObject("pageBean", pb);
		List<ArticleModel> articleModels=new ArrayList<ArticleModel>();
		for (Article article1 : pb.getList()) {
			ArticleModel model=new ArticleModel();
			model.setId(article1.getId());
			model.setAuther(article1.getAuther());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setCreatetime(sdf.format(article1.getCreatetime()));
			model.setTitle(article1.getTitle());
			articleModels.add(model);
		}
		mv.addObject("articleList",articleModels);
		return mv;
	}
	@RequestMapping("toAddArticle.do")
	public ModelAndView toAddArticle(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView("WEB-INF/view/article/article-add");
		List<Category> categories=categoryService.showcategores();
		mv.addObject("articleCategorys", categories);
		return mv;
	}
	
	@RequestMapping("deleteArticle.do")
	public int deleteArticle(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		int val = articleService.deleteByPrimaryKey(Integer.parseInt(id));
		return val;
		
	}
	
	@RequestMapping(value = "addArticle.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String addArticle(HttpServletRequest request,HttpServletResponse response) {
		String auther=request.getParameter("author");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String categoryId=request.getParameter("categoryId");
		Article article=new Article();
		article.setCategoryid(Integer.parseInt(categoryId));
		article.setTitle(title);
		String[] cs = content.split("<br/>");
		String content1="";
		for (String string : cs) {
			content1=content1+"<p>"+string+"</p>";
		}
		
		article.setContent(content1);
		article.setCreatetime(new Date());
		article.setAuther(auther);
		int val=articleService.insert(article);
		String msg="添加失败!";
		if(val!=0) {
		msg="添加成功!";
		}
		return msg;
		
	}
	
	@RequestMapping("toEditArticle.do")
	public ModelAndView toEditAricle(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		Article article=articleService.selectByPrimaryKey(Integer.parseInt(id));
		ModelAndView mv=new ModelAndView("WEB-INF/view/article/article-edit");
		String content=article.getContent().replace("<p>", "");
		content=content.replace("</p>", "");
		article.setContent(content);
		mv.addObject("article", article);
		List<Category> categories=categoryService.showcategores();
		mv.addObject("articleCategorys", categories);
		return mv;
		
	}
	@RequestMapping(value = "editArticle.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String editArticle(HttpServletRequest request,HttpServletResponse response) {
		String msg="修改失败";
		String id=request.getParameter("id");
		Article article=articleService.selectByPrimaryKey(Integer.parseInt(id));
		String auther=request.getParameter("author");
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		String categoryId=request.getParameter("categoryId");
		article.setAuther(auther);
		article.setContent("<p>"+content+"</p>");
		article.setTitle(title);
		if(categoryId!=null&&!("".equals(categoryId))) {
			article.setCategoryid(Integer.parseInt(categoryId));
		}
		int val=articleService.updateByPrimaryKey(article);
		if(val!=0) {
			msg="修改成功!";
		}
		return msg;
		
	}
	
}

