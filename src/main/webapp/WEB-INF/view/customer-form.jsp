<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
    <head>
        <title>Customer Form</title>
        <!-- her we link css styles for this page -->
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css"/>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/add-customer-style.css"/>
    </head>
    <body>

        <h3>Welcome to Customer Form!</h3>
        <div id="wrapper">
            <div id="header">
                <h2>CRM - Customer Relationship Manager</h2>
            </div>
        </div>

        <div id="container">
            <h3>Save Customer</h3>

            <form:form action="saveCustomer" modelAttribute="theCustomer" method="post">

                <!-- here we associate this data with customer id -->
                <form:hidden path="id"/>

                <table>
                    <tbody>
                        <tr>
                            <td><label>First name:</label></td>
                            <td><form:input path="firstName"/></td>
                        </tr>
                        <tr>
                            <td><label>Last name:</label></td>
                            <td><form:input path="lastName"/></td>
                        </tr>
                        <tr>
                            <td><label>Email:</label></td>
                            <td><form:input path="email"/></td>
                        </tr>
                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" class="save" value="SAVE"></td>
                        </tr>
                    </tbody>
                </table>
            </form:form>

            <p>
                <a href="${pageContext.request.contextPath}/customer/list">Back to list</a>
            </p>
        </div>
    </body>
</html>
