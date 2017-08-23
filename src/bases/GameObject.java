package bases;

import bases.physics.Physics;
import bases.physics.PhysicsBody;
import bases.pools.GameObjectPool;
import bases.renderers.Renderer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by huynq on 8/9/17.
 */
public class GameObject {
    protected Vector2D position;
    protected Vector2D screenPosition;

    protected Renderer renderer;

    protected ArrayList<GameObject> children;
    protected boolean isActive;
    private boolean isReviewing;

    private static Vector<GameObject> gameObjects = new Vector<>();
    private static Vector<GameObject> newGameObjects = new Vector<>();

    public void setScreenPosition(Vector2D screenPosition) {
        this.screenPosition = screenPosition;
    }

    public boolean isActive() {
        return isActive;
    }

    public static void runAll() {
        // instanceof
        for (GameObject gameObject : gameObjects) {
            if(gameObject.isActive) {
                gameObject.run(new Vector2D(0, 0)); // todo
            }
        }

        for (GameObject newGameObject : newGameObjects) {
            if(newGameObject instanceof PhysicsBody){
                Physics.add((PhysicsBody)newGameObject);
            }
        }

        gameObjects.addAll(newGameObjects);
        newGameObjects.clear();

        System.out.println(gameObjects.size());
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public static void renderAll(Graphics2D g2d) {
        for (GameObject gameObject : gameObjects) {
            if(gameObject.isActive && !gameObject.isReviewing)
                gameObject.render(g2d);
        }
    }

    public static void clearAll(){
        gameObjects.clear();
        newGameObjects.clear();
        Physics.clearAll();
        GameObjectPool.clearAll();
    }

    public static void add(GameObject gameObject) {
        newGameObjects.add(gameObject);
    }

    public GameObject() {
        children = new ArrayList<>();
        position = new Vector2D();
        screenPosition = new Vector2D();
        isActive = true;
    }

    public void run(Vector2D parentPosition) {
        screenPosition = parentPosition.add(position);
        isReviewing = false;
        for (GameObject child : children){
            if(child.isActive)
                child.run(screenPosition);
        }
    }

    public void render(Graphics2D g2d) {
        if (renderer != null) {
            renderer.render(g2d, screenPosition); // null.render() => NullPointerException
        }

        for(GameObject child : children){
            if(child.isActive)
                child.render(g2d);
        }
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getScreenPosition() {
        return screenPosition;
    }

    public void setPosition(Vector2D position) {
        if (position != null)
            this.position = position;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setRenderer(Renderer renderer) {
        if (renderer != null)
            this.renderer = renderer;
    }

    public  void reset(){
        this.isActive = true;
        this.isReviewing = true;

    }
}
