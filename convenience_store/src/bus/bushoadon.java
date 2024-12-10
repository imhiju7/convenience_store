
package bus;
import dao.daohoadon;
import dto.dtohoadon;
import java.util.ArrayList;
/**
 *
 * @author AD
 */
public class bushoadon {
    private daohoadon daoHD = new daohoadon();
	public ArrayList <dtohoadon> dshd;

    public bushoadon() {
        getlist() ;
    }

    // Retrieve all records through the DAO
    public void getlist() {
        dshd =  daoHD.getlist();
    }

    // Business logic method to add a new HD record
    public void add (dtohoadon HD) {
        daoHD.add(HD);
    }
    
    public int maxID() {
       return daoHD.maxID();
    }
    public dtohoadon get(int mahoadon){
        for(dtohoadon hd: dshd){
            if(hd.getMaHoaDon() == mahoadon){
                return hd;
            }
        }
        return null;
    }
    public double getTongDoanhThu(){
        return daoHD.getTongDoanhThu();
    } 
    public void addhdnokmkh(dtohoadon i){
        daoHD.addhdnokhkm(i);
    }
    public void addhdnokm(dtohoadon i){
       daoHD.addhdnokm(i);
    }
    public void addhdnokh(dtohoadon i){
        daoHD.addhdnokh(i);
    }
}

