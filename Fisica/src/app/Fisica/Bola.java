package app.Fisica;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Display;

import android.view.WindowManager;
import android.widget.Toast;

public class Bola {
	
	Float azimut;  // View to draw a compass

	private Bitmap bitmap; // the actual bitmap
	private int x; // the X coordinate
	private int y; // the Y coordinate
	private boolean touched; // if droid is touched/picked up
	
	//Atributos para criação da colisão elástica
	private int xv = 40;
	private int yv = 40;
	private int gravidade = 10;
	private float restituicao =  (float) 0.90;
	
	private boolean cair = false;
	
	private static final String TAG = MainGamePanel.class.getSimpleName();
	
	public Bola(Bitmap bitmap, int x, int y) {
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;

	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		//xv = this.x - x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		//yv = this.y - y;
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}

	public void handleActionDown(int eventX, int eventY) {
		if (eventX >= (x - bitmap.getWidth()/2 )&& (eventX <= (x + bitmap.getWidth()/2 ))) {
			if (eventY >= (y - bitmap.getHeight()/2 )&& (eventY <= (y + bitmap.getHeight()/2 ))) {
				// droid tocado
				setTouched(true);
				
				//o objeto para de cair
				setCair(false);
				Log.d(TAG, "Clicou!!! Tamanho da imagem: "+ bitmap.getHeight() +" Largura da imagem: "+ bitmap.getWidth() );
			} else {
				//droid não foi tocado
				setTouched(false);
				
			}
		} else {
			setTouched(false);
		}

	}

	public void draw(Canvas canvas,int h,int w) {
		
		if(isCair()){
			Caiu(h,w);
			
		}

		canvas.drawColor(Color.BLACK);

		canvas.drawBitmap(bitmap, x - (bitmap.getWidth() / 2), y - (bitmap.getHeight() / 2), null);
	}
	
	public boolean isCair(){
		return cair;
	}
	public void setCair(boolean c){
		cair = c;
	}
	public void Caiu(int h, int w){
		
		yv-=gravidade;
		
		x -= (xv/10);
		y -= (yv/10);
		
		
		//quando a bolinha quica em baixo
		if(y > (h-bitmap.getHeight()/2)){
			y = h-(bitmap.getHeight()/2);
			
			xv *=  restituicao;
			yv *= -restituicao;//muda o sentido da velocidade em y
		}
		
		//quando a bolinha quica em cima
		if(y < bitmap.getHeight()/2){
			y = bitmap.getHeight()/2;
			
			xv *=  restituicao;
			yv *= -restituicao;//muda o sentido da velocidade em y
		}
		
		//quando a bolinha quica na esquerda
		if(x < bitmap.getWidth()/2){
			x = bitmap.getWidth()/2;
			
			xv *=  -restituicao;
			yv *=   restituicao;//muda o sentido da velocidade em y
		}
		
		//quando a bolinha quica na direita
		if(x > w - bitmap.getWidth()/2){
			x = w - bitmap.getWidth()/2;
			
			xv *= -restituicao;
			yv *=  restituicao;//muda o sentido da velocidade em y
		}
		
	}
	public void SupriVel(int x,int y){
		xv = 40;
		yv = 40;
    }
	
}
