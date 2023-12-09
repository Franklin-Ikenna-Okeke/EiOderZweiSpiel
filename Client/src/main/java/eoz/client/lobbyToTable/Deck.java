package eoz.client.lobbyToTable;

import javafx.scene.image.Image;





public class Deck {


    public Deck( Card[] deck) {
        deck =new  Card[76];
        int index=1;

        for (int i=1; i<11; i++){
            deck[index++] =new Card(index,"Koerner" ,new Image(getClass().getResource("/cards/zwei.png").toString()),2 );
            deck [index++] =new Card(index,"Koerner" ,new Image(getClass().getResource("/cards/dreik.png").toString()),3 );
            deck [index++] =new Card(index,"Koerner" ,new Image(getClass().getResource("/cards/vier.png").toString()),4 );
            deck [index++] =new Card(index ,"Biokörner" ,new Image(getClass().getResource("/cards/zweib.png").toString()),2);
            deck [index++] =new Card(index ,"Biokörner" ,new Image(getClass().getResource("/cards/drei.png").toString()),3);


        }
        for (int j=1 ;j< 9;j++){
            deck [index++] =new Card(index, "Biokörner" ,new Image(getClass().getResource("/cards/drei.png").toString()),1);
        }
        for (int k=1 ;k< 13;k++){
            deck [index++] =new Card( index,"Fuchs" ,new Image(getClass().getResource("/cards/fuchs.png").toString()),0);
        }
        for (int l=1 ;l< 5;l++){
            deck [index++] =new Card(index, "Kuckuck" ,new Image(getClass().getResource("/cards/kuckuck.png").toString()),0);
        }
        deck [index++] =new Card(index, "Hahn" ,new Image(getClass().getResource("/cards/hahn.png").toString()),0);



    }


}
