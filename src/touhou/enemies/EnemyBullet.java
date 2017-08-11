package touhou.enemies;

import bases.GameObject;
import bases.renderers.ImageRenderer;
import tklibs.SpriteUtils;

public class EnemyBullet extends GameObject {

    public EnemyBullet() {
        super();
        this.renderer = new ImageRenderer(SpriteUtils.loadImage("assets/images/enemies/bullets/green.png"));
    }

    public void run(){
        super.run();
        position.addUp(0, 10);
    }
}
