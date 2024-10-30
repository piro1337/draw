
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class SettingColor implements MouseListener, MouseMotionListener{
    DrawCanvas dc;
    JPanel jp;
	int myColorx = 0;
	int myColory = 120;
    int clsize = 30;

    public SettingColor(JPanel jp, DrawCanvas dc){
        this.jp = jp;
        jp.addMouseMotionListener(this);
        jp.addMouseListener(this);
        this.dc = dc;
    }


	ColorInfo[] colorinfo = {
		new ColorInfo(Color.BLACK, 0, 10),	//色情報をまとめた配列
		new ColorInfo(Color.RED,30, 10),
		new ColorInfo(Color.GREEN, 60, 10),
		new ColorInfo(Color.BLUE, 90, 10),
		new ColorInfo(Color.WHITE, 120, 10),
		new ColorInfo(Color.PINK,0,40),
		new ColorInfo(Color.CYAN,30,40),
		new ColorInfo(Color.MAGENTA,60,40),
		new ColorInfo(Color.YELLOW,90,40),
		new ColorInfo(Color.DARK_GRAY,120,40),
	};

    public void drawSetColor(Graphics2D g2){
        for(ColorInfo c:colorinfo){
			g2.setColor(c.getColorInfo());
			g2.fillRect(c.getX(), c.getY(), clsize, clsize);
			g2.setColor(Color.BLACK);
			if(c.getColorHighlight())g2.drawRect(c.getX(), c.getY(), clsize, clsize);
		}

        g2.setColor(dc.getColor());
		g2.fillRect(myColorx, myColory, 50, 50);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		for(ColorInfo c: colorinfo){
			if(Util.jude(p.x,p.y,c.getX(),c.getY(),clsize,clsize)){
				c.setColorHighlight(true);
			}else{
				c.setColorHighlight(false);
			}
		}
		jp.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
		for(ColorInfo c: colorinfo){
			if(c.colorHighlight){
				dc.setColor(c.color);
			}
		}
		jp.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
 
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
