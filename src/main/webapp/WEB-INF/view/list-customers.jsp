<%--<%@ taglib prefix="for" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="com.vaer.springdemo.util.SortUtil" %>
<html>
    <head>
        <title>List-Customers</title>
        <!-- here we linc css files to page -->
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>
        <!--
             this type of link don`t work at this project:
            <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
         !-->

    </head>
    <body>
        <h1>WELCOME to List-Customers page!!!</h1>

        <div id="wrapper">
            <div id="header">
                <h3>CRM - Customer Relationship Manager</h3>
            </div>
        </div>

        <div id="container">
            <div id="content">
                <!-- add button here -->
                <input type="button" value="ADD CUSTOMER" onclick="window.location.href='showFormForAdd'" class="add-button">

                <!-- add search form -->
                <form:form action="search" method="get">
                    Search customer: <input type="text" name="theSearchName">
                    <input type="submit" value="SEARCH" class="add-button">
                </form:form>

                <!-- add table here -->
                <table>

                    <!-- construct a sort link for first name -->
                    <c:url var="sortLinkFirstName" value="/customer/list">
                        <c:param name="sort" value="<%= Integer.toString(SortUtil.FIRST_NAME) %>" />
                    </c:url>

                    <!-- construct a sort link for last name -->
                    <c:url var="sortLinkLastName" value="/customer/list">
                        <c:param name="sort" value="<%= Integer.toString(SortUtil.LAST_NAME) %>" />
                    </c:url>

                    <!-- construct a sort link for email -->
                    <c:url var="sortLinkEmail" value="/customer/list">
                        <c:param name="sort" value="<%= Integer.toString(SortUtil.EMAIL) %>" />
                    </c:url>

                    <tr>
                        <th><a href="${sortLinkFirstName}" class="aHead">First Name*</a></th>
                        <th><a href="${sortLinkLastName}" class="aHead">Last Name*</a></th>
                        <th><a href="${sortLinkEmail}" class="aHead">Email*</a></th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="customer" items="${theCustomers}">

                        <!-- create update link with customer id -->
                        <c:url var="updateLink" value="/customer/showFormForUpdate">
                            <c:param name="customerId" value="${customer.id}"/>
                        </c:url>

                        <!-- create delete link with customer id -->
                        <c:url var="deleteLink" value="/customer/delete">
                            <c:param name="customerId" value="${customer.id}"/>
                        </c:url>

                        <!-- print customer data -->
                        <tr>
                            <td>${customer.firstName}</td>
                            <td>${customer.lastName}</td>
                            <td>${customer.email}</td>

                            <td>
                                <!-- display update link -->
                                <a href="${updateLink}">UPDATE</a>
                                |
                                <!-- display delete link -->
                                <a href="${deleteLink}"
                                   onclick="if (!(confirm('Are You sure You want to DELETE this customer?'))) return false ">DELETE</a> <!-- this is JS code: when click this link in browser show context menu with message -->
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <p>* - click me if you want to have sorted data :)</p>
    </body>
</html>


