
/**
 * Beginning, Main class of the project. Application starts from here.
 */
import de.javasoft.plaf.synthetica.SyntheticaBlueSteelLookAndFeel;
import java.text.ParseException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.opencv.core.Core;
import uom.research.thalassemia.db.DatabaseConnect;
import uom.research.thalassemia.ui.Login;

/**
 *
 * @author anupama
 */
public final class Main {

    /**
     * project main method.
     *
     * @param args default arguments
     */
    public static void main(final String[] args) {
        try {
            UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            Thread thread = new Thread(() -> {
                try {
                    DatabaseConnect.getInstance();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            });
            thread.start();
            new Login(null, true).setVisible(true);
            //DatabaseConnect.checkDBExists();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * private constructor.
     */
    private Main() {
    }
}
