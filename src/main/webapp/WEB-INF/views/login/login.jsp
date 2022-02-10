<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Hamonize Login</title>
  <!-- plugins:css -->
  <link rel="stylesheet" href="../../vendors/feather/feather.css">
  <link rel="stylesheet" href="../../vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="../../vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- Plugin css for this page -->
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="../../css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="../../images/favicon.png" />
</head>

<body>
  <div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
      <div class="content-wrapper d-flex align-items-center auth px-0">
        <div class="row w-100 mx-0">
          <div class="col-lg-4 mx-auto">
            <div class="auth-form-light text-left py-5 px-4 px-sm-5">
              <div class="brand-logo">
                <a href="/"><img src="img/Hamonize.png" alt="logo"></a>
              </div>
              <h4>Login</h4>
              <h6 class="font-weight-light"> </h6>
              <form class="pt-3" method="POST" id="login-form" action="/login/login">
                <div class="form-group">
                  <input type="text" class="form-control form-control-lg" name="userid" id="userid" placeholder="아이디">
                </div>
                <div class="form-group">
                  <input type="password" class="form-control form-control-lg" name="passwd" id="passwd" placeholder="비밀번호">
                </div>
                <div class="mt-3">
                    <input type="submit" name="signin" id="signin" class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn"value="로그인"/>
                </div>
                <div class="my-2 d-flex justify-content-between align-items-center">
                  <div class="form-check">
                    <label class="form-check-label text-muted">
                      <input type="checkbox" class="form-check-input">
                     
                    </label>
                  </div>
                  <a href="#" class="auth-link text-black">비밀번호를 잊으셨습니까?</a>
                </div>
                <%-- <div class="mb-2"> --%>
                <div class="social-login">
                    <span class="social-label">Or </span>
                    <ul class="socials">
                        <li><a href="/oauth2/authorization/kakao"> <img src="/img/kakao.png" ></a></li>
                    </ul>
                </div>
                <div class="text-center mt-4 font-weight-light">
                  계정이 없으십니까? <a href="/signup" class="text-primary">회원가입 하러하기</a>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <!-- content-wrapper ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
<!-- plugins:js -->
<script src="../../vendors/js/vendor.bundle.base.js"></script>
<!-- endinject -->
<!-- Plugin js for this page -->
<!-- End plugin js for this page -->
<!-- inject:js -->
<script src="../../js/off-canvas.js"></script>
  <script src="../../js/hoverable-collapse.js"></script>
  <script src="../../js/template.js"></script>
  <script src="../../js/settings.js"></script>
  <script src="../../js/todolist.js"></script>
  <!-- endinject -->
</body>

</html>
