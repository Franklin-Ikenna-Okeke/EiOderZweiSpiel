package rmi;

public enum BroadcastType {
    SWITCH_TO_TABLE,
    START_GAME,

    UPDATE_TIMER_LABEL,

    Hahn_karte_Geben,

    // Spieler zieht eine Körner-Karte
    HAS_DRAWN_A_CARD,

    // Spieler zieht eine Fuchs-Karte
    DRAWN_FOX_CARD,

    // Spieler zieht eine Kuckucks-Karte
    DRAWN_KUCKUCK_CARD,

    CHANGE_ROOSTER_PLAYER,

    SWITCH_TO_RESULTS,

    CHAT,

    CARD_DISCARDED,
    ONE_CARD_STOLEN,

    ALL_CARDS_STOLEN,

    STEALING_CARD_COMPLETED,

    REMOVE_FOX_CARD,

    PLAYER_JOINED_GAME,

}
