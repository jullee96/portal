<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>
    Hamonize Cloud Service | Support Apply
  </title>

<!-- tuideditor -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css"/>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>

</head>
<%@ include file="../template/top2.jsp" %>

<style>
.contents{
    font-family: "Open Sans", sans-serif;
    color: #495057;
    font-size: 0.875rem;
    border-radius: 0.5rem;
}

.sub-title{
    margin-top:10px;
}

img[alt=alt_img] { 
    width: 500px; 
}


</style>


<body class="g-sidenav-show bg-gray-100">
  <input type="hidden" id="pageTitle" value="기술지원 관리">
  <input type="hidden" id="pageSubTitle" value="기술지원 신청">

  <div class="position-absolute w-100 min-height-300 top-0" >
    <span class="mask bg-primary opacity-6"></span>
  </div>
  <%@ include file="../template/aside.jsp" %>


  <div class="main-content position-relative max-height-vh-100 h-100">
    <!-- Navbar -->
    <%@ include file="../template/navbar.jsp" %>
    <!-- End Navbar -->

<form id="support-form" name="support-form" >
    <div class="card shadow-lg mx-4 card-profile-bottom">
        <div class="card-body p-3">
              <h5 class="font-weight-bolder">문의 상세 
              <input type="hidden" id="clistSize" value="${clistSize}">
                    <c:if test="${clistSize != 0}">
                        <button type="button" class="mt-2 ms-2 btn btn-outline-secondary btn-status btn-xs"> 답변완료</button>  
                    </c:if>
                    <c:if test="${clistSize == 0}">
                        <button type="button" class="mt-2 btn btn-outline-success btn-status btn-xs"> 처리중</button>  
                    </c:if>
                </h5>    
                <div class="d-flex align-items-center">
                  <input type="hidden" id="clistSize" value="${clistSize}">

                  <c:if test="${clistSize == 0}">
                    <a href="/support/edit?seq=${edit.seq}" class="btn btn-primary btn-sm ms-auto ">수정하기</a>
                  </c:if>
                  <c:if test="${clistSize != 0}">
                    <a href="/support/apply" class="btn btn-primary btn-sm ms-auto ">새 질문하기</a>
                  </c:if>
                </div>
                  <hr>

                <div class="row">
                    <div class="col-12">
                        <label class="h6 sub-title" >문의 종류</label>

                        <input type="hidden" id="select_type" value="${edit.type}" >
                        <select class="form-control" name="type" id="type" disabled focused>
                            <option value="" disabled selected hidden>선택해주세요</option>
                            <option value="P" >결제문의</option>
                            <option value="T">기술문의</option>
                            <option value="E">기타</option>
                        </select>
                    </div>

                    <div class="col-12 col-sm-6 mt-sm-4">
                        <label class="h6 text-md-start">작성자</label>
                        <input class="form-control" type="text" id="name" name="name" value="${userSession.username}" disabled >
                    </div>

                    <div class="col-12 col-sm-6  mt-sm-4">
                        <label class="h6 text-md-start">이메일</label>
                        <input class="form-control" type="email" id="email" name="email" value="${userSession.email}" disabled>

                    </div>

                    <div class="col-12 mt-sm-4">
                        <label class="h6 text-md-start">제목</label>
                        <input class="form-control" type="text" id="title" name="title" value="${edit.title}" disabled>
                    </div>

                    <div class="col-sm-12">
                    <label class="h6 mt-4">내용</label>
                    <input id="content" type="hidden" value="${edit.contents}">
                    <div class="contents" id ="viewer"> </div>
                    <hr>

                </div>

        </div>
    </div>

      <!-- 답변 -->
      <c:forEach items="${clist}" var="list" varStatus="status" >
          <c:if test="${list.seq != null}" >
          <div class="card mt-2 mb-4 shadow-lg mx-4 card-profile-bottom" style="background-color:#63b3ed1c;">
              <div class="card-body p-3" >  
                  <h6 class="font-weight-bolder">ㄴ ${list.userid}</h6>
                  <div class="ms-2 contents" id ="viewer_cmt_${status.count}"></div>
                  ${list.viewDate}
                  <input id="cmt_${status.count}" type="hidden" value='${list.comment}'>
              </div>
          </div>

          </c:if>
      </c:forEach>

  </div>
</form>
  
</body>

<%@ include file="../template/core.jsp" %>
<script class="code-js">
const content = $("#content").val();
// console.log("content : "+content)

const viewer = new toastui.Editor.factory({ 
    el: document.querySelector('#viewer'), 
    height: '500px', 
    viewer: true,
    initialValue: content  
});

const n = $("#clistSize").val();
var arr = new Array(n);

for(let i=1 ; i <= n ; i++){
    var cmt = $("#cmt_"+i).val();
    
    arr[i] = toastui.Editor.factory({
        el: document.querySelector('#viewer_cmt_'+i), 
        viewer: true, 
        height: '100px',
        initialValue : cmt 
    });
        

}

</script>
<script>

$(document).ready(function () {
  const select_type = $("#select_type").val();

  $("#type").val(select_type);  

});
</script>
</html>