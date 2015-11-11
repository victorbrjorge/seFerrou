/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seferrou;

/**
 *
 * @author victor
 */
public class Player {

    private String Name;
    private Hand currentHand;

    private Integer lifes;

    public Player(String Name, Integer lifes) {
        this.Name = Name;
        this.lifes = lifes;
        currentHand = new Hand();
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Hand getCurrentHand() {
        return currentHand;
    }

    public void setCurrentHand(Hand currentHand) {
        this.currentHand = currentHand;
    }

    public Integer getLifes() {
        return lifes;
    }

    public void lostLife() {
        this.lifes--;
    }
}
