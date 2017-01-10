package dissertation;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GrapgicFrame extends JFrame {

	public GrapgicFrame(JPanel panel) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		setVisible(true);
		this.setBounds(50, 50, 1200, 780);
		setTitle("Graphic");
		add(panel);
	}
}

