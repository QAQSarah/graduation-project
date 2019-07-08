package com.sarah.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sarah.model.Category;
import com.sarah.model.Leanline;
import com.sarah.model.LeanlineCategory;
import com.sarah.model.LeanlineModel;
import com.sarah.service.LeanLineService;
import com.sarah.service.LeanService;
import com.sarah.util.PageBean;

@RequestMapping("/line")
@Controller
public class LineController {

	@Autowired
	private LeanLineService leanLineService;
	
	@Autowired
	private LeanService leanService;
	
	
	@RequestMapping("/findCategory.do")
	public ModelAndView findCategory() {
		LeanlineCategory record=new LeanlineCategory();
		List<LeanlineCategory>  leanlines=leanLineService.seleteAll(record);
		ModelAndView mv=new ModelAndView("WEB-INF/line_page");
		if(leanlines.size()!=0) {
			mv.addObject("leanlines", leanlines);
		}
		return mv;
		
	}
	
	@RequestMapping("/findLeanLine.do")
	public ModelAndView findLeanLine(HttpServletRequest request) {
		int id=Integer.parseInt(request.getParameter("id"));
		Leanline leanline = new Leanline();
		List<Leanline> ls=new ArrayList<Leanline>();
		if(id!=0) {
			leanline.setLeanlineCategoryid(id);
			ls=leanService.seleteAll(leanline);
			ModelAndView mv=new ModelAndView("WEB-INF/showLinePage");
			mv.addObject("ls",ls);
			return mv;
		}
		ModelAndView mv=new ModelAndView("showLinePage");
		return mv;
		
	}
	@RequestMapping("leanCategoryList.do")
	public ModelAndView artileList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/view/leanCategory/leanCategoryList");
		String name=request.getParameter("name");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		LeanlineCategory leanlineCategory=new LeanlineCategory();
		if(name!=null) {
			leanlineCategory.setName(name);
		}
		PageBean<LeanlineCategory> pb=leanLineService.selectList(Integer.parseInt(pageNum),10,leanlineCategory);
		mv.addObject("pageBean", pb);
		List<LeanlineCategory> categorys=new ArrayList<LeanlineCategory>();
		for (LeanlineCategory category1 : pb.getList()) {
			LeanlineCategory model=new LeanlineCategory();
			model.setId(category1.getId());
			model.setName(category1.getName());
			model.setIntroduce(category1.getIntroduce());
			categorys.add(model);
		}
		mv.addObject("categorys", categorys);
		return mv;
	}
	@RequestMapping("toAddLeanCategory.do")
	public ModelAndView toAddCategory(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView("WEB-INF/view/leanCategory/leanCategory-add");
		return mv;
	}
	
	@RequestMapping(value="addLeanCategory.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String addCategory(HttpServletRequest request,HttpServletResponse response) {
		String msg="添加失败";
		String name=request.getParameter("name");
		String introduct=request.getParameter("introduct");
		LeanlineCategory category=new LeanlineCategory();
		category.setName(name);
		category.setIntroduce(introduct);
		int val=leanLineService.insert(category);
		if(val!=0) {
			msg="添加成功";
		}
		return msg;
		
	}
	@RequestMapping("toEditLeanCategory.do")
	public ModelAndView toEditCategory(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView("WEB-INF/view/leanCategory/leanCategory-edit");
		String id=request.getParameter("id");
		LeanlineCategory category=leanLineService.selectByPrimaryKey(Integer.parseInt(id));
		mv.addObject("category", category);
		return mv;
	}
	@RequestMapping(value="editLeanCategory.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String editCategory(HttpServletRequest request,HttpServletResponse response) {
		String msg="修改失败";
		String name=request.getParameter("name");
		String introduce=request.getParameter("introduce");
		String id=request.getParameter("id");
		LeanlineCategory category=leanLineService.selectByPrimaryKey(Integer.parseInt(id));
		if(name!=null) {
			category.setName(name);
		}
		if(introduce!=null) {
			category.setIntroduce(introduce);
		}
		int val=leanLineService.updateByPrimaryKeySelective(category);
		if(val!=0) {
			msg="修改成功";
		}
		return msg;
		
	}
	@RequestMapping(value="deleteLeanCategory.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteLeanCategory(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		String msg="因分类下有数据,不能删除!";
		int val =leanLineService.deleteByPrimaryKey(Integer.parseInt(id));
		if(val!=0) {
			msg="删除成功";
		}
		return msg;
	}
	@RequestMapping("leanList.do")
	public ModelAndView LeanList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/view/lean/leanList");
		String name=request.getParameter("name");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		Leanline leanline=new Leanline();
		if(name!=null) {
			leanline.setName(name);
		}
		PageBean<Leanline> pb=leanService.selectList(Integer.parseInt(pageNum),10,leanline);
		mv.addObject("pageBean", pb);
		List<LeanlineModel> categorys=new ArrayList<LeanlineModel>();
		for (Leanline category1 : pb.getList()) {
			LeanlineModel model=new LeanlineModel();
			model.setId(category1.getId());
			model.setName(category1.getName());
			model.setDownloadurl(category1.getDownloadurl());
			model.setLeanlineCategory(leanLineService.selectByPrimaryKey(category1.getLeanlineCategoryid()).getName());
			categorys.add(model);
		}
		mv.addObject("categorys", categorys);
		return mv;
	}
	@RequestMapping("toAddLean.do")
	public ModelAndView toAddLean(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView("WEB-INF/view/lean/lean-add");
		LeanlineCategory record=new LeanlineCategory();
		List<LeanlineCategory> categorys=leanLineService.seleteAll(record);
		mv.addObject("categorys", categorys);
		return mv;
	}
	@RequestMapping(value="addLean.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String addLean(HttpServletRequest request,HttpServletResponse response) {
		String msg="添加失败";
		String name=request.getParameter("name");
		String introduct=request.getParameter("introduct");
		String categoryId=request.getParameter("categoryId");
		Leanline category=new Leanline();
		category.setName(name);
		category.setDownloadurl(introduct);
		category.setLeanlineCategoryid(Integer.parseInt(categoryId));
		int val=leanService.insert(category);
		if(val!=0) {
			msg="添加成功";
		}
		return msg;
		
	}
	@RequestMapping("toEditLean.do")
	public ModelAndView toEditLean(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView("WEB-INF/view/lean/lean-edit");
		String id=request.getParameter("id");
		Leanline category=leanService.selectByPrimaryKey(Integer.parseInt(id));
		mv.addObject("lean", category);
		LeanlineCategory record=new LeanlineCategory();
		List<LeanlineCategory> categorys=leanLineService.seleteAll(record);
		mv.addObject("categorys", categorys);
		return mv;
	}
	@RequestMapping(value="editLean.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String editLean(HttpServletRequest request,HttpServletResponse response) {
		String msg="修改失败";
		String name=request.getParameter("name");
		String introduct=request.getParameter("introduct");
		String categoryId=request.getParameter("categoryId");
		String id=request.getParameter("id");
		Leanline category=leanService.selectByPrimaryKey(Integer.parseInt(id));
		if(name!=null) {
			category.setName(name);
		}
		if(introduct!=null) {
			category.setDownloadurl(introduct);
		}
		if(categoryId!=null) {
			category.setLeanlineCategoryid(Integer.parseInt(categoryId));
		}
		int val=leanService.updateByPrimaryKeySelective(category);
		if(val!=0) {
			msg="修改成功";
		}
		return msg;
		
	}
	@RequestMapping(value="deleteLean.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteLean(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		String msg="删除失败!";
		int val =leanService.deleteByPrimaryKey(Integer.parseInt(id));
		if(val!=0) {
			msg="删除成功";
		}
		return msg;
	}
}
