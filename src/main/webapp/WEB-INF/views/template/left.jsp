<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<style>
.top{
  margin-top:10%;
}
</style>

<div class="sidebar" data-color="white" data-active-color="danger">
  <div class="logo">
    <a href="https://www.creative-tim.com" class="simple-text logo-mini">
      <div class="logo-image-small">
        <img src="../assets/img/logo-small.png">
      </div>
    </a>
    <a href="https://www.creative-tim.com" class="simple-text logo-normal">
      ${userSession.username} 님
    </a>
  </div>
  <div class="sidebar-wrapper">
    <ul class="nav">
      <li>
        <a href="./dashboard.html">
          <i class="nc-icon nc-bank"></i>
          <p>하모나이즈 관리</p>
        </a>
      </li>
      
      <li class="active ">
        <a href="./user.html">
          <i class="nc-icon nc-single-02"></i>
          <p>회원 정보</p>
        </a>
      </li>
      
    </ul>
  </div>
</div>