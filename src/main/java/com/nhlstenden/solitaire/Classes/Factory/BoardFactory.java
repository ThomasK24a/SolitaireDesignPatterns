package main.java.com.nhlstenden.solitaire.Classes.Factory;

import main.java.com.nhlstenden.solitaire.Classes.Card;
import main.java.com.nhlstenden.solitaire.Classes.ObjectPool.CardObjectPool;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.BoardStack;
import main.java.com.nhlstenden.solitaire.Classes.Stacks.DeckStack;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import java.util.ArrayList;
import java.util.Collections;

public class BoardFactory {
    private final CardObjectPool cardObjectPool;

    public BoardFactory() {
        this.cardObjectPool = new CardObjectPool();
    }

    public void fillDeck(DeckStack deck) {
        deck.addCards(cardObjectPool.getRemainingCards());
    }

    public void fillBoardStacks(ArrayList<BoardStack> boardStacks) {
        for(int i = 0; i < boardStacks.size(); i++){
            BoardStack boardStack = boardStacks.get(i);
            ICard emptyCard = cardObjectPool.createEmptyDecoratedCard();
            boardStack.addCards(Collections.singletonList(emptyCard));
            ArrayList<ICard> cardsToAdd = cardObjectPool.getRandomCardStack(i + 1);
            boardStack.addCards(cardsToAdd);
        }
    }
}
