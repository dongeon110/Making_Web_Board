package boardproject.vo;

import java.util.Date;

public class User {
	protected	int		userNo;
	protected	String 	userID;
	protected	String 	userPassword;
	protected	String	userName;
	protected	Date	createdDate;
	protected	Date	modifyDate;
	protected	int		grade;
	
	public int getUserNo() {
		return userNo;
	}
	public User setUserNo(int userNo) {
		this.userNo = userNo;
		return this;
	}
	
	public String getUserID() {
		return userID;
	}
	public User setUserID(String userID) {
		this.userID = userID;
		return this;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	public User setUserPassword(String userPassword) {
		this.userPassword = userPassword;
		return this;
	}
	
	public String getUserName() {
		return userName;
	}
	public User setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}
	public User setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
		return this;
	}
	
	public Date getModifyDate() {
		return modifyDate;
	}
	public User setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
		return this;
	}
	
	public int getGrade() {
		return grade;
	}
	public User setGrade(int grade) {
		this.grade = grade;
		return this;
	}
	
}
