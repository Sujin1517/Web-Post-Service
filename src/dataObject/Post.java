package dataObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
	private final int postId;
	private final LocalDateTime createTime;
	private LocalDateTime editTime;
	private final String writerId;
	private String title;
	private String content;
	private List<Comment> comment = new ArrayList<>();
	private int commentNum;

	public Post(int postId, String writer, String title, String content) {
		this.postId = postId;
		this.createTime = LocalDateTime.now();
		this.editTime = null;
		this.writerId = writer;
		this.title = title;
		this.content = content;
		this.comment = null;
		this.commentNum = 0;
	}

	public LocalDateTime getEditTime() {
		return editTime;
	}

	public void setEditTime(LocalDateTime editTime) {
		this.editTime = editTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getPostId() {
		return postId;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public String getWriterId() {
		return writerId;
	}

}
