package labioopint.controller.impl;

import labioopint.controller.api.SettingsController;
import labioopint.controller.api.SettingsLogic;
import labioopint.model.utilities.impl.SettingsImpl;
import labioopint.model.enemy.api.EnemyDifficulty;
/**
 * Class that manage SettingsMenù.
 * It saves the settings choosen in the Menù.
 */
public final class SettingsLogicImpl implements SettingsLogic {

    private final SettingsController controller;
    /**
     * Construction of the {@code SettingsLogicImpl}.
     * 
     * @param controller the controller of the SettingsMenù.
     */
    public SettingsLogicImpl(final SettingsController controller) {
        this.controller = controller;
    }

    @Override
    public void saveNewSettings(final int enemyNumber, final int playersNumber, 
                                final int powerUpNumber, final String enemyDifficultyString) {
        final EnemyDifficulty enemyDifficulty = "EASY".equals(enemyDifficultyString) 
                ? EnemyDifficulty.EASY 
                : "MEDIUM".equals(enemyDifficultyString) 
                    ? EnemyDifficulty.MEDIUM 
                    : EnemyDifficulty.HARD;
        controller.changeSettings(new SettingsImpl(enemyNumber, playersNumber, powerUpNumber, enemyDifficulty));
    }
}
