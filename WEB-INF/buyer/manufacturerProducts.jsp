<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layouts/buyer/navbarHeader.jsp" />
<c:import url="../layouts/buyer/manufacturer/searchbar.jsp" />
<c:import url="../layouts/buyer/navbarFooter.jsp" />

<c:choose>
    <c:when test="${ not empty products }">
        <c:forEach items="${ manufacturers }" var="manufacturer">
            <c:if test="${ manufacturer.id eq id }"><h2 class="text-center text-success mb-5">${ manufacturer.manufacturerName }</h2></c:if>
        </c:forEach>
        <div class="card-deck">
            <c:forEach items="${ products }" var="product">
                <div class="card text-center mb-5" style="max-width: 18rem;">
                    <img class="card-img-top" src="${ product.imageURL }" alt="${ product.productName }">
                    <div class="card-body">
                        <h5 class="card-title"><a class="card-link text-success" href='<c:url value="/buyer/product?id=${ product.id }" />'>${ product.productName }</a></h5>
                        <h6 class="card-subtitle mb-2 text-muted">$ ${ product.price }</h6>
                        <p class="card-text">${ product.description }</p>
                    </div>
                    <div class="card-footer">
                        <c:set var="inCart" value="no" />
                        <c:forEach items="${ productIds }" var="productId">
                            <c:if test="${ productId eq product.id }">
                                <c:set var="inCart" value="yes" />
                            </c:if>
                        </c:forEach>
                        <c:choose>
                            <c:when test="${ inCart eq 'yes' }">
                                <button id="${ product.id }" class="btn btn-outline-warning inCart">In Cart</button>
                            </c:when>
                            <c:otherwise>
                                <button id="${ product.id }" class="btn btn-outline-success addToCart">Add to Cart</button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>
        </div>

        <nav class="text-center" aria-label="pagination">
            <ul class="pagination justify-content-center">
                <c:if test="${ totalPages gt 1 }">
                    <c:if test="${ currentPage - 1 ge 1 }">
                        <li class="page-item"><a class="page-link" href='<c:url value="/buyer/products/manufacturer?id=${ id }&currentPage=${ currentPage - 1 }&query=${ query }" />'>Previous</a></li>
                    </c:if>
                    <c:forEach var="i" begin="1" end="${ totalPages }">
                        <c:choose>
                            <c:when test="${ i eq currentPage }">
                                <li class="page-item active"><a class="page-link" href='<c:url value="/buyer/products/manufacturer?id=${ id }&currentPage=${ i }&query=${ query }" />'>${ i }</a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href='<c:url value="/buyer/products/manufacturer?id=${ id }&currentPage=${ i }&query=${ query }" />'>${ i }</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${ (currentPage + 1) le totalPages }">
                        <li class="page-item"><a class="page-link" href='<c:url value="/buyer/products/manufacturer?id=${ id }&currentPage=${ currentPage + 1 }&query=${ query }" />'>Next</a></li>
                    </c:if>
                </c:if>
            </ul>
        </nav>
    </c:when>
    <c:otherwise>
        <h2 class="text-center text-danger mb-5">No Product was Found.</h2>
    </c:otherwise>
</c:choose>

<c:import url="../layouts/buyer/footer.jsp" />