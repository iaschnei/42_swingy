package com.swingy.storage;

import com.swingy.model.hero.Hero;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroDbRepository {
    private static final int MAX_HEROES = 3;
    
    public boolean canAddHero() throws SQLException {
        String sql = "SELECT COUNT(*) FROM HERO";
        
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                int count = rs.getInt(1);
                return count < MAX_HEROES;
            }
            return true;
        }
    }

    public void saveHero(Hero hero) throws SQLException {
        if (!canAddHero()) {
            throw new SQLException("Maximum number of heroes (" + MAX_HEROES + ") reached. Delete a save first.");
        }

        // Check if hero with this name already exists
        if (getHeroByName(hero.getName()) != null) {
            throw new SQLException("Hero with name '" + hero.getName() + "' already exists.");
        }

        String sql = "INSERT INTO HERO (name, class_name, hp, def, atk, level, exp, x_pos, y_pos, " +
                "power_second_chance, power_execute, power_escape) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, hero.getName());
            pstmt.setString(2, hero.getClassName());
            pstmt.setInt(3, hero.getHp());
            pstmt.setInt(4, hero.getDefense());
            pstmt.setInt(5, hero.getAttack());
            pstmt.setInt(6, hero.getLevel());
            pstmt.setInt(7, hero.getExperience());
            pstmt.setInt(8, hero.getXPos());
            pstmt.setInt(9, hero.getYPos());
            pstmt.setBoolean(10, hero.isPowerSecondChance());
            pstmt.setBoolean(11, hero.isPowerExecute());
            pstmt.setBoolean(12, hero.isPowerEscape());
            
            pstmt.executeUpdate();
        }
    }

    public List<String> getHeroNames() throws SQLException {
        List<String> names = new ArrayList<>();
        String sql = "SELECT name FROM HERO";
        
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        }
        return names;
    }

    public Hero getHeroByName(String name) throws SQLException {
        String sql = "SELECT * FROM HERO WHERE name = ?";

        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Hero hero = new Hero();
                    hero.setName(rs.getString("name"));
                    hero.setClassName(rs.getString("class_name"));
                    hero.setHp(rs.getInt("hp"));
                    hero.setDefense(rs.getInt("def"));
                    hero.setAttack(rs.getInt("atk"));
                    hero.setLevel(rs.getInt("level"));
                    hero.setExperience(rs.getInt("exp"));
                    hero.setXPos(rs.getInt("x_pos"));
                    hero.setYPos(rs.getInt("y_pos"));
                    hero.setPowerSecondChance(rs.getBoolean("power_second_chance"));
                    hero.setPowerExecute(rs.getBoolean("power_execute"));
                    hero.setPowerEscape(rs.getBoolean("power_escape"));
                    return hero;
                }
            }
        }
        return null;
    }

    public void deleteSave(String name) throws SQLException {
        String sql = "DELETE FROM HERO WHERE name = ?";
        
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected == 0) {
                throw new SQLException("No save found with name: " + name);
            }
        }
    }

    public int getSaveCount() throws SQLException {
        String sql = "SELECT COUNT(*) FROM HERO";
        
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        }
    }
}