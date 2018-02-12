<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layouts/public/navbarHeader.jsp" />
<c:import url="../layouts/public/index/searchbar.jsp" />
<c:import url="../layouts/public/navbarFooter.jsp" />

<c:set var="username" value="${ param.username }" />
<c:if test="${ not empty logInStatus }">
    <div class="alert alert-danger offset-4 col-4">Username/Password doesn't match.</div>
</c:if>
<form action='<c:url value="/public/login" />' method="post">
    <fieldset class="form-group">
        <legend class="text-center text-success">LogIn</legend>
        <div class="form-group row">
            <label class="form-control-label col-4 col-form-label text-right" for="username">Username</label>
            <div class="col-4">
                <input class="form-control" type="text" name="username" value="${ username }" id="username" placeholder="Username" aria-describedby="usernameHelp" maxlength="15" pattern="^[\w]{1,15}$" required autofocus>
                <small id="usernameHelp" class="form-text text-muted">At max 15 character long, no spaces.</small>
            </div>
        </div>
        <div class="form-group row">
            <label class="form-control-label col-4 col-form-label text-right" for="password">Password</label>
            <div class="col-4">
                <input class="form-control" type="password" name="password" id="password" placeholder="Password" aria-describedby="passwordHelp" minlength="8" maxlength="15" pattern="^(?=.*\d)(?=.*[~!@#$%^&*()_\-+=|\\{}[\]:;<>?/])(?=.*[A-Z])(?=.*[a-z])\S{8,15}$" required>
                <small id="passwordHelp" class="form-text text-muted">8-15 characters, contain letters, numbers, special characters.</small>
            </div>
        </div>
        <div class="form-group row">
            <div class="offset-sm-4 col-4">
                <input class="btn btn-block btn-outline-success" type="submit" name="submit" value="LogIn">
            </div>
        </div>
    </fieldset>
</form>

<c:import url="../layouts/public/footer.jsp" />