package com.yourstudio.aow2;
import android.content.Context;import android.graphics.*;import android.view.*;
public class GestureSurface extends SurfaceView implements Runnable{
 private Thread loop;private boolean running=false;private final SurfaceHolder holder;
 private float camX=0,camY=0,scale=1f;private Paint p=new Paint();
 public GestureSurface(Context ctx){super(ctx);holder=getHolder();setFocusable(true);p.setColor(Color.WHITE);p.setTextSize(40f);}
 @Override public void run(){while(running){if(!holder.getSurface().isValid())continue;
  Canvas c=holder.lockCanvas();if(c!=null){try{c.drawColor(Color.DKGRAY);
   c.drawText("CAM:"+camX+","+camY+" SCALE:"+scale,50,100,p);}finally{holder.unlockCanvasAndPost(c);}}try{Thread.sleep(16);}catch(Exception e){} }}
 public void resume(){if(running)return;running=true;loop=new Thread(this);loop.start();}
 public void pause(){running=false;try{if(loop!=null)loop.join();}catch(Exception e){}}
 @Override public boolean onTouchEvent(MotionEvent e){
  int act=e.getActionMasked();
  if(act==MotionEvent.ACTION_MOVE&&e.getPointerCount()==1){camX+=e.getX(0)-getWidth()/2f;camY+=e.getY(0)-getHeight()/2f;}
  if(act==MotionEvent.ACTION_MOVE&&e.getPointerCount()==2){float dx=e.getX(0)-e.getX(1),dy=e.getY(0)-e.getY(1);float dist=(float)Math.sqrt(dx*dx+dy*dy);scale=dist/200f;}
  return true;
 }}
