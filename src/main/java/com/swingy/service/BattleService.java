package com.swingy.service;

import com.swingy.model.hero.Hero;
import com.swingy.model.villain.Villain;

import java.util.Random;

public class BattleService {

    private boolean battleWon;
    private final Hero hero;
    private final Villain villain;
    private HeroService heroService;
    private boolean didLevelUp;

    public BattleService(Hero hero, Villain villain) {
        this.hero = hero;
        this.villain = villain;
        Random random = new Random();

        boolean heroFirst = random.nextBoolean();
        if (heroFirst) {
            executeBattle(hero, villain);
        } else {
            executeBattle(villain, hero);
        }

        if (battleWon) {
            heroService = new HeroService(hero, villain);
            if (heroService.didLevelUp()) {
                this.didLevelUp = true;
            }
        }
    }

    private void executeBattle(Object attacker, Object defender) {
        while (hero.getHp() > 0 && villain.getHp() > 0) {
            if (attacker instanceof Hero) {
                int damage = calculateDamage(hero.getAttack(), villain.getDefense());
                villain.setHp(villain.getHp() - damage);
                if (villain.getHp() <= 0) {
                    battleWon = true;
                    break;
                }
                damage = calculateDamage(villain.getAttack(), hero.getDefense());
                hero.setHp(hero.getHp() - damage);
            } else {
                int damage = calculateDamage(villain.getAttack(), hero.getDefense());
                hero.setHp(hero.getHp() - damage);
                if (hero.getHp() <= 0) {
                    battleWon = false;
                    break;
                }
                damage = calculateDamage(hero.getAttack(), villain.getDefense());
                villain.setHp(villain.getHp() - damage);
            }
        }
    }

    private int calculateDamage(int attack, int defense) {
        int damage = attack - (int) (defense * 0.5);
        return Math.max(damage, 0);

    }

    public boolean isBattleWon() {
        return battleWon;
    }

    public boolean didLevelUp() {
        return didLevelUp;
    }
}
