package rmi;

import sharedClasses.Hand;
import sharedClasses.ServerCard;
import sharedClasses.ServerPlayer;
import sharedClasses.ServerTable;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameLogic {
    public ServerPlayer findWinningPlayer(Map<UUID, ServerPlayer> spielerArray, ServerTable serverTable) {
        if (serverTable.getMoveCount() < 1000) {              //Spätestens nach 1000 Durchläufen terminiert das Spiel (sollte eigentlich nie dadurch passieren)
            for (Map.Entry<UUID, ServerPlayer> entry  : spielerArray.entrySet()) {              // Überprüft ob einer der Spieler gewonnen hat
                ServerPlayer spieler = entry.getValue();
                if (spieler.getPunkte() >= 9 && spielerArray.size() == 2) {                 // 2 Spieler: 9 Eier
                    return spieler;
                } else if (spieler.getPunkte() >= 8 && spielerArray.size() == 3) {          // 3 Spieler: 8 Eier
                    return spieler;
                }else if (spieler.getPunkte() >= 7 && spielerArray.size() == 4) {           // 4 Spieler: 7 Eier
                    return spieler;
                }else if (spieler.getPunkte() >= 6 && spielerArray.size() == 5) {           // 6 Spieler: 5 Eier
                    return spieler;
                }else if (spieler.getPunkte() >= 5 && spielerArray.size() == 6) {           // 6 Spieler: 5 Eier
                    return spieler;
                }
            }
        }
        return null;
    }



    public void checkDrawnCard (ServerCard drawnCard) {
        //TODO add the correct methods to the checkDrawnCard method in GameLogic class
        //ServerCard card = table.karteZiehen() ;                                         //Die oberste Karte wird gezogen und zwischengespeichert
        //kartendeck.remove(0);
        if (drawnCard.getType() == "Fuchs") {                                           //Wenn die oberste Karte ein Fuchs ist wird die Methode fuchsklau aufgerufen
            System.out.println("steal card");
            //fuchsklau(spieler, table);
        }
        if (drawnCard.getType() == "Koerner") {                                          //Wenn die oberste Karte eine Kornkarte ist wird diese dem Spieler hinzugefügt
            System.out.println("Add this card to hand of the current player.");
            /*
            spieler.add(card);
            int xyz = spieler.getKornzahl() + card.getValue();
            spieler.setKornzahl(xyz);

             */

        }
        if (drawnCard.getType() == "Kuckuck") {                                        //Wenn die oberste Karte eine Kuckuckskarte ist erhält der Spieler einen Punkt
            System.out.println("Add the kuckuck card and increase the player point by 1 ");
            /*
            spieler.add(card);
            //TODO REMOVE THE CARD LATER
            spieler.setPunkte(spieler.getPunkte() + 1);

             */

        }

    }



}
