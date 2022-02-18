<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">


<%@ include file="../template/left.jsp" %>
<%@ include file="../template/top.jsp" %>
<style>
.label{
  font-size: 0.8571em;
  margin-top: 11px;
  margin-left: 3px;
  margin-right: 3px;
  color: #9A9A9A;
}

/* .col-md-5-2 {
    -ms-flex: 0 0 41.666667%;
    flex: 0 0 48;
    max-width: 48%;
} */

</style>
<body class="">
  <div class="wrapper ">
    <div class="main-panel">
      <div class="content">
        <div class="row">
          <div class="col-md-4">
            <div class="card card-user">
              <div class="image">
                <%-- <img src="../assets/img/damir-bosnjak.jpg" alt="..."> --%>
              </div>
              <div class="card-body">
                <div class="author">
                  <a href="#">
                    <img class="avatar border-gray" src="../assets/img/choonsik_logo.png" alt="...">
                    <h5 class="title">${userSession.username}</h5>
                  </a>
                  <p class="description">
                    @${userSession.userid}
                  </p>
                </div>
              
              </div>

            </div>
            <div class="card">
              <div class="card-header">
                <h4 class="card-title">구독 정보</h4>
              </div>
              <div class="card-body">
                 <div class="button-container">
                  <div class="row">
                    <div class="col-6">
                      <h6> 구매 상품 <br><small>Pro + </small></h6>
                    </div>
                   
                   
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-md-8">
            <div class="card card-user">
              <div class="card-header">
                <h5 class="card-title">기본 정보</h5>
              </div>
              <div class="card-body">
                <form>
                  <div class="row">
                    <%-- <div class="col-md-5 pr-1">
                      <div class="form-group">
                        <label>회사 (disabled)</label>
                        <input type="text" class="form-control" disabled="" placeholder="Company" value="Creative Code Inc.">
                      </div>
                    </div>
                    <div class="col-md-3 px-1">
                      <div class="form-group">
                        <label>Username</label>
                        <input type="text" class="form-control" placeholder="Username" value="michael23">
                      </div>
                    </div>
                    <div class="col-md-4 pl-1">
                      <div class="form-group">
                        <label for="exampleInputEmail1">Email address</label>
                        <input type="email" class="form-control" placeholder="Email">
                      </div>
                    </div> --%>
                  </div>
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label>아이디</label>
                        <input type="text" class="form-control" placeholder="" disabled="" value="${userSession.userid}">
                      </div>
                    </div>
                  </div>
                  
                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label>이름</label>
                        <input type="text" class="form-control" placeholder="" value="${userSession.username}">
                      </div>
                    </div>
                  </div>

                  <div class="row">
                    <%-- <label>이메일 </label> --%>

                    <div class="col-md-6 pr-1">
                      <div class="form-group">
                        <input type="text" class="form-control" placeholder="test" value="">
                      </div>
                    </div>
                    <p class="label"> @ </p>
                    <div class="col-md-5 pl-1">
                      <div class="form-group">
                        <input type="" class="form-control" placeholder="example.com" value="">
                      </div>
                    </div>
                  </div>
                  <%-- <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label>아이디</label>
                        <input type="text" class="form-control" placeholder="Home Address" value="Melbourne, Australia">
                      </div>
                    </div>
                  </div> --%>
                  <%-- <div class="row">
                    <div class="col-md-4 pr-1">
                      <div class="form-group">
                        <label>City</label>
                        <input type="text" class="form-control" placeholder="City" value="Melbourne">
                      </div>
                    </div>
                    <div class="col-md-4 px-1">
                      <div class="form-group">
                        <label>Country</label>
                        <input type="text" class="form-control" placeholder="Country" value="Australia">
                      </div>
                    </div>
                    <div class="col-md-4 pl-1">
                      <div class="form-group">
                        <label>Postal Code</label>
                        <input type="number" class="form-control" placeholder="ZIP Code">
                      </div>
                    </div>
                  </div> --%>
                  <%-- <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label>About Me</label>
                        <textarea class="form-control textarea">Oh so, your weak rhyme You doubt I'll bother, reading into it</textarea>
                      </div>
                    </div>
                  </div> --%>
                  <div class="row">
                    <div class="update ml-auto mr-auto">
                      <button type="submit" class="btn btn-primary btn-round"> 수정하기</button>
                    </div>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
<%@ include file="../template/footer.jsp" %>

    </div>
  </div>
  
</body>

</html>