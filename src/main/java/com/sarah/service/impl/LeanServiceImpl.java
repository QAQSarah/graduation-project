package com.sarah.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarah.dao.LeanlineMapper;
import com.sarah.model.Leanline;
import com.sarah.model.LeanlineCategory;
import com.sarah.service.LeanService;
import com.sarah.util.PageBean;
@Service
public class LeanServiceImpl implements LeanService {
	@Autowired
	private LeanlineMapper leanlineMapper ;
	public int deleteByPrimaryKey(Integer id) {
		return leanlineMapper.deleteByPrimaryKey(id);
	}

	public int insert(Leanline record) {
		return leanlineMapper.insert(record);
	}

	public int insertSelective(Leanline record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Leanline selectByPrimaryKey(Integer id) {
		return leanlineMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Leanline record) {
		return leanlineMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(Leanline record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Leanline> seleteAll(Leanline record) {
		return	leanlineMapper.selectAll(record);
	}

	public PageBean<Leanline> selectList(int page, int rows, Leanline leanline) {
		List<Leanline> all=leanlineMapper.selectAll(leanline);
		int totalRecord = 0;
		if(all!=null) {
			totalRecord=all.size();
		}
		PageBean<Leanline> pb = new PageBean<Leanline>(page, rows, totalRecord);
		int startIndex = pb.getStartIndex();
		List<Leanline> list=new ArrayList<Leanline>();
		list=leanlineMapper.selectList(startIndex ,leanline.getName(), rows);
		if(list==null) {
			return null;
		}else {
			pb.setList(list);
			return pb;
		}
	}

}
