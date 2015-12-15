
/**
 * Beginning, Main class of the project. Application starts from here.
 */
import de.javasoft.plaf.synthetica.SyntheticaBlueMoonLookAndFeel;
import javax.swing.UIManager;
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
            UIManager.setLookAndFeel(new SyntheticaBlueMoonLookAndFeel());
            //UIManager.setLookAndFeel(new SyntheticaBlueSteelLookAndFeel());
            //UIManager.setLookAndFeel(new SyntheticaOrangeMetallicLookAndFeel());
            //UIManager.setLookAndFeel(new SubstanceMagmaLookAndFeel());            
            //UIManager.setLookAndFeel(new McWinLookAndFeel());
            //UIManager.setLookAndFeel(new AcrylLookAndFeel());
            //UIManager.setLookAndFeel(new SubstanceAutumnLookAndFeel());
            //UIManager.setLookAndFeel(new SubstanceCremeLookAndFeel());
            //UIManager.setLookAndFeel(new LunaLookAndFeel());
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
