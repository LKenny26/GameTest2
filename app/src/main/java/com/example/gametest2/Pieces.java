package com.example.gametest2;

import java.lang.reflect.Array;
//purpose
public class Pieces {

    public char[] letters_for_tiles;
    int key;
    private char letter_selected;
    public Pieces(Character letter, int key){
        this.letter_selected = letter;
    }
    public void setArray(char[] arr, int initkey){
        letters_for_tiles = new char[100];
        letters_for_tiles = arr;
        key = initkey;

        for(int i = 0; i < arr.length; i++){
            if(i <= 8){
               arr[i] = 'A';
               initkey = 1;
            }
            else if(i <= 10){
                arr[i] = 'B';
                initkey = 3;
            }
            else if(i <= 12){
                arr[i] = 'C';
                initkey = 3;
            }
            else if(i <= 16){
                arr[i] = 'D';
                initkey = 2;
            }
            else if(i <= 28){
                arr[i] = 'E';
                initkey = 1;
            }
            else if(i <= 30){
                arr[i] = 'F';
                initkey = 4;
            }
            else if(i <= 33){
                arr[i] = 'G';
                initkey = 2;
            }
            else if(i <= 35){
                arr[i] = 'H';
                initkey = 4;
            }
            else if(i <= 44){
                arr[i] = 'I';
                initkey = 1;
            }
            else if(i <= 45){
                arr[i] = 'J';
                initkey = 8;
            }
            else if(i <= 46){
                arr[i] = 'K';
                initkey = 5;
            }
            else if(i <= 50){
                arr[i] = 'L';
                initkey = 1;
            }
            else if(i <= 52){
                arr[i] = 'M';
                initkey = 3;
            }
            else if(i <= 58){
                arr[i] = 'N';
                initkey = 1;
            }
            else if(i <= 66){
                arr[i] = 'O';
                initkey = 1;
            }
            else if(i <= 68){
                arr[i] = 'P';
                initkey = 3;
            }
            else if(i <= 69){
                arr[i] = 'Q';
                initkey = 10;
            }
            else if(i <= 75){
                arr[i] = 'R';
                initkey = 1;
            }
            else if(i <= 79){
                arr[i] = 'S';
                initkey = 1;
            }
            else if(i <= 85){
                arr[i] = 'T';
                initkey = 1;
            }
            else if(i <= 89){
                arr[i] = 'U';
                initkey = 3;
            }
            else if(i <= 91){
                arr[i] = 'V';
                initkey = 4;
            }
            else if(i <= 93){
                arr[i] = 'W';
                initkey = 4;
            }
            else if(i <= 94){
                arr[i] = 'X';
                initkey = 8;
            }
            else if(i <= 96){
                arr[i] = 'Y';
                initkey = 4;
            }
            else if(i <= 97){
                arr[i] = 'Z';
                initkey = 10;
            }
            else{
                arr[i] = ' ';
                initkey = 1;
                //points will change based on letter provided by player
            }
        }
    }
    public char getLet(){
        return letter_selected;
    }
}
