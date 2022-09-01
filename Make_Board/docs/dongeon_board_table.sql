CREATE DATABASE IF NOT EXISTS dongeon_board;

-- CREATE TABLE
CREATE TABLE IF NOT EXISTS board (
	pno	INTEGER	NOT NULL	COMMENT '게시글 번호',
	psubject	VARCHAR(100)	NOT NULL COMMENT '게시글 제목',
	ppwd	VARCHAR(100)	NOT NULL COMMENT '게시글 비밀번호',
	ptext	TEXT	NOT NULL COMMENT '게시글 내용',
	postername VARCHAR(100)	NOT NULL	COMMENT '작성자 이름',
	cre_date	DATETIME	NOT NULL	COMMENT '작성일',
	mod_date DATETIME NOT NULL COMMENT '마지막 수정일') COMMENT '게시글 정보';

-- ADD PK
ALTER TABLE board ADD CONSTRAINT PK_board PRIMARY KEY (pno);


-- pno Serial
ALTER TABLE board
	MODIFY COLUMN pno INTEGER NULL NULL AUTO_INCREMENT
	COMMENT '게시글 번호';

-- INSERT DATA
INSERT INTO board (psubject, ppwd, ptext, postername, CRE_DATE, MOD_DATE) VALUES
	('제목테스트1', '1234', '내용테스트1', '작성자1', NOW(), NOW());
INSERT INTO BOARD (psubject, ppwd, ptext, postername, CRE_DATE, MOD_DATE) VALUES
	('제목테스트2', '1234', '내용테스트2', '작성자2', NOW(), NOW());