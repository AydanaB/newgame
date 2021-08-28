package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static int medicHealth = 700;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {270, 260, 250, 240};
    public static int[] heroesDamage = {25, 15, 20, 30};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Archer"};
    public static int roundCounter = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            round();
        }
    }

    public static void round() {
        if (bossHealth > 0) {
            changeDefenceType();
            bossHits();
        }
        heroesHit();
        printStatistics();
    }

    public static void changeDefenceType() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose: " + bossDefenceType);
    }
    
    public static void printStatistics() {
        System.out.println("_________________________");
        System.out.println("Round: " + roundCounter);
        roundCounter++;
        System.out.println("Boss Health: " + bossHealth);
        int min = heroesHealth[0];
            for (int i : heroesHealth) {
                if (i < min){
                min = i;
                if (min > 0 && min < 100){
                    min = min + 100;
                }
            }
                System.out.println("Heroes Health: " + min);
            }
        System.out.println("Medic Health: " + medicHealth);
        System.out.println("_________________________");
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
                if (medicHealth > 0) {
                    if (medicHealth - bossDamage < 0) {
                        medicHealth = 0;
                    } else {
                        medicHealth = medicHealth - bossDamage;
                    }
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int randomValue = random.nextInt(10);
                    heroesDamage[i] = heroesDamage[i] - randomValue;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
                if (bossHealth - heroesDamage[i] < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }

    public static boolean isGameOver() {
        boolean bossDead = false;
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            bossDead = true;
            return bossDead;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
            return allHeroesDead;
        }
        return allHeroesDead;
    }
}
