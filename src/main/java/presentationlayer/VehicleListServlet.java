/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
//package presentationlayer;
//
//import datalayer.DataSource;
//import datalayer.Vehicle;
//import datalayer.VehicleDAO;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.List;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// *
// * @author Ambika
// */
//@WebServlet(name = "VehicleListServlet", urlPatterns = {"/vehicles"})
//public class VehicleListServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        doGet(request, response); // Delegate POST to GET handler
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//        try {
//            Connection conn = DataSource.getInstance().createConnection();
//            PrintWriter out = response.getWriter();
//
//            VehicleDAO vehicleDAO = new VehicleDAO(conn);
//            List<Vehicle> vehicles = vehicleDAO.findAll();
//
//            out.println("<html><head><title>Registered Vehicles</title></head><body>");
//            out.println("<h2>Registered Vehicles</h2>");
//            out.println("<table border='1'>");
//            out.println("<tr><th>ID</th><th>Type</th><th>Number</th><th>Fuel Type</th><th>Max Passengers</th><th>Route</th><th>Manager ID</th></tr>");
//
//            for (Vehicle vehicle : vehicles) {
//                out.println("<tr>");
//                out.println("<td>" + vehicle.getId() + "</td>");
//                out.println("<td>" + vehicle.getType() + "</td>");
//                out.println("<td>" + vehicle.getNumber() + "</td>");
//                out.println("<td>" + vehicle.getFuelType() + "</td>");
//                out.println("<td>" + vehicle.getMaxPassengers() + "</td>");
//                out.println("<td>" + vehicle.getCurrentAssignedRoute() + "</td>");
//                out.println("<td>" + vehicle.getManagerId() + "</td>");
//                out.println("</tr>");
//            }
//
//            out.println("</table>");
//            out.println("<br><a href='registerVehicle.html'>Register New Vehicle</a>");
//            out.println("</body></html>");
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
//        }
//    }
//}
