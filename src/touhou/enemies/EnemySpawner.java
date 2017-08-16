package touhou.enemies;

import bases.FrameCounter;
import bases.GameObject;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 * Created by huynq on 8/9/17.
 */
public class EnemySpawner extends GameObject {
    private FrameCounter spawnCounter;
    private Random random;

    public EnemySpawner() {
        super();
        spawnCounter = new FrameCounter(100);
        random = new Random();
    }

    public void spawn() {
        if (spawnCounter.run()) {
            spawnCounter.reset();
            Enemy enemy = new Enemy();
            enemy.getPosition().set(random.nextInt(384), 40);
            GameObject.add(enemy);
        }
    }
}
