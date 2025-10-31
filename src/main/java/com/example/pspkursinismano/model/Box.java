package com.example.pspkursinismano.model;


public class Box {

    private char letter;
    private int position;

    public Box(char letter, int position) {
        this.letter = letter;
        this.position = 0;
    }

    public void move(){
        position++;
    }
    public int getPosition() {
        return position;
    }
    public char getLetter() {
        return letter;
    }
    public void reset(char newLetter){
        this.letter = newLetter;
        this.position = 0;
    }

    public void render() {
        System.out.println("=====================================================================");
        System.out.println(" ".repeat(position) + " _______");
        System.out.println(" ".repeat(position) + "|   " + letter + "  |");
        System.out.println(" ".repeat(position) + " -------");
        System.out.println("=====================================================================");
    }

}
