package service;

import DAO.AccountDao;
import Global.GlobalData;

public class AccountService {
	private static AccountDao accountDao;
	private static AccountService accountService;
	public static AccountService getInstance() {
		if (accountService == null)
			accountService = new AccountService();
		return accountService;
	}
	
	public AccountService() {
		accountDao = AccountDao.getInstance();
	}

	/**
	 * 로그인합니다.
	 * @param accountName - 계정명 문자열
	 * @param password - 비밀번호 문자열
	 * @return 로그인에 성공하면 true를, 실패하면 false를 반환합니다.
	 */
	public boolean logInAccount(String accountName, String password) {
		if (accountDao.checkAccount(accountName, password)) {
			GlobalData.account = accountName;
			return true;
		}
		return false;
	}

	public void createAccount(String accountName, String password) {
		accountDao.addAccount(accountName, password);
	}

	public void deleteAccount(String accountName) {
		accountDao.delAccount(accountName);
	}
	
	/**
	 * 계정명 중복을 확인합니다.
	 * 
	 * @param accountName - 계정명 문자열
	 * @return 계정명이 이미 사용중이라면 true를, 아니면 false를 반환합니다.
	 */
	public boolean doubleCheckAccountName(String accountName) {
		return accountDao.checkAccount(accountName);
	}

	/**
	 * 비밀번호 규칙을 검사합니다.
	 * 
	 * @param password - 비밀번호 문자열
	 * @return 비밀번호가 규칙에 맞지 않으면 true를, 맞으면 false를 반환합니다.
	 */
	public boolean checkPasswordRule(String password) {
		if (password.length() < 4)
			return true;
		return false;
	}
}
