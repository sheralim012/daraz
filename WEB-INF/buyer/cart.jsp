<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layouts/buyer/navbarHeader.jsp" />
<c:import url="../layouts/buyer/index/searchbar.jsp" />
<c:import url="../layouts/buyer/navbarFooter.jsp" />

<c:choose>
    <c:when test='${ not empty buyerOrderStatus and buyerOrderStatus eq "success" }'>
        <h2 class="text-center text-success mb-5">Order has been Placed.</h2>
    </c:when>
    <c:otherwise>
        <c:if test='${ not empty buyerOrderStatus and buyerOrderStatus eq "failure" }'>
            <h2 class="text-center text-danger mb-5">Order was not Placed.</h2>
        </c:if>
        <c:choose>
            <c:when test="${ not empty productIds and not empty products }">
                <form action='<c:url value="/buyer/cart" />' method="post">
                    <table class="table table-striped table-bordered mb-5 text-center">
                        <thead class="bg-success" style="color: #FFF;">
                            <tr>
                                <th class="align-middle">Image</th>
                                <th class="align-middle">Name</th>
                                <th class="align-middle">Price</th>
                                <th class="align-middle">Quantity</th>
                                <th class="align-middle">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${ productIds }" var="productId">
                                <c:forEach items="${ products }" var="product">
                                    <c:if test="${ productId eq product.id }">
                                        <tr>
                                            <td class="align-middle"><img src="${ product.imageURL }" alt="${ product.productName }" width="200px" height="200px"></td>
                                            <td class="align-middle">${ product.productName }</td>
                                            <td class="align-middle">${ product.price }</td>
                                            <td class="align-middle"><input type="number" name="${ product.id }" min="1" required></td>
                                            <td class="align-middle"><button id="${ product.id }" class="btn btn-outline-warning inCart">In Cart</button></td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="text-center mb-5"><input type="submit" name="submit" value="Place Order" class="btn btn-outline-success"></div>
                </form>
            </c:when>
            <c:otherwise>
                <h2 class="text-center text-danger mb-5">Cart is Empty</h2>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>

<c:import url="../layouts/buyer/footer.jsp" />