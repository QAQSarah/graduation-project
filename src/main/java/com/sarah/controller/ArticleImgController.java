package com.sarah.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.sarah.model.Article;
import com.sarah.model.ArticleImg;
import com.sarah.model.ArticleImgModel;
import com.sarah.model.Category;
import com.sarah.model.User;
import com.sarah.service.ArticleImgService;
import com.sarah.service.ArticleService;
import com.sarah.service.CategoryService;
import com.sarah.util.PageBean;

@Controller
public class ArticleImgController {

	@Autowired
	private ArticleImgService articleImgService;
	@Autowired
	private ArticleService articleService;
	

	@RequestMapping("articleImgList.do")
	public ModelAndView artileImgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/view/articleImg/articleImgList");
		String name=request.getParameter("name");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
	
		PageBean<ArticleImg> pb=articleImgService.selectList(Integer.parseInt(pageNum),10);
		mv.addObject("pageBean", pb);
		List<ArticleImgModel> articleImgModels=new ArrayList<ArticleImgModel>();
		for (ArticleImg articleImg : pb.getList()) {
			ArticleImgModel articleImgModel=new ArticleImgModel();
			articleImgModel.setId(articleImg.getId());
			articleImgModel.setArticleName(articleService.selectByPrimaryKey(articleImg.getArticleid()).getTitle());
			articleImgModel.setImgurl(articleImg.getImgurl());
			articleImgModels.add(articleImgModel);
		}
		mv.addObject("imgs", articleImgModels);
		return mv;
	}
	@RequestMapping("toAddArticleImg.do")
	public ModelAndView toAddArticle(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView("WEB-INF/view/articleImg/articleImg-add");
		List<Article> articles=articleService.showArticles();
		mv.addObject("articles", articles);
		return mv;
	}
	@RequestMapping(value="addArticleImg.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String addArticleImg(@RequestParam("articleId") String articleId,@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String msg="添加失败";
		if (request instanceof MultipartHttpServletRequest) {
			String sqlPath = null;// 保存数据库的路径
			String filename = null;// 定义文件名
			String contentType = file.getContentType(); // 获取文件类型（后缀）
			// 因为获取的后缀是XXXX/xxx形式
			contentType = contentType.substring(contentType.indexOf("/") + 1);
			String time=String.valueOf(System.currentTimeMillis());
			filename = time+ "." + contentType;
			String url = "E:\\graduation_project\\it_web\\src\\main\\webapp\\userimg";
			url = url + "\\";
			file.transferTo(new File(url + filename));// 保存图片
			sqlPath = "userimg/" + filename;
			ArticleImg articleImg=new ArticleImg();
			articleImg.setImgurl(sqlPath);
			articleImg.setArticleid(Integer.parseInt(articleId));
			int val=articleImgService.insert(articleImg);
			
			if (val!=0) {
				msg="添加成功";
			}
			
	    }
		return msg;		
	}
	@RequestMapping("toEditArticleImg.do")
	public ModelAndView toEditArticle(HttpServletRequest request,HttpServletResponse response) {
		String id=request.getParameter("id");
		
		ModelAndView mv=new ModelAndView("WEB-INF/view/articleImg/articleImg-edit");
		List<Article> articles=articleService.showArticles();
		mv.addObject("articles", articles);
		ArticleImg articleImg=articleImgService.selectByPrimaryKey(Integer.parseInt(id));
		mv.addObject("articleImg", articleImg);
		return mv;
	}
	@RequestMapping(value="editArticleImg.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String editArticleImg(@RequestParam("id") String id,@RequestParam("articleId") String articleId,@RequestParam("file") MultipartFile file,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String msg="修改失败";
		ArticleImg articleImg=articleImgService.selectByPrimaryKey(Integer.parseInt(id));
		if (request instanceof MultipartHttpServletRequest) {
			if(file!=null&&file.getSize()!=0) {
				String sqlPath = null;// 保存数据库的路径
				String filename = null;// 定义文件名
				String contentType = file.getContentType(); // 获取文件类型（后缀）
				// 因为获取的后缀是XXXX/xxx形式
				contentType = contentType.substring(contentType.indexOf("/") + 1);
				String time=String.valueOf(System.currentTimeMillis());
				filename = time+ "." + contentType;
				String url = "E:\\graduation_project\\it_web\\src\\main\\webapp\\userimg";
				url = url + "\\";
				file.transferTo(new File(url + filename));// 保存图片
				sqlPath = "userimg/" + filename;
				articleImg.setImgurl(sqlPath);
			}
			
			if(articleId!=null&&"".equals(articleId)==false) {
				articleImg.setArticleid(Integer.parseInt(articleId));
			}
			int val=articleImgService.updateByPrimaryKey(articleImg);
			
			if (val!=0) {
				msg="修改成功";
			}
			
	    }
		return msg;		
	}
	
	@RequestMapping(value="deleteArticleImg.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteArticleImg(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		String msg="删除失败!";
		int val =articleImgService.deleteByPrimaryKey(Integer.parseInt(id));
		if(val!=0) {
			msg="删除成功";
		}
		return msg;
	} 

}
