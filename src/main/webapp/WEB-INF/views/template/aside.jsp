<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="ko">

<head>
<title></title>
</head>
<input type="hidden" id="userDomain" value="${userSession.domain}">
<input type="hidden" id="userRole" value="${userSession.role}">

<script>
$(document).ready(function () {
    $.ajax( {
        url : "/board/list",
        type:"POST",
        success : function(board) {
            const obj = JSON.parse(board);
            var html = "";
            var role = $("#userRole").val();
            var domain = $("#userDomain").val();
            console.log("login user role : "+role);
            console.log("login user domain : "+domain);
                
            for(let i=0;i<obj.length;i++){
              if(obj[i].smseq != undefined ){
                html += '<ul class="navbar-nav"> <li class="nav-item mt-3"><h6 id="sitemap" class="ps-4 ms-2 text-uppercase text-ms font-weight-bolder opacity-6">'+obj[i].sitemapname+'</h6></li>';
              } else{
                
                console.log("menu > "+obj[i].bcseq+ " : "+obj[i].bcrole);
                if(obj[i].bcrole =="USER"){
          
                  var jsonArr = JSON.parse(obj[i].bcdomains);
                  for(let i=0;i<jsonArr.length;i++){
                    console.log("text : " + jsonArr[i].text);
                    if(jsonArr[i].text == domain){
                        html += '<li class="nav-item"><a class="nav-link" href="'+obj[i].bcid+'"> <span id="menu" class="nav-link-text ms-4 text-sm">'+obj[i].bcname+'</span></a> </li> ';  
                    }
                  }

                } else if(obj[i].bcrole== "ADMIN"){
                  if(role == "ROLE_ADMIN"){
                    html += '<li class="nav-item"><a class="nav-link" href="'+obj[i].bcid+'"> <span id="menu" class="nav-link-text ms-4 text-sm">'+obj[i].bcname+'</span></a> </li> ';  
                  }
 
                } else{ // role == all
                  html += '<li class="nav-item"><a class="nav-link" href="'+obj[i].bcid+'"> <span id="menu" class="nav-link-text ms-4 text-sm">'+obj[i].bcname+'</span></a> </li> ';
                }
              }  
              
              html += '</li>';
            }
             
            $("#side").append(html);
        }
      });
  
});

</script>
<body>
<aside class="sidenav bg-white navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-4 " id="sidenav-main">
<%-- <input type="hidden" id="userDomain" value="${userSession.domain}">
<input type="hidden" id="userRole" value="${userSession.role}"> --%>

    <div class="sidenav-header">
      <i class="fas fa-times p-3 cursor-pointer text-secondary opacity-5 position-absolute end-0 top-0 d-none d-xl-none" aria-hidden="true" id="iconSidenav"></i>
      <c:if test="${userSession.domain != null}">
        <a class="navbar-brand m-0" href="http://localhost:8081/" target="_blank">
          <span class="ms-1 font-weight-bold">${userSession.domain} </span>
        </a>
      </c:if>
    </div>
    <hr class="horizontal dark mt-0">
    <div id="side" class="collapse navbar-collapse w-auto" id="sidenav-collapse-main">
      <%-- <ul class="navbar-nav"> <li class="nav-item mt-3"><h6 id="sitemap" class="ps-4 ms-2 text-uppercase text-ms font-weight-bolder opacity-6">AAA</h6></li>
      <li class="nav-item"><a class="nav-link" href="#"><span id="menu" class="nav-link-text ms-4 text-sm">aa</span></a> </li> </ul> --%>
    </div>
     
</aside>
</body>

</html>