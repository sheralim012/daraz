<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layouts/admin/navbarHeader.jsp" />
<c:import url="../layouts/admin/index/searchbar.jsp" />
<c:import url="../layouts/admin/navbarFooter.jsp" />

<c:choose>
    <c:when test="${ not empty products }">
        <h2 class="text-center text-success mb-5">Products</h2>
        <div class="card-deck">
            <c:forEach items="${ products }" var="product">
                <div class="card text-center mb-5" style="max-width: 18rem;">
                    <img class="card-img-top" src="${ product.imageURL }" alt="${ product.productName }">
                    <div class="card-body">
                        <h5 class="card-title"><a class="card-link text-success" href='<c:url value="/admin/product?id=${ product.id }" />'>${ product.productName }</a></h5>
                        <p class="card-text">${ product.description }</p>
                    </div>
                    <div class="card-footer">
                        <a href='<c:url value="/admin/edit/product?id=${ product.id }" />' class="card-link btn btn-outline-success">Edit</a>
                    </div>
                </div>
            </c:forEach>
        </div>

        <nav class="text-center" aria-label="pagination">
            <ul class="pagination justify-content-center">
                <c:if test="${ totalPages gt 1 }">
                    <c:if test="${ currentPage - 1 ge 1 }">
                        <li class="page-item"><a class="page-link" href='<c:url value="/admin/index?currentPage=${ currentPage - 1 }&query=${ query }" />'>Previous</a></li>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${ totalPages }">
                        <c:choose>
                            <c:when test="${ i eq currentPage }">
                                <li class="page-item active"><a class="page-link" href='<c:url value="/admin/index?currentPage=${ i }&query=${ query }" />'>${ i }</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href='<c:url value="/admin/index?currentPage=${ i }&query=${ query }" />'>${ i }</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${ (currentPage + 1) le totalPages }">
                        <li class="page-item"><a class="page-link" href='<c:url value="/admin/index?currentPage=${ currentPage + 1 }&query=${ query }" />'>Next</a></li>
                    </c:if>
                </c:if>
            </ul>
        </nav>
    </c:when>
    <c:otherwise>
        <h2 class="text-center text-danger mb-5">No Product was Found.</h2>
    </c:otherwise>
</c:choose>

<c:import url="../layouts/admin/footer.jsp" />