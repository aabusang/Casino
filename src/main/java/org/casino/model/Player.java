package org.casino.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private List<Card> hand;

    public Player(String name){
        this.name = name;
        this.hand = new ArrayList<Card>();
    }


    public void addCard(Card card){
        hand.add(card);
    }

    public List<Card> getHand(){
        return hand;
    }

}

