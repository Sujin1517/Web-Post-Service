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
	 * �α����մϴ�.
	 * @param accountName - ������ ���ڿ�
	 * @param password - ��й�ȣ ���ڿ�
	 * @return �α��ο� �����ϸ� true��, �����ϸ� false�� ��ȯ�մϴ�.
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
	 * ������ �ߺ��� Ȯ���մϴ�.
	 * 
	 * @param accountName - ������ ���ڿ�
	 * @return �������� �̹� ������̶�� true��, �ƴϸ� false�� ��ȯ�մϴ�.
	 */
	public boolean doubleCheckAccountName(String accountName) {
		return accountDao.checkAccount(accountName);
	}

	/**
	 * ��й�ȣ ��Ģ�� �˻��մϴ�.
	 * 
	 * @param password - ��й�ȣ ���ڿ�
	 * @return ��й�ȣ�� ��Ģ�� ���� ������ true��, ������ false�� ��ȯ�մϴ�.
	 */
	public boolean checkPasswordRule(String password) {
		if (password.length() < 4)
			return true;
		return false;
	}
}
