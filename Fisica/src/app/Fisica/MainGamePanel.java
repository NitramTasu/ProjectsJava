package app.Fisica;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainGamePanel extends SurfaceView implements
		SurfaceHolder.Callback {
	private MainThread thread;
	private Bola bola;
	private static final String TAG = MainGamePanel.class.getSimpleName();

	public MainGamePanel(Context context) {
		super(context);
		// adiciona o (this) para o surface possa segurar qualquer interrupção
		getHolder().addCallback(this);

		thread = new MainThread(getHolder(), this);

		bola = new Bola(BitmapFactory.decodeResource(getResources(),R.drawable.bola), 60, 60);

		// Torna o GamePanel focavel para que possa receber os eventos
		setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		thread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;

		while (retry) {
			try {
				thread.join();
				retry = false;

			} catch (Exception e) {

			}

		}

	}

	protected void onDraw(Canvas canvas) {
		
		
		bola.draw(canvas, getHeight(),getWidth());
		
		// canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),
		// R.drawable.droid_1), 10, 10, null);
		
		
	}

	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			// Verifica se o droid esta sendo tocado
			bola.handleActionDown((int) event.getX(), (int) event.getY());

			// Verifica a parte baixa da tela foi tocada para sair da aplicação
			
			if (event.getY() > getHeight() - 50 && event.getX() > getWidth() - 50) {
				thread.setRunning(false);
				((Activity) getContext()).finish();
			} else {
				Log.d(TAG, "Coords: x=" + event.getX() + ",y=" + event.getY());

			}
		}
		if (event.getAction() == MotionEvent.ACTION_MOVE) {
			// o gesto
			if (bola.isTouched()) {
				// o droid foi pego e foi arrastado
				bola.setX((int) event.getX());
				bola.setY((int) event.getY());
				bola.SupriVel((int)event.getX(),(int)event.getY());
				//bola.Arrastando(event.getX(), event.getY());
				bola.setCair(false);
				
			}
		}
		if (event.getAction() == MotionEvent.ACTION_UP) {
			// parou de tocar a tela
			if (bola.isTouched()) {
				bola.setTouched(false);
				bola.setCair(true);
			}
			
		}
		
		return true;
	}
	
	

}