package main;

import java.util.Scanner;

import controller.MainController;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		MainController mainController = MainController.getInstance(sc);
		mainController.startMain();
	}

}
