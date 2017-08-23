package bases.renderers;

import bases.FrameCounter;
import bases.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class Animation implements Renderer {

    private List<BufferedImage> images;
    private FrameCounter frameCounter;
    private int currentImageIndex;
    private boolean reverse;
    private boolean oneTime;
    private boolean stopped;

    public Animation(int fameDelay, boolean oneTime, boolean reverse, BufferedImage... images) {
        this.images = Arrays.asList(images);
        this.frameCounter = new FrameCounter(fameDelay);
        this.currentImageIndex = 0;
        this.reverse = reverse;
        this.oneTime = oneTime;
    }

    public Animation(BufferedImage... images) {
        this(12, false, false, images);
    }

    @Override
    public void render(Graphics2D g2d, Vector2D position) {
        if (!stopped) {
            BufferedImage image = images.get(currentImageIndex);
            Vector2D renderPosition = position.subtract(image.getWidth() / 2, image.getHeight() / 2);

            g2d.drawImage(image, (int) renderPosition.x, (int) renderPosition.y, null);
            updateCurrentImage();
        }
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public boolean isStopped(){
        return stopped;
    }

    public void reset(){
        stopped = false;
        currentImageIndex = 0;
    }

    private void updateCurrentImage() {
        if (frameCounter.run()) {
            frameCounter.reset();
            if (!reverse) {
                currentImageIndex++;
                //repeat animation
                if (currentImageIndex >= images.size() - 1) {
                    if (!oneTime) {
                        currentImageIndex = 0;
                    } else {
                        stopped = true;
                    }
                }
            } else {
                currentImageIndex--;
                if (currentImageIndex < 0) {
                    if (!oneTime) {
                        currentImageIndex = images.size() - 1;
                    } else {
                        stopped = true;
                    }
                }
            }
        }
    }
}



