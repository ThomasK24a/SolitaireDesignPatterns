package main.java.com.nhlstenden.solitaire.Classes;

import main.java.com.nhlstenden.solitaire.Enums.Suit;
import main.java.com.nhlstenden.solitaire.Enums.Value;
import main.java.com.nhlstenden.solitaire.Exceptions.ResourceNotFoundException;

import javax.swing.*;
import java.net.URL;
import java.util.EnumMap;

/**
 * Holds a read-only dictionary of assets that can be used throughout the project.
 */
public class DecoratorLibrary {

    protected static DecoratorLibrary instance;

    EnumMap<Suit, String> suitIconMap = new EnumMap<>(Suit.class);
    EnumMap<Value, String> valueIconRedMap = new EnumMap<>(Value.class);
    EnumMap<Value, String> valueIconBlackMap = new EnumMap<>(Value.class);

    public DecoratorLibrary() {
        fillSuitIconMap();
        fillValueIconMap("red.png", valueIconRedMap);
        fillValueIconMap("black.png", valueIconBlackMap);
    }

    /**
     * Dictionary with the Suit enum values as keys, and their respective image names as values. e.g. spade.png
     * @return EnumMap
     */
    public EnumMap<Suit, String> getSuitIconMap() {
        return suitIconMap;
    }

    /**
     * Dictionary with the Value enum values as keys, and their respective red image names as values. e.g. ace_red.png
     * @return EnumMap
     */
    public EnumMap<Value, String> getValueIconRedMap() {
        return valueIconRedMap;
    }

    /**
     * Dictionary with the Value enum values as keys, and their respective black image names as values. e.g. ace_black.png
     * @return EnumMap
     */
    public EnumMap<Value, String> getValueIconBlackMap() {
        return valueIconBlackMap;
    }

    /**
     * Fills the Suit EnumMap.
     */
    private void fillSuitIconMap() {
        suitIconMap.put(Suit.NONE, "blank_front_with_num_boarders.png");
        suitIconMap.put(Suit.HEART, "heart.png");
        suitIconMap.put(Suit.SPADE, "spade.png");
        suitIconMap.put(Suit.DIAMOND, "diamond.png");
        suitIconMap.put(Suit.CLUB, "club.png");
    }

    /**
     * Fills the Value EnumMaps with red or black values for the cards.
     * @param mapColor
     * @param valueMap
     */
    private void fillValueIconMap(String mapColor, EnumMap<Value, String> valueMap) {
        valueMap.put(Value.NONE, "ace_");
        valueMap.put(Value.ACE, "ace_" + mapColor);
        valueMap.put(Value.TWO, "2_" + mapColor);
        valueMap.put(Value.THREE, "3_" + mapColor);
        valueMap.put(Value.FOUR, "4_" + mapColor);
        valueMap.put(Value.FIVE, "5_" + mapColor);
        valueMap.put(Value.SIX, "6_" + mapColor);
        valueMap.put(Value.SEVEN, "7_" + mapColor);
        valueMap.put(Value.EIGHT, "8_" + mapColor);
        valueMap.put(Value.NINE, "9_" + mapColor);
        valueMap.put(Value.TEN, "10_" + mapColor);
        valueMap.put(Value.JACK, "jack_" + mapColor);
        valueMap.put(Value.QUEEN, "queen_" + mapColor);
        valueMap.put(Value.KING, "king_" + mapColor);
    }

    /**
     * Get Icon from resource folder.
     *
     * @param imageName name of the image
     * @return ImageIcon object
     */
    public ImageIcon getIcon(String imageName) {
        URL imgURL = this.getClass().getClassLoader().getResource("card_sprites/" + imageName);
        if (imgURL == null) {
            throw new ResourceNotFoundException(imageName + "sprite");
        } else {
            return new ImageIcon(imgURL);
        }
    }

    /**
     * Get the instance of this <code>DecoratorLibrary</code>
     * @return <code>DecoratorLibrary</code>.
     */
    public static DecoratorLibrary getInstance() {
        if (instance == null)
            instance = new DecoratorLibrary();

        return instance;
    }

}
