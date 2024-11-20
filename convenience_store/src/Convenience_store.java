
/**
 *
 * @author Hieu PC
 */
import java.sql.SQLException;

import gui.form.*;
import gui.main.Guimain;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Convenience_store {

    /**
     * @param args the command line arguments
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {
        try {
            //new frmlogin().setVisible(true);
            new Guimain(1).setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(Convenience_store.class.getName()).log(Level.SEVERE, null, ex);
        }
    public static void main(String[] args) throws SQLException, ParseException {
       //new frmlogin().setVisible(true);
       new Guimain(1).setVisible(true);
    }
}
