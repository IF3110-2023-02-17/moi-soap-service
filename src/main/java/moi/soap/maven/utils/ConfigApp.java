package moi.soap.maven.utils;

import lombok.Getter;

public class ConfigApp {

    @Getter
    private ConfigProperties cp;
    private static ConfigApp instance = null;

    private ConfigApp() {
        String dockerConf = System.getenv("SOAP_DOCKER_CONFIG");
        if (dockerConf == null || dockerConf.equals("off")) {
            this.cp = new ConfigProperties("config.properties");
        } else {
            this.cp = new ConfigProperties("config.docker.properties");
        }
    }

    public static ConfigApp getInstance() {
        if (instance == null) {
            instance = new ConfigApp();
        }
        return instance;
    }

    public String get(String key) {
        return this.cp.getProp(key);
    }

}
