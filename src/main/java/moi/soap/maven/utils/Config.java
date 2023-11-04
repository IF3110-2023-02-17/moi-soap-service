package moi.soap.maven.utils;

import lombok.Getter;

public class Config {

    @Getter
    private ConfigProperties cp;
    @Getter
    private ConfigDotenv env;
    private static Config instance = null;

    private Config() {
        String dockerConf = System.getenv("SOAP_DOCKER_CONFIG");
        if (dockerConf == null || dockerConf.equals("off")) {
            this.cp = new ConfigProperties("config.properties");
        } else {
            this.cp = new ConfigProperties("config.docker.properties");
        }
        this.env = new ConfigDotenv();

    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    public String getProp(String key) {
        return this.cp.getProp(key);
    }

    public String getEnv(String key) {
        return this.env.getEnv(key);
    }

}
