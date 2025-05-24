package com.swingy.service;

import com.swingy.model.hero.Hero;
import com.swingy.model.villain.Villain;

public class HeroService {

    private boolean didLevelUp;

    public HeroService(Hero hero, Villain villain) {

        didLevelUp = false;

        int heroLevel = hero.getLevel();
        int necessaryExperience = heroLevel * 1000 + (((heroLevel - 1) * (heroLevel - 1)) * 450);
        int gainedExperience = villain.getLevel() * 500;

        hero.setExperience(hero.getExperience() + gainedExperience);
        if (hero.getExperience() >= necessaryExperience) {
            hero.setLevel(hero.getLevel() + 1);
            hero.setExperience(hero.getExperience() - necessaryExperience);
            hero.setHp(hero.getMaxHp() + 3);
            hero.setMaxHp(hero.getHp());
            hero.setAttack(hero.getAttack() + 2);
            hero.setDefense(hero.getDefense() + 2);
            didLevelUp = true;
        }
    }

    public boolean didLevelUp() {
        return didLevelUp;
    }
}
