import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SettingSave implements MouseListener,MouseMotionListener{
    DrawCanvas dc;
    JPanel jp;
    int saveButtonX = 30;
    int saveButtonY = 400;
    int saveButtonSizeW = 60;
    int saveButtonSizeH = 30;
    boolean saveButton = false;
    int filecnt = 1;

    public SettingSave(JPanel jp,DrawCanvas dc){
        this.dc = dc;
        this.jp = jp;
        jp.addMouseListener(this);
        jp.addMouseMotionListener(this);
    }

    public void drawSetSave(Graphics2D g2){
        g2.setColor(Color.GRAY);
        g2.fillRect(saveButtonX, saveButtonY, saveButtonSizeW, saveButtonSizeH);
        if(saveButton){
            g2.setColor(Color.BLACK);
            g2.fillRect(saveButtonX, saveButtonY, saveButtonSizeW, saveButtonSizeH);
        }
        g2.setColor(Color.white);
        g2.drawString("SAVE", saveButtonX+13, saveButtonY+20);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point p = e.getPoint();
        if(Util.jude(p.x, p.y, saveButtonX, saveButtonY, saveButtonSizeW, saveButtonSizeH)){
            saveButton = true;
        }else{
            saveButton = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(saveButton){
            saveButton = false;
            // File defaultFile = new File("paint.png");
            jp.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));   //ダイアログが表示されるまで待ち状態のカーソルに変更
            dc.savePNG("save/paint"+filecnt+".png");
            jp.setCursor(null);
            filecnt++;
            // JFileChooser filechooser = new JFileChooser();
            // filechooser.setSelectedFile(defaultFile);
            // int selected = filechooser.showSaveDialog(dc);
            // jp.setCursor(null);
            // if (selected == JFileChooser.APPROVE_OPTION){
            //     File file = filechooser.getSelectedFile();
            //     String fileName = file.getName();
            //     System.out.println(fileName);
            //     int fileType = Util.filecheck(file);//戻り値が0は無効な名前。1はpng、2はjpg
            //     if(fileType==1){
            //         dc.savePNG(file.getAbsolutePath());
            //     }else if(fileType==2){
            //         dc.saveJPG(file.getAbsolutePath());
            //     }else{
            //         JOptionPane.showMessageDialog(dc, "拡張子は.pngと.jpgのみ有効です");
            //     }
            // }else if (selected == JFileChooser.CANCEL_OPTION){
                
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
