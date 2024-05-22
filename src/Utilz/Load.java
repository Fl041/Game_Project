package Utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Load {

    public static BufferedImage loadResources(String entity){
        BufferedImage img = null ;
        InputStream is = Load.class.getResourceAsStream(entity);
        try {
            img = ImageIO.read(is);
        } catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return  img;
    }
}
