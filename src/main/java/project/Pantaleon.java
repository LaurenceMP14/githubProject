package project;

import java.util.Random;
import java.util.Scanner;
import java.lang.*;
import java.util.*;

/**
    * Simple Text-Based Adventure Game
    * @author Laurence M. Pantaleon
    * @since January 2022
*/
    
public class Pantaleon {
    
    static void gameMode(){ //method used to select the game mode
        Scanner name = new Scanner(System.in);
        System.out.println("----------------------------------------------------------------");
        System.out.println("\tTo continue to the game, please enter your name:");
        String str = name.nextLine();
        System.out.println("--------------------------------------------");
        System.out.println("\n\tWelcome to Warzone Region, " + str + " ! ");
        System.out.println("\t Please select a game mode: ");
        System.out.println("\t\t 1. Adventure Mode ");
        System.out.println("\t\t 2. Boss Fight ");

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        if (input.equals("1")) {
            System.out.println("--------------------------------------------");
            System.out.println("--------Selecting Adventure Mode!----------");
            adventure_mode();
            }

        else if (input.equals("2")) {
            System.out.println("--------------------------------------------");
            System.out.println("----------Selecting Boss Mode!-------------");
            boss_mode();
        }

        else{
            System.out.println("Invalid command");
            gameMode();
        }
    }

    static void adventure_mode() { //method of adventure mode
        //System objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
      
        //Game Variables
        String[] enemies = { "Soldier", "Infantry", "Mercenary", "Assassin", "Trooper" };
        int maxEnemyHealth = 100;
        int enemyAttackDamage = 20;

        //Player Variables
        int playerHealth = 100;
        int playerAttackDamage = 35; 
        int healthPotions = 4;
        int potion_healAmount = 40; 
        int potion_dropChance = 50;   // percent
        int runningAway_chance = 70;  

        boolean running = true;

        while(running) {
            System.out.println("--------------------------------------------");
            System.out.println("\t\tFinding an enemy.....");
            System.out.println("--------------------------------------------");
            System.out.println("\t\tENEMY FOUND!\n");
            int enemyHealth = rand.nextInt(maxEnemyHealth);     //health of the enemy is random
            String enemy = enemies[rand.nextInt(enemies.length)]; //random enemy from the array above
            System.out.println("\t* " + enemy + " has appeared! *\n");
            
            while(enemyHealth > 0) {
                System.out.println("\tYour HP: " + playerHealth);   //printing out important information
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth); //player's and enemy's HP
                choices(); //calling the method choices

                String input = in.nextLine();

                if(input.equals("1")){ //choice of attack
                    int damageDealt = rand.nextInt(playerAttackDamage);
                    int damageTaken = rand.nextInt(enemyAttackDamage);
                    
                    enemyHealth -= damageDealt; //subtract the random value of damageDealt to the enemy's health
                    playerHealth -= damageTaken; 

                    System.out.println("\t> You inflict  " + damageDealt + " damage to the  " + enemy );
                    System.out.println("\t> You receive " + damageTaken + " damage in return!\n " );

                    if (playerHealth < 1) { //player's HP is lower than 1
                        System.out.println("\t> Damage taken is too much, health is critical to go on! " );
                        break;
                    }
                }

                else if (input.equals("2")){ //choice of using a health potion
                    if(healthPotions > 0) {
                        healthPotions--; //decrement health potions
                        int healAmount = rand.nextInt(potion_healAmount); //storing the random value of potion_healAmount to healAmount
                        playerHealth += healAmount;
                        System.out.println("\t> You have drink a health potion, healing yourself for " + healAmount + " HP."  
                                        + "\n\t> You HP is now " + playerHealth + "."
                                        + "\n\t You now have " + healthPotions + " health potions left.\n"); //printing information
                    }
                    else { //no health potion left
                        System.out.println("\t Sorry, you have no health potions left. Defeat the enemy for a chance to to get one!\n ");
                    }
                }

                else if (input.equals("3")){ //choice of running away
                    int chance_runAway = rand.nextInt(runningAway_chance); //storing the random value of runningAway_chance to chance_runAway
                    System.out.println("\t>You have " + chance_runAway + " percent chance to run away from the " + enemy);
                    System.out.println("\t>Incoming fatal attacks");
                        if (chance_runAway > 33) { //chance_runAway should be 34% or more than 1/3 for successful escape
                            System.out.println("\n\t* SUCCESSFUL ESCAPE! *");
                            choiceToFight();
                        }
                        else {
                            System.out.println("\n\t* FAILED ESCAPE! *");
                            playerHealth = 0; 
                            break;
                        }
                }
                
                else {
                    System.out.println("Invalid command");
                }
            }

            if (playerHealth < 1) { //defeating the enemy
                System.out.println("\tYou have been defeated by the " + enemy + "!");
                System.out.println("\tYou flop out of the Warzone, weak from the battle.\n");
                playAgain();
            }

            System.out.println("--------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! #");
            System.out.println(" # You have " + playerHealth + "HP left # ");
            
            if (rand.nextInt(100) > potion_dropChance) { //random chances of potion should be above 50%
            healthPotions++;
            System.out.println(" # The enemy dropped a health potion! # ");
            System.out.println(" # You now have " + healthPotions + " health potion(s). # ");
            }
        }
    }

    static void boss_mode() { //method for boss mode
        //System objects
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
      
        //Game Variables
        String[] enemiesBoss = { "Arishem: The Judge", "Eson: The Searcher", "Jemiah: The Analyzer", "Nezarr: The Calculator", "Tiamut: The Communicator" };
        int maxBossHealth = 200;    //name of the random Bosses
        int bossAttackDamage = 50;
        int bossHealAmount = 50;
        int bossChanceToHeal = 100; //percent

        //Player Variables
        int playerHealth = 150;
        int playerAttackDamage = 45;
        int healthPotions = 3;
        int potion_healAmount = 50;
        int potion_dropChance = 40; //percent
        int runningAway_chance = 50; 

        boolean running = true;

        while(running) {
            System.out.println("--------------------------------------------");

            System.out.println("\tAn enemy boss is coming behind you! Beware.\n");
            int bossHealth = rand.nextInt(maxBossHealth);
            System.out.println("--------------------------------------------");
            String enemyBoss = enemiesBoss[rand.nextInt(enemiesBoss.length)];
            System.out.println("\t* " + enemyBoss + " has appeared! *\n");
            
            while(bossHealth > 0) {
                System.out.println("\tYour HP: " + playerHealth);
                System.out.println("\t" + enemyBoss + "'s HP: " + bossHealth);
                choices();

                String input = in.nextLine();

                if(input.equals("1")){
                    int damageDealt = rand.nextInt(playerAttackDamage);
                    int damageTaken = rand.nextInt(bossAttackDamage);
                    
                    bossHealth -= damageDealt;
                    playerHealth -= damageTaken;

                    System.out.println("\t> You inflict " + damageDealt + " damage to " + enemyBoss);
                    System.out.println("\t> You receive " + damageTaken + " damage in retaliation!\n " );

                    int chanceOfBossToHeal = rand.nextInt(bossChanceToHeal); //storing the random value of bossChanceToHeal to chanceOfBossToHeal
                    if (chanceOfBossToHeal > 50) { //should be above 50% for the Boss to heal
                        int boss_HealAmount = rand.nextInt(bossHealAmount);
                        bossHealth += boss_HealAmount;
                        System.out.println("\t> " + enemyBoss + " heal itself for " + boss_HealAmount + " HP ");
                        System.out.println("\t> " + enemyBoss + " HP is now " + bossHealth + " . \n");
                    }

                    if (playerHealth < 1) {
                        System.out.println("\t> Damage taken is too much, health is critical to go on! " );
                        break;
                    }

                }
                else if (input.equals("2")){
                    if(healthPotions > 0) {
                        healthPotions--;
                        playerHealth += potion_healAmount;
                        System.out.println("\t> You have drink a health potion, healing yourself for " + potion_healAmount + " HP."  
                                        + "\n\t> You HP is now " + playerHealth + "."
                                        + "\n\t You now have " + healthPotions + " health potions left.\n");
                    }
                    else {
                        System.out.println("\t Sorry, you have no health potions left. Defeat the enemy for a chance to to get one!\n ");
                    }
                }

                else if (input.equals("3")){
                    int chance_runAway = rand.nextInt(runningAway_chance);
                    System.out.println("\tYou have " + chance_runAway + " percent chance to run away from the " + enemyBoss);
                    System.out.println("\tIncoming fatal attacks");
                        if (chance_runAway > 66) { //chance_runAway should be 67% or more than 2/3 for successful escape
                            System.out.println("\n\t* SUCCESSFUL ESCAPE! *");
                            choiceToFight();
                        }
                        else {
                            System.out.println("\n\t* FAILED ESCAPE! *");
                            playerHealth = 0; 
                            break;
                        }
                }
                
                else {
                    System.out.println("Invalid command");
                }
            }

            if (playerHealth < 1) {
                System.out.println("\tYou have been defeated by " + enemyBoss + "!");
                System.out.println("\tYou flop out of the Death Zone, weak from the battle.\n");
                playAgain();
            }

            System.out.println("--------------------------------------------");
            System.out.println(" # " + enemyBoss + " was defeated! #");
            System.out.println(" # You have " + playerHealth + "HP left # ");
            
            if (rand.nextInt(100) < potion_dropChance) {
            healthPotions++;
            System.out.println(" # The enemy dropped a health potion! # ");
            System.out.println(" # You now have " + healthPotions + " health potion(s). # ");
            }
        }
    }

    static void options(){ //method for options
        System.out.println("-------------------------------------------------");
        System.out.println("\tWELCOME TO MY TEXT-BASED SIMPLE ADVENTURE GAME!");
        System.out.println("What do you want to do now?");
        System.out.println("\t1. Play!");
        System.out.println("\t2. About the Game");
        System.out.println("\t3. Exit the game.");

        Scanner in = new Scanner(System.in); //user input
        String input = in.nextLine();

        if (input.equals("1")) {
            System.out.println("\t* You have chosen to play, goodluck on your battles! *");
            gameMode(); //if input is 1, call method gameMode
            }

        else if (input.equals("2")) {
            System.out.println("\t* About the Game *");
            aboutGame(); //if input is 2, displaying the game mechanics
     
        }
        else if (input.equals("3")) {
            System.out.println("\t* EXITING THE GAME... *");
            System.exit(0); //exiting the game
        }
    }

    static void aboutGame(){ //method for aboutGame, printing these lines
        System.out.println("-------------------------------------------------");
        System.out.println(">The inspiration of this game is from my hobbies of playing online games and watching movies especially Marvel.!");
        System.out.println(">This text-based game has two modes: adventure and boss mode. ");
        System.out.println(">Adventure mode is much easy than the boss mode as the enemies' HP and attack power is lesser than of the Bosses.");
        System.out.println(">Player attack damage is randomly selected up to 35 in adventure and 45 in boss mode");
        System.out.println(">Your starting HP in adventure mode is 100 and 150 in boss");
        System.out.println(">Random enemies would have an HP up to 100 and Bosses would have a maximun of 200 HP");
        System.out.println(">In Boss mode, Bosses have a chance to heal themselves.");
        System.out.println(">Also, amount of healing potion to yourself in Boss Mode is certain to add 50 HP to your health");
        System.out.println(">In adventure mode, it is randomly added to your health up to 40 HP\n");
        System.out.println(">This is a fun and simple game that you will surely enjoy");
        System.out.println(">@Author - Laurence M. Pantaleon");
        System.out.println("-------------------------------------------------");
        options(); //calling the options method again
    }

    static void choices(){ //method for choices of the player
        System.out.println("\n\tWhat would you like to do?");
        System.out.println("\t1. Attack the enemy!");
        System.out.println("\t2. Drink healing potion");
        System.out.println("\t3. Run!");
    }

    static void choiceToFight(){ //method for choiceToFight
        System.out.println("-------------------------------------------------");
        System.out.println("What do you want to do now?");
        System.out.println("1. Continue Fighting!");
        System.out.println("2. Exit the Warzone.");

        Scanner in = new Scanner(System.in); //user input
        String input = in.nextLine();

        if (input.equals("1")) {
            System.out.println("You continue on your adventure!");
            }

        else if (input.equals("2")) {
            System.out.println("You exit the warzone, successful from your battles!"); 
            playAgain(); //calling the playAgain method
        }
        else{
            System.out.println("Invalid command");
            choiceToFight();
        }
    }

    static void playAgain(){ //method for playAgain
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");
        System.out.println("Do you want to play again?");
        System.out.println("1. Yes!");
        System.out.println("2. No.");

        Scanner in = new Scanner(System.in); //user input
        String input = in.nextLine();

        while (!input.equals("1") && !input.equals("2")) {
            System.out.println("Invalid command");
            input = in.nextLine();
        }
        if (input.equals("1")) {
            gameMode(); //calling the gameMode method
            }
        else if (input.equals("2")) {
            System.out.println("\t\t\t** EXITING THE GAME **");
            System.out.println("\t\t\t************************");
            System.out.println("\t\t\t* THANKS FOR PLAYING! *");
            System.out.println("\t\t\t************************");
            System.exit(0);
        }
    }

public static void main(String[] args) { //calling the main method
    options(); 
    }
}