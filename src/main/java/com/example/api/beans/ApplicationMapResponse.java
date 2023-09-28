package com.example.api.beans;

import lombok.Data;

import java.util.Map;

@Data
public class ApplicationMapResponse {
    private ApplicationMap data;

    @Data
    public static class ApplicationMap {
        private Map<String, ApiEntry> apiEntries;
    }

    @Data
    public static class ApiEntry {
        private String apiAlias;
        private String version;
        private AccessControlEntry accessControlEntry;
        private PathOverride[] pathOverrides;
    }

    @Data
    public static class AccessControlEntry {
        private boolean enabled;
        private String action;
        private String actionMessage;
    }

    @Data
    public static class PathOverride {
        private String method;
        private String path;
        private AccessControlEntry accessControlEntry;
    }
}
