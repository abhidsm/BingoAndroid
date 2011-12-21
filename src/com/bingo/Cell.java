package com.bingo;

import android.graphics.Point;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Cell extends Point{
	
	int number;
	int status;
	
	public Cell(int x, int y) {	
		super(x, y);	
	}		
	
	public void draw(Canvas g, Resources res, int x, int y, int w, int h) {
		Paint paintTool = new Paint();
		paintTool.setARGB(255, 0, 0, 0);
        paintTool.setAntiAlias(true);
        paintTool.setStyle(Style.STROKE);
        paintTool.setTextSize(40);
        g.drawText(String.valueOf(this.number), (x*w)+(w/10), (y*h)+(2*h/3), paintTool);
    }
	
}
