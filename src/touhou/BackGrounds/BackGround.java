package touhou.BackGrounds;

import bases.GameObject;
import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class BackGround extends GameObject {

    public BackGround() {
        super();
        this.renderer = new Animation(SpriteUtils.loadImage("assets/images/background/0.png"));
    }

    public void run(Vector2D parentPosition){
        super.run(parentPosition);
        //position.addUp(0, 1);
    }
}
