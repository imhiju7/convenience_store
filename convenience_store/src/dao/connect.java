/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author giavi
 */
public class connect {
    public connect(){
        
    }
    
    public static Connection connection(){
        String url = "jdbc:mysql://localhost:3306/qlcuahangtienloi";  // tao database trong mysql ten qlcuahangtienloi
        // luu y cho Toan: 3307 con lai: 3306
        String username = "root";

        String password = "Tanle1298"; // password tuy moi nguoi dat cho cai connect trong mysql

        /*Hieu: ToilaHieuday7 */
        Connection con = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
        }catch(ClassNotFoundException | SQLException e){
        }
        return con;
    }
    
    public static void main(String[] args) {
        Connection con = connection();
        if(con!=null){
            System.out.println("dao.connect.main()");
        }
    }
}
