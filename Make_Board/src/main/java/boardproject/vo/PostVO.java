package boardproject.vo;

import java.util.Date;

public class PostVO {
	protected	int		postNo; // PK
	protected	String	postSubject; // 제목
	protected	String	postText; // 내용
	protected	String	postPassword; // 글 비밀번호
	protected	String 	posterName; // 작성자 이름
	protected	Date	postCreatedDate; // 작성날짜
	protected	Date	postModifyDate;
	
	// getter, setter
	public int getPostNo() {
		return postNo;
	}
	public PostVO setPostNo(int postNo) {
		this.postNo = postNo;
		return this;
	}
	
	public String getPostSubject() {
		return postSubject;
	}
	public PostVO setPostSubject(String postSubject) {
		this.postSubject = postSubject;
		return this;
	}
	
	public String getPostText() {
		return postText;
	}
	public PostVO setPostText(String postText) {
		this.postText = postText;
		return this;
	}
	
	public String getPostPassword() {
		return postPassword;
	}
	public PostVO setPostPassword(String postPassword) {
		this.postPassword = postPassword;
		return this;
	}
	
	public String getPosterName() {
		return posterName;
	}
	public PostVO setPosterName(String posterName) {
		this.posterName = posterName;
		return this;
	}
	
	public Date getPostCreatedDate() {
		return postCreatedDate;
	}
	public PostVO setPostCreatedDate(Date postCreatedDate) {
		this.postCreatedDate = postCreatedDate;
		return this;
	}
	
	public Date getPostModifyDate() {
		return postModifyDate;
	}
	public PostVO setPostModifyDate(Date postModifyDate) {
		this.postModifyDate = postModifyDate;
		return this;
	}
}
