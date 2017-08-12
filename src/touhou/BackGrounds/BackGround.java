package touhou.BackGrounds;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class BackGround extends GameObject {

    public BackGround() {
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/background/0.png"));
    }

    public void run(){
        //super.run();
        position.addUp(0, 1);
    }
}
