package com.sarah.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarah.dao.ResponseMapper;
import com.sarah.dao.UserMapper;
import com.sarah.model.Comment;
import com.sarah.model.ReponseModel;
import com.sarah.model.Response;
import com.sarah.model.User;
import com.sarah.service.ReponseService;
import com.sarah.util.PageBean;

@Service
public class ResponseServiceImpl implements ReponseService {
	@Autowired
	private ResponseMapper responseMapper;
	@Autowired
	private UserMapper userMapper;

	public int deleteByPrimaryKey(Integer id) {
		return responseMapper.deleteByPrimaryKey(id);
	}

	public int insert(Response record) {
		int r=0;
		if(record!=null) {
			r=responseMapper.insert(record);
		}
		return r;
	}

	public int insertSelective(Response record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Response selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateByPrimaryKeySelective(Response record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Response record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<ReponseModel> selectAll(Response record) {
		List<ReponseModel> list = new ArrayList<ReponseModel>();
		if (record != null) {
			List<Response> list1 = responseMapper.selectAll(record);
			for (Response response : list1) {
				ReponseModel reponseModel = new ReponseModel();
				reponseModel.setContext(response.getContent());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				reponseModel.setTime(sdf.format(response.getCreatetime()));
				User user=userMapper.selectByPrimaryKey(response.getFromUid());
				if(user!=null) {
					reponseModel.setFuser(user.getName());
				}
				list.add(reponseModel);
			}
		}
		return list;
	}

	public List<Response> selectResponseAll(Response record) {
		return responseMapper.selectAll(record);
	}

	public PageBean<Response> selectList(int pageNum, int rows) {
		Response response=new Response();
		List<Response> all = responseMapper.selectAll(response);
		int totalRecord = 0;
		if(all!=null) {
			totalRecord=all.size();
		}
		PageBean<Response> pb = new PageBean<Response>(pageNum, rows, totalRecord);
		int startIndex = pb.getStartIndex();
		 List<Response> list = responseMapper.selectList(startIndex , rows);
		if(list==null) {
			return null;
		}else {
			pb.setList(list);
			return pb;
		}
	}

}
