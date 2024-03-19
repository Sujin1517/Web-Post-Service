package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import Global.DBInit;
import dataObject.Comment;

public class CommentDao {
	private static CommentDao commentDao;

	static public CommentDao getInstance() {
		if (commentDao == null)
			commentDao = new CommentDao();
		return commentDao;
	}

	public Comment getCommentById(int commentId) {
		try (Connection c = DriverManager.getConnection(DBInit.getUrl(), DBInit.getUser(), DBInit.getPw())) {
			String sql = "select * from comment where id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, commentId);
			ResultSet rs = ps.executeQuery(sql);
			if (rs.getRow() == 1) {
				rs.next();
				String accountName = rs.getString("writer_id");
				int postId = rs.getInt("post_id");
				String content = rs.getString("content");
				LocalDateTime createTime = LocalDateTime.parse(rs.getString("create_time"));
				Comment comment = new Comment(accountName, postId, content, createTime);
				comment.setEditTime(LocalDateTime.parse(rs.getString("edit_time")));
				comment.setCommentId(rs.getInt("next_comment_id"));
				return comment;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setComment(Comment comment) {
		try (Connection c = DriverManager.getConnection(DBInit.getUrl(), DBInit.getUser(), DBInit.getPw())) {
			String sql = "insert into comment values (null, ?, ?, ?, null, ?, ?)";
			c.setAutoCommit(false);
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, comment.getWriterId());
			ps.setInt(2, comment.getPostId());
			ps.setString(3, LocalDateTime.now().toString());
			ps.setString(4, comment.getContent());
			ps.setInt(5, comment.getCommentId() == 0 ? null : comment.getCommentId());
			int rs = ps.executeUpdate();
			if (rs == 1)
				c.commit();
			else {
				c.rollback();
				System.out.println("[Error]: comment insert fail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delComment(int commnetId) {
		try (Connection c = DriverManager.getConnection(DBInit.getUrl(), DBInit.getUser(), DBInit.getPw())) {
			String sql = "delete from comment where id = ?";
			c.setAutoCommit(false);
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, commnetId);
			int rs = ps.executeUpdate();
			if (rs == 1)
				c.commit();
			else {
				c.rollback();
				System.out.println("[Error]: comment delete fail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Comment> getAllCommentByAccount(String accountName) {
		try (Connection c = DriverManager.getConnection(DBInit.getUrl(), DBInit.getUser(), DBInit.getPw())) {
			List<Comment> comments = new ArrayList<Comment>();
			String sql = "select * from favorite where account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, accountName);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				int postId = rs.getInt("post_id");
				String content = rs.getString("content");
				LocalDateTime createTime = LocalDateTime.parse(rs.getString("create_time"));
				Comment comment = new Comment(accountName, postId, content, createTime);
				comment.setEditTime(LocalDateTime.parse(rs.getString("edit_time")));
				comments.add(comment);
			}
			return comments;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Comment> getAllCommentByPost(int postId) {
		try (Connection c = DriverManager.getConnection(DBInit.getUrl(), DBInit.getUser(), DBInit.getPw())) {
			List<Comment> comments = new ArrayList<Comment>();
			String sql = "select * from favorite where post_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, postId);
			ResultSet rs = ps.executeQuery(sql);
			while (rs.next()) {
				String accountName = rs.getString("writer_id");
				String content = rs.getString("content");
				LocalDateTime createTime = LocalDateTime.parse(rs.getString("create_time"));
				Comment comment = new Comment(accountName, postId, content, createTime);
				comment.setEditTime(LocalDateTime.parse(rs.getString("edit_time")));
				comment.setCommentId(rs.getInt("next_comment_id"));
				comments.add(comment);
			}
			return comments;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
