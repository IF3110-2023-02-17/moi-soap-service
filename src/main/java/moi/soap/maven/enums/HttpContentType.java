package moi.soap.maven.enums;

public enum HttpContentType {
    APPLICATION_JSON("application/json"),
    FORM_URL_ENCODED("application/x-www-form-urlencoded");
    private String value;

    HttpContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
