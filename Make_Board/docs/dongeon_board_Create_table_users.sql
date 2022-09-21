CREATE TABLE IF NOT EXISTS users (
	userno			serial			unique NOT NULL primary key,
	userid			varchar(100)	unique NOT NULL,
	userpassword	varchar(100)	NOT NULL,
	username		varchar(100)	NOT NULL,
	createddate		TIMESTAMP		NOT NULL,
	modifydate		TIMESTAMP		NOT NULL,
	grade			INTEGER			NOT NULL default 2
	);

COMMENT ON TABLE users IS '사용자 정보';
COMMENT ON COLUMN users.userno IS '사용자 고유 번호';
COMMENT ON COLUMN users.userid IS '사용자 ID';
COMMENT ON COLUMN users.userpassword IS '사용자 비밀번호';
COMMENT ON COLUMN users.username IS '사용자 이름';
COMMENT ON COLUMN users.createddate IS '생성일';
COMMENT ON COLUMN users.modifydate IS '수정일';
COMMENT ON COLUMN users.grade IS '사용자등급 1:관리자 2:일반';


INSERT INTO users (userid, userpassword, username, createddate, modifydate, grade) VALUES
	('testid', '1234', 'testname', NOW(), NOW(), 1);
INSERT INTO users (userid, userpassword, username, createddate, modifydate) VALUES
	('testid2', '1234', 'testname2', NOW(), NOW());
















