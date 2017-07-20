/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;


/**
 *
 * @author Rahul Bhardwaj
 */
public class CompanyLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        PrintWriter out = response.getWriter();
        
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        
           try
        {
            Connection con=dbconnectivity.DbConnectivity.getConnect();
            PreparedStatement ps=con.prepareStatement("select * from COMPANYLOGINS where email='"+email+"' and password='"+password+"'");
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                //create Json Object
                JSONObject json = new JSONObject();

                  // put some value pairs into the JSON object .
                  json.put("company_name", rs.getString("company_name"));
                  json.put("fname", rs.getString("fname"));
                  json.put("lname", rs.getString("lname"));
                  json.put("contact", rs.getString("contact"));
                  json.put("email", rs.getString("email"));
                  json.put("address", rs.getString("address"));
                  
                  out.print(json.toString());
            }
            else
            {
                out.println("Invalid email and password");
            }
        }
        catch(Exception e)
        {
            out.println(e);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
