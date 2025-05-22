package com.swingy.controller;

import com.swingy.model.hero.Hero;
import com.swingy.storage.HeroDbRepository;
import com.swingy.view.View;

import java.sql.SQLException;

public class HeroLoader {
    private final HeroDbRepository heroRepository;
    private final View view;
    private Hero currentHero;

    public HeroLoader(HeroDbRepository heroRepository, View view) {
        this.heroRepository = heroRepository;
        this.view = view;
    }

    public Hero loadHero(String heroName) {
        try {
            Hero hero = heroRepository.getHeroByName(heroName);
            if (hero != null) {
                currentHero = hero;
                view.showMessage("Hero '" + heroName + "' loaded successfully!");
                view.showHeroStats(hero);
                return hero;
            } else {
                view.showError("Hero '" + heroName + "' not found!");
                return null;
            }
        } catch (SQLException e) {
            view.showError("Error loading hero: " + e.getMessage());
            return null;
        }
    }

    public Hero getCurrentHero() {
        return currentHero;
    }
}
