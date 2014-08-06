package app.Fisica;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class FisicaActivity extends Activity  {
    /** Called when the activity is first created. */
	
	
	
	private static final String TAG = FisicaActivity.class.getSimpleName();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        //bloqueia o orientação
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //tira o titulo da tela
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Faz a tela ficar Full Screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        
        //Chama a tela que vai imprimir o jogo na tela
        setContentView(new MainGamePanel(this));
        
        Log.d(TAG, "View added");
    }
    
    protected void onDestroy(){
    	super.onDestroy();
    }
    
    protected void onStop(){
    	super.onStop();
    }

	

	
    
    
}