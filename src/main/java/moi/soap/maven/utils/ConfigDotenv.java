package moi.soap.maven.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class ConfigDotenv {
    private final Dotenv env;

    public ConfigDotenv() {
        this.env = Dotenv.load();
    }

    public String getEnv(String key) { return this.env.get(key); }
}