/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtophieunhap;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
/**
 *
 * @author giavi
 */
public class daophieunhap {
    
     public double getTongChiPhi() {
        double result=0;
        String query = """
                SELECT SUM(tongTien) AS tongChiPhi FROM phieunhap WHERE isDelete = 0;
                """;
        
        try (Connection connection = connect.connection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            
            while (resultSet.next()) {
               result=resultSet.getInt("tongChiPhi");
            }
        } catch (Exception e) {
        }
        return result;
    }
}
