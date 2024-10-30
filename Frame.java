
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

public class Frame extends JFrame{
	public static void main(String[] args) {
		Frame f = new Frame();
		f.setVisible(true);
	}
	Frame(){
		DrawCanvas dc = new DrawCanvas();
		Setting st = new Setting(dc,this);
		Toolkit tk = getToolkit();
		Dimension dim = tk.getScreenSize();
		JSplitPane sp = new JSplitPane();
		
		sp.setLeftComponent(st);
		sp.setRightComponent(dc);
		sp.setContinuousLayout(true);
		sp.setOneTouchExpandable(true);
		sp.setResizeWeight(0.11);
		sp.setDividerSize(30);
		//System.out.println(sp.getDividerSize());
		this.add(sp);
		
		this.addKeyListener(dc);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(dim.width,dim.height);
//		this.setSize(1000,800);
//		System.out.println(dim.height);
		this.setTitle("キャンパス");
//		this.setUndecorated(true);			//上のバー
	}
}



