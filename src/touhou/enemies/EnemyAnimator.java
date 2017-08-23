package touhou.enemies;

import bases.Vector2D;
import bases.renderers.Animation;
import bases.renderers.Renderer;
import tklibs.SpriteUtils;
import touhou.Settings.Settings;

import java.awt.*;
import java.util.Random;

public class EnemyAnimator implements Renderer {

    private Random rand;
    Settings settings = Settings.instance;

    private Animation blackEnemyAnimation = new Animation(
            SpriteUtils.loadImage("assets/images/enemies/level0/black/0.png"),
//            SpriteUtils.loadImage("assets/images/enemies/level0/black/1.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/black/2.png"),
//            SpriteUtils.loadImage("assets/images/enemies/level0/black/3.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/black/4.png"),
//            SpriteUtils.loadImage("assets/images/enemies/level0/black/5.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/black/6.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/black/8.png")
    );

    private Animation blueEnemyAnimation  = new Animation(
            SpriteUtils.loadImage("assets/images/enemies/level0/blue/0.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/blue/1.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/blue/2.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/blue/3.png")
    );

    private Animation pinkEnemyAnimation  = new Animation(
            SpriteUtils.loadImage("assets/images/enemies/level0/pink/0.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/pink/1.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/pink/2.png"),
            SpriteUtils.loadImage("assets/images/enemies/level0/pink/3.png")
    );

    private Animation currentAnimation = blueEnemyAnimation;


    public void enemyUpdate(EnemySpawner enemySpawner){
        Random rand = enemySpawner.getRandom();
        if(rand.nextInt() < 100){
            currentAnimation = blueEnemyAnimation;
        } else if(rand.nextInt() > 200 ){
            currentAnimation = pinkEnemyAnimation;
        } else{
            currentAnimation = blackEnemyAnimation;
        }


    }

    public void bossEnemy(Enemy enemy) {
        currentAnimation = blackEnemyAnimation;
    }


    @Override
    public void render(Graphics2D g2d, Vector2D position){
        currentAnimation.render(g2d, position);
    }

}
