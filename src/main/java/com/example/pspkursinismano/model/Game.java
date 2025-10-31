package com.example.pspkursinismano.model;

import com.example.pspkursinismano.model.Box;
import com.example.pspkursinismano.model.Player;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player player;
    private Box box;
    private boolean running;
    private int difficulty;
    private Random random;

    public Game() {
        player = new Player();
        box = new Box('a',0);
        running = false;
        difficulty = 1;
        random = new Random();
    }

    public void chooseDifficulty() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("+ Pasirinkite zaidimo sunkuma:                         +");
        System.out.println("+ 1) Easy                                              +");
        System.out.println("+ 2) Normal                                            +");
        System.out.println("+ 3) Hard                                              +");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        do {
            System.out.print("Iveskite skaiciu (1-3): ");
            difficulty = scanner.nextInt();
        } while (difficulty < 1 || difficulty > 3);
    }

    private char randomLetter() {
        return (char) ('a' + random.nextInt(3));
    }

    public void start(){
        running = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Paspauskite enter jei norite pradeti zaidima... ");
        scanner.nextLine();
        box.reset(randomLetter());
        gameLoop();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void gameLoop(){
        Scanner scanner = new Scanner(System.in);

        while(running && player.getLives() > 0){
            clearScreen();
            player.render();
            box.render();

            System.out.println("Pasirinkite konteineri (1-3) ir paspauskite Enter:");
            String input = "";
            try{
                if(System.in.available()>0){
                    input = scanner.nextLine();
                    if(!input.isEmpty()){
                        player.changeDiverter(input.charAt(0));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            box.move();


            if(box.getPosition() >50){
                if((char) ('a' + player.getDiverter()) == box.getLetter()){
                    player.addScore(10*difficulty);
                } else {
                    player.loseLife();
                }
                box.reset(randomLetter());
            }
            try{
                Thread.sleep(50/difficulty);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("\n\nZAIDIMAS BAIGTAS!");
        System.out.println("Galutiniai taskai: " + player.getScore());
    }


}
