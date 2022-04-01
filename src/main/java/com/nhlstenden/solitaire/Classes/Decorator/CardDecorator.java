package main.java.com.nhlstenden.solitaire.Classes.Decorator;

import main.java.com.nhlstenden.solitaire.Classes.DecoratorLibrary;
import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Interfaces.ICard;

import java.awt.*;
import java.util.EnumMap;

public class CardDecorator {

    EnumMap<Suit, String> suitIconMap;
    EnumMap<Value, String> valueIconRedMap;
    EnumMap<Value, String> valueIconBlackMap;

    public CardDecorator() {
        suitIconMap = DecoratorLibrary.getInstance().getSuitIconMap();
        valueIconBlackMap = DecoratorLibrary.getInstance().getValueIconBlackMap();
        valueIconRedMap = DecoratorLibrary.getInstance().getValueIconRedMap();
    }

    public void decorateCard(ICard card) {
        if(card.getSuit().equals(Suit.NONE)){
            System.out.println("dasd");
        }
        card.setSuitSprite(DecoratorLibrary.getInstance().getIcon(suitIconMap.get(card.getSuit())));

        if (card.isBlack()) {
            card.setValueSprite(DecoratorLibrary.getInstance().getIcon(valueIconBlackMap.get(card.getValue())));
            return;
        }

        card.setValueSprite(DecoratorLibrary.getInstance().getIcon(valueIconRedMap.get(card.getValue())));
    }

    private Color getColor(ICard card) {
        if (card.isBlack()) return Color.BLACK;
        else return Color.RED;
    }

}
