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
import com.sarah.model.Article;
import com.sarah.model.ArticleModel;
import com.sarah.model.Collect;
import com.sarah.service.ArticleImgService;
import com.sarah.service.CollectService;

@Controller
public class CollectController {
	@Autowired
	private CollectService collectService;
	@Autowired
	private ArticleImgService articleImgService;

	@SuppressWarnings("unchecked")
	@RequestMapping("/addCollet.do")
	@ResponseBody
	public void addCollect(Collect collect, HttpServletResponse response) throws IOException {
		collect.setCreattime(new Date());
		int r = collectService.insert(collect);
		Map<String, Object> map = new HashMap();
		if (r == 0) {
			map.put("msg", "收藏失败或之前已收藏");
			String jsres = JSONObject.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(jsres);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			map.put("msg", "已收藏");
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
	@RequestMapping("/removeCollet.do")
	@ResponseBody
	public void removeCollect(Collect collect, HttpServletResponse response) throws IOException {
		int r = collectService.deleteByCollect(collect);
		Map<String, Object> map = new HashMap();
		if (r == 0) {
			map.put("msg", "取消收藏失败");
			String jsres = JSONObject.toJSONString(map);
			try {
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(jsres);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			map.put("msg", "已移除收藏");
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

	@RequestMapping("/findC.do")
	@ResponseBody
	public ModelAndView findC(HttpServletRequest request, HttpServletResponse response) {
		int uid = Integer.parseInt(request.getParameter("userId"));
		List<Article> articles = collectService.selectByUser(uid);
		List<ArticleModel> articleModels = new ArrayList<ArticleModel>();
		ModelAndView mv = new ModelAndView("context");
		if (articles!=null) {
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
