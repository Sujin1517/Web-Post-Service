package controller;

import java.util.Scanner;

import service.AccountService;

public class LogInController {
	AccountService accountService;
	Scanner sc;
	static LogInController logInController;

	static public LogInController getInstance(Scanner sc) {
		if (logInController == null)
			logInController = new LogInController(sc);
		return logInController;
	}

	public LogInController(Scanner sc) {
		accountService = AccountService.getInstance();
		this.sc = sc;
	}

	public boolean logInMenu() {
		System.out.println("\n�α��θ޴�");
		System.out.print("\n1.�α����ϱ�\t 2.ȸ�������ϱ�\t 3.�����ϱ�\n:");
		String answer = sc.nextLine();
		
		if ("1".equals(answer)) {
			String accountName;
			String password;
			System.out.print("\n������ �Է�\n:");
			accountName = sc.nextLine();
			System.out.print("\n��й�ȣ �Է�\n:");
			password = sc.nextLine();
			
			if (accountService.logInAccount(accountName, password)) {
				System.out.println("\n�α��ο� �����߽��ϴ�.");
			} else {
				System.out.println("\n������ �Ǵ� ��й�ȣ�� �ùٸ��� �ʽ��ϴ�.");
			}
			
		} else if ("2".equals(answer)) {
			createAccountMenu();
			
		} else if ("3".equals(answer)) {
			System.out.println("\n������ּż� �����մϴ�.\n���α׷��� �����մϴ�.");
			return true;
			
		} else {
			System.out.println("\n�ùٸ� ����� �Է����ּ���.");
		}
		return false;
	}

	private void createAccountMenu() {
		String accountName;
		String password;
		System.out.println("\n�ű� ������ �����մϴ�.");
		System.out.print("\n������ �Է�\n:");
		accountName = sc.nextLine();
		if ("".equals(accountName)) {
			System.out.println("\n�������� ������ �� �����ϴ�.");
			return;
			
		} else {
			if (accountService.doubleCheckAccountName(accountName)) {
				System.out.println("\n�̹� ������� �������Դϴ�.\n�α��� �޴��� ���ư��ϴ�.");
				return;
			}
		}
		
		System.out.print("\n��й�ȣ �Է�\n:");
		password = sc.nextLine();
		if ("".equals(password)) {
			System.out.println("\n��й�ȣ�� ������ �� �����ϴ�.\n�α��� �޴��� ���ư��ϴ�.");
			return;
			
		} else {
			if (accountService.checkPasswordRule(password)) {
				System.out.println("\n��й�ȣ�� 4�ڸ� �̻����� �����ؾ� �մϴ�.\n�α��� �޴��� ���ư��ϴ�.");
				return;
			}
		}
		
		System.out.print("\n��й�ȣ Ȯ��\n:");
		if (!password.equals(sc.nextLine())) {
			System.out.println("\n��й�ȣ�� ��ġ���� �ʽ��ϴ�.\n�α��� �޴��� ���ư��ϴ�.");
			return;
		}
		
		StringBuilder star = new StringBuilder();
		
		for (int i = 0; i < password.length(); i++) {
			star.append("*");
		}

		System.out.print("\n�Է��Ͻ� ������ ������ �����ϱ�?(y/n)" + 
				"\n������: " + accountName + 
				"\n��й�ȣ: " + star + 
				"\n:");
		
		if("y".equals(sc.nextLine())) {
			System.out.println("\n������ �����Ǿ����ϴ�.");
		} else {
			System.out.println("\n���� ������ ����մϴ�.");
		}
		System.out.println("�α��� �޴��� ���ư��ϴ�.");
	}
}
