package inf112.skeleton.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;


public class GameStateManager {
    private Stack<State> states;

    public GameStateManager(){
        states = new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

    public State lastState(){
        int size = states.size();
        if (size < 2) {
            return null; // No previous state available
        } else {
            return states.elementAt(size - 2);
        }
    }

    public State getState(){
        return states.peek();
    }
}
