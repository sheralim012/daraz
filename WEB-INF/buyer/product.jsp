<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layouts/buyer/navbarHeader.jsp" />
<c:import url="../layouts/buyer/index/searchbar.jsp" />
<c:import url="../layouts/buyer/navbarFooter.jsp" />

<c:choose>
    <c:when test="${ not empty product }">
        <h2 class="text-center text-success mb-5">Product</h2>
        <div class="card text-center mb-5 mx-auto" style="max-width: 30rem;">
            <img class="card-img-top" src="${ product.imageURL }" alt="${ product.productName }">
            <div class="card-body">
                <h5 class="card-title"><a class="card-link text-success" href='<c:url value="/buyer/product?id=${ product.id }" />'>${ product.productName }</a></h5>
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
        <c:if test="${ not empty postReviewStatus }">
            <c:choose>
                <c:when test="${ postReviewStatus eq 'success' }">
                    <h2 class="text-center text-success mb-5">Review has been posted.</h2>
                </c:when>
                <c:otherwise>
                    <h2 class="text-center text-danger mb-5">Review was not posted.</h2>
                </c:otherwise>
            </c:choose>
        </c:if>
        <c:if test="${ canReview eq 'yes' }">
            <form action='<c:url value="/buyer/product?id=${ product.id }" />' method="post">
                <fieldset class="form-group">
                    <legend class="text-center text-success">Post Review</legend>
                    <div class="form-group row">
                        <label for="rating" class="form-control-label col-4 col-form-label text-right">Rating</label>
                        <div class="col-4">
                            <input class="form-control" type="number" name="rating" value="" id="rating" placeholder="Rating" min="1" max="5" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="message" class="form-control-label col-4 col-form-label text-right">Message</label>
                        <div class="col-4">
                            <textarea name="message" class="form-control" id="message" rows="3" placeholder="Message" aria-describedby="messageHelp" maxlength="255" required>${ message }</textarea>
                            <small id="messageHelp" class="form-text text-muted">At max 255 character long.</small>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="offset-sm-4 col-4">
                            <input class="btn btn-block btn-outline-success" type="submit" name="submit" value="Post Review">
                        </div>
                    </div>
                </fieldset>
            </form>
        </c:if>
    </c:when>
    <c:otherwise>
        <h2 class="text-center text-success mb-5">No Product Was Found.</h2>
    </c:otherwise>
</c:choose>

<c:import url="../layouts/buyer/footer.jsp" />