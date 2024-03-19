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
		System.out.println("\n로그인메뉴");
		System.out.print("\n1.로그인하기\t 2.회원가입하기\t 3.종료하기\n:");
		String answer = sc.nextLine();
		
		if ("1".equals(answer)) {
			String accountName;
			String password;
			System.out.print("\n계정명 입력\n:");
			accountName = sc.nextLine();
			System.out.print("\n비밀번호 입력\n:");
			password = sc.nextLine();
			
			if (accountService.logInAccount(accountName, password)) {
				System.out.println("\n로그인에 성공했습니다.");
			} else {
				System.out.println("\n계정명 또는 비밀번호가 올바르지 않습니다.");
			}
			
		} else if ("2".equals(answer)) {
			createAccountMenu();
			
		} else if ("3".equals(answer)) {
			System.out.println("\n사용해주셔서 감사합니다.\n프로그램을 종료합니다.");
			return true;
			
		} else {
			System.out.println("\n올바른 명령을 입력해주세요.");
		}
		return false;
	}

	private void createAccountMenu() {
		String accountName;
		String password;
		System.out.println("\n신규 계정을 생성합니다.");
		System.out.print("\n계정명 입력\n:");
		accountName = sc.nextLine();
		if ("".equals(accountName)) {
			System.out.println("\n계정명은 공백일 수 없습니다.");
			return;
			
		} else {
			if (accountService.doubleCheckAccountName(accountName)) {
				System.out.println("\n이미 사용중인 계정명입니다.\n로그인 메뉴로 돌아갑니다.");
				return;
			}
		}
		
		System.out.print("\n비밀번호 입력\n:");
		password = sc.nextLine();
		if ("".equals(password)) {
			System.out.println("\n비밀번호는 공백일 수 없습니다.\n로그인 메뉴로 돌아갑니다.");
			return;
			
		} else {
			if (accountService.checkPasswordRule(password)) {
				System.out.println("\n비밀번호는 4자리 이상으로 설정해야 합니다.\n로그인 메뉴로 돌아갑니다.");
				return;
			}
		}
		
		System.out.print("\n비밀번호 확인\n:");
		if (!password.equals(sc.nextLine())) {
			System.out.println("\n비밀번호가 일치하지 않습니다.\n로그인 메뉴로 돌아갑니다.");
			return;
		}
		
		StringBuilder star = new StringBuilder();
		
		for (int i = 0; i < password.length(); i++) {
			star.append("*");
		}

		System.out.print("\n입력하신 정보가 다음과 같습니까?(y/n)" + 
				"\n계정명: " + accountName + 
				"\n비밀번호: " + star + 
				"\n:");
		
		if("y".equals(sc.nextLine())) {
			System.out.println("\n계정이 생성되었습니다.");
		} else {
			System.out.println("\n계정 생성을 취소합니다.");
		}
		System.out.println("로그인 메뉴로 돌아갑니다.");
	}
}
