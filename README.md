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
- 게시판
1. 게시물 리스트 보기  
  1.1. /board/list.do  
  1.2. 게시물 10개 마다 잘라서 분할하여 나타내는 페이지 기능  
  1.3. 게시물 제목, 내용, 작성자에 대해 **검색 기능**  
  1.4. 각 게시물 제목을 클릭하여 게시물 내용을 볼 수 있음(/board/view.do)  
2. 게시물 쓰기  
  2.1. '신규 글 작성' 버튼을 통해 신규 글 등록 가능  
  2.2. 제목, 내용, 작성자, 비밀번호를 작성하고 신규 글 작성 가능  
  2.3. 빈 칸이 있으면 등록되지 않음  
3. 게시물 보기  
  3.1. 게시물 내용과 상세 정보를 볼 수 있음  
  3.2. 게시물 수정, 삭제, 답글달기 기능 사용 가능  
  3.3. 쿠키 사용하여 조회수 갱신, 쿠키 MaxAge는 60*60(1시간)  
4. 게시물 수정  
  4.1. 비밀번호 입력시 수정 가능  
  4.2. 기존 내용이 입력되어 있고 수정 후 저장 가능  
5. 게시물 삭제  
  5.1. 비밀번호 입력시 삭제 가능  
6. 답글 달기  
  6.1. 게시물 보기에서 답글달기 버튼을 눌러, 답글 기능 사용 가능  
  6.2. 답글을 달면 게시물 번호와는 상관없이 해당 글 바로 위에 등록이 되어 어떤 글의 답글인지 알 수 있음  
  6.3. 답글은 여러개 달 수 있지만, 답글에 답글을 달아도 원래 글에 답글이 달림  

7. 로그인 기능 추가  
  7.1. 등록된 아이디와 비밀번호로 로그인 가능  
  7.2. ID에 일반 / 관리자 사용권한이 부여됨  
  7.3. 로그인 사용자는 게시물 작성시 작성자, 비밀번호를 쓰지 않아도 게시물 등록 및 수정 가능  
  7.4. 로그인 사용자가 등록한 게시물은 사용자 ID 또는 관리자만 수정 및 삭제 가능  
  7.5. 관리자는 모든 글에 대해 수정 및 삭제 가능  
  7.6. 관리자는 회원 관리 페이지에서 다른 사용자에게 일반/관리자 권한 부여 가능  

## 동작 과정
1. 서버 실행  
2. ServletContextListener를 구현한 ContextLoaderListener 에서 contextInitialized() 메서드 구현  
2.1. ApplicationContext 객체 생성  
2.2. ApplicationContext 에서 Reflection API와 ServletRequestDataBinder를 사용하여 사용할 객체를 미리 준비  
2.3. 각 객체에서 setter메서드를 찾아 의존성 주입  
3. 서버에 요청이 들어옴.
4. FrontController역할을 수행하는 DispatcherServlet이 요청을 받음  
5. HashMap 객체를 하나 만들어 데이터를 요청에 따라 데이터를 가져감  
6. 각 pageController들 (Controller구현한 것, controls패키지 안)들이 반환하는 값들로 주소 맵핑하여 요청 처리

## 역할
- Model
    - Dao
        - mybatis 사용하여 postgre DB에서 사용할 쿼리를 .xml파일에 저장해놓고 사용
- View
    - src/main/webapp 내의 JSP파일들
- Controller
    - FrontController 역할 : DispatcherServlet
    - PageController 역할 : boardproject/controls 내의 controller 구현한 객체들

### 스프링과 비교  
스프링 빈의 LifeCycle의 경우  
스프링 IoC 컨테이너 생성 -> 스프링 빈 생성 -> 의존관계 주입 -> 초기화 콜백 메소드 호출 -> 사용 -> 소멸 전 콜백 메소드 호출  

스프링에서 사용하는 방식과 이름을 최대한 일치 시켜서 사용했음.
- ContextLoaderListener.java 에서 contextInitialized() 메서드 호출시 ApplicationContext (IoC Container역할) 생성  
- ApplicationContext 에서 Hashtable 형태로 빈 생성  
- ApplicationContext 에서 정의한 메소드로 의존관계 주입  
  - prepareObjectByAnnotation() : Reflection API로 Annotation이 있는 클래스들을 찾아 빈을 생성해줌    
  - prepareObjectByProperties() : properties 파일에 정의된 클래스들을 찾아 빈을 해줌. (서버에서 제공하는 객체는 예외처리. 이것도 properties 파일에서 선언)  
  - injectDependecy() : 준비된 빈들의 의존 관계를 주입해줌.
    - callSetter() : 의존성 주입을 하는 메서드들을 set~ 으로 정의하였기 때문에 set으로 시작하는 메서드들을 찾아 호출
    - findObjectByType() : 의존성 주입을 위해 받은 파라미터의 객체의 타입을 찾아서 알맞게 반환

- ContextLoaderListener가 ApplicationContext를 생성하고 
  ApplicationContext 내의 메서드들을 호출하여 사용하면서 일어남
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
　│　 　│　└ ApplicationContext.java   (IoC Container 역할)
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


## 필요한 개선점  
1. (관리자) 삭제 글 복구 기능  
2. 글번호 개선  
3. Date -> Datetime  
4. 답글 depth, 제한  
5. 원글 삭제시 답글 조치  
6. 비밀번호 암호화  
