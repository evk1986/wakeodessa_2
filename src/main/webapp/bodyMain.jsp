<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
    <!-- Bootstrap -->
   <%-- <link href="${pageContext.request.contextPath}/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/bootstrap/css/4-col-portfolio.css" rel="stylesheet">--%>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<!-- universal navbar  page -->
<%--	<%@ include file="navbar.jsp"%>--%>

<div class="container">
    <!-- page Heading -->
    <div style="margin-top: 30px;" class="row">
        <div class="col-lg-12">
            <h1 class="page-header">
                Bookshop
                <small>step academy java#1</small>
            </h1>
        </div>

        <!--End of page heading   -->

        <!-- /.row -->
        <!-- Projects Row -->
        <div class="row">
            <c:forEach items="${books}" var="book">
                <div class="col-md-3 product-sm-container"
                     style="display: table-cell">
                    <div class="row" style="display: table-cell">
                        <div class="col-md-2 pull-left">
                            <img class="img-rounded pull-left" src="<c:url value = "cover/small/${book.product_id}"/>">
                        </div>
                        <div class="col-md-2 pull-right">
                            <form action="" method="" product_id="1">
                                <input style="width: 30px;" type="form-control input-sm"
                                       onclick="alert('sdfsdf')" value="1"> <input
                                    type="button" onclick="alert('sdfsdf')" value="Купить">
                            </form>
                            <span><em>Price:</em></span> <span> <strong><em
                                class="price">"${book.price}"</em> </strong></span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <!-- Pagination -->
        <div class="row text-center">
            <div class="col-lg-12">
                <%--	<c:url var="firstUrl" value="/1" />--%>
                <c:url var="lastUrl" value="/${page.totalPages}"/>
                <c:url var="prevUrl" value="/${currentIndex - 1}"/>
                <c:url var="nextUrl" value="/${currentIndex + 1}"/>
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${currentIndex == 1}">
                            <li class="disabled"><a href="#">&lt;&lt;</a></li>
                            <li class="disabled"><a href="#">&lt;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${firstUrl}">&lt;&lt;</a></li>
                            <li><a href="${prevUrl}">&lt;</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                        <c:url var="pageUrl" value="/${i}"/>
                        <c:choose>
                            <c:when test="${i == currentIndex}">
                                <li class="active"><a href="${pageUrl}"><c:out value="${i}"/></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${pageUrl}"><c:out value="${i}"/></a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${currentIndex == page.totalPages}">
                            <li class="disabled"><a href="#">&gt;</a></li>
                            <li class="disabled"><a href="#">&gt;&gt;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${nextUrl}">&gt;</a></li>
                            <li><a href="${lastUrl}">&gt;&gt;</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
        <!-- /.row -->

    </div>
</div>
<!-- jQuery -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</body>
</html>