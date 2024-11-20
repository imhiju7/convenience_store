/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import dto.dtouudai;
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
public class daouudai {
    public daouudai(){
    }
    
    public ArrayList<dtouudai> getList(){
       ArrayList<dtouudai> list = new ArrayList<>();
            try (java.sql.Connection con = connect.connection()) {
                String sql = "select * from uudai where isDelete=0 ";
                PreparedStatement pst = con.prepareStatement(sql);
                ResultSet rs = pst.executeQuery();
                while(rs.next()){
                    int maUuDai = rs.getInt("maUuDai");
                    int mocUuDai = rs.getInt("mocUuDai");
                    int tiLeGiam = rs.getInt("tiLeGiam");
                    int isDelete = rs.getInt("isDelete");
                    dtouudai km = new dtouudai(maUuDai, mocUuDai, tiLeGiam, isDelete);
                    list.add(km);
                }   
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            return list;
    }
    
    public int getMaxMaUuDai() {
        int max = 0;
        try (java.sql.Connection con = connect.connection()) {
            String sql = "SELECT MAX(maUuDai) AS max_maUuDai FROM uudai";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                max = rs.getInt("max_maUuDai");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return max;
    }

    public boolean addUuDai(dtouudai uudai) {
        boolean isSuccess = false;
        try (java.sql.Connection con = connect.connection()) {
            String sql = "INSERT INTO uudai (maUuDai,MocUuDai, tiLeGiam,isDelete) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(sql);

            // Gán giá trị cho các tham số
            pst.setInt(1, uudai.getMaUuDai());
            pst.setInt(2, uudai.getMocUuDai());
            pst.setInt(3, uudai.getTiLeGiam());
            pst.setInt(4,0);
            // Thực thi câu lệnh
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                isSuccess = true; // Nếu có dòng bị ảnh hưởng, tức là thêm thành công
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isSuccess; // Trả về true nếu thêm thành công, ngược lại trả về false
    }

    public Boolean Deleted(int maUuDai) {
        boolean isSuccess = false;
        String sql = "UPDATE uudai SET isDelete = 1 WHERE maUuDai = ?";

        try (java.sql.Connection con = connect.connection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1, maUuDai);
            int rowsAffected = pst.executeUpdate();

            // Kiểm tra nếu có ít nhất 1 hàng bị cập nhật
            isSuccess = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }

    public Boolean Update(dtouudai uudai) {
        boolean isSuccess = false;
        String sql = "UPDATE uudai SET  mocUuDai=?, tiLeGiam=?, isDelete=? where maUudai=?";

        try (java.sql.Connection con = connect.connection(); PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setInt(1,uudai.getMocUuDai());
            pst.setInt(2,uudai.getTiLeGiam());
            pst.setInt(3,uudai.getIsDelete());
            pst.setInt(4, uudai.getMaUuDai());
            int rowsAffected = pst.executeUpdate();

            // Kiểm tra nếu có ít nhất 1 hàng bị cập nhật 
            isSuccess = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSuccess;
    }
    
   
    
    public ArrayList<dtouudai> getListByCondition(String value){
        ArrayList<dtouudai> list = new ArrayList<>();
        ArrayList<dtouudai> list2 = new daouudai().getList();
        for(dtouudai uudai : list2){
            if(value.equals(uudai.getMaUuDai()+"") || value.equals(uudai.getMocUuDai()+"") || value.equals(uudai.getTiLeGiam()+"")){
                list.add(uudai);
            }
        }
        return list;
    }
    
    public static void main(String[] args) {
        new daouudai().Update(new dtouudai(12,1200,12,0));
    }
}
