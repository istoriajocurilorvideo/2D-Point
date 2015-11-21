package platform.javagithub;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
 
public class SoftPie {
	
	private static int W,H;
	private static String title;
	
	public SoftPie() {
    	W = 640;
    	H = 480;
    	title = "Default";
    }
    public SoftPie(int width , int height, String title) {
    	W = width;
    	H = height;
    	SoftPie.title = title;
         
    }
    public void init()
    {
    	 try {
             Display.setDisplayMode(new DisplayMode(W,H));
             Display.setVSyncEnabled(true);
             Display.setTitle(title);
             Display.create();
         } catch (LWJGLException e) {
             e.printStackTrace();
             System.exit(0);
         }
    	 GL11.glMatrixMode(GL11.GL_PROJECTION);
    	 GL11.glLoadIdentity();
    	 GL11.glOrtho(0, W, 0, H, 1, -1);
    	 GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
    public int getWidth()
    {
    	return W;
    }
    public int getHeight()
    {
    	return H;
    }
    public String getTitle()
    {
    	return title;
    }
    public boolean isRunning()
    {
    	return !Display.isCloseRequested();
    }
    public void end()
    {
    	Display.update();
    }
    public void begin()
    {
    	GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
    }
    public void finish()
    {
    	Display.destroy();
    }
    public void drawQuad(float x, float y, float width, float height, 
    					float red, float green, float blue, float alpha)
    {
    	GL11.glColor4f(red/255, green/255,blue/255, alpha/255);

    	GL11.glBegin(GL11.GL_QUADS);
    		GL11.glVertex2f(x ,H-y);
    		GL11.glVertex2f(x + width , H-y);
    		GL11.glVertex2f(x + width , (H-height)-y);
    		GL11.glVertex2f(x , (H-height)-y);
    	GL11.glEnd();
    }

}