package com.vaer.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/TestDBServlet")
public class TestDBServlet extends HttpServlet{

    /* class fields */
    private static final long serialVertionUID = 1L;

    /* methods */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        /**
         * TO TEST THIS SERVLET ENTER IN BROWSER: http://localhost:8080/webcustomertracker/TestDBServlet
         * */

        /** setup connection variable*/
        String user = "springstudent";
        String password = "springstudent";
        String jdbcURL = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
        String driver = "com.mysql.cj.jdbc.Driver";

        /** get connection to DB*/
        try{

            /** this helps us to print in webpage*/
            PrintWriter out = response.getWriter();
            out.println("Connecting to DB: " + jdbcURL);

            /** load our MYSQL class driver*/
            Class.forName(driver);

            /** set our connection settings */
            Connection myConnection = DriverManager.getConnection(jdbcURL, user, password);

            /** print successful msg*/
            out.println("Connection successful!");

            /** close connection*/
            myConnection.close();

        }catch (Exception exc){
            exc.printStackTrace();
            throw new ServletException(exc);
        }
    }
}
