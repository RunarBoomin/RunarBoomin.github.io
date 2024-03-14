package inf112.skeleton.app;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;


public class HelloWorld extends Game{

	public static HelloWorld INSTANCE;
	private int widthScreen, heightScreen;
	private OrthographicCamera orthographicCamera;
	
	public HelloWorld(){
		INSTANCE = this;
	}


	@Override
	public void create(){
		this.widthScreen = Gdx.graphics.getWidth();
		this.heightScreen = Gdx.graphics.getHeight();
		this.orthographicCamera = new OrthographicCamera();
		this.orthographicCamera.setToOrtho(false,widthScreen,heightScreen);
		setScreen(new GameScreen(orthographicCamera));
	}
}


