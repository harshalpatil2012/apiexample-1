
package com.example.api.beans;
import lombok.Data;

@Data
public class JourneyConfigPutRequest {
    private String journeyName;
    private String clientType;
    private String version;
    private String key;
    private String launchUrl;
    private String bundleName;
    private String clientVersion;
    private String osVersion;
    private String environment;
    private boolean enabled;
    private String throttle;
}
