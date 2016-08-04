package systemtraytimer;

import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class SystemTrayTimer {

    public static JFrame mainWindow;

    public static void main(String[] args) throws IOException, URISyntaxException {

        // importing image icon
        Image img = ImageIO.read(new File(SystemTrayTimer.class.getResource("/timer.png").toURI()));

        // creating main window
        mainWindow = new JFrame("Light show!");
        mainWindow.setLayout(new GridBagLayout());
        mainWindow.setSize(300, 300);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setIconImage(img);
        JLabel jlabel = new JLabel("SHOWTIME!!!");
        jlabel.setFont(new Font(null, Font.BOLD, 42));
        mainWindow.add(jlabel);
        mainWindow.setVisible(false);
        
        // creating settings window
        SettingsWindow f = new SettingsWindow();
        f.setIconImage(img);
        f.setTitle("Settings");
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setVisible(false);

        // creating popup menu for system tray
        PopupMenu pum = new PopupMenu();
        MenuItem openItem = new MenuItem("Settings");
        MenuItem closeItem = new MenuItem("Close");
        pum.add(openItem);
        pum.add(closeItem);

        // adding action listener to popup menu items
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.setVisible(true);
            }
        });
        closeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int dialogue = JOptionPane.showConfirmDialog(f, "Do you realy want to close SystemTrayTimer?", "", JOptionPane.YES_NO_OPTION);
                if (dialogue == 0) {
                    System.exit(0);
                } else {

                }
            }
        });

        // creatiing system tray icon
        SystemTray st = SystemTray.getSystemTray();
        try {
            TrayIcon icon = new TrayIcon(img);
            icon.setImageAutoSize(true);
            icon.setToolTip("Right click to se options");
            icon.setPopupMenu(pum);
            st.add(icon);
        } catch (AWTException ex) {
            Logger.getLogger(SystemTrayTimer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
