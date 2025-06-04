package com.swingy.view.gui;

import com.swingy.controller.GameController;
import com.swingy.model.hero.Hero;
import com.swingy.model.map.GameMap;
import com.swingy.model.villain.Villain;
import com.swingy.view.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GUIView implements View {

    private JFrame mainFrame;
    private JPanel mapPanel;
    private JPanel statsPanel;
    private JTextArea messageArea;
    private JTextField inputField;
    private String currentState;
    private final boolean visible_enemies;
    GameController controller;


    public GUIView(boolean visible_enemies) {
        this.visible_enemies = visible_enemies;
        initializeGUI();
        currentState = null;
    }

    public void setController(GameController controller) {
        this.controller = controller;
    }

    private void initializeGUI() {
        mainFrame = new JFrame("Swingy");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setResizable(false);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setFocusable(true);

        mainFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {

                if (currentState != null && currentState.equals("requestMovement")) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> controller.onHeroMovement("N");
                        case KeyEvent.VK_DOWN -> controller.onHeroMovement("S");
                        case KeyEvent.VK_RIGHT -> controller.onHeroMovement("E");
                        case KeyEvent.VK_LEFT -> controller.onHeroMovement("W");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });


        JPanel mainPanel = new JPanel(new BorderLayout());

        mapPanel = new JPanel();
        mapPanel.setBorder(BorderFactory.createTitledBorder("Map Panel"));

        statsPanel = new JPanel();
        statsPanel.setPreferredSize(new Dimension(200, 0));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Hero Stats"));

        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(BorderFactory.createTitledBorder("Messages"));

        messageArea = new JTextArea(5, 40);
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        inputField = new JTextField();
        inputField.addActionListener(e -> handleInput(inputField.getText()));

        messagePanel.add(scrollPane, BorderLayout.CENTER);
        messagePanel.add(inputField, BorderLayout.SOUTH);

        mainPanel.add(mapPanel, BorderLayout.CENTER);
        mainPanel.add(statsPanel, BorderLayout.EAST);
        mainPanel.add(messagePanel, BorderLayout.SOUTH);

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
        mainFrame.requestFocus();
    }

    private void handleInput(String input) {
        inputField.setText("");

        switch (currentState) {
//            case "requestLoadOrCreate" -> controller.onLoadOrCreateChoice(input);
            case "requestHeroName" -> controller.heroCreationInput.onNameInput(input);
//            case "requestHeroClass" -> controller.heroCreationInput.onClassInput(input);
//            case "requestGameStartConfirm" -> controller.heroCreationInput.onGameStartConfirm(input);
//            case "requestHeroSelection" -> controller.onHeroLoad(input);
            case "requestMovement" -> controller.onHeroMovement(input);
            case "requestBattleDecision" -> controller.onBattleDecision(input);
//            case "requestArtifactDecision" -> controller.onArtifactDecision(input);
        }
    }

    @Override
    public void showHeroStats(Hero hero) {
        statsPanel.removeAll();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));

        addStatLabel("Name: " + hero.getName());
        addStatLabel("Class: " + hero.getClassName());
        addStatLabel("Level: " + hero.getLevel());
        addStatLabel("HP: " + hero.getHp() + "/" + hero.getMaxHp());
        addStatLabel("Attack: " + hero.getAttack());
        addStatLabel("Defense: " + hero.getDefense());

        if (hero.getArtifact() != null) {
            addStatLabel("Artifact: " + hero.getArtifact().getType() + " (" + hero.getArtifact().getLevel() + ")");
        }

        int level = hero.getLevel();
        int currentExp = hero.getExperience();
        int necessaryExp = level * 1000 + (((level - 1) * (level - 1)) * 450);

        JProgressBar xpBar = new JProgressBar(0, necessaryExp);
        xpBar.setValue(currentExp);
        xpBar.setStringPainted(true);
        xpBar.setString(String.format("%d / %d XP", currentExp, necessaryExp));
        xpBar.setAlignmentX(Component.LEFT_ALIGNMENT);

        statsPanel.add(Box.createVerticalStrut(10));
        statsPanel.add(xpBar);

        statsPanel.revalidate();
        statsPanel.repaint();
    }

    private void addStatLabel(String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsPanel.add(label);
        statsPanel.add(Box.createVerticalStrut(5));
    }

    @Override
    public void showMap(GameMap gameMap) {

        mapPanel.removeAll();
        mapPanel.setLayout(new GridLayout(gameMap.getSize(), gameMap.getSize()));

        for (int i = 0; i < gameMap.getSize(); i++) {
            for (int j = 0; j < gameMap.getSize(); j++) {
                JPanel cell = new JPanel();
                cell.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                if (gameMap.getTile(i, j).isHero()) {
                    cell.setBackground(Color.BLUE);
                } else if (gameMap.getTile(i, j).isEnemy() && this.visible_enemies) {
                    cell.setBackground(Color.RED);
                } else if (gameMap.getTile(i, j).isVisited()) {
                    cell.setBackground(Color.LIGHT_GRAY);
                } else {
                    cell.setBackground(Color.WHITE);
                }

                mapPanel.add(cell);
            }
        }

        mapPanel.revalidate();
        mapPanel.repaint();
    }

    @Override
    public void showBattleResult(boolean success, Villain villain) {
        if (success) {
            JOptionPane.showMessageDialog(mainFrame,
                    "You have won this battle!",
                    "Victory!", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(mainFrame,
                    String.format("You were defeated by the villain!\nThe villain still had %d HP left...", villain.getHp()),
                "Defeat...", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void showVillain(Villain villain) {
        mapPanel.removeAll();
        mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));

        // Villain stats panel
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        statsPanel.setBorder(BorderFactory.createTitledBorder("Villain Stats"));

        addLabelToPanel(statsPanel, "Level: " + villain.getLevel());
        addLabelToPanel(statsPanel, "HP: " + villain.getHp());
        addLabelToPanel(statsPanel, "Attack: " + villain.getAttack());
        addLabelToPanel(statsPanel, "Defense: " + villain.getDefense());

        if (villain.getArtifact() != null) {
            addLabelToPanel(statsPanel, "Carries: Level " + villain.getArtifact().getLevel() +
                    " " + villain.getArtifact().getType());
        }

        mapPanel.add(statsPanel);
        mapPanel.add(Box.createVerticalStrut(20)); // Add some space

        mapPanel.revalidate();
        mapPanel.repaint();
    }

    private void addLabelToPanel(JPanel panel, String text) {
        JLabel label = new JLabel(text);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createVerticalStrut(5));
    }


    @Override
    public void showLevelUp(Hero hero) {
        JOptionPane.showMessageDialog(mainFrame,
                "You gained a level!\n+3 max HP\n+2 ATK\n+2 DEF",
                "Level Up!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showExperienceProgress(Hero hero) {
        // In GUI, it makes more sense to update everything at once, so there is no need for a separate
        // show experience progress method.
        showHeroStats(hero);
    }


    @Override
    public void showVictory() {
        JOptionPane.showMessageDialog(mainFrame,
                "Congratulations! You've won!\nTry again with a different class!",
                "Victory!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showGameOver() {
        JOptionPane.showMessageDialog(mainFrame,
                "Game Over!\nTry again with a different strategy!",
                "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showMessage(String message) {
        messageArea.append(message + "\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
    }

    @Override
    public void showError(String message) {
        JOptionPane.showMessageDialog(mainFrame, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void requestLoadOrCreate(GameController controller) {
        inputField.setEnabled(false);
        this.controller = controller;
        mapPanel.removeAll();
        mapPanel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton loadButton = new JButton("Load Save");
        JButton createButton = new JButton("Create Save");
        JButton deleteButton = new JButton("Delete Save");

        loadButton.addActionListener(e -> controller.onLoadOrCreateChoice("Load_save"));
        createButton.addActionListener(e -> controller.onLoadOrCreateChoice("Create_save"));
        deleteButton.addActionListener(e -> controller.onLoadOrCreateChoice("Delete_save"));

        mapPanel.add(loadButton);
        mapPanel.add(createButton);
        mapPanel.add(deleteButton);

        mapPanel.revalidate();
        mapPanel.repaint();

        this.currentState = "requestLoadOrCreate";

    }

    @Override
    public void requestSaveToDelete(List<String> saves) {
        inputField.setEnabled(false);
        mapPanel.removeAll();
        mapPanel.setLayout(new GridLayout(saves.size(), 1, 5, 5));

        for (String save : saves) {
            JButton saveButton = new JButton(save);
            saveButton.addActionListener(e -> controller.onSaveToDelete(save));
            mapPanel.add(saveButton);
        }

        mapPanel.revalidate();
        mapPanel.repaint();

        this.currentState = "requestSaveToDelete";
    }

    @Override
    public void requestHeroName() {
        inputField.setEnabled(false);
        String name = JOptionPane.showInputDialog(mainFrame, "Enter your hero's name:");
        if (name == null) {
            this.requestLoadOrCreate(this.controller);
            return ;
        }
        this.controller.heroCreationInput.onNameInput(name);
        this.currentState = "requestHeroName";
    }

    @Override
    public void requestHeroClass() {
        inputField.setEnabled(false);

        mapPanel.removeAll();
        mapPanel.setLayout(new GridLayout(3, 2, 10, 10));  // 3 rows, 2 columns

        // Berserker
        mapPanel.add(new JLabel("<html>Berserk<br>HP: 15<br>ATK: 5<br>DEF: 10<br>Has a second chance at 1 HP</html>"));
        JButton berserkButton = new JButton("Choose Berserker");
        berserkButton.addActionListener(e -> controller.heroCreationInput.onClassInput("Berserk"));
        mapPanel.add(berserkButton);

        // Mage
        mapPanel.add(new JLabel("<html>Mage<br>HP: 10<br>ATK: 15<br>DEF: 5<br>Executes under 3 HP</html>"));
        JButton mageButton = new JButton("Choose Mage");
        mageButton.addActionListener(e -> controller.heroCreationInput.onClassInput("Mage"));
        mapPanel.add(mageButton);

        // Archer
        mapPanel.add(new JLabel("<html>Archer<br>HP: 12<br>ATK: 10<br>DEF: 7<br>Escape chance +15%</html>"));
        JButton archerButton = new JButton("Choose Archer");
        archerButton.addActionListener(e -> controller.heroCreationInput.onClassInput("Archer"));
        mapPanel.add(archerButton);

        mapPanel.revalidate();
        mapPanel.repaint();
    }

    @Override
    public void requestGameStartConfirm() {
        int choice = JOptionPane.showConfirmDialog(mainFrame,
                "Ready to start the game?",
                "Start Game", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            controller.heroCreationInput.onGameStartConfirm("Yes");
        } else {
            controller.heroCreationInput.onGameStartConfirm("No");
        }
    }

    @Override
    public void requestHeroSelection(List<String> heroes) {
        mapPanel.removeAll();
        mapPanel.setLayout(new GridLayout(heroes.size(), 1, 5, 5));

        for (String hero : heroes) {
            JButton heroButton = new JButton(hero);
            heroButton.addActionListener(e -> controller.onHeroLoad(hero));
            mapPanel.add(heroButton);
        }

        mapPanel.revalidate();
        mapPanel.repaint();

        this.currentState = "requestHeroSelection";
    }

    @Override
    public void requestMovement() {
        inputField.setEnabled(true);
        messageArea.append("Where do you want to go next ?\n");
        messageArea.append("Use arrow keys or type [N] - [S] - [E] - [W]\n");
        messageArea.setCaretPosition(messageArea.getDocument().getLength());
        this.currentState = "requestMovement";
        mainFrame.requestFocus();
    }

    @Override
    public void requestBattleDecision(Villain villain, int escapeChance) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Battle Options"));

        JButton fightButton = new JButton("Fight");
        JButton escapeButton = new JButton("Escape (" + escapeChance + "%)");

        fightButton.addActionListener(e -> controller.onBattleDecision("Fight"));

        escapeButton.addActionListener(e -> controller.onBattleDecision("Escape"));

        // Center align buttons
        fightButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        escapeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add some spacing between buttons
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(fightButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(escapeButton);
        buttonPanel.add(Box.createHorizontalGlue());

        mapPanel.add(buttonPanel);

        mapPanel.revalidate();
        mapPanel.repaint();

        showMessage("Oh no! You encountered an enemy!");
        showMessage("What do you want to do?");

        this.currentState = "requestBattleDecision";

    }

    @Override
    public void showArtifactDrop(Villain villain) {
        if (villain.getArtifact() != null) {
            showMessage("Artifact dropped: Level " + villain.getArtifact().getLevel() +
                    " " + villain.getArtifact().getType());
        }
    }

    @Override
    public void requestArtifactDecision(Hero hero, Villain villain) {
        int choice = JOptionPane.showConfirmDialog(mainFrame,
                "Do you want to pick up the artifact ?",
                "New artifact", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            controller.onArtifactDecision("Yes");
        } else {
            controller.onArtifactDecision("No");
        }
    }

    @Override
    public boolean getVisibleEnemies() {
        return this.visible_enemies;
    }

    public void exitWindow() {
        mainFrame.dispose();
    }
}
