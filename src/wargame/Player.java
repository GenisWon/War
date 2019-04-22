/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wargame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bbgen
 */
public class Player {
    public List<Card> hand;
    
    public Player()
    {
        hand = new ArrayList<>();
    }
}
