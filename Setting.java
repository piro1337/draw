
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Setting extends JPanel{
	DrawCanvas dc;
	SettingColor setColor;
	SettingSize setSize;
	SettingSave setSave;
	SettingOpen setOpen;
	int clsize = 30;

	public Setting(DrawCanvas dc,JFrame jf){
		this.setColor = new SettingColor(this,dc);
		this.setSize = new SettingSize(this,dc,jf);
		this.setSave = new SettingSave(this, dc);
		this.setOpen = new SettingOpen(this, dc);
		this.dc = dc;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		setColor.drawSetColor(g2);
		setSize.drawSetSize(g2);
		setSave.drawSetSave(g2);
		setOpen.drawSetOpen(g2);
	}
}
