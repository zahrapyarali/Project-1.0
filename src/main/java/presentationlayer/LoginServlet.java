/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentationlayer;

import datalayer.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * LoginServlet servlet acts as a central controller for handling all login-related actions.
 * Supports login (database credentials) and dynamically generates HTML views.
 * Author: Ambika
 * This servlet follows a Front Controller pattern with action-based dispatching.
 */
public class LoginServlet extends HttpServlet {

    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String action = req.getParameter("action");
        if (action == null) action = "loginPage";

        switch (action) {

        }
    }

    private void showLoginPage(HttpServletResponse resp) throws IOException {
        String html = generateHtml("Enter DBMS Credentials", "<h1>Enter DBMS Credentials</h1>" +
                "<form action='Controller' method='post'>" +
                "<label>Username:</label><input name='dbUser'/><br/>" +
                "<label>Password:</label><input type='password' name='dbPass'/><br/>" +
                "<input type='hidden' name='action' value='authenticate'/>" +
                "<button type='submit'>Login</button>" +
                "</form>");
        sendResponse(resp, html);
    }

    private void handleAuthenticate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("dbUser");
        String password = req.getParameter("dbPass");

        DataSource.getInstance().setCredentials(username, password);

        try (Connection conn = DataSource.getInstance().createConnection()) {
            String html = generateHtml("Enter DBMS Credentials", "<h1>Enter DBMS Credentials</h1>" +
                    "<p style='color:green;'>Login successful!</p>" +
                    "<form action='Controller' method='post'>" +
                    "<label>Username:</label><input name='dbUser'/><br/>" +
                    "<label>Password:</label><input type='password' name='dbPass'/><br/>" +
                    "<input type='hidden' name='action' value='authenticate'/>" +
                    "<button type='submit'>Login</button>" +
                    "</form>" );
            sendResponse(resp, html);
        } catch (SQLException e) {
            String html = generateHtml("Enter DBMS Credentials", "<h1>Enter DBMS Credentials</h1>" +
                    "<p class='error'>Invalid username or password. Please try again.</p>" +
                    "<form action='Controller' method='post'>" +
                    "<label>Username:</label><input name='dbUser'/><br/>" +
                    "<label>Password:</label><input type='password' name='dbPass'/><br/>" +
                    "<input type='hidden' name='action' value='authenticate'/>" +
                    "<button type='submit'>Login</button>" +
                    "</form>" );
            sendResponse(resp, html);
        }
    }


 
    private void showError(HttpServletResponse resp, String msg) throws IOException {
        String html = generateHtml("Error", "<h1>Error: " + msg + "</h1>");
        sendResponse(resp, html);
    }

    private String generateHtml(String title, String content) {
        return "<html><head><title>" + title + "</title><style>" +
                "body{background:#FAF3E0;font-family:Georgia;} .container{width:400px;margin:80px auto;}" +
                "label{display:inline-block;width:100px;font-weight:bold;}input{width:200px;padding:4px;margin:5px;}" +
                ".buttons{text-align:center;margin-top:20px;}button{margin:0 5px;padding:6px 12px;}" +
                ".error{color:red;}" +
                "</style></head><body><div class='container'>" + content + "</div></body></html>";
    }


    private void sendResponse(HttpServletResponse resp, String html) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println(html);
    }
}