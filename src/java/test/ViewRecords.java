///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package test;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class ViewRecords extends HttpServlet {
//
//    // JDBC URL for connecting to MySQL database
//    private static final String jdbcURL = "jdbc:mysql://localhost:3306/activelearningdb?zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false";
//    private static final String jdbcUsername = "root";
//    private static final String jdbcPassword = "hope072002";
//
//protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//
//        // Check if 'action' parameter is present
//        String action = request.getParameter("action");
//        if ("View".equals(action)) {
//            // Handle AJAX request to fetch course information and display the table
//            String courseName = request.getParameter("courseName");
//            // Fetch course information based on the courseName parameter
//            // Generate HTML for the information table
//            // Write HTML response to the PrintWriter
//            try (PrintWriter out = response.getWriter()) {
//                // Write HTML response
//                out.println("<!DOCTYPE html>");
//                out.println("<html>");
//                out.println("<head>");
//                out.println("<title>Course Information</title>");
//                out.println("</head>");
//                out.println("<body>");
//                out.println("</body>");
//                out.println("</html>");
//            }
//        } else {
//            // Regular request, generate HTML for instructor management page
//            try (PrintWriter out = response.getWriter()) {
//                // Load MySQL JDBC driver
//                try {
//                    Class.forName("com.mysql.cj.jdbc.Driver");
//                } catch (ClassNotFoundException ex) {
//                    out.println("JDBC Driver not found: " + ex.getMessage());
//                    return;
//                }
//                
//                // Open a connection to the database
//                try (Connection conn = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword)) {
//                    // SQL query to retrieve data from courses table
//                    String sql = "SELECT * FROM courses";
//
//                    try (PreparedStatement statement = conn.prepareStatement(sql)) {
//                        // Execute the query
//                        try (ResultSet resultSet = statement.executeQuery()) {
//                            // Output HTML
//                            out.println("<!DOCTYPE html>");
//                            out.println("<html>");
//                            out.println("<head>");
//                            out.println("<title>Instructor Management</title>");
//                            out.println("</head>");
//                            out.println("<body>");
//                            out.println("<h1>Instructor Management</h1>");
//                            out.println("<table id=\"coursesTable\" border='1'>");
//                            out.println("<tr><th>Course Name</th><th>Instructor</th><th>StartDate</th><th>End Date</th>"
//                                    + "<th>Time</th><th>Banner</th></tr>");
//
//                            // Process the result set and generate HTML for table rows
//                            while (resultSet.next()) {
//                                out.println("<tr>");
//                                out.println("<td>" + resultSet.getString("CourseName") + "</td>");
//                                out.println("<td>" + resultSet.getString("Instructor") + "</td>");
//                                out.println("<td>" + resultSet.getString("StartDate") + "</td>");
//                                out.println("<td>" + resultSet.getString("EndDate") + "</td>");
//                                out.println("<td>" + resultSet.getString("Time") + "</td>");
//                                out.println("<td>" + resultSet.getString("Banner") + "</td>");
//                                // Add View button with onclick event to trigger AJAX request
//                                out.println("<td><button class=\"viewButton\" onclick=\"viewCourse('" + resultSet.getString("CourseName") + "')\"</td>");
//                                out.println("</tr>");
//                            }
//
//                            out.println("</table>");
//                            out.println("</body>");
//                            out.println("</html>");
//                        }
//                    }
//                } catch (SQLException e) {
//                    // Handle SQL exceptions
//                    e.printStackTrace();
//                    out.println("Database error: " + e.getMessage());
//                }
//            }
//        }
//    }
//
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    @Override
//    public String getServletInfo() {
//        return "View Records Servlet";
//    }
//}
