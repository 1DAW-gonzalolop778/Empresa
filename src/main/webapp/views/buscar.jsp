<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Buscar Nomina</title>
</head>
<body>
<h1>Buscar Nomina</h1>
<form action="nominas" method="post">
    <input type="hidden" name="opcion" value="buscarNom">
    <table border="1">
        <tr>
            <td>DNI:</td>
            <td><input type="text" name="dni" size="50"></td>
        </tr>
    </table>
    <input type="submit" value="buscarNom">
</form>
</body>
</html>
