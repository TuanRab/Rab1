package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;

import java.util.Random;

/**
 * Created by huynq on 8/9/17.
 */
public class EnemySpawner extends GameObject {
    private FrameCounter spawnCounter;
    private Random random;
    private EnemyAnimator animator;
    //public static  final EnemySpawner instan  = new EnemySpawner();

    public EnemySpawner() {
        super();
        spawnCounter = new FrameCounter(70);
        random = new Random();
        this.animator = new EnemyAnimator();
        renderer = animator;
    }

    public void spawn() {
        if (spawnCounter.run()) {
            spawnCounter.reset();
            Enemy enemy = new Enemy();
            enemy.getPosition().set(random.nextInt(384), 20);
            GameObject.add(enemy);
            animator.enemyUpdate(this);
        }
    }

    public Random getRandom() {
        return random;
    }
}
