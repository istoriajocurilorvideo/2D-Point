package platform.javagithub;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import platform.javagithub.SoftPie;

public class Main {
	
	public static int dim = 20;
	//color
	class Color
	{
		public int r,g,b,a;
		public Color(int r,int g, int b, int a)
		{
			this.r = r;
			this.g = g;
			this.b = b;
			this.a = a;
		}
	}
	Color c = new Color(0, 150, 0, 1);
	Color c2 = new Color(120, 0, 0, 1);

	int width = soft.getWidth()/dim;
	int height = soft.getHeight()/dim;
	int mousePos[][] = new int[width+1][height+1];
	
	public static SoftPie soft = new SoftPie();
	//drawing a pixel
	public void dpixel(int x, int y, Color col)
	{
		soft.drawQuad(x, y, 1,1, col.r,col.g,col.b,col.a );
	}
	// still working on it
	public void dline(int x1,int y1, int x2,int y2, Color col)
	{
		
	}
	public void desq(int x,int y, int h, int ratio)
	{
	   for(int i=x;i<=(x+h)*ratio;i++)
			dpixel(i,y,c);
	   for(int i=y+1;i<=(y+h)*ratio;i++)
			dpixel(x,i,c);
	   for(int i=(x+h)*ratio;i>=x;i--)
		dpixel(i,(y+h)*ratio,c);
	   for(int i=ratio*(y+h);i>=y;i--)
			dpixel((x+h)*ratio,i,c);
	}
	public void dfsq(int x, int y,int h, int ratio, Color col)
	{
		soft.drawQuad(x, y, h*ratio,h*ratio,col.r,col.g,col.b,col.a );
	}
	public int[][] rm(int[][] v, int value)
	{
		for(int i=0;i<=v.length-1;i++)
			for(int j=0;j<=v[0].length-1;j++)
				v[i][j] = value;
		return v;
	}
	public void addtts(int v[][], int mposx, int mposy)
	{
		if(Mouse.isButtonDown(0))
			if(mposx <= width && mposy <= height)
			 if(v[mposx][mposy] == -1)
				 v[mposx][mposy] = 1;
	}
	public void dms(int v[][], int w)
	{
		for(int i=0;i<=v.length-1;i++)
			for(int j=0;j<=v[0].length-1;j++)
				if(v[i][j] == 1)
					dfsq(i * w, j * w, w+1, 1, c2);
	}
	public void initFuncs()
	{
		soft.init();
    	mousePos = rm(mousePos,-1);
	}
	public void runnable(int dim)
	{
//---------------------------------
		for(int x=0;x<=width;x++)
			for(int y=0;y<=height;y++)
     			desq(x * dim, y *dim ,dim , 1);
//----------------------------------	
		int pos1 = (int)(Mouse.getX()/dim);
		int pos2 = (int)((soft.getHeight()-Mouse.getY())/dim);
		
		if(pos1 <= width && pos2 <= height)
			dfsq(dim*pos1 ,dim*pos2,dim+1,1,c2);
		addtts(mousePos , pos1 , pos2);
		dms(mousePos , dim);
		
		while(Keyboard.next())
		{	
			if(Keyboard.getEventKey() == Keyboard.KEY_C)
				mousePos = rm(mousePos,-1);
			
		}
				
	}

    public static void main(String[] argv) {
    	Main main = new Main();
    	main.initFuncs(); 
    	while(soft.isRunning())
    	{
    		soft.begin();
    		dim = dim + Mouse.getDWheel()/100;
    		if(dim <= 1)
    			dim = 1;
    		main.runnable(dim);
    		soft.end();
    	}
    	soft.finish();
    }
	
    
}