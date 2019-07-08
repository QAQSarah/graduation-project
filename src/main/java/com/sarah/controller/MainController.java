package com.sarah.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.resource.spi.AuthenticationMechanism;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sarah.model.Article;
import com.sarah.model.ArticleImg;
import com.sarah.model.ArticleModel;
import com.sarah.model.Category;
import com.sarah.model.Collect;
import com.sarah.model.Comment;
import com.sarah.model.CommentModel;
import com.sarah.model.Likes;
import com.sarah.service.ArticleImgService;
import com.sarah.service.ArticleService;
import com.sarah.service.CategoryService;
import com.sarah.service.CollectService;
import com.sarah.service.CommentService;
import com.sarah.service.LikesService;
import com.sarah.util.PageBean;

@Controller
public class MainController {
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private ArticleImgService articleImgService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CollectService collectService;
	@Autowired
	private LikesService likesService;
	
	@RequestMapping("/show.do")
	@ResponseBody
	public ModelAndView showCategorys2() {
		List<Category> categoryList=categoryService.showcategores();
		ModelAndView mv=new ModelAndView("WEB-INF/index");
		mv.addObject("categoryList", categoryList);
		return mv;
	}
	@RequestMapping("/register.do")
	@ResponseBody
	public ModelAndView register() {
		ModelAndView mv=new ModelAndView("WEB-INF/register");
		return mv;
	}
	@RequestMapping("/forgetting.do")
	@ResponseBody
	public ModelAndView forgetting() {
		ModelAndView mv=new ModelAndView("WEB-INF/forgetting");
		return mv;
	}
	@RequestMapping("/header.do")
	@ResponseBody
	public ModelAndView header() {
		ModelAndView mv=new ModelAndView("WEB-INF/header.jsp");
		return mv;
	}
	@RequestMapping("/footer.do")
	@ResponseBody
	public ModelAndView footer() {
		ModelAndView mv=new ModelAndView("WEB-INF/footer.jsp");
		return mv;
	}
	@RequestMapping("/showR.do")
	@ResponseBody
	public ModelAndView showR() {
		List<Category> categoryList=categoryService.showcategores();
		List<Article> articles=articleService.selectRand();
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
		ModelAndView mv=new ModelAndView("WEB-INF/context");
		mv.addObject("articleList",articleModels );
		return mv;
	}
	@RequestMapping("/show_hot_article.do")
	@ResponseBody
	public ModelAndView show_hot_article() {
		List<Article> articles=articleService.selectHotArticle();
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
		ModelAndView mv=new ModelAndView("WEB-INF/context");
		mv.addObject("articleList",articleModels );
		return mv;
	}
	@RequestMapping("/showByCategory.do")
	@ResponseBody
	public ModelAndView showByCategory(HttpServletRequest request, HttpServletResponse response) {
		String id= request.getParameter("id");
		ModelAndView mv = new ModelAndView("WEB-INF/context");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		PageBean<Article> pb = articleService.seleteByCategory(Integer.parseInt(pageNum),10,Integer.parseInt(id));
		mv.addObject("pageBean", pb);
		List<ArticleModel> articleModels=new ArrayList<ArticleModel>();
		for (Article article : pb.getList()) {
			ArticleModel model=new ArticleModel();
			model.setId(article.getId());
			model.setArticleImg(articleImgService.selectByArticle(article));
			model.setAuther(article.getAuther());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			model.setCreatetime(sdf.format(article.getCreatetime()));
			model.setTitle(article.getTitle());
			model.setCategoryId(article.getCategoryid());
			articleModels.add(model);
			
		}
		mv.addObject("articleList",articleModels);
		return mv;
	}
	@RequestMapping("/showPage.do")
	@ResponseBody
	public ModelAndView showPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id= request.getParameter("id");
		Article  article=new Article();
		ModelAndView mv=new ModelAndView("WEB-INF/showPageDetail");
		if(id!=null&&!("".equals(id))) {
			article=articleService.selectByPrimaryKey(Integer.valueOf(id));
			if(article!=null) {
				int rq=0;
				if(article.getReadingQuantity()==null) {
					rq=0;
				}
				rq++;
				article.setReadingQuantity(rq);
				articleService.updateByPrimaryKeySelective(article);
			}
		}
		if(article!=null) {
			List<ArticleImg> articleImgs=articleImgService.selectByArticleList(article);
			Comment record=new Comment();
			record.setArticleid(Integer.parseInt(id));
			List<CommentModel> comments=commentService.seleteByComment(record);
			ArticleModel articleModel=new ArticleModel();
			articleModel.setId(article.getId());
			articleModel.setAuther(article.getAuther());
			articleModel.setTitle(article.getTitle());
			articleModel.setContent(article.getContent());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			articleModel.setCreatetime(sdf.format(article.getCreatetime()));
			mv.addObject("article",articleModel);	
			if(articleImgs!=null) {
				mv.addObject("articleImgs", articleImgs);
			}
			if(comments!=null) {
				mv.addObject("commentList",comments);
			}
			String userId=request.getParameter("userid");
			if(userId!=null) {
				Collect collect=new Collect();
				collect.setId(Integer.parseInt(id));
				collect.setUid(Integer.parseInt(userId));
				List<Collect> list=collectService.selectAll(collect);
				if(list.size()==0) {
					mv.addObject("collect",1);
				}
				Likes likes=new Likes();
				likes.setId(Integer.parseInt(id));
				likes.setUid(Integer.parseInt(userId));
				List<Likes> list2=likesService.selectAll(likes);
				if(list2.size()==0) {
					mv.addObject("Likes", 1);
				}
			}
		}
		return mv;
	}
	
	
	
	
}
