<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layouts/admin/navbarHeader.jsp" />
<c:import url="../layouts/admin/index/searchbar.jsp" />
<c:import url="../layouts/admin/navbarFooter.jsp" />

<h2 class="text-center text-success mb-5">Categories</h2>

<table class="table table-striped table-bordered text-center">
    <thead class="bg-success" style="color: #FFF;">
        <tr>
            <th class="align-middle">Category Name</th>
            <th class="align-middle">Visibility</th>
            <th class="align-middle">Created Date</th>
            <th class="align-middle">Modified Date</th>
            <th class="align-middle">Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${ limitedCategories }" var="limitedCategory">
            <tr>
                <td class="align-middle">${ limitedCategory.categoryName }</td>
                <td class="align-middle">
                <c:choose>
                    <c:when test="${ limitedCategory.visibility eq 1 }">Yes</c:when>
                    <c:otherwise>No</c:otherwise>
                </c:choose>
                </td>
                <td class="align-middle">${ limitedCategory.createdDate }</td>
                <td class="align-middle">${ limitedCategory.modifiedDate }</td>
                <td class="align-middle"><a class="btn btn-outline-success" href='<c:url value="/admin/edit/category?id=${ limitedCategory.id }" />'>Edit</a></td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<nav class="text-center" aria-label="pagination">
    <ul class="pagination justify-content-center">
        <c:if test="${ totalPages gt 1 }">
            <c:if test="${ currentPage - 1 ge 1 }">
                <li class="page-item"><a class="page-link" href='<c:url value="/admin/manage/categories?currentPage=${ currentPage - 1 }" />'>Previous</a></li>
            </c:if>
            <c:forEach var="i" begin="1" end="${ totalPages }">
                <c:choose>
                    <c:when test="${ i eq currentPage }">
                        <li class="page-item active"><a class="page-link" href='<c:url value="/admin/manage/categories?currentPage=${ i }" />'>${ i }</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item"><a class="page-link" href='<c:url value="/admin/manage/categories?currentPage=${ i }" />'>${ i }</a></li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
            <c:if test="${ (currentPage + 1) le totalPages }">
                <li class="page-item"><a class="page-link" href='<c:url value="/admin/manage/categories?currentPage=${ currentPage + 1 }" />'>Next</a></li>
            </c:if>
        </c:if>
    </ul>
</nav>

<c:import url="../layouts/admin/footer.jsp" />