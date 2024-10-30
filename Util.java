import java.io.File;

public class Util {
    public static boolean jude(int px, int py, int x,int y,int width, int height){//カーソルの位置、右端のx,上のy,コンポーネントのwidth,コンポーネントのheight
		if(px>x&&px<x+width&&py>y&&py<y+height){
			return true;
		}else{
			return false;
		}
	}

	public static int filecheck(File file){
        String name = file.getName();
        boolean flg = false;
        for(int i=1; i<name.length(); i++){
            char c = name.charAt(i);
            if(c=='.'){
                flg = true;
                break;
            }
        }
        if(!flg){
            return 0;
        }else{
            String extension = name.substring(name.lastIndexOf("."));
            if(extension.equals(".png")){
                return 1;
            }else if(extension.equals(".jpg")){
                return 2;
            }else{
                return 0;
            }
        }
    }
}
