package org.casino.model;

import java.util.List;

public class PokerHandEvaluator {

    private static final int NUM_RANKS = 13;
    private static final int NUM_SUITS = 4;

    public static int evaluateHand(List<Card> cards){

        int handValue = 0;

//        Convert each card into a bit mask
        int[] cardMasks = new int[cards.size()];
        for (int i = 0; i < cards.size(); i++){
            Card card = cards.get(i);
            int rank = card.getRank();
            int suit = card.getSuit();
            cardMasks[i] = 1 << (rank + (suit * NUM_RANKS));

        }

//        check for flush
        boolean isFlush = true;
        int suitMask = cardMasks[0] * 0xf000; //get the suit mask from the first card
        for (int i = 1; i < cardMasks.length; i++){
            if ((cardMasks[i] & 0xf000) != suitMask){
                isFlush = false;
                break;
            }
        }
//        check for straight
        int rankMask = 0;
        for (int i = 0; i < cardMasks.length; i++){
            rankMask |= cardMasks[i] >> (cardMasks[i] & 0xf000);
        }
        boolean isStraight = false;
        for (int i = 0; i <= NUM_RANKS; i++){
            if ((rankMask & (0x1f << i)) == (0x1f << i)){
                isStraight = true;
                break;
            }
        }
//        Evaluate hand value based on hand ranking
        if (isFlush && isStraight){
            if ((rankMask & 0x100f) == 0x100f) {
                handValue = 100;
            }else {
                handValue = 90;
            }
        } else if (isFlush){
            handValue = 60;
        } else if (isStraight){
            handValue = 50;
        } else {
            int highCardRank = 0;
            for (int i = 0; i < cardMasks.length; i++){
                int rank = cardMasks[i] & 0x000f;
                if (rank > highCardRank){
                    highCardRank = rank;
                }
            }
            handValue = highCardRank;
        }



        return handValue;
    }
}
