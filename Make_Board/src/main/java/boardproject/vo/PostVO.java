package boardproject.vo;

import java.util.Date;

public class PostVO {
	protected	int		rowNum;
	protected	int		postNo; // PK
	protected	String	postSubject; // 제목
	protected	String	postText; // 내용
	protected	String	postPassword; // 글 비밀번호
	protected	String 	posterName; // 작성자 이름
	protected	Date	postCreatedDate; // 작성날짜
	protected	Date	postModifyDate; // 수정날짜
	protected	int		repost; // 답글 그룹 번호
	protected	int		postViews; // 조회수
	
	// getter, setter
	public int getRowNum() {
		return rowNum;
	}
	public PostVO setRowNum(int rowNum) {
		this.rowNum = rowNum;
		return this;
	}
	
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
	
	public int getRepost() {
		return repost;
	}
	public PostVO setRepost(int repost) {
		this.repost = repost;
		return this;
	}
	
	public int getPostViews() {
		return postViews;
	}
	public PostVO setPostViews(int postViews) {
		this.postViews = postViews;
		return this;
	}
}
