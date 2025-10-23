<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Editar Empleado</title>
</head>
<body>
<h1>Buscar Empleado</h1>
<c:if test="${not empty mensaje}">
  <p>${mensaje}</p>
</c:if>


<form action="empleados" method="post">
  <input type="hidden" name="opcion" value="buscarEmpleado">

  <table border="1">
    <tr>
      <td>Criterio de búsqueda:</td>
      <td>
        <select name="criterio">
          <option value="dni">DNI</option>
          <option value="nombre">Nombre</option>
          <option value="sexo">Sexo</option>
          <option value="categoria">Categoría</option>
          <option value="anyos">Años trabajados</option>
        </select>
      </td>
    </tr>
    <tr>
      <td>Valor:</td>
      <td><input type="text" name="valor" size="40"></td>
    </tr>
  </table>

  <input type="submit" value="Buscar">
</form>
<form action="empleados" method="post">
  <input type="submit" name="opcion" value="Volver">
</form>

</body>
</html>
