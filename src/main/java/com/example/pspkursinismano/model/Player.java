package com.example.pspkursinismano.model;

public class Player {
    private int lives;
    private int score;
    private int diverter;

    public Player() {
        lives = 3;
        score = 0;
        diverter = 1;
    }

    public void changeDiverter(char key){
        if(key == '1') diverter =0;
        else if(key == '2') diverter =1;
        else if(key == '3') diverter =2;

    }
    public int getDiverter(){
        return diverter;
    }
    public int getLives() {
        return lives;
    }
    public int getScore() {
        return score;
    }


    public void addScore(int score){
        this.score += score;
    }
    public void loseLife(){
        lives--;
    }

    public void render() {
        System.out.println("---------------------------------");
        System.out.println("Gyvybes: " + lives);
        System.out.println("Taskai: " + score);
        System.out.println("---------------------------------");
        System.out.println("Prijungtas konteineris: " + (char) ('a' + diverter));
    }

}
