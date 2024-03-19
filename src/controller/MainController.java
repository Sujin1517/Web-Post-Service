package controller;

import java.util.Scanner;

import Global.GlobalData;

public class MainController {
	LogInController logInController;
	private Scanner sc;
	private static MainController mainController;
	public static MainController getInstance(Scanner sc) {
		if (mainController == null)
			mainController = new MainController(sc);
		return mainController;
	}

	public MainController(Scanner sc) {
		logInController = LogInController.getInstance(sc);
		this.sc = sc;
	}
	
	public void startMain() {
		System.out.println("\n웹 커뮤니티 시스템 시작");
		while(true) {
			if ("".equals(GlobalData.account)) {
				if(logInController.logInMenu()) break;
			} else {
				selectMenu();
			}
		}
	}
	
	
	private void selectMenu() {
		System.out.println("\n웹 커뮤니티에 오신것을 환영합니다.");
		System.out.print("\n1.게시글 보기\t 2.내 정보 보기\t 3.로그아웃하기\n:");
		String answer = sc.nextLine();
		if("1".equals(answer)) {
			System.out.println("1");
			
		} else if("2".equals(answer)) {
			System.out.println("2");
			
		} else if("3".equals(answer)) {
			GlobalData.account = "";
			System.out.println("\n로그아웃합니다.");
		} else {
			System.out.println("\n올바른 명령을 입력해주세요.");
		}
	}
}
