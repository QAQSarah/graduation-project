package com.sarah.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.sarah.dao.CommentMapper;
import com.sarah.model.Article;
import com.sarah.model.ArticleImg;
import com.sarah.model.ArticleImgModel;
import com.sarah.model.ArticleModel;
import com.sarah.model.CommemtVo;
import com.sarah.model.Comment;
import com.sarah.service.ArticleImgService;
import com.sarah.service.ArticleService;
import com.sarah.service.CommentService;
import com.sarah.service.UserService;
import com.sarah.util.PageBean;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private ArticleImgService articleImgService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private UserService userService;

	@RequestMapping("/addComment.do")
	@ResponseBody
	public void addComment(Comment comment, HttpServletResponse response) {
		int r = 0;
		if (comment != null) {
			comment.setCreatetime(new Date());
			r = commentService.insert(comment);
		}
		Map<String, Object> map = new HashMap();
		if (r != 0) {
			map.put("msg", "已评论成功!");
			String jsres = JSONObject.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(jsres);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			map.put("msg", "评论失败!");
			String jsres = JSONObject.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(jsres);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping("/findComment.do")
	@ResponseBody
	public ModelAndView findComment(HttpServletRequest request, HttpServletResponse response) {
		int uid = Integer.parseInt(request.getParameter("userId"));
		List<Article> articles = commentService.selectByUser(uid);
		List<ArticleModel> articleModels = new ArrayList<ArticleModel>();
		ModelAndView mv = new ModelAndView("context");
		if (articles != null) {
			for (Article article : articles) {
				ArticleModel model = new ArticleModel();
				model.setId(article.getId());
				model.setArticleImg(articleImgService.selectByArticle(article));
				model.setAuther(article.getAuther());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				model.setCreatetime(sdf.format(article.getCreatetime()));
				model.setTitle(article.getTitle());
				articleModels.add(model);
			}
			mv.addObject("articleList", articleModels);
		}

		return mv;
	}

	@RequestMapping("commentList.do")
	public ModelAndView artileImgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/view/comment/commentList");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}

		PageBean<Comment> pb = commentService.selectList(Integer.parseInt(pageNum), 10);
		mv.addObject("pageBean", pb);
		List<CommemtVo> comments = new ArrayList<CommemtVo>();
		for (Comment comment : pb.getList()) {
			CommemtVo commemtVo = new CommemtVo();
			commemtVo.setId(comment.getId());
			commemtVo.setContent(comment.getContent());
			commemtVo.setArticleName(articleService.selectByPrimaryKey(comment.getArticleid()).getTitle());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			commemtVo.setCreatetime(sdf.format(comment.getCreatetime()));
			commemtVo.setFromUser(userService.selectByPrimaryKey(comment.getFromUid()).getName());
			commemtVo.setId(comment.getId());
			comments.add(commemtVo);

		}
		mv.addObject("comments", comments);
		return mv;
	}

	@RequestMapping(value = "deleteComment.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteArticle(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String msg = "删除失败!";
		int val = commentService.deleteByPrimaryKey(Integer.parseInt(id));
		if (val != 0) {
			msg = "删除成功";
		}
		return msg;

	}
}
