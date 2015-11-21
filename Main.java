package platform.javagithub;

import org.lwjgl.input.Mouse;

import platform.javagithub.SoftPie;

public class Main {
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
	
	int mousePos[][] = new int[32][32];
	int width;
	int height;
	
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
	public int[][] rm(int[][] v)
	{
		int n,m;
		n = v.length;
		m = v[0].length;
		int temp[][] = new int[n][m];
		for(int i=0;i<=n;i++)
			for(int j=0;j<=m-1;j++)
				temp[i][j] = -1;
		return temp;
	}
	/*public void dbsos(MouseComponents[] v, int mposx, int mposy, int w)
	{
		if(v.length == 0)
			return;
		if(Mouse.isButtonDown(0))
		{
			for(int i=1;i<=v.length;i++)
				{
				if(((v[i].pos1 == -1) && (v[i].pos2 == -1))||(v[i].pos1 != mposx) && (v[i].pos2) != mposy)
					{   v[i].pos1 = mposx;
						v[i].pos2 = mposy;}
				}
		}
		for(int i=1;i<=v.length;i++)
			if((v[i].pos1 != -1) && (v[i].pos2 != -1))
				dfsq(w*mposx, w*mposy, w,1, c2);
	}*/
	public void initFuncs()
	{
		soft.init();
    	mousePos = rm(mousePos);
	}
	public void runnable(int w)
	{
		width = soft.getWidth()/w;
		height = soft.getHeight()/w;
//---------------------------------
		for(int x=0;x<=width;x++)
			for(int y=0;y<=height;y++)
     			desq(x * w, y *w ,w , 1);
//----------------------------------	
		int pos1 = (int)(Mouse.getX()/w);
		int pos2 = (int)((soft.getHeight()-Mouse.getY())/w);
		dfsq(w*pos1 ,w*pos2,w+1,1,c2);
		//dbsos(m , pos1 , pos2, w+1 );
	}

    public static void main(String[] argv) {
    	Main main = new Main();
    	main.initFuncs();
    	while(soft.isRunning())
    	{
    		soft.begin();
    			main.runnable(30);
    		soft.end();
    	}
    	soft.finish();
    }
	
    
}