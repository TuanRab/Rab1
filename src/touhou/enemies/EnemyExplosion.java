package touhou.enemies;

import bases.GameObject;
import bases.Vector2D;
import bases.pools.GameObjectPool;
import bases.renderers.Animation;
import tklibs.SpriteUtils;

import java.awt.*;

public class EnemyExplosion extends GameObject {
    private Animation animation;

    public EnemyExplosion(){
        super();
        animation = new Animation(6, true, false,
                SpriteUtils.loadImage("assets/images/enemies/explosion/0.png") ,
                SpriteUtils.loadImage("assets/images/enemies/explosion/3.png") ,
                SpriteUtils.loadImage("assets/images/enemies/explosion/7.png")
        );
        this.renderer = animation;
    }
    @Override
    public void reset(){
        super.reset();
        animation.reset();
    }
    @Override
    public  void render(Graphics2D g2d){
        super.render(g2d);

        if(animation.isStopped()){
            this.isActive = false;
        }
    }

}
