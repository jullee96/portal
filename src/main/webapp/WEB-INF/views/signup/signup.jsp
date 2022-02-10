<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up | Hamonize Cloud Service</title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="/fonts/material-icon/css/material-design-iconic-font.min.css">

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
<style>
.id_ok{
    color:#fff; 
    display: none;
}

.id_already{
    color:#6A82FB; 
    display: none;
}

</style>

<body>
    <div class="main">

        <!-- Sign up form -->
        <section class="signup">
            <div class="container">
                <div class="signup-content">
                    <div class="signup-form">
                        <h2 class="form-title">Sign up</h2>
                        <form method="POST" class="register-form" id="register-form" action="/signup/signup">
                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="username" id="username" placeholder="이름"  />
                            </div>

                            <div class="form-group">
                                <label for="name"><i class="zmdi zmdi-account material-icons-name"></i></label>
                                <input type="text" name="userid" id="userid" placeholder="아이디"  />
                            </div>
                            <div class="form-group">
                                <label for="email"><i class="zmdi zmdi-email"></i></label>
                                <input type="email" name="email" id="email" placeholder="이메일"  />
                            </div>
                            <div class="form-group">
                                <label for="pass"><i class="zmdi zmdi-lock"></i></label>
                                <input type="password" name="passwd" id="passwd" placeholder="비밀번호" />
                            </div>
                            <div class="form-group">
                                <label for="re-pass"><i class="zmdi zmdi-lock-outline"></i></label>
                                <input type="password" name="re_passwd" id="re_passwd" placeholder="비밀번호 확인"  />
                            </div>
                            <div class="form-group form-button">
                                <input type="submit" name="signup" id="signup" class="form-submit" value="가입하기"/>
                            </div>
                        </form>
                    
                        <div class="signup-image">
                            <a href="/login" class="signup-image-link">로그인 하러가기</a>
                        </div>
                    </div>
       
                </div>
            </div>
        </section>


    </div>


<script>
$(document).ready(function () {

    $("#register-form").validate({
      submitHandler: function(form) {
          form.submit();
      }  
    });
   
    // $("#userid").keyup(function(){
    //     var id = $(this).val();
    //     console.log("id >> "+id);
    
    //     $.ajax({
    //         url:'/signup/idDupCheck', 
    //         type:'post', 
    //         data:{userid: id},
    //         success:function(cnt){
    //             $("#register-form").validate({
    //                 rules:{
    //                     userid:{
    //                         idDupChk: 1 
    //                     }  
    //                 },
    //                 message: {
    //                     userid:{
    //                         idDupChk: "중복된 아이디입니다" 
    //                     }  
    //                 },
    //                 submitHandler: function(form) {
    //                   form.submit();
    //                 }  
    //             });

    //        }
    //     });
        
    // });


});
</script>

</body>

</html>