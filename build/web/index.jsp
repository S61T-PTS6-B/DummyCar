<%-- 
    Document   : index
    Created on : 8-mrt-2016, 11:48:28
    Author     : Max
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="ControllerServlet" method="POST">
            Url: <input type="text" name="input">
            <input type="submit" value="Start sending">
        </form>
    </body>
</html>
