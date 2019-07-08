package com.sarah.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarah.dao.LeanlineCategoryMapper;
import com.sarah.dao.LeanlineMapper;
import com.sarah.model.Category;
import com.sarah.model.Leanline;
import com.sarah.model.LeanlineCategory;
import com.sarah.service.LeanLineService;
import com.sarah.util.PageBean;
@Service
public class LeanLineServiceImpl implements LeanLineService {

	@Autowired
	private LeanlineCategoryMapper leanlineCategoryMapper;
	@Autowired
	private LeanlineMapper leanlineMapper;
	
	public int deleteByPrimaryKey(Integer id) {
		int val=0;
		Leanline leanline=new Leanline();
		leanline.setLeanlineCategoryid(id);
		List<Leanline> leanlines=leanlineMapper.selectAll(leanline);
		if(leanlines.size()<=0) {
			val=leanlineCategoryMapper.deleteByPrimaryKey(id);
		}
		return val;
	}

	public int insert(LeanlineCategory record) {
		return leanlineCategoryMapper.insert(record);
	}

	public int insertSelective(LeanlineCategory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public LeanlineCategory selectByPrimaryKey(Integer id) {
		return leanlineCategoryMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(LeanlineCategory record) {
		return leanlineCategoryMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(LeanlineCategory record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<LeanlineCategory> seleteAll(LeanlineCategory record) {
		return  leanlineCategoryMapper.selectAll(record);
	}

	public PageBean<LeanlineCategory> selectList(int page, int rows, LeanlineCategory leanlineCategory) {
		List<LeanlineCategory> all=leanlineCategoryMapper.selectAll(leanlineCategory);
		int totalRecord = 0;
		if(all!=null) {
			totalRecord=all.size();
		}
		PageBean<LeanlineCategory> pb = new PageBean<LeanlineCategory>(page, rows, totalRecord);
		int startIndex = pb.getStartIndex();
		List<LeanlineCategory> list=new ArrayList<LeanlineCategory>();
		list=leanlineCategoryMapper.selectList(startIndex ,leanlineCategory.getName(), rows);
		if(list==null) {
			return null;
		}else {
			pb.setList(list);
			return pb;
		}
	}

}
