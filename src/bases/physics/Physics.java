package bases.physics;

import bases.Vector2D;
import touhou.enemies.Enemy;

import java.util.Vector;

public class Physics {
    private static Vector<PhysicsBody> bodies = new Vector<>();

    public static Enemy colliderWithEnemy(BoxCollider boxCollider) {
        for( PhysicsBody body : bodies) {
            if(body.isActive()) {
                if (body instanceof Enemy && body.getBoxCollider().intersects(boxCollider)) {
                    return (Enemy) body;
                }
            }
        }

        return null;
    }

    public static void add(PhysicsBody body) {
        bodies.add(body);
    }
}
