package com.example.api.beans;
import lombok.Data;

@Data
public class ApplicationInstanceResponse {
    private ApplicationInstance data;

    @Data
    public static class ApplicationInstance {
        private String alias;
        private String apiType;
        private String dateAdded;
        private Instance instance;
    }

    @Data
    public static class Instance {
        private String url;
        private String raml;
        private String version;
        private String[] pathOverrides;
    }
}
