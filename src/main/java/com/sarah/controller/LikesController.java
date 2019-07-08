package com.sarah.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.sarah.model.Article;
import com.sarah.model.ArticleModel;
import com.sarah.model.Likes;
import com.sarah.service.ArticleImgService;
import com.sarah.service.LikesService;

@Controller
public class LikesController {
	@Autowired
	private LikesService likesService;
	@Autowired
	private ArticleImgService articleImgService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/addLikes.do")
	@ResponseBody
	public void addLikes(Likes likes, HttpServletResponse response) throws IOException {
		int r = likesService.insert(likes);
		Map<String, Object> map = new HashMap();
		if (r == 0) {
			map.put("msg", "已是喜欢的文章或者设置为喜欢失败");
			String jsres = JSONObject.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(jsres);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			map.put("msg", "已订阅为喜欢");
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

	@SuppressWarnings("unchecked")
	@RequestMapping("/removeLikes.do")
	@ResponseBody
	public void removeLikes(Likes likes, HttpServletResponse response) throws IOException {
		int r = likesService.deleteByPrimaryKey(likes);
		Map<String, Object> map = new HashMap();
		if (r == 0) {
			map.put("msg", "取消为喜欢失败");
			String jsres = JSONObject.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(jsres);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			map.put("msg", "已取消为喜欢");
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

	@RequestMapping("/findLikes.do")
	@ResponseBody
	public ModelAndView findLikes(HttpServletRequest request, HttpServletResponse response) {
		int uid = Integer.parseInt(request.getParameter("userId"));
		List<Article> articles = likesService.selectByUser(uid);
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

}
