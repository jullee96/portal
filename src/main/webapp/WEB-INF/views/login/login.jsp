<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign In | Hamonize Cloud Service</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="/css/style.css">

    <!-- form validation check -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script> 
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
    <script src="/js/form-validation.js"></script>

    <!-- JS -->
    <script src="/js/main.js"></script>

</head>
<body>

    <div class="main">

        <!-- Sing in  Form -->
        <section class="sign-in">
            <div class="container">
                <div class="signin-content">
                    <div class="signin-image">
                        <figure>
                            <%-- <img src="images/signin-image.jpg" alt="sing up image"> --%>
                        </figure>
                        <a href="/signup" class="signup-image-link"> 회원가입하기</a>
                    </div>

                    <div class="signin-form">
                        <h2 class="form-title">Sign In</h2>
                        
                        <form method="POST" class="register-form" id="login-form" action="/login/login">
                            <div class="form-group">
                                <label for="userid"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="userid" id="userid" placeholder="아이디"/>
                            </div>
                            <div class="form-group">
                                <label for="your_pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="passwd" id="passwd" placeholder="비밀번호" />
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signin" id="signin" class="form-submit" value="로그인"/>
                            </div>
                        </form>

                        <div class="social-login">
                            <span class="social-label">Or </span>
                            <ul class="socials">
                                <li><a href="#"><i class="display-flex-center zmdi zmdi-google"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </div>

<script>
$(document).ready(function () {

    $("#login-form").validate({
      submitHandler: function(form) {
          form.submit();
      }
});

</script>

</body>
</html>