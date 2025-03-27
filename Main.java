import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CadastroView view = new CadastroView();
            view.setVisible(true);
        });
    }
}