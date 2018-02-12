<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layouts/admin/navbarHeader.jsp" />
<c:import url="../layouts/admin/index/searchbar.jsp" />
<c:import url="../layouts/admin/navbarFooter.jsp" />

<c:set var="manufacturerName" value="" />
<c:set var="visibility" value="yes" />

<c:if test="${ not empty addNewManufacturerStatus }">
    <c:choose>
        <c:when test='${ addNewManufacturerStatus eq "success" }'>
            <div class="alert alert-success offset-4 col-4">Manufacturer has been added.</div>
        </c:when>
        <c:when test='${ addNewManufacturerStatus eq "failure" }'>
            <c:set var="manufacturerName" value="${ param.manufacturerName }" />
            <c:set var="visibility" value="${ param.visibility }" />
            <div class="alert alert-danger offset-4 col-4">Manufacturer was not added.
                <c:if test="${ not empty duplicateManufacturerName }">
                    <br>Manufacturer name already exist.
                </c:if>
            </div>
        </c:when>
    </c:choose>
</c:if>

<form action='<c:url value="/admin/add/new/manufacturer" />' method="post">
    <fieldset class="form-group">
        <legend class="text-center text-success">Add New Manufacturer</legend>
        <div class="form-group row">
            <label class="form-control-label col-4 col-form-label text-right" for="manufacturerName">Manufacturer Name</label>
            <div class="col-4">
                <input class="form-control" type="text" name="manufacturerName" value="${ manufacturerName }" id="manufacturerName" placeholder="Manufacturer Name" aria-describedby="manufacturerNameHelp" maxlength="50" required autofocus>
                <small id="manufacturerNameHelp" class="form-text text-muted">At max 50 character long.</small>
            </div>
        </div>
        <div class="form-group row">
            <label for="visibility" class="form-control-label col-4 col-form-label text-right">Visibility</label>
            <div class="col-4">
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="visibility" id="visibilityYes" value="yes" <c:if test='${ visibility eq "yes" }'>checked</c:if>>
                    <label class="form-check-label" for="visibilityYes">Yes</label>
                </div>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" type="radio" name="visibility" id="visibilityNo" value="no" <c:if test='${ visibility eq "no" }'>checked</c:if>>
                    <label class="form-check-label" for="visibilityNo">No</label>
                </div>
            </div>
        </div>
        <div class="form-group row">
            <div class="offset-sm-4 col-4">
                <input class="btn btn-block btn-outline-success" type="submit" name="submit" value="Add New Manufacturer">
            </div>
        </div>
    </fieldset>
</form>

<c:import url="../layouts/admin/footer.jsp" />