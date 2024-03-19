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
		System.out.println("\n�� Ŀ�´�Ƽ �ý��� ����");
		while(true) {
			if ("".equals(GlobalData.account)) {
				if(logInController.logInMenu()) break;
			} else {
				selectMenu();
			}
		}
	}
	
	
	private void selectMenu() {
		System.out.println("\n�� Ŀ�´�Ƽ�� ���Ű��� ȯ���մϴ�.");
		System.out.print("\n1.�Խñ� ����\t 2.�� ���� ����\t 3.�α׾ƿ��ϱ�\n:");
		String answer = sc.nextLine();
		if("1".equals(answer)) {
			System.out.println("1");
			
		} else if("2".equals(answer)) {
			System.out.println("2");
			
		} else if("3".equals(answer)) {
			GlobalData.account = "";
			System.out.println("\n�α׾ƿ��մϴ�.");
		} else {
			System.out.println("\n�ùٸ� ����� �Է����ּ���.");
		}
	}
}
