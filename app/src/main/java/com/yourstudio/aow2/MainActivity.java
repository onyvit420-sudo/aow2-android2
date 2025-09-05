package com.yourstudio.aow2;
import android.app.Activity;import android.os.Bundle;import android.view.WindowManager;
public class MainActivity extends Activity{
 private GestureSurface surface;
 @Override protected void onCreate(Bundle b){super.onCreate(b);
  getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
  surface=new GestureSurface(this);setContentView(surface);}
 @Override protected void onResume(){super.onResume();surface.resume();}
 @Override protected void onPause(){super.onPause();surface.pause();}
}