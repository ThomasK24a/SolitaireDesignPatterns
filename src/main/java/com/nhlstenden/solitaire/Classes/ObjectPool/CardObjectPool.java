package main.java.com.nhlstenden.solitaire.Classes.ObjectPool;

import main.java.com.nhlstenden.solitaire.Classes.Card;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardObjectPool {
    private final ArrayList<Card> pool;

    public CardObjectPool() {
        pool = createFullDeck();
        Collections.shuffle(pool);
    }

    public ArrayList<Card> getRandomCardStack(int cardStackSize){
        ArrayList<Card> cards = new ArrayList<>();
        for(int i = 0; i < cardStackSize; i++){
            //if pool is empty return the cards gotten so far
            if (pool.size() == 0) return cards;

            cards.add(getRandomCard());
        }
        return cards;
    }

    public Card getRandomCard(){
        //return null if pool is empty
        if(pool.size() == 0) return null;
        //get a random index of the pool
        Random random = new Random();
        int randomCardIndex = random.nextInt(pool.size());

        Card card = pool.get(randomCardIndex);
        //remove the card from the pool
        pool.remove(randomCardIndex);
        return card;
    }

    public void returnCard(Card card){
        pool.add(card);
    }

    private ArrayList<Card> createFullDeck(){
        ArrayList<Card> deck = new ArrayList<>();
        for(Suit suit : Suit.class.getEnumConstants()){
            for(Value value : Value.class.getEnumConstants()){
                deck.add(new Card(suit, value, false));
            }
        }
        return deck;
    }
}
