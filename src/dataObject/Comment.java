package dataObject;

import java.time.LocalDateTime;

public class Comment {
	private final String writerId;
	private final int postId;
	private final LocalDateTime createTime;
	private LocalDateTime editTime;
	private String content;
	private int commentId;

	public Comment(String writer, int postId, String content, LocalDateTime createTime) {
		this.writerId = writer;
		this.postId = postId;
		this.createTime = createTime;
		this.editTime = null;
		this.content = content;
		this.commentId = 0;
	}

	public LocalDateTime getEditTime() {
		return editTime;
	}

	public void setEditTime(LocalDateTime editTime) {
		this.editTime = editTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public String getWriterId() {
		return writerId;
	}

	public int getPostId() {
		return postId;
	}

}
