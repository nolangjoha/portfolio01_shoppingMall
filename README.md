# 의류 쇼핑몰 Smile Mall :D
#### Spring Boot를 사용하여 쇼핑몰의 기본기능 구현에 집중한 프로젝트입니다.

<br/>

## 개발자  
- 안선영 

<br/>

## 개발기간
- 24.06.16 ~ 24.10.16(유지보수예정)

<br/>

## 개발환경
- Front-end : HTML5, CSS3, JavaScript, jQuery, BootStrap 4.6, Thymeleaf
- Back-end : SpringBoot (JDK 17), Mybatis
- Datavase : Oracle
- 버전 및 이슈관리 : Github

<br/>

## 주요기능
### [사용자 페이지]
### 1. 메인화면

<details>
  <summary>메인화면보기</summary>
<p>
  <img src="https://github.com/user-attachments/assets/82dcfe79-6ee3-470d-8289-c48433373935">
</p>
</details>

- 슬라이드 배너, 신상품, 베스트셀러 품목이 있습니다. ‘상품보기’클릭시 각각 상품 상세 페이지로 이동합니다.
- 로그인을 하지 않은 상태로 ‘주문내역’, ‘마이페이지’, ‘장바구니’에 접근할 경우 로그인 폼으로 이동합니다.
- 로그인을 한 경우 ‘로그인’, ‘회원가입’버튼 대신 ‘로그아웃’버튼이 표시됩니다. 
- 각 카테고리 클릭 시 2차 카테고리가 출력 됩니다.
  
<br/>

### 2. 회원가입
<details>
  <summary>회원가입 화면보기</summary>
<p>
  <img src="https://github.com/user-attachments/assets/55687d30-47a3-484b-a431-32fe365d8226">
</p>
</details>

- 아이디, 닉네임 유효성검사를 통해 중복된 아이디와 닉네임이 있을 경우 경고창이 출력됩니다.
- 이메일 인증을 진행합니다. 인증요청시 작성된 이메일로 인증코드가 발송되며, 인증코드 확인 후 일치하지 않을 경우 경고창이 출력됩니다.
- 카카오지도 api를 사용하여 우편번호 검색 및 상세주소 검색기능을 추가했습니다.
- 회원가입 완료 후 메인 홈페이지로 이동합니다.

<br/>

### 3. 로그인 및 아이디, 비밀번호 찾기
<details>
  <summary>로그인 화면보기</summary>
<p>
  <img src="https://github.com/user-attachments/assets/c8347e5f-6a9b-4088-a218-a9453929bbbd">
</p>
</details>


  - 로그인 시도시 유효성 검사를 통과하지 못 할 경우 경고창이 출력됩니다.
  - 로그인 성공 시 메인홈페이지로 이동합니다. 
  - 아이디, 비밀번호 찾기를 할 경우 이메일 인증요청 후 메일로 발급받은 인증번호와 입력한 인증번호가 일치하지 않으면 경고창이 출력됩니다.
  - 인증 완료 후 이메일로 찾은 아이디가 발송됩니다.
  - 인증 완료 후 이메일로 새로 발급된 비밀번호가 발송됩니다.

<br/>

### 4. 마이페이지

<details>
  <summary>내정보, 비밀번호 변경 화면보기</summary>
<p>
  <img src="https://github.com/user-attachments/assets/35b78c72-75c5-4679-91cb-916bdcefb207">
</p>
</details>
    
  - 내 정보 : 회원가입 시 기입한 정보를 조회합니다. 해당 페이지에서 정보 수정이 가능합니다.
  - 비밀번호 변경 : 현재 비밀번호를 신규 비밀번호로 변경할 수 있습니다.
 

<details>
  <summary>나의 주문내역 화면보기</summary>
<p>
  <img src="https://github.com/user-attachments/assets/f6f96c89-6735-44e1-b970-662c287f58d7">
</p>
</details>
    

  - 사용자의 주문목록을 확인 할 수 있습니다.
  - 해당목록은 주문한 상품별로 출력되며 ‘상세보기’ 클릭 시 함께 주문한 상품과 함께 결제정보 및 배송정보를 확인 할 수 있습니다.
  - 상품명, 주문번호, 결제상태에 따라 검색할 수 있으며 검색 초기화를 통해 처음 상태로 되돌아올 수 있습니다. 


<details>
  <summary>나의 상품리뷰 화면보기</summary>
<p>
  <img src="https://github.com/user-attachments/assets/c77a64df-9a6e-4718-bc2c-3f8d44ab20a7">
</p>
</details>
    

  - 사용자가 작성한 리뷰목록을 확인 할 수 있습니다.
  - 운영자의 답변이 있을 경우 답변배지가 표시되며, ‘상세보기’ 클릭 시 작성내용과 함께 운영자의 답변을 확인 할 수 있습니다.
  - 해당 페이지에서 리뷰 수정 및 삭제가 가능하며, 이미지 또는 상품명 클릭시 해당 상품의 상세페이지로 이동합니다.
  - 상품명, 리뷰제목, 리뷰내용 리뷰날짜, 운영자의 답변여부에 따라 검색 할 수 있으며 검색 초기화를 통해 처음 상태로 되돌아 올 수 있습니다. 

<details>
  <summary>나의 상품문의 화면보기</summary>
<p>
  <img src="https://github.com/user-attachments/assets/9c1a652c-19c1-45b6-b12a-477807095d38">
</p>
</details>
    
  - 사용자가 작성한 문의목록을 확인 할 수 있습니다.
  - 운영자의 답변이 있을 경우 답변배지가 표시되며, ‘상세보기’ 클릭 시 작성내용과 함께 운영자의 답변을 확인 할 수 있습니다.
  - 해당 페이지에서 문의 수정 및 삭제가 가능하며, 이미지 또는 상품명 클릭시 해당 상품의 상세페이지로 이동합니다.
  - 상품명, 문의제목, 문의내용 문의날짜, 운영자의 답변여부에 따라 검색 할 수 있으며 검색 초기화를 통해 처음 상태로 되돌아 올 수 있습니다.  


<details>
  <summary>회원탈퇴 화면보기</summary>
<p>
  <img src="https://github.com/user-attachments/assets/28fff4d2-99f4-4754-b9e8-e1b1c18791d7">
</p>
</details>
    
  - 회원탈퇴시 회원테이블에서 회원정보가 삭제되며, 재로그인이 불가합니다.

<br/>

### 5. 상품관련

<details>
  <summary>상품목록 화면보기</summary>
<p>
  <img src="https://github.com/user-attachments/assets/c9262452-b734-4b26-86a6-131d526c897f">
</p>
</details>
    
  - 카테고리 클릭시 2차 카테고리가 출력되며, 2차 카테고리 상품목록으로 이동합니다.
  - 등록된 상품이 없을 경우 해당 카테고리에는 ‘등록된 상품이 없습니다.’ 문구가 출력됩니다.
  - 해당페이지에서는 바로구매와 장바구니 담기가 가능합니다.

<details>
  <summary>상품상세 화면보기</summary>
<p>
  <img src="https://github.com/user-attachments/assets/9d33e4ba-a9cd-4913-858f-ab4e6872ff6a">
</p>
</details>
    
  - 상품 수량 선택 후 바로구매 혹은 장바구니 담기가 가능합니다.
  - 해당 페이지에서는 핸들바기능을 통해 구현한 상세정보, 상품리뷰, 상품 문의 확인할 수 있습니다.
  - 상품리뷰 및 문의 페이지 에서는 리뷰, 문의를 작성, 수정, 삭제 할 수 있으며, 로그인을 하지 않은 상태에서는 작성을 시도할 경우 로그인 페이지로 이동합니다.
  - 로그인 사용자가 아닌 타사용자가 작성한 리뷰, 문의의 아이디는 필터링 되며 수정, 삭제가 불가합니다.

<details>
  <summary>장바구니 화면보기</summary>
<p>
  <img src="https://github.com/user-attachments/assets/19ce5395-0547-4216-8170-2b633696a7a6">
</p>
</details>
    
  - 장바구니에 담은 목록을 확인할 수 있습니다.
  - 체크박스로 선택, 일괄 삭제가 가능합니다.
  - 장바구니 상에서 수량 변경이 가능합니다.
  - 장바구니가 비어있을 경우 '장바구니가 비어있습니다'안내와 함께 상품관련 버튼이 비활성화 됩니다.


<details>
  <summary>무통장입금 화면보기</summary>
<p>
  <img src="https://github.com/user-attachments/assets/00852d83-fea8-4707-9912-2abce7f5acb9">
</p>
</details>

<details>
  <summary>카카오페이 화면보기</summary>
<p>
  <img src="https://github.com/user-attachments/assets/c691b08a-1872-4a32-a79b-2696108b6ceb">
</p>
</details>
    
  - 주문서 작성 페이지에서 ‘회원정보와 동일’을 체크할 경우 회원정보에 저장된 정보가 출력됩니다.
  - 무통장입금, 카카오페이 중 하나를 결제방법으로 선택 할 수 있습니다.
  - 주문완료 후 주문내역에서 확인 가능하며 무통장 입금은 결제대기, 카카오페이는 결제완료로 표시됩니다. 




### [관리자 페이지]

.
.
.


작성중
