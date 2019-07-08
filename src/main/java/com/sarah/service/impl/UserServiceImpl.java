package com.sarah.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarah.dao.UserMapper;
import com.sarah.model.Comment;
import com.sarah.model.CommentModel;
import com.sarah.model.User;
import com.sarah.service.CommentService;
import com.sarah.service.UserService;
import com.sarah.util.MD5Utils;
import com.sarah.util.PageBean;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userDao;
	@Autowired
	private CommentService commentService;

	public int deleteByPrimaryKey(Integer id) {
		int r=0;
		User user=new User();
		user.setId(id);
		User user1 = selectBySelective(user);
		if(user1.getStatus()==0) {
			Comment record=new Comment();
			record.setFromUid(id);
			List<CommentModel> comments=commentService.seleteByComment(record);
			for (CommentModel commentModel : comments) {
				commentService.deleteByPrimaryKey(commentModel.getId());
			}		
			r=userDao.deleteByPrimaryKey(id);
		}
		return r;
	}

	public int insert(User record) {

		return 0;
	}

	public int insertSelective(User record) {

		return 0;
	}

	public User selectByPrimaryKey(Integer id) {
		return userDao.selectByPrimaryKey(id);
	}

	public String updateByPrimaryKeySelective(User record) {
		String msg = "";
		User user = new User();
		user.setName(record.getName());
		user.setExmail(record.getExmail());
		List<User> list = userDao.selectAll(user);
		if (list.size() > 0) {
			record.setId(list.get(0).getId());
			String password = record.getPassword();
			record.setPassword(MD5Utils.str2MD5(password));
			int val = userDao.updateByPrimaryKeySelective(record);
			if (val != 0) {
				msg = "重新设置成功!";
			} else {
				msg = "重新设置失败,稍后再试!";
			}
		} else {
			msg = "没有该用户!";
		}

		return msg;
	}

	public int updateByPrimaryKey(User record) {

		return userDao.updateByPrimaryKey(record);
	}

	public String insertUser(User record) {
		String msg = "";
		List<String> list1 = userDao.selectUserName(record);
		List<String> list2 = userDao.selectEmail(record);
		if (list1.size() > 0) {
			msg = "该用户名已存在";
		} else if (list2.size() > 0) {
			msg = "邮箱已存在";
		} else {
			String password = MD5Utils.str2MD5(record.getPassword());
			record.setPassword(password);
			int val = userDao.insertSelective(record);
			if (val != 0) {
				msg = "操作成功";
			} else {
				msg = "操作失败";
			}

		}
		return msg;
	}

	public Map selectByUser(User record) {
		String msg = "";
		String password = MD5Utils.str2MD5(record.getPassword());
		record.setPassword(password);
		List<User> list = userDao.selectAll(record);
		Map map = new HashMap<String, Object>();
		if (list.size() > 0) {
			User user = list.get(0);
			if (user.getStatus() == 1) {
				if (user.getName().equals("admin") && user.getExmail().equals("1137160979@qq.com")) {
					msg = "管理员";
				} else {
					msg = "普通用户";
				}
				map.put("user", user);
			}
		} else {
			msg = "访客";
		}
		map.put("msg", msg);
		return map;
	}

	public User selectByAdmin(User record) {
		User user = new User();
		List<User> list = userDao.selectAll(record);
		if (list.size() != 0 && list.get(0).getName().equals("admin")) {
			user = list.get(0);
		}
		return user;
	}

	public List<User> selectAllUser(User record) {
		List<User> list = new ArrayList<User>();
		list = userDao.selectAll(record);
		return list;
	}

	public PageBean<User> seleteUserPageBean(int pageNum, int rows) {
		User user = new User();
		List<User> list = new ArrayList<User>();
		list = userDao.selectAll(user);
		int totalRecord = 0;
		if (list != null) {
			totalRecord = list.size();
		}
		PageBean<User> pb = new PageBean<User>(pageNum, rows, totalRecord);
		int startIndex = pb.getStartIndex();
		List<User> list2 = new ArrayList<User>();
		list2 = userDao.selectUserPage(startIndex, rows);
		if (list2 == null) {
			return null;
		} else {
			pb.setList(list2);
			return pb;
		}
	}
	public PageBean<User> seleteSreach(int pageNum, int rows,User user) {
		List<User> list = new ArrayList<User>();
		list = userDao.selectAll(user);
		int totalRecord = 0;
		if (list != null) {
			totalRecord = list.size();
		}
		PageBean<User> pb = new PageBean<User>(pageNum, rows, totalRecord);
		int startIndex = pb.getStartIndex();
		List<User> list2 = new ArrayList<User>();
		list2 = userDao.selectSreachUser(startIndex, rows,user.getName(),user.getExmail(),user.getStatus());
		if (list2 == null) {
			return null;
		} else {
			pb.setList(list2);
			return pb;
		}
	}

	public User selectBySelective(User record) {
		User user = new User();
		List<User> list = userDao.selectAll(record);
		if (list.size() != 0) {
			user = list.get(0);
		}
		return user;
	}

	public int updateUser(User record) {
		String password=MD5Utils.str2MD5(record.getPassword());
		record.setPassword(password);
		return userDao.updateByPrimaryKeySelective(record);
	}
}
