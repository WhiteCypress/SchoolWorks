package imageprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main {
    public static void main(String[] args) 
    {
        try
        {
            BufferedImage input = ImageIO.read(new File("images/Lenna.png"));
            BufferedImage output = ImageProcessing.applyGaussianBlur(input);
            ImageIO.write(output, "png", new File("images/Lenna_Blurred.png"));
            
            output = ImageProcessing.applyBoxBlur(input, 10);
            ImageIO.write(output, "png", new File("images/Lenna_BoxBlur.png"));
            
            output = ImageProcessing.applySharpen(input);
            ImageIO.write(output, "png", new File("images/Lenna_Sharpen.png"));
        }
        catch(Exception e)
        {
        }
    }
}
