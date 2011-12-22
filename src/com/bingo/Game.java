package com.bingo;

import java.util.Random;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Canvas;

public class Game extends View {

	int rowCount = 5;
	int columnCount = 5;
	private int height;
	private int width;
	private Paint paintTool;
	private Cell[][] elements = null;
	private Context context;
	
	public Game(Context context) {
		super(context);
		this.context = context;
		this.setBackgroundColor(Color.WHITE);
		paintTool = new Paint();
        this.paintTool.setARGB(255, 0, 0, 0);
        this.paintTool.setAntiAlias(true);
        this.paintTool.setStyle(Style.STROKE);
        this.paintTool.setStrokeWidth(1);
        
        height = this.getHeight();
        width = this.getWidth();
        
        int cellHeight = height/rowCount;
        int cellWidth = width/columnCount;
        
        elements = new Cell[rowCount][columnCount];
        
        for(int i=0; i < rowCount; i++){
        	for(int j=0; j < columnCount; j++){
        		elements[i][j] = new Cell(cellHeight * j, cellWidth * i);
        	}
        }
        Board newBoard = new Board(rowCount, columnCount);
        for(int i=0; i < rowCount; i++){
        	for(int j=0; j < columnCount; j++){
        		elements[i][j].number = newBoard.elements[i][j];
        	}
        }        
	}
	
	 @Override
     protected void onDraw(Canvas canvas) {
             for (int i = 0; i < elements.length; i++) {
                     for (int j = 0; j < elements[i].length; j++) {
                             elements[i][j].draw(canvas, getResources(), j, i, this.getWidth()/elements.length, this.getHeight()/elements[0].length);
                     }
             }
             int cellHeight = this.getHeight()/rowCount;
             int cellWidth = this.getWidth()/columnCount;

             for (int i = 0; i <= columnCount; i++) {
                     canvas.drawLine(cellWidth * i, 0, cellWidth * i, this.getHeight(), paintTool);
             }
             for (int i = 0; i <= rowCount; i++) {
                     canvas.drawLine(0, cellHeight * i, this.getWidth(), cellHeight * i, paintTool);
             }
             super.onDraw(canvas);
     }
	 
	 @Override
     public boolean onTouchEvent(MotionEvent event) {
             int x_aux = (int) (event.getX() / (this.getWidth() / rowCount));
             int y_aux = (int) (event.getY() / (this.getHeight() / columnCount));
             elements[y_aux][x_aux].status = 1;
             //Log.d("Bingo","Touched on x: "+String.valueOf(x_aux)+", y: "+String.valueOf(y_aux));
             this.invalidate();
             Toast.makeText(context, "You have selected "+ String.valueOf(elements[y_aux][x_aux].number), Toast.LENGTH_SHORT).show();
             selectANumber();
             return false;
     }
	 
	 private void selectANumber(){
		 int[] notSelectedNumbers = new int[25];
		 int count = 0;
		 for(int i=0;i<rowCount;i++){
			 for(int j=0;j<columnCount;j++){
				 if(elements[i][j].status == Cell.NOT_SELECTED){
					 notSelectedNumbers[count] = elements[i][j].number;
					 count++;
				 }
			 }
		 }
		 if(count >0){
			 Random randomGenerator = new Random();
			 int randomInt = randomGenerator.nextInt(count);
			 int selectedNumber = notSelectedNumbers[randomInt];
			 
			 int posX=0, posY=0;
			 for(int i=0;i<rowCount;i++){
				 for(int j=0;j<columnCount;j++){
					 if(elements[i][j].number == selectedNumber){
						 posX = i;
						 posY = j;
					 }
				 }
			 }
	         Toast.makeText(context, "I have selected "+ String.valueOf(selectedNumber), Toast.LENGTH_SHORT).show();
			 elements[posX][posY].status = 1;
	         this.invalidate();
         }
	 }
	 
	
}
