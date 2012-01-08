package com.bingo;

import java.util.Random;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Canvas;

public class Game extends View {

	public static final int EASY_MODE = 19;
	public static final int MEDIUM_MODE = 18;
	public static final int DIFFICULT_MODE = 17;
	public static final int TOTAL_NUMBERS = 25;
	
	int rowCount = 5;
	int columnCount = 5;
	private int height;
	private int width;
	private Paint paintTool;
	private Cell[][] elements = null;
	private Context context;
	boolean gameOver = false;
	
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
		 if(!gameOver){
             int x_aux = (int) (event.getX() / (this.getWidth() / rowCount));
             int y_aux = (int) (event.getY() / (this.getHeight() / columnCount));
             if(elements[y_aux][x_aux].status == Cell.NOT_SELECTED){
                 elements[y_aux][x_aux].status = Cell.SELECTED;
                 //Log.d("Bingo","Touched on x: "+String.valueOf(x_aux)+", y: "+String.valueOf(y_aux));
            	 //Toast.makeText(context, "You have selected "+ String.valueOf(elements[y_aux][x_aux].number), Toast.LENGTH_SHORT).show();
                 int totalLines = markTheLine();
            	 this.invalidate();
            	 if(!isGameOver(totalLines)){
                	 selectANumber();
                	 totalLines = markTheLine();
                	 this.invalidate();
                	 isGameOver(totalLines);
                 }
             }
		 }
		 return false;
     }
	 
	 private boolean isGameOver(int totalLines){
    	 int selectedNumbersCount = TOTAL_NUMBERS - getNotSelectedNumbersCount();
    	 
         if(totalLines == rowCount){
        	 gameOver = true;
             if(selectedNumbersCount >= EASY_MODE){
            	 BingoApplication.result = BingoApplication.TIE;
             }else{
            	 BingoApplication.result = BingoApplication.WIN;
             }
             showResultPage();
         }else if(selectedNumbersCount >= EASY_MODE){
        	 gameOver = true;
        	 BingoApplication.result = BingoApplication.FAIL;
        	 showResultPage();
         }
         return gameOver;
	 }
	 
	 private void showResultPage(){
		Intent intent = new Intent(this.getContext().getApplicationContext(), ResultActivity.class);
		this.getContext().startActivity(intent);
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
			 elements[posX][posY].status = Cell.SELECTED;
         }
	 }
	 
	 public int markTheLine(){
		 int totalLines = 0;
		 int selectedCountInALine;
		 for(int i=0;i<rowCount;i++){
			 selectedCountInALine = 0;
			 for(int j=0;j<columnCount;j++){
				 if(elements[i][j].status != Cell.NOT_SELECTED){
					 selectedCountInALine++;
				 }
			 }
			 if(selectedCountInALine == columnCount){
				 totalLines++;
				 for(int j=0;j<columnCount;j++){
					 elements[i][j].status = Cell.COMPLETED_A_LINE;
				 }
			 }

			 selectedCountInALine = 0;
			 for(int j=0;j<columnCount;j++){
				 if(elements[j][i].status != Cell.NOT_SELECTED){
					 selectedCountInALine++;
				 }
			 }
			 if(selectedCountInALine == columnCount){
				 totalLines++;
				 for(int j=0;j<columnCount;j++){
					 elements[j][i].status = Cell.COMPLETED_A_LINE;
				 }
			 }

		 }
		 
		 selectedCountInALine = 0;
		 for(int i=0;i<rowCount;i++){
			 if(elements[i][i].status != Cell.NOT_SELECTED){
				 selectedCountInALine++;
			 }
		 }
		 if(selectedCountInALine == rowCount){
			 totalLines++;
			 for(int i=0;i<rowCount;i++){
				 elements[i][i].status = Cell.COMPLETED_A_LINE;
			 }
		 }
		 
		 selectedCountInALine = 0;
		 for(int i=0;i<rowCount;i++){
			 if(elements[rowCount-(i+1)][i].status != Cell.NOT_SELECTED){
				 selectedCountInALine++;
			 }
		 }
		 if(selectedCountInALine == rowCount){
			 totalLines++;
			 for(int i=0;i<rowCount;i++){
				 elements[rowCount-(i+1)][i].status = Cell.COMPLETED_A_LINE;
			 }
		 }
		 
		 return totalLines;
	 }
	 
	 private int getNotSelectedNumbersCount(){
		 int count = 0;
		 for(int i=0;i<rowCount;i++){
			 for(int j=0;j<columnCount;j++){
				 if(elements[i][j].status == Cell.NOT_SELECTED){
					 count++;
				 }
			 }
		 }
		 return count;
	 }
	 
	
}
