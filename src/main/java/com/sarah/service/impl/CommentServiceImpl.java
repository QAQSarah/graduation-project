package com.sarah.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sarah.dao.ArticleMapper;
import com.sarah.dao.CommentMapper;
import com.sarah.dao.ResponseMapper;
import com.sarah.dao.UserMapper;
import com.sarah.model.Article;
import com.sarah.model.ArticleImg;
import com.sarah.model.Comment;
import com.sarah.model.CommentModel;
import com.sarah.model.Likes;
import com.sarah.model.ReponseModel;
import com.sarah.model.Response;
import com.sarah.service.CommentService;
import com.sarah.service.ReponseService;
import com.sarah.util.PageBean;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ReponseService reponseService;
	@Autowired
	private ArticleMapper articleMapper;

	public int deleteByPrimaryKey(Integer id) {
		Response response = new Response();
		response.setCommentid(id);
		List<Response> list2 = reponseService.selectResponseAll(response);
		for (Response reponse : list2) {
			reponseService.deleteByPrimaryKey(reponse.getId());
		}
		return commentMapper.deleteByPrimaryKey(id);
	}

	public int insert(Comment record) {
		int r = 0;
		if (record != null) {
			r = commentMapper.insert(record);
		}
		return r;
	}

	public int insertSelective(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Comment selectByPrimaryKey(Integer id) {
		return commentMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKeyWithBLOBs(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateByPrimaryKey(Comment record) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<CommentModel> seleteByComment(Comment record) {
		List<CommentModel> list = new ArrayList<CommentModel>();
		if (!("".equals(record)) && record != null) {
			List<Comment> list1 = commentMapper.seleteByComment(record);
			for (Comment comment : list1) {
				CommentModel commentModel = new CommentModel();
				commentModel.setId(comment.getId());
				String name = userMapper.selectByPrimaryKey(comment.getFromUid()).getName();
				commentModel.setFuser(name);
				commentModel.setAritcleId(commentModel.getAritcleId());
				commentModel.setComtext(comment.getContent());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				commentModel.setTime(sdf.format(comment.getCreatetime()));
				Response response = new Response();
				response.setCommentid(comment.getId());
				List<ReponseModel> list2 = reponseService.selectAll(response);
				commentModel.setReponses(list2);
				list.add(commentModel);
			}
		}
		return list;
	}

	public List<Article> selectByUser(int userid) {
		Comment comment = new Comment();
		List<Comment> comments = new ArrayList<Comment>();
		List<Article> articles = new ArrayList<Article>();
		if (userid != 0) {
			comment.setFromUid(userid);
			comments = commentMapper.seleteByComment(comment);
		}
		List<Integer> aList = new ArrayList<Integer>();
		if (comments.size() != 0) {
			for (Comment comment2 : comments) {
				aList.add(comment2.getArticleid());
			}
			List<Integer> listWithoutDup = new ArrayList<Integer>(new HashSet<Integer>(aList));

			for (Integer integer : listWithoutDup) {
				articles.add(articleMapper.selectByPrimaryKey(integer));
			}
			
			return articles;
		} else {
			return null;
		}

	}

	public PageBean<Comment> selectList(int pageNum, int rows) {
		Comment comment=new Comment();
		List<Comment> all = commentMapper.seleteByComment(comment);
		int totalRecord = 0;
		if(all!=null) {
			totalRecord=all.size();
		}
		PageBean<Comment> pb = new PageBean<Comment>(pageNum, rows, totalRecord);
		int startIndex = pb.getStartIndex();
		 List<Comment> list = commentMapper.selectList(startIndex , rows);
		if(list==null) {
			return null;
		}else {
			pb.setList(list);
			return pb;
		}
	}

}
