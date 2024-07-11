package main.java.simulation.entities;

public abstract class Creature extends Entity {

    private final int attackPower;
    private final int hungerDamage;
    private boolean readyToMove;
    private final String prefersFood;
    private int hitPoints;

    public boolean isReadyToMove() {
        return readyToMove;
    }

    public void setReadyToMove(boolean readyToMove) {
        this.readyToMove = readyToMove;
    }

    public Creature(String prefersFood, int attackPower) {
        this.prefersFood = prefersFood;
        this.hitPoints = 8;
        this.readyToMove = true;
        this.hungerDamage = 1;
        this.attackPower = attackPower;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public String getPrefersFood() {
        return prefersFood;
    }

    public void increaseHitPoints(int hp){
        this.hitPoints = this.hitPoints + hp;
        if (this.hitPoints > 9) {
            this.hitPoints = 9;
        }
    }

    public void reduceHitPoints(int hp){
        this.hitPoints = this.hitPoints - hp;
    }

    public void getHungerDamage() {
        this.hitPoints = this.hitPoints - hungerDamage;
    }
}
