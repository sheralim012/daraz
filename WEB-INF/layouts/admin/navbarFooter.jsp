<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Menu</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href='<c:url value="/admin/edit/profile" />'>Edit Profile</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href='<c:url value="/admin/add/new/admin" />'>Add New Admin</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href='<c:url value="/admin/add/new/manufacturer" />'>Add New Manufacturer</a>
                            <a class="dropdown-item" href='<c:url value="/admin/manage/manufacturers" />'>Manage Manufacturers</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href='<c:url value="/admin/add/new/category" />'>Add New Category</a>
                            <a class="dropdown-item" href='<c:url value="/admin/manage/categories" />'>Manage Categories</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href='<c:url value="/admin/add/new/product" />'>Add New Product</a>
                            <a class="dropdown-item" href='<c:url value="/admin/manage/products" />'>Manage Products</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href='<c:url value="/admin/orders" />'>View Orders</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href='<c:url value="/admin/edit/profile" />'>Hello! ${ sessionScope.username }</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href='<c:url value="/public/logout" />'>LogOut</a>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container mt-5">