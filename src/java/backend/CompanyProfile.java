/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jdbc.driver.DBConversion;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Rahul Bhardwaj
 */
public class CompanyProfile extends HttpServlet {

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
        
        PrintWriter out=response.getWriter();
        String companyname=request.getParameter("companyname"),
                companyemail=request.getParameter("companyemail"),
                description=request.getParameter("description"),
                address=request.getParameter("address");
        
        String UPLOAD_DIRECTORY="C:\\Users\\Rahul Bhardwaj\\Documents\\NetBeansProjects\\Internship\\build\\web\\uploads\\";
        
        String logo=null;
        
        if(ServletFileUpload.isMultipartContent(request))
        {
            try
            {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
              
                for(FileItem item : multiparts)
                {
                    if(!item.isFormField())
                    {
                        if(item.getFieldName().equals("logo"))
                        {
                             logo=new File(item.getName()).getName();
                             item.write( new File(UPLOAD_DIRECTORY+logo));
                        }
                        
                    }
                    if(item.getFieldName().equals("companyname"))
                        {
                             companyname=item.getString();
                        }
                        if(item.getFieldName().equals("companyemail"))
                        {
                             companyemail=item.getString();
                        }
                        if(item.getFieldName().equals("description"))
                        {
                             description=item.getString();
                        }
                        if(item.getFieldName().equals("address"))
                        {
                             address=item.getString();
                        }
                }
            }
            catch (Exception ex)
            {
                out.print("File Upload Failed due to " + ex);

            }          

        }
        else
        {
            out.print("Sorry this Servlet only handles file upload request");

        }

            try
            {
                Connection con=dbconnectivity.DbConnectivity.getConnect();

                PreparedStatement ps=con.prepareStatement("insert into companyprofile values(?,?,?,?,?)");
                ps.setString(1, companyname);
                ps.setString(2, companyemail);
                ps.setString(3, logo);
                ps.setString(4, description);
                ps.setString(5, address);
                int i=ps.executeUpdate();

                if(i>0)
                {
                    out.println("..........success");
                }
                else
                {
                    out.println("fail");
                }
                  con.close();
            }
            catch(Exception e)
            {
                out.print("exception 2 :- "+e);
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
