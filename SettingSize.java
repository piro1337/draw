import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SettingSize implements MouseListener,MouseMotionListener,KeyListener{
    JPanel jp;
    DrawCanvas dc;
    int sizeBar = 0;
	int clickGapX = 0;
	boolean sizeBarClick = false;
	final int setWriteX = 20;
	final int setWriteY = 210;
	final int setWriteSizeX = 90;
	final int setWriteSizeY = 25;
	boolean write = false;
	boolean writeClick = false;
	String viewWrite = null;

    public SettingSize(JPanel jp, DrawCanvas dc, JFrame jf){
        this.jp = jp;
        this.dc = dc;
        sizeBar = (int)dc.getDrawSize();
		viewWrite = String.valueOf(sizeBar);
        jp.addMouseListener(this);
        jp.addMouseMotionListener(this);
		jf.addKeyListener(this);
    }

    public void drawSetSize(Graphics2D g2){
		g2.setColor(Color.WHITE);
		g2.fillRect(setWriteX, setWriteY, setWriteSizeX, setWriteSizeY);
		if(writeClick){
			g2.setColor(Color.BLACK);
			//System.out.println(viewWrite);
			g2.drawLine(setWriteX+2+(viewWrite.length()*7), setWriteY+2, setWriteX+2+(viewWrite.length()*7), setWriteY+setWriteSizeY-2);
		}
		g2.setColor(Color.BLACK);
		g2.drawString(""+sizeBar, setWriteX+3, setWriteY+15);

		g2.setColor(Color.BLACK);
		g2.drawLine(10, 250, 120, 250);
		g2.fillRect(0, 240, 10, 20);
		g2.fillRect(120, 240, 10, 20);
		
		g2.setColor(Color.GRAY);
		g2.fillRect(10+sizeBar, 240, 10, 20);

		BasicStroke bs = new BasicStroke(sizeBar,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		g2.setStroke(bs);
		g2.setColor(dc.getColor());
		g2.drawLine(20, 320, 150, 320);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
		Point p = e.getPoint();
		if(sizeBarClick){
			this.setSizeBar(p.x-clickGapX);
			//System.out.println(clickGapX);
			jp.repaint();
		}
    }

    @Override
    public void mouseMoved(MouseEvent e) {
		Point p = e.getPoint();
		if(Util.jude(p.x,p.y,setWriteX,setWriteY,setWriteSizeX,setWriteSizeY)){
			jp.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));//カーソルをテキストタイプに変更
			write = true;
		}else{
			jp.setCursor(null);
			write = false;
		}
    }

    @Override
    public void mouseClicked(MouseEvent e) {
		if(write){
			writeClick = true;
		}else{
			writeClick = false;
		}
    }

    @Override
    public void mousePressed(MouseEvent e) {
		Point p = e.getPoint();
		if(Util.jude(p.x, p.y, 10+sizeBar, 240, 10, 20)){
			sizeBarClick = true;
			clickGapX = p.x-sizeBar;
		}
    }

    @Override
    public void mouseReleased(MouseEvent e) {
		if(sizeBarClick){
			sizeBarClick = false;
			dc.setDrawSize(sizeBar);
		}
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    public void setSizeBar(int x){
		this.sizeBar = (x>=0)?(x<=100)?x:100:0;
		this.viewWrite = String.valueOf(this.sizeBar);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(writeClick){
			if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE){
				int idx = Integer.valueOf((int)dc.getDrawSize());
				viewWrite=String.valueOf(idx/10);
				setSizeBar(Integer.valueOf(viewWrite));
				dc.setDrawSize(sizeBar);
				jp.repaint();
			}
			for(int i=48; i<=57; i++){
				if(e.getKeyCode()==i){
					//System.out.println(i);
					viewWrite = String.valueOf((int)dc.getDrawSize());
					viewWrite += i-48;
					int idx = Integer.valueOf(viewWrite);
					viewWrite = String.valueOf(idx);
					System.out.println(idx);
					setSizeBar(idx);
					dc.setDrawSize(sizeBar);
					jp.repaint();
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
