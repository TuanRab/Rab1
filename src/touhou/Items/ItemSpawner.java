package touhou.Items;

import bases.FrameCounter;
import bases.GameObject;

import java.util.Random;

public class ItemSpawner {
    private FrameCounter spawnCounter;
    private Random random;

    public ItemSpawner() {
        super();
        spawnCounter = new FrameCounter(700);
        random = new Random();
    }

    public void spawn() {
        if (spawnCounter.run()) {
            spawnCounter.reset();
            Item item = new Item();
            item.getPosition().set(random.nextInt(384), 40);
            GameObject.add(item);
        }
    }
}
