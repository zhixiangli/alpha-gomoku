package com.zhixiangli.gomoku.console.common;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author zhixiangli
 */
public class PlayerProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerProperties.class);

    public static final String PLAYER_CONF = "player";

    public static String playerBlackCommand;

    public static String playerBlackAlias;

    public static String playerWhiteCommand;

    public static String playerWhiteAlias;

    private PlayerProperties() {
    }

    public static void parse(final String configPath) {
        try {
            final PropertiesConfiguration playerConfig = new Configurations().properties(new File(configPath));
            playerBlackCommand = playerConfig.getString("player.black.cmd");
            playerBlackAlias = playerConfig.getString("player.black.alias");
            playerWhiteCommand = playerConfig.getString("player.white.cmd");
            playerWhiteAlias = playerConfig.getString("player.white.alias");
        } catch (final ConfigurationException e) {
            LOGGER.error("load player config error", e);
        }
    }

}
