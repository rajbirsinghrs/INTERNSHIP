/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnectivity;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rahul Bhardwaj
 */
public class DbConnectivity
{
    static Connection con;
    public static Connection getConnect()
    {
        try
        {
            Class.forName("oracle.jdbc.OracleDriver");
             con=DriverManager.getConnection("jdbc:oracle:thin:@144.217.163.57:1521:XE","finalp1","finalp1");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return con;
    }
}
