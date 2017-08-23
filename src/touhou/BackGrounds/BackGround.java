package touhou.BackGrounds;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;
import touhou.Settings.Settings;

public class BackGround extends GameObject {
    private ImageRenderer imageRenderer;
    private final float SPEEP = 4;
    private final float imageHeight;

    public BackGround() {
        super();
        this.imageRenderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/background/0.png"));
        this.imageRenderer.getAnchor().set(0, 1);
        this.position.set(0, Settings.instance.getGamePlayHeight());
        this.imageHeight = imageRenderer.image.getHeight();
        this.renderer = imageRenderer;
    }

    @Override
    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        position.y += SPEEP;
        if (position.y > imageHeight){
            position.y = imageHeight;
        }
        //position.addUp(0, 1);
    }
}
