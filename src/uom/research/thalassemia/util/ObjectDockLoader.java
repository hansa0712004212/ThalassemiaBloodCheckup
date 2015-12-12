package uom.research.thalassemia.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import uom.research.thalassemia.ui.Login;

/**
 *
 * @author hansa
 */
public final class ObjectDockLoader {

    /**
     * Image Icons list to place in object dock bar.
     */
    private ImageIcon[] icons = {
        new ImageIcon("./src/objectdock/1.png"),
        new ImageIcon("./src/objectdock/2.png"),
        new ImageIcon("./src/objectdock/3.png"),
        new ImageIcon("./src/objectdock/4.png"),
        new ImageIcon("./src/objectdock/login64.png")};

    /**
     * App Name list to place in object dock bar.
     */
    private String[] name = {"My Computer", "Control Panel",
        "Windows Media Player", "Task Manager", "Log Out"};

    /**
     * Object Bar Doc Loader.
     *
     * @param docBarPane jDocBar
     * @param jFrame jframe
     */
    public ObjectDockLoader(final JPanel docBarPane, final JFrame jFrame) {
        ActionListener[] actionListeners = {new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    Process myComputer = Runtime.getRuntime()
                            .exec("");
                } catch (Exception ex) {
                }
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    Process controlPanel = Runtime.getRuntime().exec("control");
                } catch (Exception ex) {
                }
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    Process mediaPlayer = Runtime.getRuntime().exec("");
                } catch (Exception ex) {
                    try {
                        Process mediaPlayer = Runtime.getRuntime().exec("");
                    } catch (Exception ex1) {
                    }
                }
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    Process taskmgr = Runtime.getRuntime().exec("taskmgr");
                } catch (Exception ex) {
                    try {
                        Process taskmgr = Runtime.getRuntime().exec("");
                    } catch (Exception ex1) {
                    }
                }
            }
        }, new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (Message.showQuestionYesNo(" Do You Really Need To Log Out ? ") == Message.YES_OPTION) {
                    jFrame.dispose();
                    new Login(null, true).setVisible(true);
                }
            }
        }
        };
        try {
            new ObjectBarSetter().setDockBarIcon(docBarPane, name, icons,
                    actionListeners);
        } catch (Exception e) {
        }
    }
}
