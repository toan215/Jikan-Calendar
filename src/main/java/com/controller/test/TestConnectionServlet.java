package com.controller.test;

import com.database.DBinformation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet(name = "TestConnectionServlet", urlPatterns = { "/test-connection" })
public class TestConnectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>Database Connection Test</title></head>");
        out.println("<body style='font-family: monospace; padding: 20px;'>");
        out.println("<h2>Database Connection Test</h2>");

        // Display configuration
        out.println("<h3>Current Configuration:</h3>");
        out.println("<pre>");
        out.println("DB_TYPE: " + DBinformation.DB_TYPE);
        out.println("Driver: " + DBinformation.driverName);
        out.println("URL: " + DBinformation.dbURL);
        out.println("User: " + DBinformation.userDB);
        out.println("Password: " + (DBinformation.passDB != null
                ? "***" + DBinformation.passDB.substring(Math.max(0, DBinformation.passDB.length() - 4))
                : "null"));
        out.println("</pre>");

        // Test connection
        out.println("<h3>Connection Test:</h3>");
        out.println("<pre>");

        Connection conn = null;
        try {
            // Load driver
            Class.forName(DBinformation.driverName);
            out.println("✓ Driver loaded successfully");

            // Try to connect
            out.println("\nAttempting connection...");
            conn = DriverManager.getConnection(
                    DBinformation.dbURL,
                    DBinformation.userDB,
                    DBinformation.passDB);

            out.println("✓ CONNECTION SUCCESSFUL!");
            out.println("Database: " + conn.getCatalog());
            out.println("Metadata: " + conn.getMetaData().getDatabaseProductName()
                    + " " + conn.getMetaData().getDatabaseProductVersion());

        } catch (Exception e) {
            out.println("✗ CONNECTION FAILED!");
            out.println("\nError Type: " + e.getClass().getName());
            out.println("Error Message: " + e.getMessage());
            out.println("\nFull Stack Trace:");
            e.printStackTrace(out);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    out.println("\n✓ Connection closed");
                } catch (Exception e) {
                    out.println("\n✗ Error closing connection: " + e.getMessage());
                }
            }
        }

        out.println("</pre>");
        out.println("</body></html>");
    }
}
