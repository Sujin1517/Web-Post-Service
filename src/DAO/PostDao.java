package DAO;

import java.util.List;

import dataObject.Post;

public class PostDao {
	private static PostDao postDao;
	static public PostDao getInstance() {
		if (postDao == null)
			postDao = new PostDao();
		return postDao;
	}

	public Post getPost(int id) {
		return null;
	}

	public void setPost(Post post) {

	}

	public void delPost(int id) {
		
	}
	
	public List<Post> getPostList(int limit) {
		return null;
	}

	public List<Integer> getPostListByWriter(int id) {
		return null;
	}
}
