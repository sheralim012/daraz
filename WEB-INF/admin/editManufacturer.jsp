<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layouts/admin/navbarHeader.jsp" />
<c:import url="../layouts/admin/index/searchbar.jsp" />
<c:import url="../layouts/admin/navbarFooter.jsp" />

<c:set var="id" value="${ manufacturer.id }" />
<c:set var="manufacturerName" value="${ manufacturer.manufacturerName }" />
<c:set var="visibility" value="${ manufacturer.visibility }" />

<c:if test="${ not empty editManufacturerStatus }">
    <c:choose>
        <c:when test='${ editManufacturerStatus eq "success" }'>
            <div class="alert alert-success offset-4 col-4">Manufacturer has been updated.</div>
        </c:when>
        <c:when test='${ editManufacturerStatus eq "failure" }'>
            <div class="alert alert-danger offset-4 col-4">Manufacturer was not updated.
                <c:if test="${ not empty duplicateManufacturerName }">
                    <br>Manufacturer name already exist.
                </c:if>
            </div>
        </c:when>
    </c:choose>
</c:if>

<form action='<c:url value="/admin/edit/manufacturer?id=${ id }" />' method="post">
    <fieldset class="form-group">
        <legend class="text-center text-success">Edit Manufacturer</legend>
        <div class="form-group row">
            <label class="form-control-label col-4 col-form-label text-right" for="manufacturerName">Manufacturer Name</label>
            <div class="col-4">
                <input class="form-control" type="text" name="manufacturerName" value="${ manufacturerName }" id="manufacturerName" placeholder="Manufacturer Name" aria-describedby="manufacturerNameHelp" maxlength="25" required autofocus>
                <small id="manufacturerNameHelp" class="form-text text-muted">At max 25 character long.</small>
            </div>
        </div>
        <div class="form-group row">
            <label for="visibility" class="form-control-label col-4 col-form-label text-right">Visibility</label>
            <div class="col-4">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="visibility" id="visibilityYes" value="yes" <c:if test="${ visibility eq 1}">checked</c:if>>
                    <label class="form-check-label" for="visibilityYes">Yes</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="visibility" id="visibilityNo" value="no" <c:if test="${ visibility eq 0}">checked</c:if>>
                    <label class="form-check-label" for="visibilityNo">No</label>
                </div>
            </div>
        </div>
        <div class="form-group row">
            <div class="offset-sm-4 col-4">
                <input class="btn btn-block btn-outline-success" type="submit" name="submit" value="Edit Manufacturer">
            </div>
        </div>
    </fieldset>
</form>

<c:import url="../layouts/admin/footer.jsp" />