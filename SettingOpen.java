import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SettingOpen implements MouseListener, MouseMotionListener{
    DrawCanvas dc;
    JPanel jp;
    int openButtonX = 30;
    int openButtonY = 450;
    int openButtonSizeW = 60;
    int openButtonSizeH = 30;
    boolean openButton = false;
    public SettingOpen(JPanel jp, DrawCanvas dc){
        this.dc = dc;
        this.jp = jp;
        jp.addMouseListener(this);
        jp.addMouseMotionListener(this);
    }

    public void drawSetOpen(Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.fillRect(openButtonX, openButtonY, openButtonSizeW, openButtonSizeH);
        if(openButton){
            g2.setColor(Color.BLACK);
            g2.fillRect(openButtonX, openButtonY, openButtonSizeW, openButtonSizeH);
        }
        g2.setColor(Color.white);
        g2.drawString("CLEAR", openButtonX+13, openButtonY+20);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        if(Util.jude(p.x, p.y, openButtonX, openButtonY, openButtonSizeW, openButtonSizeH)){
            openButton = true;
        }else{
            openButton = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(openButton){
            openButton = false;
            dc.openFile("");
            // jp.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));   //ダイアログが表示されるまで待ち状態のカーソルに変更
            // JFileChooser filechooser = new JFileChooser();
            // int selected = filechooser.showOpenDialog(dc);
            // jp.setCursor(null);
            // if (selected == JFileChooser.APPROVE_OPTION){
            //     File file = filechooser.getSelectedFile();
            //     //String fileName = file.getName();
            //     int fileType = Util.filecheck(file);//戻り値が0は無効な名前。1はpng、2はjpg
            //     if(fileType==0){
            //         JOptionPane.showMessageDialog(dc, "拡張子は.pngと.jpgのみ有効です");
            //     }else{
            //         dc.openFile(file.getAbsolutePath());
            //     }
            // }
        }
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
