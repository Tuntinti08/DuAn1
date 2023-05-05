package views.main;

import cores.truongPhongs.views.TPThongKeBieuDo;
import cores.truongPhongs.views.TP_ChucVuForm;
import cores.truongPhongs.views.TP_KhachHangForm;
import cores.truongPhongs.views.TP_QuanLyPhieuHoanNhap;
import cores.truongPhongs.views.TpDonViView;
import cores.truongPhongs.views.TpLuongNhapView;
import cores.truongPhongs.views.TpQuanLyNhanVien;
import cores.truongPhongs.views.TpQuanLySanPhamForm;
import cores.truongPhongs.views.TpThongKeView;
import cores.truongPhongs.views.TpXemPhieuXuatView;
import views.component.Header;
import views.event.EventMenuSelected;
import views.event.EventShowPopupMenu;
import views.form.MainForm;
import views.swing.MenuItem;
import views.swing.PopupMenu;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import utilities.Auth;
import views.component.MenuTruongPhong;
import views.component.TrangChu;

/**
 *
 * @author QUOC HUY
 */
public class TruongPhong extends javax.swing.JFrame {

    private MigLayout layout;
    private MenuTruongPhong menu;
    private Header header;
    private MainForm main;
    private Animator animator;
    private Frame frame;

    public TruongPhong() {
        initComponents();
        frame = new Frame();
        frame = this;

        init();
    }

    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new MenuTruongPhong();
        header = new Header();
        main = new MainForm();
        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                if (menuIndex == 0) {
                    main.showForm(new TrangChu());
                }
                if (menuIndex == 1) {
                    main.showForm(new TP_ChucVuForm());
                }
                if (menuIndex == 2) {
                    main.showForm(new TP_KhachHangForm());
                }
                if (menuIndex == 3) {
                    main.showForm(new TpQuanLySanPhamForm());
                }
                if (menuIndex == 4) {
                    main.showForm(new TpQuanLyNhanVien());
                }
                if (menuIndex == 5) {
                    main.showForm(new TpDonViView());
                }
                if (menuIndex == 5) {
                    main.showForm(new TpDonViView());
                }
                if (menuIndex == 6) {
                    if (subMenuIndex == 0) {
                        main.showForm(new TpThongKeView());
                    }
                    if (subMenuIndex == 1) {
                        main.showForm(new TPThongKeBieuDo());
                    }
                }
                if (menuIndex == 7) {

                    if (subMenuIndex == 0) {
                        main.showForm(new TpLuongNhapView());
                    }
                    if (subMenuIndex == 1) {
                        main.showForm(new TP_QuanLyPhieuHoanNhap());
                    }
                    if (subMenuIndex == 2) {
                        main.showForm(new TpXemPhieuXuatView());
                    }
                }

                if (menuIndex == 8) {
                    if (subMenuIndex == 2) {
                        Auth.clear();
                        frame.setVisible(false);
                        try {
                            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                                if ("Windows".equals(info.getName())) {
                                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                    break;
                                }
                            }
                        } catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (InstantiationException ex) {
                            ex.printStackTrace();
                        } catch (IllegalAccessException ex) {
                            ex.printStackTrace();
                        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                            ex.printStackTrace();
                        }
                        //</editor-fold>
                        //</editor-fold>

                        /* Create and display the form */
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                new LoginView().setVisible(true);
                            }
                        });
                    }
                }

            }
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(TruongPhong.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = TruongPhong.this.getX() + 52;
                int y = TruongPhong.this.getY() + com.getY() + 86;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        header.addClose(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        header.addSap(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.setExtendedState(frame.ICONIFIED);
            }
        });

//        header.openNavBar(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                main.showForm(new NvqlXemThongTinCaNhanForm());
//            }
//        });
        menu.initMenuItem();
        bg.add(menu, "w 170!, spany 2");    // Span Y 2cell
        bg.add(header, "h 100!, wrap");
        bg.add(main, "w 100%, h 100%");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (170 * (1f - fraction));
                } else {
                    width = 60 + (170 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);

        //  Start with this form
        main.showForm(new TrangChu());

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setOpaque(true);
        bg.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                bgMouseDragged(evt);
            }
        });
        bg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                bgMousePressed(evt);
            }
        });

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
                bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1366, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
                bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 783, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bg)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(bg)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    int xy, xx;

    private void bgMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_bgMouseDragged

    private void bgMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_bgMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TruongPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TruongPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TruongPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TruongPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TruongPhong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane bg;
    // End of variables declaration//GEN-END:variables
}
