package com.greedygame.app.dialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.greedygame.Assets;

public class RetryDialog {

	private Button retryButton, supportButton, scoreButton;
	private Window window;
	private Stage stage;
	private WindowDialogListener dialogListener;
	
	public RetryDialog(WindowDialogListener dL, Stage s){
		dialogListener = dL;
		stage = s;
		retryButton = createButton("retry", Assets.IconRetry);
		supportButton = createButton("support", Assets.IconSupport);
        scoreButton = createButton("score", Assets.IconScore);
        
        window = new Window("Play Again", Assets.SKIN);
        window.setMovable(false);
        window.setModal(true);
        window.setSize(680, 350);
		window.padTop(100);
		window.row().expandX();
		window.padBottom(50);
		window.add(retryButton);
		window.add(scoreButton);
		window.add(supportButton);

		stage.addActor(window);


		window.setVisible(false);
		


		
	}
	
	private void setButtonListener(){
		retryButton.addCaptureListener(new  ClickListener(){
		     public boolean touchDown(InputEvent event, float x, float y, int pointer, int    button1){
		    	   dialogListener.onButtonClicked(retryButton.getName());
		           return false;
		      }
		});
		
		supportButton.addCaptureListener(new  ClickListener(){
		     public boolean touchDown(InputEvent event, float x, float y, int pointer, int    button1){
		    	   dialogListener.onButtonClicked(supportButton.getName());
		           return false;
		      }
		});
		
		scoreButton.addCaptureListener(new  ClickListener(){
		     public boolean touchDown(InputEvent event, float x, float y, int pointer, int    button1){
		    	   dialogListener.onButtonClicked(scoreButton.getName());
		           return false;
		      }
		});
	}
	
	private InputProcessor parentInputProcessor;
	public void show(){
		if(window.isVisible() == false){
			window.setVisible(true);
			parentInputProcessor = Gdx.input.getInputProcessor();
			setButtonListener();
			Gdx.input.setInputProcessor(stage);
			setButtonListener();
		}
	}
	
	public void hide(){
		if(window.isVisible()){
			window.setVisible(false);
			Gdx.input.setInputProcessor(parentInputProcessor);
		}
	}
	
	private Button createButton(String name,TextureRegion texture){
		Button button = new Button(new Image(texture), Assets.SKIN);
        button.center();
        button.setSize(128, 128);
        button.setName(name);
        return button;
	}

	public void setPosition(float x,float y){
		window.setPosition(x, y);
	}

	public float getHeight() {
		return window.getHeight();
	}

	public float getWidth() {
		return window.getWidth();
	}

	public boolean isVisible() {
		return window.isVisible();
	}
}
