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
import com.sarah.service.CategoryService;
import com.sarah.util.PageBean;

@RequestMapping("/category")
@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	

	@RequestMapping("articleCategoryList.do")
	public ModelAndView artileList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/view/articleCategory/articleCategoryList");
		String name=request.getParameter("name");
		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		Category category=new Category();
		if(name!=null) {
			category.setCategoryname(name);
		}
		PageBean<Category> pb=categoryService.seleteCategorys(Integer.parseInt(pageNum),10,category);
		mv.addObject("pageBean", pb);
		List<Category> categorys=new ArrayList<Category>();
		for (Category category1 : pb.getList()) {
			Category model=new Category();
			model.setId(category1.getId());
			model.setCategoryname(category1.getCategoryname());
			categorys.add(model);
		}
		mv.addObject("categorys", categorys);
		return mv;
	}
	
	@RequestMapping("toAddCategory.do")
	public ModelAndView toAddCategory(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView("WEB-INF/view/articleCategory/articleCategory-add");
		return mv;
	}
	@RequestMapping(value="addCategory.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String addCategory(HttpServletRequest request,HttpServletResponse response) {
		String msg="添加失败";
		String name=request.getParameter("name");
		Category category=new Category();
		category.setCategoryname(name);
		int val=categoryService.insertCategory(category);
		if(val!=0) {
			msg="添加成功";
		}
		return msg;
		
	}
	@RequestMapping("toEditCategory.do")
	public ModelAndView toEditCategory(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView mv=new ModelAndView("WEB-INF/view/articleCategory/articleCategory-edit");
		String id=request.getParameter("id");
		Category category=categoryService.selectById(Integer.parseInt(id));
		mv.addObject("category", category);
		return mv;
	}
	@RequestMapping(value="editCategory.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String editCategory(HttpServletRequest request,HttpServletResponse response) {
		String msg="修改失败";
		String name=request.getParameter("name");
		String id=request.getParameter("id");
		Category category=new Category();
		category.setId(Integer.parseInt(id));
		category.setCategoryname(name);
		int val=categoryService.updateCategory(category);
		if(val!=0) {
			msg="修改成功";
		}
		return msg;
		
	}
	@RequestMapping(value="deleteCategory.do",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String deleteCategory(HttpServletRequest request,HttpServletResponse response) {
		String id = request.getParameter("id");
		String msg="因分类下有文章,不能删除!";
		int val =categoryService.deleteByPrimaryKey(Integer.parseInt(id));
		if(val!=0) {
			msg="删除成功";
		}
		return msg;
	}
}
