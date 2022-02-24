<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<title></title>
</head>

<script>
function gotoUrl(url){
  console.log(" url >>"+ url);
  location.href = url;
}

</script>

<body>
<aside class="sidenav bg-white navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-4 " id="sidenav-main">

    <div class="sidenav-header">
      <i class="fas fa-times p-3 cursor-pointer text-secondary opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
      <c:if test="${userSession.domain != null}">
        <a class="navbar-brand m-0" href="http://localhost:8081/" target="_blank">
          <%-- <img src="../argon/assets/img/logo-ct-dark.png" class="navbar-brand-img h-100" alt="main_logo"> --%>
          <span class="ms-1 font-weight-bold">${userSession.domain} </span>
        </a>
      </c:if>
      
    </div>
    <hr class="horizontal dark mt-0">
    <div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
      <ul class="navbar-nav">
        
        <li class="nav-item mt-3">
           <h6 class="ps-4 ms-2 text-uppercase text-ms font-weight-bolder opacity-6"> <i class="ni ni-tv-2 text-primary"></i> 하모나이즈 관리 </h6>
        </li>
        <li class="nav-item">
          <a class="nav-link " href="../pages/dashboard.html">
            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
            </div>
            <span class="nav-link-text ms-1 text-sm">하모나이즈 매뉴얼</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link " href="../pages/dashboard.html">
            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
            </div>
            <span class="nav-link-text ms-1 text-sm">어플리케이션 다운로드</span>
          </a>
        </li>

        <li class="nav-item mt-3">
            
          <h6 class="ps-4 ms-2 text-uppercase text-ms font-weight-bolder opacity-6"><i class="ni ni-credit-card text-success "></i> 결제 관리</h6>
        </li>
        <li class="nav-item">
          <a class="nav-link " href="/subscribe/bills">
            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
            </div>
            <span class="nav-link-text ms-1 text-sm">결제 수단 관리</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link " href="/subscribe/invoices">
            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
            </div>
            <span class="nav-link-text ms-1 text-sm">결제 내역</span>
          </a>
        </li>
       
        <li class="nav-item mt-3">
          <h6 class="ps-4 ms-2 text-uppercase text-ms font-weight-bolder opacity-6"> <i class="ni ni-single-02 text-dark text-sm opacity-10"></i> 회원정보 관리</h6>
        </li>

        <li class="nav-item">
          <a class="nav-link "  href="javascript:;" onClick="gotoUrl('/user/detail')">
            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
            </div>
            <span class="nav-link-text ms-1 text-sm">회원정보 수정</span>
          </a>
        </li>
        <li class="nav-item">
          <%-- <a class="nav-link " href="javascript:;" onClick="gotoUrl('/user/resign')"> --%>
          <a class="nav-link " href="javascript:;" >

            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
            </div>
            <span class="nav-link-text ms-1 text-sm">회원 탈퇴</span>
          </a>
        </li>

        <li class="nav-item mt-3">
          <h6 class="ps-4 ms-2 text-uppercase text-ms font-weight-bolder opacity-6">
          <svg class="text-dark" width="16px" height="16px" viewBox="0 0 46 42" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"> <title>customer-support</title> <g id="Basic-Elements" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd"> <g id="Rounded-Icons" transform="translate(-1717.000000, -291.000000)" fill="#FFFFFF" fill-rule="nonzero"> <g id="Icons-with-opacity" transform="translate(1716.000000, 291.000000)"> <g id="customer-support" transform="translate(1.000000, 0.000000)"> <path class="color-background" d="M45,0 L26,0 C25.447,0 25,0.447 25,1 L25,20 C25,20.379 25.214,20.725 25.553,20.895 C25.694,20.965 25.848,21 26,21 C26.212,21 26.424,20.933 26.6,20.8 L34.333,15 L45,15 C45.553,15 46,14.553 46,14 L46,1 C46,0.447 45.553,0 45,0 Z" id="Path" opacity="0.59858631"></path> <path class="color-foreground" d="M22.883,32.86 C20.761,32.012 17.324,31 13,31 C8.676,31 5.239,32.012 3.116,32.86 C1.224,33.619 0,35.438 0,37.494 L0,41 C0,41.553 0.447,42 1,42 L25,42 C25.553,42 26,41.553 26,41 L26,37.494 C26,35.438 24.776,33.619 22.883,32.86 Z" id="Path"></path> <path class="color-foreground" d="M13,28 C17.432,28 21,22.529 21,18 C21,13.589 17.411,10 13,10 C8.589,10 5,13.589 5,18 C5,22.529 8.568,28 13,28 Z" id="Path"></path> </g> </g> </g> </g> </svg>
       기술지원 관리</h6>
        </li>

        <li class="nav-item">
          <a class="nav-link " href="/support/list">
            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
            </div>
            <span class="nav-link-text ms-1 text-sm">기술지원 문의 내역</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link " href="/support/view">
            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
            </div>
            <span class="nav-link-text ms-1 text-sm">기술지원 신청</span>
          </a>
        </li>
        
        <%-- <li class="nav-item">
          <a class="nav-link active" href="/user/detail">
            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
            </div>
            <span class="nav-link-text ms-1 text-sm">회원정보 수정</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link active" href="../pages/profile.html">
            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
            </div>
            <span class="nav-link-text ms-1 text-sm">기술지원</span>
          </a>
        </li> --%>

      </ul>
    </div>
     
</aside>
</body>
<%-- 
<script>
function gotoUrl(url){
  console.log(" url >>"+ url);
  location.href = url;
}

</script> --%>

</html>