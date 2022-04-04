
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
          <h5 class="font-weight-bolder">${boardCfg.bcname}</h5>    
          <div class="table-responsive">
            <table class="table align-items-center mb-0">
              <thead>
                <tr>
                  <th class="text-uppercase text-dark text-xs font-weight-bolder opacity-7 dark" style="width: 10%;">글 번호</th>
                  <th class="text-uppercase text-dark text-xs font-weight-bolder opacity-7" style="width: 35%;">제목</th>
                  <th class="text-uppercase text-dark text-xs font-weight-bolder opacity-7 ps-2" style="width: 15%;" >작성자</th>
                  <th class="text-uppercase text-dark text-xs font-weight-bolder opacity-7 ps-2" style="width: 25%;">등록일</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                 <c:forEach items="${boards}" var="list" varStatus="status" >
                <tr>
                  <td>
                    <div class="d-flex align-items-center">
                        <div class="d-flex align-items-center">
                          <span class="me-2 text-xs ps-4">${list.bseq}</span>
                        </div>
                    </div>
                  </td>
                  <td>
                    <div class="d-flex align-items-center">
                      <%-- <a href="/board/view" class="me-2 text-xs ps-3">${list.btitle}</a> --%>
                      <a href="/board/${boardCfg.bcname}/view/${list.bseq}" class="me-2 text-xs ps-3">${list.btitle}</a>
                    </div>
                  </td>
                  <td>
                    <div class="d-flex align-items-center">
                      <span class="me-2 text-xs">${list.userid}</span>
                    </div>
                  </td>
                  <td class="align-middle text-center">
                    <div class="d-flex align-items-center">
                      <span class="me-2 text-xs">${list.viewdate}</span>
                    </div>
                  </td>

                  <td class="align-middle">
                    <button class="btn btn-link text-secondary mb-0">
                      <i class="fa fa-ellipsis-v text-xs" aria-hidden="true"></i>
                    </button>
                  </td>
                </tr>
               </c:forEach>

              </tbody>
            </table>
          </div>

      </div>
    </div>

  </div>
</body>


</html>

  
  