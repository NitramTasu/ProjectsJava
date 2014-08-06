package app.Fisica;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

	// flag to hold game state
	
	//Define a TAG para utilizar no Log
	private static final String TAG = MainThread.class.getSimpleName();
	
	private SurfaceHolder surfaceHolder;
	private MainGamePanel gamePanel;
	
	private boolean running;
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public MainThread(SurfaceHolder surfaceHolder, MainGamePanel gamePanel) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.gamePanel = gamePanel;
	}

	

	@Override
	public void run() {
		long tickCount = 0L;
		
		Canvas canvas;
		
		Log.d(TAG, "Iniciando o Loop Game");
		while (running) {
			
			canvas = null;
			tickCount++;
			
			try {
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					//Estado de atualização gráfica do jogo
					//desenha o canvas no panel
					this.gamePanel.onDraw(canvas);
					
				}
			} finally{
				
				if(canvas != null){
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
			
		}
		Log.d(TAG, "Game Loop executado " + tickCount + " vezes");
	}
}