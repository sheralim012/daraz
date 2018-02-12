<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layouts/admin/navbarHeader.jsp" />
<c:import url="../layouts/admin/index/searchbar.jsp" />
<c:import url="../layouts/admin/navbarFooter.jsp" />

<c:if test="${ not empty orderProcessStatus }">
    <c:choose>
        <c:when test="${ orderProcessStatus eq 'success' }">
            <h2 class="text-center text-success mb-5">Order has been Processed.</h2>
        </c:when>
        <c:otherwise>
            <h2 class="text-center text-danger mb-5">Order was not Processed.</h2>
        </c:otherwise>
    </c:choose>
</c:if>

<form action='<c:url value="/admin/orders" />' method="post" class="form-inline mb-5">
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
                <th class="align-middle">Order Quantity</th>
                <th class="align-middle">Stock</th>
                <th class="align-middle">Buyer</th>
                <th class="align-middle">Email</th>
                <th class="align-middle">Contact No.</th>
                <th class="align-middle">Address</th>
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
                    <td class="align-middle">${ order.productQuantity }</td>
                    <td class="align-middle">${ order.buyerName }</td>
                    <td class="align-middle">${ order.buyerEmail }</td>
                    <td class="align-middle">${ order.buyerContactNo }</td>
                    <td class="align-middle">${ order.buyerAddress }</td>
                    <td class="align-middle">
                        <c:choose>
                            <c:when test="${ order.status eq 1 }">
                                <a href='<c:url value="/admin/order/process?id=${ order.orderDetailId }&status=2" />' class="btn btn-outline-primary mb-1">Accept</a>
                                <a href='<c:url value="/admin/order/process?id=${ order.orderDetailId }&status=3" />' class="btn btn-outline-danger">Reject</a>
                            </c:when>
                            <c:when test="${ order.status eq 2 }">
                                <a href='<c:url value="/admin/order/process?id=${ order.orderDetailId }&status=4" />' class="btn btn-outline-success">Deliver</a>
                            </c:when>
                                <c:when test="${ order.status eq 3 }">
                                <a class="btn btn-outline-danger">Rejected</a>
                            </c:when>
                                <c:when test="${ order.status eq 4 }">
                                <a class="btn btn-outline-warning">Delivered</a>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</c:if>

<c:import url="../layouts/admin/footer.jsp" />