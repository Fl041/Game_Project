package Utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * class which contains the function to retrieve the resources necessary for the game
 */
public class Load {

    /**
     * allows you to recover game resources
     * @param path
     * @return
     */
    public static BufferedImage loadResources(String path){
        BufferedImage img = null ;
        InputStream is = Load.class.getResourceAsStream(path);
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
