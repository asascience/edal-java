package uk.ac.rdg.resc.edal.graphics;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.io.OutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Writes PNG images using the ImageIO class. Only one instance of this class
 * will ever be created, so this class contains no member variables to ensure
 * thread safety.
 * 
 * @author jdb
 */
public class JpegFormat extends SimpleFormat {
    /**
     * Protected default constructor to prevent direct instantiation.
     */
    protected JpegFormat() {
    }

    @Override
    public String getMimeType() {
        return "image/jpeg";
    }

    @Override
    public boolean supportsMultipleFrames() {
        return false;
    }

    @Override
    public boolean supportsFullyTransparentPixels() {
        return false;
    }

    @Override
    public boolean supportsPartiallyTransparentPixels() {
        return false;
    }

    @Override
    public void writeImage(List<BufferedImage> frames, OutputStream out) throws IOException {
        if (frames.size() > 1) {
            throw new IllegalArgumentException("Cannot render animations in JPEG format");
        }
        ImageIO.write(frames.get(0), "jpeg", out);
    }
}