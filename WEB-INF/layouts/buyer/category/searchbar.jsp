<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form action='<c:url value="/buyer/products/category?query=${ query }" />' method="get" class="form-inline my-2 my-lg-0">
    <input type="hidden" name="id" value="${ id }">
    <input class="form-control mr-sm-2" type="search" name="query" value="${ query }" placeholder="Search" aria-label="Search">
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
</form>