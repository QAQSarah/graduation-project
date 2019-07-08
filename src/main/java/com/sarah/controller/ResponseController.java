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
import com.sarah.model.Response;
import com.sarah.model.User;
import com.sarah.service.CommentService;
import com.sarah.service.ReponseService;
import com.sarah.service.UserService;
import com.sarah.service.impl.ReponseVo;
import com.sarah.util.PageBean;
@Controller
public class ResponseController {
	@Autowired
	private ReponseService reponseService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;

	@RequestMapping("/addResponse.do")
	@ResponseBody
	public void addResponse(Response response, HttpServletResponse res) {
		int r = 0;
		if (response != null) {
			response.setCreatetime(new Date());
			r=reponseService.insert(response);
		}
		Map<String, Object> map = new HashMap();
		if (r != 0) {
			map.put("msg", "回复成功!");
			String jsres = JSONObject.toJSONString(map);
			try {
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.print(jsres);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			map.put("msg", "回复失败!");
			String jsres = JSONObject.toJSONString(map);
			try {
				res.setCharacterEncoding("UTF-8");
				PrintWriter out = res.getWriter();
				out.print(jsres);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@RequestMapping("reponseList.do")
	public ModelAndView artileImgList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/view/reponse/reponseList");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
	
		PageBean<Response> pb=reponseService.selectList(Integer.parseInt(pageNum),10);
		mv.addObject("pageBean", pb);
		List<ReponseVo> reponseVos=new ArrayList<ReponseVo>();
		for (Response response2 : pb.getList()) {
			ReponseVo reponseVo=new ReponseVo();
			reponseVo.setId(response2.getId());
			reponseVo.setContent(response2.getContent());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			reponseVo.setCreatetime(sdf.format(response2.getCreatetime()));
			User user=userService.selectByPrimaryKey(response2.getFromUid());
			if(user!=null) {
				reponseVo.setFromUser(user.getName());
			}
			reponseVo.setCommentContext(commentService.selectByPrimaryKey(response2.getCommentid()).getContent());
			reponseVos.add(reponseVo);
		}
		mv.addObject("reponseVos", reponseVos);
		return mv;
	}
	@RequestMapping(value="deleteReponse.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteReponse(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		String msg="删除失败!";
		int val = reponseService.deleteByPrimaryKey(Integer.parseInt(id));
		if(val!=0) {
			msg="删除成功";
		}
		return msg;
		
	}
}
