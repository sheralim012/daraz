<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layouts/buyer/navbarHeader.jsp" />
<c:import url="../layouts/buyer/index/searchbar.jsp" />
<c:import url="../layouts/buyer/navbarFooter.jsp" />

<form action='<c:url value="/buyer/orders" />' method="post" class="form-inline mb-5">
    <label class="sr-only" for="orderId">Order #</label>
    <select class="form-control mr-2" id="buyerOrderId" name="buyerOrderId">
        <option value="0" selected>Order #</option>
        <c:forEach items="${ buyerOrders }" var="buyerOrder">
            <option value="${ buyerOrder.id }">${ buyerOrder.id }</option>
        </c:forEach>
    </select>
    <label class="sr-only" for="manufacturerId">Manufacturer</label>
    <select class="form-control mr-2" id="manufacturerId" name="manufacturerId">
        <option value="0" selected>Manufacturer</option>
        <c:forEach items="${ manufacturers }" var="manufacturer">
            <option value="${ manufacturer.id }">${ manufacturer.manufacturerName }</option>
        </c:forEach>
    </select>
    <label class="sr-only" for="categoryId">Category</label>
    <select class="form-control mr-2" id="categoryId" name="categoryId">
        <option value="0" selected>Category</option>
        <c:forEach items="${ categories }" var="category">
            <option value="${ category.id }">${ category.categoryName }</option>
        </c:forEach>
    </select>
    <label class="sr-only" for="status">Status</label>
    <select class="form-control mr-2" id="status" name="status">
        <option value="0" selected>Status</option>
        <option value="1">Pending</option>
        <option value="2">Accepted</option>
        <option value="3">Rejected</option>
        <option value="4">Delivered</option>
    </select>
    <button type="submit" class="btn btn-outline-success">Submit</button>
</form>

<c:if test="${ not empty orders }">
    <table class="table table-striped table-bordered text-center">
        <thead class="bg-success" style="color: #FFF;">
            <tr>
                <th class="align-middle">Order #</th>
                <th class="align-middle">Date</th>
                <th class="align-middle">Product</th>
                <th class="align-middle">Quantity</th>
                <th class="align-middle">Manufacturer</th>
                <th class="align-middle">Category</th>
                <th class="align-middle">Status</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${ orders }" var="order">
                <tr>
                    <td class="align-middle">${ order.buyerOrderId }</td>
                    <td class="align-middle">${ order.orderDate }</td>
                    <td class="align-middle">${ order.productName }</td>
                    <td class="align-middle">${ order.orderQuantity }</td>
                    <td class="align-middle">${ order.manufacturerName }</td>
                    <td class="align-middle">${ order.categoryName }</td>
                    <td class="align-middle">
                        <c:choose>
                            <c:when test="${ order.status eq 1 }">
                                <button class="btn btn-outline-primary">Pending</button>
                            </c:when>
                            <c:when test="${ order.status eq 2 }">
                                <button class="btn btn-outline-success">Accepted</button>
                            </c:when>
                                <c:when test="${ order.status eq 3 }">
                                <button class="btn btn-outline-danger">Rejected</button>
                            </c:when>
                                <c:when test="${ order.status eq 4 }">
                                <button class="btn btn-outline-warning">Delivered</button>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

<c:import url="../layouts/buyer/footer.jsp" />