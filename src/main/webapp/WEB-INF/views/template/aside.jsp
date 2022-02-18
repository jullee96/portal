<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<aside class="sidenav bg-white navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-4 " id="sidenav-main">

    <div class="sidenav-header">
      <i class="fas fa-times p-3 cursor-pointer text-secondary opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
      <a class="navbar-brand m-0" href="http://localhost:8081/" target="_blank">
        <img src="../argon/assets/img/logo-ct-dark.png" class="navbar-brand-img h-100" alt="main_logo">
        <span class="ms-1 font-weight-bold">${userSession.domain} </span>
      </a>
    </div>
    <hr class="horizontal dark mt-0">
    <div class="collapse navbar-collapse  w-auto " id="sidenav-collapse-main">
      <ul class="navbar-nav">
        
        <li class="nav-item mt-3">
           <h6 class="ps-4 ms-2 text-uppercase text-lg font-weight-bolder opacity-6"> <i class="ni ni-tv-2 text-primary"></i> 하모나이즈 관리 </h6>
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
            
          <h6 class="ps-4 ms-2 text-uppercase text-lg font-weight-bolder opacity-6"><i class="ni ni-credit-card text-success "></i> 결제 관리</h6>
        </li>
        <li class="nav-item">
          <a class="nav-link " href="../pages/billing.html">
            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
            </div>
            <span class="nav-link-text ms-1 text-sm">결제 내역</span>
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link " href="../pages/billing.html">
            <div class="icon icon-shape icon-sm border-radius-md text-center me-2 d-flex align-items-center justify-content-center">
            </div>
            <span class="nav-link-text ms-1 text-sm">결제 수단 변경</span>
          </a>
        </li>
       
        <li class="nav-item mt-3">
          <h6 class="ps-4 ms-2 text-uppercase text-lg font-weight-bolder opacity-6"> <i class="ni ni-single-02 text-dark text-sm opacity-10"></i> 회원정보 관리</h6>
        </li>
        <li class="nav-item">
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
        </li>

      </ul>
    </div>
     
</aside>