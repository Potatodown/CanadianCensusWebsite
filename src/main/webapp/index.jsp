<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Canadian Census Database</title>
</head>
<body style="background: antiquewhite">
<br/>
<%if (request.getAttribute("error") != null){%>
<div class="w3-panel w3-red">
    <p style="color: red"><%=request.getAttribute("error")%></p>
</div>
<%}%>
<div style="text-align: center">
    <h1>Login</h1>
    <form action="<%= request.getContextPath()%>/mainpageServlet" method="get">

        <div>
            <label for="userName">Username:</label>
            <input type="text" id="userName" name="userName">
        </div>
        <div>
            <label for="password">Password: </label>
            <input style="margin-left: 0.2em" type="password" id="password" name="password">
        </div>
<br>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>