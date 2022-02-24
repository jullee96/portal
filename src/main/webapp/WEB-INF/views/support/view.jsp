<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>
    Hamonize Cloud Service | Support List
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

  <div class="position-absolute w-100 min-height-300 top-0" style="background-color: #5e72e4;">
    <span class="mask bg-primary opacity-6"></span>
  </div>
  <%@ include file="../template/aside.jsp" %>


  <div class="main-content position-relative max-height-vh-100 h-100">
    <!-- Navbar -->
    <%@ include file="../template/navbar.jsp" %>
    <!-- End Navbar -->

<form id="support-form" method="POST" action="/support/save">
    <div class="card shadow-lg mx-4 card-profile-bottom">
        <div class="card-body p-3">
            <h5 class="font-weight-bolder">1:1 문의하기</h5>    
                <div class="d-flex align-items-center">
                  <button type="submit" class="btn btn-primary btn-sm ms-auto ">신청하기</button>
                </div>


                <div class="row">
                    <div class="col-12">
                        <label class="sub-title" >선택</label>
                        <select class="form-control" name="choices-button" id="choices-button" placeholder="Departure" required focused>
                            <option value="" disabled selected hidden>선택해주세요</option>
                            <option value="1" >결제문의</option>
                            <option value="2">기술문의</option>
                            <option value="3">기타</option>
                        </select>
                    </div>
                    <div class="col-12 col-sm-6 ">
                        <label class="sub-title">조직명</label>
                        <input class="form-control" type="text" >
                    </div>
                    <div class="col-12 col-sm-6 mt-3 mt-sm-0">
                        <label class="text-md-start">담당자</label>
                        <input class="form-control" type="text" value="${userSession.username}" >

                    </div>
                    <div class="col-12 col-sm-6 ">
                        <label class="text-md-start">연락처</label>
                        <input class="phoneNumber form-control" type="text" name="tel" maxlength=13>

                    </div>
                    <div class="col-12 col-sm-6 mt-3 mt-sm-0">
                        <label class="text-md-start">이메일</label>
                        <input class="form-control" type="email" name="email" value="${userSession.email}" >

                    </div>

                    <div class="col-12">
                        <label class="text-md-start">제목</label>
                        <input class="form-control" type="text" value="" name="contents">
                    </div>

                    <div class="col-sm-12">
                    <label class="mt-4">내용</label>
                    <div class="contents" id ="editor"></div>
                    <div id="contents"></div>
                    
                </div>

        </div>

    </div>
  
  </div>
</form>
  
</body>

<%@ include file="../template/core.jsp" %>
<script class="code-js">

const Editor = toastui.Editor;
const editor = new Editor({ 
    el: document.querySelector('#editor'), 
    height: '500px', 
    initialEditType: 'wysiwyg',
    previewStyle: 'vertical',
    customHTMLRenderer: {
    htmlBlock: {
        iframe(node) {
                return [
                { type: 'openTag', tagName: 'iframe', outerNewLine: true, attributes: node.attrs },
                { type: 'html', content: node.childrenHTML },
                { type: 'closeTag', tagName: 'iframe', outerNewLine: true },
                ];
            },
        }
    },
    hooks:{
        addImageBlobHook: (blob, callback) => {
            const img_url = uploadImage(blob);
            callback(img_url.split("uploads")[1] , 'alt_img');
        }    
    }
    
});



function uploadImage(blob){
    console.log("uploadImage >> "+blob);
    var url;

    let filename = new Date().getTime() + ".png";
    let InputFiles = new File([blob], filename, {
        type: "image/png",
        lastModified: Date.now()
    });

    console.log(InputFiles);

    var keytype ="support";

    console.log("keytype > "+keytype);
    console.log("filename > "+filename);
    
    if(InputFiles == null ){
        alert("파일을 선택해주세요");
        return;
    }
  
    var formData = new FormData();
    formData.append("keyfile", InputFiles);
    formData.append("keytype", keytype);
    


    $.ajax({
        type:"POST",
        url: "/file/upload",
        processData: false,
        contentType: false,
        data: formData,
        async:false,
        success: function(retval){
            if(retval != "F"){
                console.log("업로드 성공" +retval);
                console.log("111url >>>>>>>> " +url);
               
            } else{
                console.log("업로드 실패");
            }
            console.log("retval >> " +retval);
        
            url = retval;
            console.log("url ?? " +url);
    
        }
    });

    return url;
}

document.querySelector('#contents').insertAdjacentHTML('afterbegin' ,editor.getHTML());
console.log(editor.getHTML());
</script>
<script>
$(document).on("keyup", ".phoneNumber", function() { 
    $(this).val( $(this).val().replace(/[^0-9]/g, "")
    .replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3")
    .replace("--", "-") ); 
});

</script>
</html>