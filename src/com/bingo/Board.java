package com.bingo;

import java.util.Random;

public class Board {
	int[][] elements;
	
 	public Board(int rowCount, int columnCount){
		 elements = new int[5][5];
		 int[] numArr = new int[25];
		 Random randomGenerator = new Random();
		 int randomInt = randomGenerator.nextInt(25);
		 if (randomInt == 0) randomInt++;
		 for(int i=0; i <= 25-randomInt; i++){
			 numArr[i] = i+randomInt;
		 }
		 for(int i=25-randomInt+1; i < 25; i++){
			 numArr[i] = i-(25-randomInt);
		 }
		 
		 for(int i=0; i < rowCount; i++){
			 for(int j=0; j < columnCount; j++){
				 if(randomInt%4==0){
					 elements[i][j] = numArr[(i*rowCount)+(j)];
				 }else if(randomInt%4==1){
					 elements[j][i] = numArr[(i*rowCount)+(j)];
				 }else if(randomInt%4==2){
					 elements[i][columnCount-(j+1)] = numArr[(i*rowCount)+(j)];
				 }else {
					 elements[rowCount-(j+1)][i] = numArr[(i*rowCount)+(j)];
				 }
			 }
		 }
	 }
}
