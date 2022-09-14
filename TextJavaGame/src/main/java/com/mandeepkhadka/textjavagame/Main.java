/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mandeepkhadka.textjavagame;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author man_d
 */
public class Main {

    public static void main(String[] args) {
        // System objects
        Scanner userInput = new Scanner(System.in);
        Random randomChoice = new Random();

        // Game variables
        String[] enemies = {"Skeleton", "Zombie", "Warrior", "Assassin"};
        int maxEnemyHealth = 100;
        int enemyAttackDamage = 25;

        // Player variables
        int health = 120;
        int attackDamage = 30;
        int numHealthPotions = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChance = 50; // Percentage drop

        boolean gameIsRunning = true;

        System.out.println("Welcome to the Dungeon!");
        GAME:
        while (gameIsRunning) {
            System.out.println("------------------------------------------------");
            int enemyHealth = randomChoice.nextInt(maxEnemyHealth); //generates a random integer between 0 and given integer value
            String enemy = enemies[randomChoice.nextInt(enemies.length)];
            System.out.println("\t### " + enemy + " appeared! ###\n");
            while (enemyHealth > 0) {
                System.out.println("\tYour HP: " + health);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");

                int input = Integer.valueOf(userInput.nextLine());
                if (input == 1) {
                    int damageDealt = randomChoice.nextInt(attackDamage);
                    int damageTaken = randomChoice.nextInt(enemyAttackDamage);
                    enemyHealth -= damageDealt;
                    health -= damageTaken;
                    System.out.println("\t>> You struck " + enemy + " for " + damageDealt + " damage.");
                    System.out.println("\t>> You took " + damageTaken + " damage in retaliation");
                    if (health < 1) {
                        System.out.println("\t>> You have taken too much damage, too weak to go on!");
                        break;
                    }
                } else if (input == 2) {
                    if (numHealthPotions > 0) {
                        health += healthPotionHealAmount;
                        numHealthPotions--;
                        System.out.println("\t>> You drank a health potion, healing yourself for " + healthPotionHealAmount + " . "
                                + "\n\t>> You now have " + health + " HP."
                                + "\n\t>> You have " + numHealthPotions + " health potions left.\n");
                    } else {
                        System.out.println("\t>> You do not have any more health potions left! Defeat enemies for a chance to get one!!\n");
                    }
                } else if (input == 3) {
                    System.out.println("\t>> You ran away from the " + enemy + "!");
                    continue GAME;
                } else {
                    System.out.println("\t>> INVALID COMMAND");
                }
            }
            if (health < 1) {
                System.out.println("\t>> You limped out of the dungeon, too weak to carry on the battle.");
                break;
            }

            System.out.println("------------------------------------------------");
            System.out.println("\t### " + enemy + " was defeated! ###");
            System.out.println("\t>> You have " + health + " HP left.\n");
            if (randomChoice.nextInt(100) < healthPotionDropChance) {
                numHealthPotions++;
                System.out.println("\t>> The " + enemy + " dropped a health potion!\n");
                System.out.println("\t>> You now have " + numHealthPotions + " health potions.\n");
            }
            System.out.println("------------------------------------------------");
            System.out.println("\n\t### What would you like to do next? ###\n");
            System.out.println("\t1. CONTINUE PLAYING");
            System.out.println("\t2. EXIT THE GAME");
            String inputToNextStage = userInput.nextLine();
            if (!inputToNextStage.equals("1") && !inputToNextStage.equals("2")) {
                System.out.println("\t>> INVALID COMMAND");
                inputToNextStage = userInput.nextLine();
            }
            if (inputToNextStage.equals("1")) {
                System.out.println("\t>> You are continuing your adventure in the dungeon!\n");
            } else {
                System.out.println("\t>> You exited the game successfully!\n");
                break;
            }
        }
        System.out.println("##########################");
        System.out.println("### THANKS FOR PLAYING ###");
        System.out.println("##########################");
    }
}
