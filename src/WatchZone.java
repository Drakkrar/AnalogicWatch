import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
public class WatchZone extends JFrame implements Runnable {
    Drawer draw;
    public WatchZone(String zona) {
        super(zona);
        setVisible(true);
        setResizable(false);
        Container box = this.getContentPane();
        box.setLayout(new BorderLayout());
        draw = new Drawer(zona);
        box.add(draw, BorderLayout.CENTER);
        this.pack();
        draw.mreloj();
    }

    @Override
    public void run() {
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
