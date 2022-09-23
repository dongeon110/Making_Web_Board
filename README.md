# Making_Web_Board
게시판을 만들어 봅시다  

서블릿으로 MVC모델을 사용한 게시판 만들기

## page 기능
```
　┬ /board/
　│　├ list.do   (게시판)
　│　├ view.do   (게시물 보기)
　│　├ update.do   (게시물 수정)
　│　├ delete.do   (게시물 삭제)
　│　├ repost.do   (답글 달기)
　│　└ add.do   (게시물 추가)
　│
　├ /auth/
　│　├ login.do   (로그인)
　│　└ logout.do   (로그아웃)
　│
　└ /user/
 　　├ list.do   (사용자 관리)
 　　├ add.do   (회원가입)
 　　├ delete.do    (사용자 삭제)
 　　└ update.do    (사용자 수정)
```

<!-- ## 동작 과정
1. 서버 실행  
2. ServletContextListener를 구현한 ContextLoaderListener 에서 contextInitialized() 메서드 구현  
2.1. ApplicationContext 객체 생성  
2.2. ApplicationContext 에서 Reflection API와 ServletRequestDataBinder를 사용하여 사용할 객체를 미리 준비  
2.3. 각 객체에서 setter메서드를 찾아 의존성 주입  
3. 서버에 요청이 들어옴.
4. FrontController역할을 수행하는 DispatcherServlet이 요청을 받음  
5. HashMap 객체를 하나 만들어 데이터를 요청에 따라 데이터를 가져감  
6. 각 pageController들 (Controller구현한 것, controls패키지 안)들이 반환하는 값들로 주소 맵핑하여 요청 처리
 -->
## 역할
- Model
    - Dao
        - mybatis 사용하여 postgre DB에서 사용할 쿼리를 .xml파일에 저장해놓고 사용
- View
    - src/main/webapp 내의 JSP파일들
- Controller
    - FrontController 역할 : DispatcherServlet
    - PageController 역할 : boardproject/controls 내의 controller 구현한 객체들

## src
```
　┬ src/main/java/
　│　└ boardproject/
　│　 　├ annotation/
　│　 　│　└ Component.java
　│　 　│
　│　 　├ bind/
　│　 　│　├ DataBinding.java   (interface)
　│　 　│　└ ServletRequestDataBinder.java
　│　 　│
　│　 　├ context/
　│　 　│　└ ApplicationContext.java
　│　 　│　 
　│　 　├ listeners/
　│　 　│　└ ContextLoaderListener.java
　│　 　│　 
　│　 　├ filters/
　│　 　│　└ CharacterEncodingFilter.java
　│　 　│　 
　│　 　├ servlets/   (front Controller)
　│　 　│　└ DispatcherServlet.java
　│　 　│　 
　│　 　├ vo/
　│　 　│　├ PostVO.java
　│　 　│　└ User.java
　│　 　│　 
　│　 　├ dao/
　│　 　│　├ PostDao.java
　│　 　│　├ PostDao.xml
　│　 　│　├ UserDao.java
　│　 　│　├ UserDao.xml
　│　 　│　├ db.properties
　│　 　│　└ mybatis-config.xml
　│　 　│
　│　 　└ controls/
　│　 　 　├ Controller.java   (interface)
　│　 　 　├ post/
　│　 　 　│　├ BoardListController.java
　│　 　 　│　├ PostAddController.java
　│　 　 　│　├ PostDeleteController.java
　│　 　 　│　├ PostUpdateController.java
　│　 　 　│　├ PostViewController.java
　│　 　 　│　└ RepostController.java
　│　 　 　│
　│　 　 　└ user/
　│　 　 　 　├ LoginController.java
　│　 　 　 　├ LogoutController.java
　│　 　 　 　├ UserAddController.java
　│　 　 　 　├ UserDeleteController.java
　│　 　 　 　├ UserListController.java
　│　 　 　 　└ UserUpdateController.java
　│
　└ src/main/webapp/
　 　├ Error.jsp
　 　├ Header.jsp
　 　├ Tail.jsp
　 　│
　 　├ auth/
　 　│　├ CheckDeletePw.jsp
　 　│　├ CheckNull.jsp
　 　│　├ CheckPostPw.jsp
　 　│　├ CheckPwFail.jsp
　 　│　├ LogInFail.jsp
　 　│　├ LogInForm.jsp
　 　│　└ NotWriter.jsp
　 　│
　 　├ board/
　 　│　├ postAdd.jsp
　 　│　├ postList.jsp
　 　│　├ postRepost.jsp
　 　│　├ postUpdate.jsp
　 　│　└ postView.jsp
　 　│
　 　├ user/
　 　│　├ userForm.jsp
　 　│　├ userList.jsp
　 　│　└ userUpdateForm.jsp
　 　│
　 　├ resources/   (bootstarp, jquery 설치)
　 　│　├ css/
　 　│　└ js/
　 　│
　 　├ WEB-INF/
　 　│　├ lib/    (JDBC를 포함한 각종 라이브러리 설치)
　 　│　├ application-context.properties
　 　│　└ web.xml
　 　│
　 　└ META-INF/
　 　 　└ MANIFEST.MF
```

Jdk 11, Tomcat 9.0, mybatis, postgreSQL + JDBC, bootstrap, reflection API  

DB 만들 때 사용한 쿼리문은  
Make_board/docs/ 에 정리해둠 

~ Create_table board, users 는 postgreSQL이고  
그냥 table은 변경 전 mariaDB로 했던 SQL

## 참고자료  
열혈강의 자바 웹 개발 워크북 - 엄진영 저 [(깃허브)](https://github.com/eomjinyoung/JavaWebProgramming)