import java.awt.Color;

public class ColorInfo {
    Color color;
    int colorX;
    int colorY;
    boolean colorHighlight = false;

    public ColorInfo(Color color,int colorX, int colorY){
        this.color = color;
        this.colorX = colorX;
        this.colorY = colorY;
    }

    public int getX(){
        return this.colorX;
    }

    public int getY(){
        return this.colorY;
    }

    public boolean getColorHighlight(){
        return this.colorHighlight;
    }

    public void setColorHighlight(boolean set){
        this.colorHighlight = set;
    }

    public Color getColorInfo(){
        return this.color;
    }
}
