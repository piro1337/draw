import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawCanvas extends JPanel implements MouseListener,MouseMotionListener,KeyListener{
    	private int prex,prey,nowx,nowy,savex,savey;
	private float size =10f;
	private Color c = Color.BLACK;
	BufferedImage img = null;
	BufferedImage[] hozonimg = new BufferedImage[100];
	Graphics2D g2;
	int imgIndex = 0;
	int imgMax = 0;
	boolean mousepress = false;
	String outputname = "hozon.png";
	Graphics g;

    boolean KeyZ = false;
    boolean KeyCtrl = false;
    boolean KeyS = false;
	DrawCanvas(){
		prex=-1000;
		prey=-1000;
		nowx=-1000;
		nowy=-1000;
		savex=-1000;
		savey=-1000;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		Toolkit tk = getToolkit();
		Dimension dim = tk.getScreenSize();
		img = new BufferedImage(dim.width-100,dim.height-100,BufferedImage.TYPE_INT_RGB);
		System.out.println("moto"+img.getHeight());
		g2 = (Graphics2D) img.createGraphics();
		g2.setColor(Color.WHITE);
		g2.fillRect(0, 0, dim.width, dim.height);

		//repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2.setColor(c);
		BasicStroke bs = new BasicStroke(size,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);
		g2.setStroke(bs);
		savex = nowx;
		savey = nowy;
		g2.drawLine(prex, prey, nowx, nowy);

		g.drawImage(img,0,0,null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousepress = true;
		Point p = e.getPoint();
		prex = p.x;
		prey = p.y;
		nowx = p.x;
		nowy = p.y;
		savex = nowx;
		savey = nowy;
		repaint();

		hozonimg[imgIndex] = deepCopy(img);
		imgIndex++;
		if(imgIndex==hozonimg.length)imgIndex=0;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		prex=-1000;
		prey=-1000;
		nowx=-1000;
		nowy=-1000;
		mousepress = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		Point p = e.getPoint();
		prex = savex;
		prey = savey;
		nowx = p.x;
		nowy = p.y;
//		System.out.println("aprex:"+prex+"  nowx:"+nowx+"  prey:"+prey+"  nowy:"+nowy);
		repaint();
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		

	}

	@Override
	public void keyTyped(KeyEvent e) {


	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_Z){
            KeyZ = true;
		}else if(e.getKeyCode()==KeyEvent.VK_CONTROL){
            KeyCtrl = true;
        }else if(e.getKeyCode()==KeyEvent.VK_S){
            KeyS = true;
        }


        if(KeyZ&&KeyCtrl&&!mousepress){//undo
			imgIndex--;
			if(imgIndex==-1){
				imgIndex = hozonimg.length-1;
			}
			g2.drawImage(hozonimg[imgIndex], 0,0,null);
			repaint();
        }else if(KeyS&&KeyCtrl&&!mousepress){//保存
                        File defaultFile = new File("paint.png");
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));   //ダイアログが表示されるまで待ち状態のカーソルに変更
            JFileChooser filechooser = new JFileChooser();
            filechooser.setSelectedFile(defaultFile);
            int selected = filechooser.showSaveDialog(this);
            this.setCursor(null);
            if (selected == JFileChooser.APPROVE_OPTION){
                File file = filechooser.getSelectedFile();
                String fileName = file.getName();
                System.out.println(fileName);
                int fileType = Util.filecheck(file);//戻り値が0は無効な名前。1はpng、2はjpg
                if(fileType==1){
                    savePNG(file.getAbsolutePath());
                }else if(fileType==2){
                    saveJPG(file.getAbsolutePath());
                }else{
                    JOptionPane.showMessageDialog(this, "拡張子は.pngと.jpgのみ有効です");
                }
            }
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_Z){
            KeyZ = false;
		}else if(e.getKeyCode()==KeyEvent.VK_CONTROL){
            KeyCtrl = false;
        }else if(e.getKeyCode()==KeyEvent.VK_S){
            KeyS = false;
        }
		
	}

	public void setColor(Color c){
		this.c = c;
	}
	
	public Color getColor() {
		return this.c;
	}

	public void setDrawSize(float size){
		this.size = size;
	}

	public float getDrawSize(){
		return this.size;
	}

	public BufferedImage deepCopy(BufferedImage bi) {//undoするためのコピー
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	public void savePNG(String fileName){
		try{
			ImageIO.write(img, "png", new File(fileName));
			} catch (Exception ex) {
	
			// ファイルの入出力でエラーになった場合
			ex.printStackTrace();
	
			}
	}

	public void saveJPG(String fileName){
		try{
			ImageIO.write(img, "jpg", new File(fileName));
			} catch (Exception ex) {
	
			// ファイルの入出力でエラーになった場合
			ex.printStackTrace();
	
			}
	}

	public void openFile(String fileName){
		try{
			hozonimg[imgIndex] = deepCopy(img);
			imgIndex++;
			if(imgIndex==hozonimg.length)imgIndex=0;
			BufferedImage bufferedImage = ImageIO.read(new File(fileName));
			Toolkit tk = getToolkit();
			Dimension dim = tk.getScreenSize();
			System.out.println(bufferedImage.getTileHeight());
			g2.drawImage(bufferedImage, 0,0,dim.width-100,dim.height-100,null);
			repaint();
		}catch(Exception ex){
			// ファイルの入出力でエラーになった場合
			ex.printStackTrace();
		}

	}

}
