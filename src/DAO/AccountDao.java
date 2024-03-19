package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Global.DBInit;

public class AccountDao{
	private static AccountDao accountDao;
	static public AccountDao getInstance() {
		if (accountDao == null)
			accountDao = new AccountDao();
		return accountDao;
	}
	
	public void addAccount(String accountName, String password) {
		try ( Connection c = DriverManager.getConnection(DBInit.getUrl(), DBInit.getUser(), DBInit.getPw()) ){
			String sql = "insert into account values (?, ?)";
			c.setAutoCommit(false);
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, accountName);
			ps.setString(2, password);
			int rs = ps.executeUpdate();
			if (rs == 1)
				c.commit();
			else {
				c.rollback();
				System.out.println("[Error]: account insert fail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delAccount(String accountName) {
		try ( Connection c = DriverManager.getConnection(DBInit.getUrl(), DBInit.getUser(), DBInit.getPw()) ){
			String sql = "delete from account where id = ?";
			c.setAutoCommit(false);
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, accountName);
			int rs = ps.executeUpdate();
			if (rs == 1)
				c.commit();
			else {
				c.rollback();
				System.out.println("[Error]: account delete fail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean checkAccount(String accountName) {
		try ( Connection c = DriverManager.getConnection(DBInit.getUrl(), DBInit.getUser(), DBInit.getPw()) ){
			String sql = "select * from account where id = '?'";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, accountName);
			ResultSet rs = ps.executeQuery(sql);
			if (rs.getRow() == 1)
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean checkAccount(String accountName, String password) {
		try ( Connection c = DriverManager.getConnection(DBInit.getUrl(), DBInit.getUser(), DBInit.getPw()) ){
			String sql = "select * from account where id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery(sql);
			if (rs.getRow() == 1) {
				rs.next();
				if(rs.getString("pw").equals(password))
					return true;
			}
		} catch (SQLException e) {
			System.out.println("[Error]: connection fail");
			e.printStackTrace();
		}
		return false;
	}
}
