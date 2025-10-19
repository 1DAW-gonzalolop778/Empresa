<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Listar Nominas Empleados</title>
</head>
<body>
<h1>Listar Nominas</h1>
<table border="1">
    <tr>
        <td>Dni</td>
        <td>Nomina</td>
    </tr>
    <c:forEach var="empleado" items="${lista}">
        <tr>
            <td>
                <a href="productos?opcion=meditar&id=<c:out value="${ empleado.dni}"></c:out>">
                    <c:out value="${ empleado.dni}"></c:out>
                </a>
            </td>
            <td><c:out value="${ empleado.nombre}"></c:out></td>
            <td>
                <a href="productos?opcion=eliminar&id=<c:out value="${ empleado.dni}"></c:out>">
                    Eliminar
                </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>