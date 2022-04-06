
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  
  <title>
    Hamonize Cloud Service 
  </title>
<!-- tuideditor -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css"/>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
<script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>

</head>

<%@ include file="../template/top2.jsp" %>

<style>
.toastui-editor-contents img[alt="alt_img"] { 
  max-width: 600px;

}

@media screen and (max-width: 768px) {
  .toastui-editor-contents img[alt="alt_img"] { 
    max-width: 300px;
  }
}


</style>
<body class="g-sidenav-show bg-gray-100">
  <input type="hidden" id="pageTitle" value="${sitemap.sitemapname}">
  <input type="hidden" id="pageSubTitle" value="${boardCfg.bcname}">

  <div class="position-absolute w-100 min-height-300 top-0" >
    <span class="mask bg-primary opacity-6"></span>
  </div>
  <%@ include file="../template/aside.jsp" %>


  <div class="main-content position-relative max-height-vh-100 h-100">
    <!-- Navbar -->
    <%@ include file="../template/navbar.jsp" %>
    <!-- End Navbar -->
     <div class="card shadow-lg mx-4 card-profile-bottom">
        <div class="card-body p-3">
          <h5 class="font-weight-bolder">${board.btitle}</h5>    
          <div class="row">
            <div class="dd col-12">
              
              <div id=viewer></div>
            </div> 
          </div>
        </div>
      </div>
      <input type="hidden" id="content" value="${board.bcontent}" >
      <!-- 답변 -->
  </div>
</body>
<script>
const content = $("#content").val();
// console.log("content : "+content)

const viewer = new toastui.Editor.factory({ 
    el: document.querySelector('#viewer'), 
    height: '500px', 
    viewer: true,
    initialValue: content  
});
</script>

</html>