package touhou.players.spheres;

import bases.FrameCounter;
import bases.GameObject;
import javafx.scene.shape.Sphere;

import java.util.Random;

public class SphereSpawner extends GameObject {
    private FrameCounter spawnCounter;

    public SphereSpawner() {
        super();
        spawnCounter = new FrameCounter(30);
        //random = new Random();

    }

    public void spawner() {
        if (spawnCounter.run()) {
            spawnCounter.reset();
            SphereBullet sphereBullet = new SphereBullet();
            sphereBullet.getPosition().set(this.position.add(0, 20));
            GameObject.add(sphereBullet);
        }
    }
}
