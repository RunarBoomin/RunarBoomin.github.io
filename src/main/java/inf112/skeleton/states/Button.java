package inf112.skeleton.states;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Button {
    private Texture texture;
    private float x, y;
    private float width, height;
    private Rectangle2D bounds;
    private Runnable onClickListener;

    private String buttonText;
    private BitmapFont font;

    public Button(Texture texture, float x, float y, float width, float height) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle2D.Float(x, y, width, height);
    

        this.buttonText = "";
        this.font = new BitmapFont();
        this.font.setColor(Color.BLACK);
    }

    public void render(SpriteBatch sb) {
        sb.draw(texture, x, y, width, height);
        if (!buttonText.isEmpty()) {
            GlyphLayout layout = new GlyphLayout(font, buttonText);
            float textX = x + (width - layout.width) / 2;
            float textY = y + (height + layout.height) / 2;
            font.draw(sb, layout, textX, textY);
        }
    }
    
    public void hover(SpriteBatch sb) {
        // Draw drop shadow
        sb.setColor(0, 0, 0, 0.3f); // Set shadow color to black with 50% transparency
        sb.draw(texture, x - 5, y - 5, width + 10, height + 10); // Draw shadow offset by 2 pixels to the right and 2 pixels up
    
        // Draw button
        sb.setColor(Color.WHITE); // Reset color to default (white)
        sb.draw(texture, x, y, width, height); // Draw button texture
    }

    public boolean contains(int mouseX, int mouseY) {
        return bounds.contains(mouseX, mouseY);
    }

    public void setOnClickListener(Runnable onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void handleClick() {
        if (onClickListener != null) {
            onClickListener.run();
        }
    }

    public void setButtonText(String buttonText, int fontSize) {
        this.buttonText = buttonText;
        this.font.getData().setScale((float) fontSize / font.getCapHeight());
    }

    public float getHeight(){
        return texture.getHeight();
    }
}
