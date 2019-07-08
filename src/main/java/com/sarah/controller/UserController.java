package com.sarah.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sarah.model.User;
import com.sarah.model.UserModel;
import com.sarah.service.UserService;
import com.sarah.util.PageBean;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/register.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String addUser(User user) throws IOException {
		String msg = "操作失败!";
		user.setCreatetime(new Date());
		user.setStatus(1);
		if (user != null) {
			msg = userService.insertUser(user);
		}
		return msg;

	}

	@RequestMapping("/registerwithimg.do")
	@ResponseBody
	public void upload(@RequestParam("name") String username, @RequestParam("password") String password,
			@RequestParam("email") String email, @RequestParam("file") MultipartFile file, HttpServletResponse response)
			throws Exception {
		String sqlPath = null;// 保存数据库的路径
		String filename = null;// 定义文件名
		String contentType = file.getContentType(); // 获取文件类型（后缀）
		// 因为获取的后缀是XXXX/xxx形式
		contentType = contentType.substring(contentType.indexOf("/") + 1);
		filename = username + "." + contentType;
		String url = "E:\\graduation_project\\it_web\\src\\main\\webapp\\userimg";
		url = url + "\\";
		file.transferTo(new File(url + filename));// 保存图片
		sqlPath = "userimg/" + filename;
		User user = new User();
		String msg = "";
		if (user != null || !("".equals(user))) {
			user.setCreatetime(new Date());
			user.setStatus(1);
			user.setExmail(email);
			user.setName(username);
			user.setPassword(password);
			user.setImgurl(sqlPath);
			msg = userService.insertUser(user);
		} else {
			msg = "操作失败!";
		}
		response.reset();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script language=\"javascript\">alert('" + msg + "');setTimeout(\"window.history.go(-1);window.history.go(-1);\",500);</script>");
		out.flush();
		out.close();
	}
	@RequestMapping(value = "/addUserByAdmin.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String addUserByAdmin(User user) throws IOException {
		String msg = "操作失败!";
		user.setCreatetime(new Date());
		user.setStatus(1);
		if (user != null) {
			msg = userService.insertUser(user);
		}
		return msg;

	}

	@RequestMapping("/addUserWithimgByAdmin.do")
	@ResponseBody
	public void uploadByAdmin(@RequestParam("name") String username, @RequestParam("password") String password,
			@RequestParam("email") String email, @RequestParam("file") MultipartFile file, HttpServletResponse response)
			throws Exception {
		String sqlPath = null;// 保存数据库的路径
		String filename = null;// 定义文件名
		String contentType = file.getContentType(); // 获取文件类型（后缀）
		// 因为获取的后缀是XXXX/xxx形式
		contentType = contentType.substring(contentType.indexOf("/") + 1);
		filename = username + "." + contentType;
		String url = "E:\\graduation_project\\it_web\\src\\main\\webapp\\userimg";
		url = url + "\\";
		file.transferTo(new File(url + filename));// 保存图片
		sqlPath = "userimg/" + filename;
		User user = new User();
		String msg = "";
		if (user != null || !("".equals(user))) {
			user.setCreatetime(new Date());
			user.setStatus(1);
			user.setExmail(email);
			user.setName(username);
			user.setPassword(password);
			user.setImgurl(sqlPath);
			msg = userService.insertUser(user);
		} else {
			msg = "操作失败!";
		}
		response.reset();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script language=\"javascript\">alert('" + msg + "');setTimeout(\"window.history.go(-1);\",500);</script>");
		out.flush();
		out.close();
	}

	@RequestMapping("/editUser.do")
	@ResponseBody
	public void editUser(@RequestParam("id") int id, @RequestParam("name") String username,
			@RequestParam("password") String password, HttpServletResponse response) throws Exception {
		User user = new User();
		user.setId(id);
		user.setPassword(password);
		int val = userService.updateUser(user);
		String msg = "修改成功";
		if (val == 0) {
			msg = "修改失败!";
		}
		response.reset();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(msg);
		out.flush();
		out.close();
	}

	@SuppressWarnings("unchecked")
	@RequestMapping("/login.do")
	@ResponseBody
	public void selectUser(User user, HttpServletResponse response) throws IOException {
		Map map = userService.selectByUser(user);
		String jsres = JSONObject.toJSONString(map);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(jsres);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/resetPassword.do")
	@ResponseBody
	public void resetPassword(User user, HttpServletResponse response) throws IOException {
		String msg = "";
		if (user != null || !("".equals(user))) {
			msg = userService.updateByPrimaryKeySelective(user);
		} else {
			msg = "重置失败!";
		}
		response.reset();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("<script language=\"javascript\">alert('" + msg
				+ "');window.location.href='/it_web/forgetting.jsp';</script>");
		out.flush();
		out.close();
	}

	@RequestMapping("/toManagement.do")
	@ResponseBody
	public void toManagement(User user, HttpServletResponse response) throws IOException {
		if (user != null) {
			User record = userService.selectByAdmin(user);

			Map<String, Object> map = new HashMap<String, Object>();
			if (record != null) {
				map.put("user", record);
				String jsres = JSONObject.toJSONString(map);
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.print(jsres);
			}
		}
	}

	@RequestMapping("/tot.do")
	@ResponseBody
	public ModelAndView tot(User user) throws IOException {
		ModelAndView mv = new ModelAndView();
		if (user != null && user.getName() != null && user.getPassword() != null && !(user.getName().equals(""))
				&& !(user.getPassword().equals(""))) {
			mv.addObject("user", user);
			mv.setViewName("WEB-INF/view/index");
		} else {
			mv.setViewName("index");
		}
		return mv;
	}

	@RequestMapping("toUserInfo.do")
	public ModelAndView toUserInfo(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String username = request.getParameter("username");
		ModelAndView mv = new ModelAndView("WEB-INF/view/userInfo");
		User user = new User();
		if (id != null) {
			user.setId(Integer.parseInt(id));
			User u = userService.selectBySelective(user);
			mv.addObject("user", u);
		} else if (username != null) {
			user.setName(username);
			User u = userService.selectByAdmin(user);
			mv.addObject("user", u);
		}
		return mv;
	}

	@RequestMapping("EditMenber.do")
	public ModelAndView showMenberList(HttpServletRequest request, HttpServletResponse response, User user) {
		ModelAndView mv = new ModelAndView("WEB-INF/view/member/memberList");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		PageBean<User> pb = userService.seleteUserPageBean(Integer.parseInt(pageNum), 10);
		mv.addObject("pageBean", pb);
		List<UserModel> userModels = new ArrayList<UserModel>();
		for (int i = 0; i < pb.getList().size(); i++) {
			UserModel userModel = new UserModel();
			userModel.setId(pb.getList().get(i).getId());
			userModel.setExmail(pb.getList().get(i).getExmail());
			userModel.setName(pb.getList().get(i).getName());
			userModel.setImgurl(pb.getList().get(i).getImgurl());
			userModel.setStatus(pb.getList().get(i).getStatus());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			userModel.setCreatetime(sdf.format(pb.getList().get(i).getCreatetime()));
			userModels.add(userModel);
		}
		mv.addObject("userList", userModels);

		return mv;
	}

	@RequestMapping("SreachMenber.do")
	public ModelAndView showSreachMenberList(HttpServletRequest request, HttpServletResponse response) {
		String name=request.getParameter("name");
		String exmail=request.getParameter("exmail");
		String status=request.getParameter("status");
		User user=new User();
		user.setName(name);
		user.setExmail(exmail);
		if(status!=null&&status.equals("")==false) {
			if(Integer.parseInt(status)!=0) {
				user.setStatus(Integer.parseInt(status));	
			}
		}
		ModelAndView mv = new ModelAndView("WEB-INF/view/member/memberList");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		PageBean<User> pb = userService.seleteSreach(Integer.parseInt(pageNum), 10, user);
		mv.addObject("pageBean", pb);
		List<UserModel> userModels = new ArrayList<UserModel>();
		for (int i = 0; i < pb.getList().size(); i++) {
			UserModel userModel = new UserModel();
			userModel.setId(pb.getList().get(i).getId());
			userModel.setExmail(pb.getList().get(i).getExmail());
			userModel.setName(pb.getList().get(i).getName());
			userModel.setImgurl(pb.getList().get(i).getImgurl());
			userModel.setStatus(pb.getList().get(i).getStatus());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			userModel.setCreatetime(sdf.format(pb.getList().get(i).getCreatetime()));
			userModels.add(userModel);
		}
		mv.addObject("userList", userModels);
		return mv;
	}

	@ResponseBody
	@RequestMapping("editUserStatus.do")
	public JSONObject editUserStatus(HttpServletRequest request, HttpServletResponse response) {
		JSONObject result = new JSONObject();
		int id = Integer.parseInt(request.getParameter("id"));
		String type = request.getParameter("type");
		User user = new User();
		user.setId(id);
		List<User> list = userService.selectAllUser(user);
		if (list.size() != 0 && type.equals("add")) {
			User u = new User();
			u = list.get(0);
			u.setStatus(0);
			int r = userService.updateByPrimaryKey(u);
			if (r != 0) {
				result.put("msg", "success");
			} else {
				result.put("msg", "erro");
			}
		} else if (list.size() != 0 && type.equals("delete")) {
			User u = new User();
			u = list.get(0);
			u.setStatus(1);
			int r = userService.updateByPrimaryKey(u);
			if (r != 0) {
				result.put("msg", "success");
			} else {
				result.put("msg", "erro");
			}
		}
		return result;
	}

	@RequestMapping("addUser.do")
	public ModelAndView addUser() {
		ModelAndView mv = new ModelAndView("WEB-INF/view/member/member-add");
		return mv;

	}

	@RequestMapping(value = "deleteUser.do", produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteUser(HttpServletRequest request) {
		String msg="激活状态,不能删除";
		String id = request.getParameter("id");
		int val = userService.deleteByPrimaryKey(Integer.parseInt(id));
		if(val!=0) {
			msg="已删除";
		}
		return msg;

	}
}
