package com.bingo;

import java.util.Arrays;
import java.util.Random;

import android.content.Context;
import android.view.View;
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
	
	public Game(Context context) {
		super(context);
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
        createBoard();
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
	 
	 private void createBoard(){
		 int[] numArr = new int[25];
		 Random randomGenerator = new Random();
		 int randomInt = randomGenerator.nextInt(25);
		 for(int i=0; i <= 25-randomInt; i++){
			 numArr[i] = i+randomInt;
		 }
		 for(int i=25-randomInt+1; i < 25; i++){
			 numArr[i] = i-(25-randomInt);
		 }
		 
		 for(int i=0; i < rowCount; i++){
			 for(int j=0; j < columnCount; j++){
				 if(randomInt%2==0){
					 elements[i][j].number = numArr[(i*rowCount)+(j)];
				 }else{
					 elements[j][i].number = numArr[(i*rowCount)+(j)];
				 }
			 }
		 }
	 }
	
}
