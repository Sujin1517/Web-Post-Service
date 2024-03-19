package controller;

import java.util.Scanner;

public class PostController {
	Scanner sc;
	static PostController postController;
	static public PostController getInstance(Scanner sc) {
		if (postController == null)
			postController = new PostController(sc);
		return postController;
	}
	
	public PostController(Scanner sc) {
		this.sc = sc;
	}
	
	
	public void showPosts(int limit) {
		
	}
}
