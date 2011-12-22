package com.bingo;

import android.graphics.Point;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

public class Cell extends Point{
	
	public static final int NOT_SELECTED = 0;
	public static final int SELECTED = 1;
	public static final int COMPLETED_A_LINE = 2;
	
	int number;
	int status;
	
	public Cell(int x, int y) {	
		super(x, y);
		status = NOT_SELECTED;
	}		
	
	public void draw(Canvas g, Resources res, int x, int y, int w, int h) {
		Paint paintTool = new Paint();
        paintTool.setAntiAlias(true);
        paintTool.setTextSize(40);
        paintTool.setStyle(Style.STROKE);
        paintTool.setARGB(255, 0, 0, 0);
        //Log.d("Bingo","Touched on x: "+String.valueOf(x)+", y: "+String.valueOf(y));
        if(status == 1){
            paintTool.setARGB(255, 255, 0, 0);
        }
        g.drawText(String.valueOf(this.number), (x*w)+(w/10), (y*h)+(2*h/3), paintTool);

	}
	
}
