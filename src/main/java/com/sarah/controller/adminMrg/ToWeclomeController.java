package com.sarah.controller.adminMrg;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sarah.model.Article;
import com.sarah.model.Collect;
import com.sarah.model.Comment;
import com.sarah.model.CommentModel;
import com.sarah.model.Likes;
import com.sarah.model.User;
import com.sarah.service.ArticleService;
import com.sarah.service.CollectService;
import com.sarah.service.CommentService;
import com.sarah.service.LikesService;
import com.sarah.service.UserService;

@Controller
@RequestMapping("/adminMrg")
public class ToWeclomeController {

	@Autowired
	private UserService userService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private CollectService collectService;
	@Autowired
	private LikesService likesService;

	@RequestMapping("/toWeclome.do")
	public ModelAndView toWeclome() {
		ModelAndView mv = new ModelAndView("WEB-INF/view/welcome");
		List<User> userList=userService.selectAllUser(new User());
		mv.addObject("userCount", userList.size());
		List<Article> articles=articleService.showArticles();
		mv.addObject("articleCount", articles.size());
		Comment comment=new Comment();
		List<CommentModel> comments=commentService.seleteByComment(comment);
		mv.addObject("commentCount", comments.size());
		Likes likes=new Likes();
		List<Likes> likelist=likesService.selectAll(likes);
		mv.addObject("likeCount",likelist.size());
		Collect collect=new Collect();
		List<Collect> collects=collectService.selectAll(collect);
		mv.addObject("collectCount", collects.size());
		return mv;
	}
}
