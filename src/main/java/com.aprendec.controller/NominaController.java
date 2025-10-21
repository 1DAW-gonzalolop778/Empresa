package com.aprendec.controller;

import com.aprendec.dao.NominaDAO;
import com.aprendec.model.Nomina;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Servlet implementation class ProductoController
 */
@WebServlet(description = "administra peticiones para la tabla nominas", urlPatterns = { "/nominas" })
public class NominaController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    String dni;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public NominaController() {
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

        if (opcion.equals("buscar")) {
            System.out.println("Usted a presionado la opcion buscar");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/buscar.jsp");
            requestDispatcher.forward(request, response);
        } else if (opcion.equals("listarNom")) {
            //no se mete aqui, averiguar porque
            System.out.println("Me he metido en listarNom");
            NominaDAO nominaDAO = new NominaDAO();
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
        } else if (opcion.equals("meditar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            System.out.println("Editar id: " + id);
            NominaDAO nominaDAO = new NominaDAO();
            Nomina p = new Nomina();
            try {
                p = nominaDAO.obtenerNominas(id);
                System.out.println(p);
                request.setAttribute("nomina", p);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
                requestDispatcher.forward(request, response);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else if (opcion.equals("eliminar")) {
            NominaDAO nominaDAO = new NominaDAO();
            int id = Integer.parseInt(request.getParameter("id"));
            try {
                nominaDAO.eliminar(id);
                System.out.println("Registro eliminado satisfactoriamente...");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listarNom.jsp");
                requestDispatcher.forward(request, response);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

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
        Date fechaActual = new Date();

        if (opcion.equals("buscarNom")) {

            dni = request.getParameter("dni");
            response.sendRedirect("nominas?opcion=listarNom");
            //el error estaba en que hacia dos veces lo mismo
            //Lo he solucionado en vez de mandando otro request llamando directamente a listarNom

            /*
            System.out.println("Me he metido en buscarNom");
            NominaDAO nominaDAO = new NominaDAO();
            Nomina nomina = new Nomina();
            dni = request.getParameter("dni");
            //nomina.setNominas(Integer.parseInt(request.getParameter("nominas")));
            try {
                nominaDAO.obtenerNominas(dni);
                System.out.println("Registro guardado satisfactoriamente...");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/listarNom.jsp");
                requestDispatcher.forward(request, response);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            */

        } else if (opcion.equals("editar")) {
            Nomina nomina = new Nomina();
            NominaDAO nominaDAO = new NominaDAO();

            nomina.setDni(request.getParameter("dni"));
            nomina.setNominas(Integer.parseInt(request.getParameter("nominas")));
            try {
                nominaDAO.editar(nomina);
                System.out.println("Registro editado satisfactoriamente...");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
                requestDispatcher.forward(request, response);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        // doGet(request, response);
    }

}