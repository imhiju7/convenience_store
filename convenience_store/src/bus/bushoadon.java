
package bus;
import dao.daohoadon;
import dto.dtohoadon;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author AD
 */
public class bushoadon {
    private daohoadon daoHD = new daohoadon();
    public List<dtohoadon> hd;
    
    public void gethd() {
        this.hd = daoHD.getAll();
    }
    public double getTongDoanhThu(){
        return daoHD.getTongDoanhThu();
    }
}
