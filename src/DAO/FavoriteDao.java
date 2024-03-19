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

public class FavoriteDao {
	private static FavoriteDao favoriteDao;
	static public FavoriteDao getInstance() {
		if (favoriteDao == null)
			favoriteDao = new FavoriteDao();
		return favoriteDao;
	}
	
	public int getFavoriteNumByPost(int postId) {
		try ( Connection c = DriverManager.getConnection(DBInit.getUrl(), DBInit.getUser(), DBInit.getPw()) ){
			String sql = "select * from favorite where post_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, postId);
			ResultSet rs = ps.executeQuery(sql);
			return rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
