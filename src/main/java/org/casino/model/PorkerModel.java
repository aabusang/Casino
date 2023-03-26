package org.casino.model;

import java.util.*;


public class PorkerModel {

    private List<Player> players;
    private List<Card> deck;
    private int NUM_CARDS_PER_PLAYER = 5;
    public void shuffleDeck(){
        Collections.shuffle(deck);
    }

    public void dealCards(){
        for (Player player: players){
            for (int i = 0; i < NUM_CARDS_PER_PLAYER; i++){
                Card card = deck.remove(0);
                player.addCard(card);
            }
        }
    }

    public Player determineWinner(){
        Player winner = null;
        int highestRank = -1;

        for (Player player : players){
            int rank = calcHandRank(player.getHand());
            if (rank > highestRank){
                winner = player;
                highestRank = rank;
            }
        }
        return winner;
    }

    private int calcHandRank(List<Card> hand){
        int[] cards = new int[hand.size()];
        for (int i = 0; i < hand.size(); i++){
            Card card = hand.get(i);
            cards[i] = card.getRank() + card.getSuit() * 13;
        }

        return 1;
//        return HandEvaluator.evaluate(cards);
    }

}
