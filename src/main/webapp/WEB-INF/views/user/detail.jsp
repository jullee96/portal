<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>
  Hamonize Cloud Service | User detail
  </title>

</head>
<%@ include file="../template/top2.jsp" %>

<style>
/*--------------------
  file drag & drop
----------------------*/
.file-drop-area {
  position: relative;
  display: flex;
  align-items: center;
  width: 90%;
  max-width: 100%;
  padding: 10px;
  border: 1px dashed #ccc;

  border-radius: 3px;
  transition: 0.2s
}

.choose-file-button {
  color: grey;
  flex-shrink: 0;
  background-color: #ececf1;
  border: 1px solid #ccc;
  border-radius: 3px;
  padding: 8px 15px;
  margin-right: 10px;
  font-size: 14px;
  text-transform: uppercase
}

.file-message {
  color: grey;
  margin-left: 30%;
  font-size: 14px;
  font-weight: 300;
  line-height: 1.4;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis
}

.file-input {
  position: absolute;
  height: 100%;
  width: 100%;
  cursor: pointer;
  opacity: 0;
  border: 1px solid #ced4da;
}

.detailProfileImg{
  height: 74px;
  width: 74px;
}

</style>

<body class="g-sidenav-show bg-gray-100">
  <input type="hidden" id="pageTitle" value="회원정보 관리">
  <input type="hidden" id="pageSubTitle" value="회원정보 수정">

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
        <div class="row gx-4">
          
          <div class="col-auto">
            <div class="avatar avatar-xl position-relative">
              <!-- button trigger modal -->
              <c:if test="${profileImg != null}" >
                <img src="/user/images" class="border-radius-md detailProfileImg" > 
              </c:if>
              <c:if test="${profileImg == null}">
                <img src="/img/user.svg" class="border-radius-md detailProfileImg">  
              </c:if>
              <a href="javascript:;" data-bs-toggle="modal" data-bs-target="#exampleModal" class="btn btn-sm btn-icon-only bg-gradient-light position-absolute bottom-0 end-0 mb-n2 me-n2">
                <i class="fa fa-pen top-0" data-bs-toggle="tooltip" data-bs-placement="bottom" title="" aria-hidden="true" data-bs-original-title="이미지 변경" aria-label="Edit Image"></i><span class="sr-only">Edit Image</span>
              </a>
               

              <!-- Modal -->
              <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="exampleModalLabel">프로필 이미지 변경</h5>
                      <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                    
                    <div class="modal-body">
                    
                      <div class="container d-flex justify-content-center">
                        <div class="file-drop-area"> 
                          <span class="choose-file-button">파일 선택</span> 
                          <span class="file-message">or drag&drop</span>
                            <input id="file-input" class="file-input" type="file" multiple>
                        </div>
                      </div>

                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn bg-gradient-secondary" data-bs-dismiss="modal">닫기</button>
                      <button type="button" class="btn bg-gradient-primary" onclick="uploadFile('img')">수정</button>
                    </div>
                  </div>
                </div>
              </div>
              <!-- End of Modal -->

            </div>
          </div>

          <div class="col-auto my-auto">
            <div class="h-100">
              <h5 class="mb-1">
                ${userSession.username}
              </h5>
              <p class="mb-0 font-weight-bold text-sm">
                ${userSession.domain}
              </p>
            </div>
          </div>
        <%-- <div class="col-md-6 my-auto">
            <div class="ms-6 h-100">
              <p class="mb-1">
                구독중
              </p>
              <p class="mb-0 font-weight-bold text-sm">
                Free
              </p>
            </div>
          </div> --%>
        
          <!--- tab menu --->
          <div class="z-index-1 col-lg-4 col-md-12 my-sm-auto ms-sm-auto me-sm-0 mx-auto mt-3">
            <div class="nav-wrapper position-relative end-0">
              <ul class="nav nav-pills nav-fill p-1" role="tablist">
                <li class="nav-item">
                  <a class="nav-link mb-0 px-0 py-1 d-flex align-items-center justify-content-center active" data-bs-toggle="tab" href="#div-user" role="tab" data-load="true" aria-selected="true">
                    <i class="ni ni-circle-08"></i>
                    <span class="ms-2">회원정보</span>
                  </a>
                </li>
                <li class="nav-item">
                  <a class="nav-link mb-0 px-0 py-1 d-flex align-items-center justify-content-center" data-bs-toggle="tab" href="#div-domain" role="tab" data-load="false" aria-selected="false">
                    <i class="ni ni-world-2"></i>
                    <span  class="ms-2">구독 정보</span>
                  </a>
                </li>
                
            </div>
          </div>
          <!--- end tab menu --->

        </div>

      </div>
    </div>

    <form id="user-form" action="/user/update" method="POST">
      <div class="tab-content">
        
        <div id ="div-user" class="tab-pane in active container-fluid py-4">
          <div class="row">
            <div class="col-md-12">
              <div class="card">
                <div class="card-header pb-0">
                  <div class="d-flex align-items-center">
                    <p class="mb-0">회원정보 수정 </p>
                    <button type="button" class="btn btn-secondary btn-sm ms-auto" onclick="fnDeleteUser('${userSession.userid}', '${userSession.email}' )">탈퇴하기</button>
                    <button type="submit" class="btn btn-primary btn-sm ms-4">수정하기</button>
                  </div>
                </div>
                <div class="card-body">
                  <p class="text-uppercase text-sm">회원 정보</p>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label for="example-text-input" class="form-control-label">아이디</label>
                        <input class="form-control" type="text" value="${userSession.userid}" disabled>
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label for="example-text-input" class="form-control-label">이름</label>
                        <input class="form-control" id="username" name="username" type="text" value="${userSession.username}">
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label for="example-text-input" class="form-control-label">Email</label>
                        <input class="form-control" id="email" name="email" type="email" value="${userSession.email}" >
                      </div>
                    </div>
                    <div class="col-md-12">
                      <div class="form-group">
                        <label for="example-text-input" class="form-control-label">이전 비밀번호 확인</label>
                        <input class="form-control" id="" name="beforepasswd" type="password" value="">
                      </div>
                    </div>

                    <div class="col-md-6">
                      <div class="form-group">
                        <label for="example-text-input" class="form-control-label">비밀번호 변경</label>
                        <input class="form-control" id="passwd" name="passwd" type="password" value="">
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label for="example-text-input" class="form-control-label">비밀번호 변경 확인</label>
                        <input class="form-control" id="re_passwd" name="re_passwd" type="password" value="">
                      </div>
                    </div>
                  </div>
                  
                  <hr class="horizontal dark">
                  <p class="text-uppercase text-sm">회사 정보</p>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label for="example-text-input" class="form-control-label"> 회사명</label>
                        <input class="form-control" type="text" id="companyName" name="companyName" value="${companyInfo.companyName}">
                      </div>
                    </div>
                    <div class="col-md-12">
                      <div class="form-group">
                        <label for="example-text-input" class="form-control-label"> 대표명 </label>
                        <input class="form-control" type="text" id="rprsName" name="rprsName" value="${companyInfo.rprsName}">
                      </div>
                    </div>
                    
                    <div class="col-md">
                      <div class="form-group">
                        <label for="example-text-input" class="form-control-label">사업자 번호 </label>
                          <input class="form-control" type="text" id="businessNumber" name="businessNumber" value="${companyInfo.businessNumber}" maxlength=12>
                      </div>
                    </div>

                    
  
                  </div>
                  
                </div>


              </div>
            </div>
            
          </div>
          <!-- footer -->
          <%@ include file="../template/footer.jsp" %>
        
        </div>

        <div id ="div-domain" class="tab-pane container-fluid py-4">
          <div class="row">
            <div class="col-md-12">
                <div class="card">
                  <div class="mt-2 card-header pb-0">
                    <p>구독 정보 보기</p>
                  </div>  
                  <div class="card-body pb-0">
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group">

                          <p class="text-uppercase text-sm">구독중인 상품</p>
                          <p class="ms-2 font-weight-bold"> ${subscribeInfo.product.pdname}</p>

                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group">

                          <p class="text-uppercase text-sm">결제 수단</p>
                          <p class="ms-2 font-weight-bold"> ${subscribeInfo.cardnum}</p>

                        </div>
                      </div>

                     </div> 
                  
                    <%-- <div class="row">
                      <p class="text-uppercase text-sm">결제 수단</p>
                        ${subscribeInfo.cardnum}
                    </div> --%>
                  </div>

                </div>
            </div>  
          </div>
        </div>

        
      </div>
    </form>
</div>
  
</body>
<script>

function checkNumber(event) {
  if(event.key === '.' 
     || event.key === '-'
     || event.key >= 0 && event.key <= 9) {
    return true;
  }
  
  return false;
}

$(document).on("keyup", "#businessNumber", function() { 
    $(this).val( $(this).val().replace(/[^0-9]/g, "")
    .replace(/^(\d{3})(\d{2})(\d{5})$/, `$1-$2-$3`)
    .replace("--", "-") ); 
});


$(document).ready(function () {
  $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
    console.log("show");
  });


  $('a[data-toggle="tab"]').on('hide.bs.tab', function (e) {
    console.log("hide");
  });



  $("#user-form").validate({
    submitHandler: function(form) {
        form.submit();
    }  
  });

});


$(document).on('change', '.file-input', function() {
    var filesCount = $(this)[0].files.length;
    var textbox = $(this).prev();
    const maxSize = 1048576;
    console.log("filesCount >  "+ filesCount);

    console.log("size : "+ this.files[0].size);

    if (filesCount === 1) {
      if( maxSize > this.files[0].size ){
          var fileName = $(this).val().split('\\').pop();
          textbox.text(fileName);
      } else{
        alert("파일 사이즈는 5MB를 초과할 수 없습니다");
        return;
      }

     
    } else {
      textbox.text('선택된 파일이 없습니다');
    }
});

function uploadFile(keytype){
  var InputFiles;

  InputFiles = $("#file-input")[0];
  var filename =  $("#file-input").val().split('\\').pop();

  console.log("keytype > "+keytype);
  console.log("InputFiles > "+InputFiles);
  console.log("InputFiles > "+InputFiles.files[0]);
  console.log("filename > "+filename);
  
  if(InputFiles.files.length === 0){
      alert("파일을 선택해주세요");
    return;
    }
  
  var formData = new FormData();
  formData.append("keyfile", InputFiles.files[0]);
  formData.append("keytype", keytype);

  $.ajax({
      type:"POST",
      url: "/file/upload",
      processData: false,
      contentType: false,
      data: formData,
    success: function(retval){
      if(retval!="F"){
        alert("업로드 성공");
        location.reload();
      }else{
        alert("업로드 실패");
        location.reload();
      }
    },
    err: function(err){
      console.log("err:", err)
    }
  });

}

function fnDeleteUser(userid, email){
  if(confirm("하모나이즈 서비스를 탈퇴하시겠습니까?" + userid)){
    $.ajax({
      type:"POST",
      url: "/user/resign",
      data:{
        userid : userid,
        email : email
      },
      success: function(retval){
        if(retval == userid){
          alert("이메일로 탈퇴 확인 메일을 보내드렸습니다. 확인을 완료해야 탈퇴가 완료됩니다.");
          location.reload();
        }else{
          alert("전송 실패" + retval);
          location.reload();
        }
      },
      err: function(err){
        console.log("err:", err)
        }
      });
  } else{

  }
}

</script>

<%@ include file="../template/core.jsp" %>

</html>