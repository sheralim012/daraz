<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layouts/admin/navbarHeader.jsp" />
<c:import url="../layouts/admin/index/searchbar.jsp" />
<c:import url="../layouts/admin/navbarFooter.jsp" />

<c:choose>
    <c:when test="${ not empty product }">
        <h2 class="text-center text-success mb-5">Product</h2>
        <div class="card text-center mb-5 mx-auto" style="max-width: 30rem;">
            <img class="card-img-top" src="${ product.imageURL }" alt="${ product.productName }">
            <div class="card-body">
                <h5 class="card-title"><a class="card-link text-success" href='<c:url value="/admin/product?id=${ product.id }" />'>${ product.productName }</a></h5>
                <h6 class="card-subtitle mb-2 text-muted">$ ${ product.price }</h6>
                <c:forEach items="${ manufacturers }" var="manufacturer">
                    <c:if test="${ manufacturer.id eq product.manufacturerId }">
                        <h6 class="card-subtitle mb-2 text-muted">${ manufacturer.manufacturerName }</h6>
                    </c:if>
                </c:forEach>
                <c:forEach items="${ categories }" var="category">
                    <c:if test="${ category.id eq product.categoryId }">
                        <h6 class="card-subtitle mb-2 text-muted">${ category.categoryName }</h6>
                    </c:if>
                </c:forEach>        
                <p class="card-text">${ product.description }</p>
            </div>
            <div class="card-footer">
                <a href='<c:url value="/admin/edit/product?id=${ product.id }" />' class="card-link btn btn-outline-success">Edit</a>
            </div>
        </div>
        <c:if test="${ not empty reviewers }" >
            <div class="card mx-auto mb-5" style="width: 30rem;">
                <div class="card-header"><b>Rating (${ rating })</b></div>
                <ul class="list-group list-group-flush">
                    <c:forEach items="${ reviewers }" var="reviewer">
                        <li class="list-group-item"><b>${ reviewer.buyerName }:</b> ${ reviewer.message }</li>
                    </c:forEach>
                </ul>
            </div>
        </c:if>
    </c:when>
    <c:otherwise>
        <h2 class="text-center text-danger mb-5">No Product was Found.</h2>
    </c:otherwise>
</c:choose>

<c:import url="../layouts/admin/footer.jsp" />