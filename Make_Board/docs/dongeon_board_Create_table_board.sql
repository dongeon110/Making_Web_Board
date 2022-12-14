-- CREATE TABLE
CREATE TABLE IF NOT EXISTS board (
	pno	SERIAL UNIQUE NOT NULL PRIMARY KEY,
	psubject	VARCHAR(100)	NOT NULL,
	ppwd	VARCHAR(100)	NOT NULL,
	ptext	TEXT	NOT NULL,
	postername VARCHAR(100)	NOT NULL,
	cre_date	TIMESTAMP	NOT NULL,
	mod_date	TIMESTAMP	NOT NULL,
	repost	INTEGER NOT NULL DEFAULT currval('board_pno_seq')
	);

-- COMMENT
COMMENT ON TABLE board IS '게시글 정보';
COMMENT ON COLUMN board.pno IS '게시글 번호';
COMMENT ON COLUMN board.psubject IS '게시글 제목';
COMMENT ON COLUMN board.ppwd IS '게시글 비밀번호';
COMMENT ON COLUMN board.ptext IS '게시글 내용';
COMMENT ON COLUMN board.postername IS '작성자 이름';
COMMENT ON COLUMN board.cre_date IS '작성일';
COMMENT ON COLUMN board.mod_date IS '수정일';
COMMENT ON COLUMN board.repost IS '답글 게시글 번호';

-- INSERT DATA
INSERT INTO board (psubject, ppwd, ptext, postername, CRE_DATE, MOD_DATE) VALUES
	('제목테스트1', '1234', '내용테스트1', '작성자1', NOW(), NOW());
INSERT INTO BOARD (psubject, ppwd, ptext, postername, CRE_DATE, MOD_DATE) VALUES
	('제목테스트2', '1234', '내용테스트2', '작성자2', NOW(), NOW());
INSERT INTO BOARD (psubject, ppwd, ptext, postername, CRE_DATE, MOD_DATE, repost) VALUES
	('  [RE]제목테스트3', '1234', '내용테스트3', '작성자3', NOW(), NOW(), 1);
INSERT INTO BOARD (psubject, ppwd, ptext, postername, CRE_DATE, MOD_DATE) VALUES
	('디폴트 테스트', '1234', '내용테스트3', '작성자3', NOW(), NOW());

-- ADD COLUMN (pviews 조회수)
ALTER TABLE board ADD COLUMN pviews integer DEFAULT 0;
COMMENT ON COLUMN board.pviews IS '게시글 조회수';

-- ADD COLUMN (작성자 user 여부) > users.userno 에 foreign key 추가
ALTER TABLE board ADD COLUMN byuser INTEGER REFERENCES users (userno);
COMMENT ON COLUMN board.byuser IS '작성user 고유no값';

-- 비밀번호 및 작성자 DROP NOT NULL (작성user 있으면 필요 없기 때문)
ALTER TABLE board ALTER COLUMN ppwd DROP NOT NULL;
ALTER TABLE board ALTER COLUMN postername DROP NOT NULL;

-- forien key DELETE CASCADE로 재 설정
ALTER TABLE board
DROP CONSTRAINT "board_byuser_fkey",
ADD CONSTRAINT "board_byuser_fkey" FOREIGN KEY ("byuser") REFERENCES users("userno") ON DELETE CASCADE;