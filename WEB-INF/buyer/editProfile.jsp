<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layouts/buyer/navbarHeader.jsp" />
<c:import url="../layouts/buyer/index/searchbar.jsp" />
<c:import url="../layouts/buyer/navbarFooter.jsp" />

<c:set var="username" value="${ user.username }" />
<c:set var="firstName" value="${ buyer.firstName }" />
<c:set var="lastName" value="${ buyer.lastName }" />
<c:set var="email" value="${ buyer.email }" />
<c:set var="contactNo" value="${ buyer.contactNo }" />
<c:set var="street" value="${ address.street }" />
<c:set var="city" value="${ address.city }" />
<c:set var="state" value="${ address.state }" />
<c:set var="country" value="${ address.country }" />
<c:set var="zip" value="${ address.zip }" />

<c:if test="${ not empty editProfileStatus }">
    <c:choose>
        <c:when test='${ editProfileStatus eq "success" }'>
            <div class="alert alert-success offset-4 col-4">Account has been updated.</div>
        </c:when>
        <c:when test='${ editProfileStatus eq "failure" }'>
            <div class="alert alert-danger offset-4 col-4">Account was not updated.
                <c:if test="${ not empty duplicateUsername }">
                    <br>Username already exist.
                </c:if>
            </div>
        </c:when>
    </c:choose>
</c:if>

<form action='<c:url value="/buyer/edit/profile" />' method="post">
    <fieldset class="form-group">
        <legend class="text-center text-success">Edit Profile</legend>
        <div class="form-group row">
            <label class="form-control-label col-4 col-form-label text-right" for="username">Username</label>
            <div class="col-4">
                <input class="form-control" type="text" name="username" value="${ username }" id="username" placeholder="Username" aria-describedby="usernameHelp" maxlength="15" pattern="^[\w]{1,15}$" required autofocus>
                <small id="usernameHelp" class="form-text text-muted">At max 15 character long, no spaces.</small>
            </div><!-- div.col-4 -->
        </div><!-- div.form-group.row -->
        <div class="form-group row">
            <label class="form-control-label col-4 col-form-label text-right" for="password">Password</label>
            <div class="col-4">
                <input class="form-control" type="password" name="password" id="password" placeholder="Password" aria-describedby="passwordHelp" minlength="8" maxlength="15" pattern="^(?=.*\d)(?=.*[~!@#$%^&*()_\-+=|\\{}[\]:;<>?/])(?=.*[A-Z])(?=.*[a-z])\S{8,15}$" required>
                <small id="passwordHelp" class="form-text text-muted">8-15 characters, contain letters, numbers, special characters.</small>
            </div><!-- div.col-4 -->
        </div><!-- div.form-group.row -->
        <div class="form-group row">
            <label for="firstName" class="form-control-label col-4 col-form-label text-right">Name</label>
            <div class="col-2">
                <input class="form-control" type="text" name="firstName" value="${ firstName }" id="firstName" placeholder="First" maxlength="25" required>
            </div><!-- div.col-2 -->
            <div class="col-2">
                <input class="form-control" type="text" name="lastName" value="${ lastName }" id="lastName" placeholder="Last" maxlength="25" required>
            </div><!-- div.col-2 -->
        </div><!-- div.form-group.row -->
        <div class="form-group row">
            <label for="email" class="form-control-label col-4 col-form-label text-right">Email</label>
            <div class="col-4">
                <input class="form-control" type="email" name="email" value="${ email }" id="email" placeholder="Email" maxlength="255" pattern="^[\w.%+\-]+@[\w.\-]+\.[A-Za-z]{2,6}$" required>
            </div><!-- div.col-4 -->
        </div><!-- div.form-group.row -->
        <div class="form-group row">
            <label for="contactNo" class="form-control-label col-4 col-form-label text-right">Contact No.</label>
            <div class="col-4">
                <input class="form-control" type="tel" name="contactNo" value="${ contactNo }" id="contactNo" placeholder="Contact No." maxlength="20" pattern="^[\d]{1,20}$" required>
            </div><!-- div.col-4 -->
        </div><!-- div.form-group.row -->
        <div class="form-group row">
            <label for="street" class="form-control-label col-4 col-form-label text-right">Address</label>
            <div class="col-4 d-block">
                <input class="form-control" type="text" name="street" value="${ street }" id="street" placeholder="Street" maxlength="255" required>
            </div><!-- div.offset-4.col-4.d-block -->
            <div class="offset-4 col-4 d-block">
                <input class="form-control" type="text" name="city" value="${ city }" placeholder="City" maxlength="255" required>
            </div><!-- div.offset-4.col-4.d-block -->
            <div class="offset-4 col-4 d-block">
                <input class="form-control" type="text" name="state" value="${ state }" placeholder="State" maxlength="255" required>
            </div><!-- div.offset-4.col-4.d-block -->
            <div class="offset-4 col-4 d-block">
                <input class="form-control" type="text" name="country" value="${ country }" placeholder="Country" maxlength="255" required>
            </div><!-- div.offset-4.col-4.d-block -->
            <div class="offset-4 col-4 d-block">
                <input class="form-control" type="text" name="zip" value="${ zip }" placeholder="Zip" maxlength="20" required>
            </div><!-- div.offset-4.col-4.d-block -->
        </div><!-- div.form-group.row -->
        <div class="form-group row">
            <div class="offset-4 col-4">
                <input class="btn btn-block btn-outline-success" type="submit" name="submit" value="Edit Profile">
            </div><!-- div.offset-4.col-4 -->
        </div><!-- div.form-group.row -->
    </fieldset>
</form>

<c:import url="../layouts/buyer/footer.jsp" />