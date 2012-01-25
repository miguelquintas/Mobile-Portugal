package MobilePortugal.main;

import MobilePortugal.main.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {
    
    private final int SPLASH_DISPLAY_LENGHT = 3000;

    /** Called when the activity is first created. */
    @Override   
        public void onCreate(Bundle icicle) {
            super.onCreate(icicle);
            setContentView(R.layout.splash);

            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/

            new Handler().postDelayed(new Runnable(){
                    public void run() {
                            /* Create an Intent that will start the Menu-Activity. */
                    		Intent mainIntent = new Intent(SplashScreen.this,ListNewsActivity.class);
                            SplashScreen.this.startActivity(mainIntent);
                            SplashScreen.this.finish();
                    }
            }, SPLASH_DISPLAY_LENGHT);
    }
}