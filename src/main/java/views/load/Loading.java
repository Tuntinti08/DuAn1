//package views.load;
//
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import utilities.HibernateUtil;
//
///**
// *
// * @author QUOC HUY
// */
//public class Loading extends Thread {
//    private Load lv = new Load();
//    public void run() {
//        lv.setVisible(true);
//        HibernateUtil.getSessionFactory();
//        try {
//            this.sleep(3800);
//            lv.setVisible(false);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(Loading.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.stop();
//    }
//
//}
