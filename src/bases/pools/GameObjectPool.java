package bases.pools;

import bases.GameObject;
import javafx.print.PageLayout;
import touhou.players.PlayerSpell;

import java.util.Vector;

public class GameObjectPool {
    private static Vector<GameObject> pool = new Vector<>();

    //tim kiem va va tai tao lai
    public static <T extends GameObject> T recycle(Class <T> classz) {
        for (GameObject gameObject : pool){
            if ( gameObject.getClass().equals(classz)) {
                if (!gameObject.isActive()) {
                    gameObject.setActive(true);
                    return (T) gameObject;
                }
            }
        }
        try {
            T newGameObject = classz.newInstance();
            GameObject.add(newGameObject);
            pool.add(newGameObject);
            return newGameObject;
        }

        catch (InstantiationException | IllegalAccessException e ) {
            e.printStackTrace();
            return null;
        }
    }
}
