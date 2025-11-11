package com.aprendec.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aprendec.dao.DAOFactory;
import com.aprendec.dao.EmpleadoDAO;
import com.aprendec.model.Empleado;
import com.aprendec.dao.NominaDAO;
import com.aprendec.model.Nomina;

/**
 * Servlet implementation class EmpleadoController
 */

//He pasado de tener dos controller a solo uno:
//He quitado el NominaController pasando todos sus metodos a este controller
//De este modo he implementado el patron FrontController
@WebServlet(description = "administra peticiones para la tabla empleados", urlPatterns = { "/empleados" })
public class EmpleadoController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String dni;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpleadoController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub

        String opcion = request.getParameter("opcion");

        if (opcion.equals("crear")) {
            System.out.println("Usted a presionado la opcion crear");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
            requestDispatcher.forward(request, response);
        } else if (opcion.equals("listar")) {

            EmpleadoDAO empleadoDAO = (EmpleadoDAO) DAOFactory.getDAO("empleado");

            List<Empleado> lista = new ArrayList<>();
            try {
                lista = empleadoDAO.obtenerEmpleados();
                for (Empleado empleado : lista) {
                    System.out.println(empleado);
                }

                request.setAttribute("lista", lista);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
                requestDispatcher.forward(request, response);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("Usted a presionado la opcion listar");
        } else if (opcion.equals("meditar")) {
            String dni = request.getParameter("dni");
            System.out.println("Editar dni: " + dni);
            EmpleadoDAO empleadoDAO = (EmpleadoDAO) DAOFactory.getDAO("empleado");
            Empleado emp = new Empleado();
            try {
                emp = empleadoDAO.obtenerEmpleado(dni);
                System.out.println(emp);
                request.setAttribute("empleado", emp);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
                requestDispatcher.forward(request, response);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (opcion.equals("eliminar")) {
            EmpleadoDAO empleadoDAO = (EmpleadoDAO) DAOFactory.getDAO("empleado");
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                empleadoDAO.eliminar(id);
                System.out.println("Registro eliminado satisfactoriamente...");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        else if (opcion.equals("buscar")) {
            System.out.println("Buscar empleado");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/buscarEmpleado.jsp");
            requestDispatcher.forward(request, response);
        }else if (opcion.equals("buscarNom")) {
            System.out.println("Usted a presionado la opcion buscar");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/buscar.jsp");
            requestDispatcher.forward(request, response);
        }else if (opcion.equals("listarNom")) {
            //no se mete aqui, averiguar porque
            System.out.println("Me he metido en listarNom");
            NominaDAO nominaDAO = (NominaDAO) DAOFactory.getDAO("nomina");
            List<Nomina> lista = new ArrayList<>();
            try {
                lista = nominaDAO.obtenerNominas(dni);
                for (Nomina nomina : lista) {
                    System.out.println(nomina);
                }

                request.setAttribute("lista", lista);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listarNom.jsp");
                requestDispatcher.forward(request, response);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            System.out.println("Usted a presionado la opcion listar");
        }


        // response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String opcion = request.getParameter("opcion");


        if (opcion.equals("guardar")) {
            EmpleadoDAO empleadoDAO = (EmpleadoDAO) DAOFactory.getDAO("empleado");
            Empleado empleado = new Empleado();
            empleado.setNombre(request.getParameter("nombre"));
            empleado.setSexo(request.getParameter("sexo"));
            empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
            empleado.setAnyos(Integer.parseInt(request.getParameter("anyos")));
            try {
                empleadoDAO.guardar(empleado);
                System.out.println("Registro guardado satisfactoriamente...");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (opcion.equals("editar")) {
            Empleado empleado = new Empleado();
            EmpleadoDAO empleadoDAO = (EmpleadoDAO) DAOFactory.getDAO("empleado");

            empleado.setDni(request.getParameter("dni"));
            empleado.setNombre(request.getParameter("nombre"));
            empleado.setSexo(request.getParameter("sexo"));
            empleado.setCategoria(Integer.parseInt(request.getParameter("categoria")));
            empleado.setAnyos(Integer.parseInt(request.getParameter("anyos")));
            try {
                if(empleado.getAnyos() <= 0 || empleado.getAnyos() >= 10 || empleado.getCategoria() <= 0 || empleado.getCategoria() >= 10 ){
                    request.setAttribute("mensaje", "Categoria o años deben estas entre 1 y 10");
                    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }else{
                    empleadoDAO.editar(empleado);
                    System.out.println("Registro editado satisfactoriamente...");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                    requestDispatcher.forward(request, response);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else if (opcion.equals("buscarEmpleado")) {
            String criterio = request.getParameter("criterio");
            String valor = request.getParameter("valor");

            EmpleadoDAO empleadoDAO = (EmpleadoDAO) DAOFactory.getDAO("empleado");
            List<Empleado> lista = new ArrayList<>();

            try {
                lista = empleadoDAO.buscarEmpleado(criterio, valor);
                if (lista.isEmpty()) {
                    request.setAttribute("mensaje", "No ha puesto ningun criterio");
                    RequestDispatcher rd = request.getRequestDispatcher("/views/buscarEmpleado.jsp");
                    rd.forward(request, response);
                } else if (lista.size() == 1) {
                    request.setAttribute("empleado", lista.get(0));
                    RequestDispatcher rd = request.getRequestDispatcher("/views/editar.jsp");
                    rd.forward(request, response);
                } else {
                    request.setAttribute("mensaje", "Se han encontrado varios empleados con ese mismo dato. Use un criterio más específico para la búsqueda.");
                    RequestDispatcher rd = request.getRequestDispatcher("/views/buscarEmpleado.jsp");
                    rd.forward(request, response);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else if (opcion.equals("Volver")) {
            System.out.println("Vuelvo a menu principal");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(request, response);
        }
        else if (opcion.equals("PaginaAnterior")) {
            System.out.println("Vuelvo a la pagina anterior");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/buscarEmpleado.jsp");
            requestDispatcher.forward(request, response);
        }else if (opcion.equals("buscarNomina")) {

            dni = request.getParameter("dni");
            response.sendRedirect("empleados?opcion=listarNom");

        }else if (opcion.equals("PaginaAnterior2")) {
            System.out.println("Vuelvo a la pagina anterior");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/buscar.jsp");
            requestDispatcher.forward(request, response);
        }


        // doGet(request, response);
    }

}