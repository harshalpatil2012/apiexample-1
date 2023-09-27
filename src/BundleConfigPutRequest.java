import lombok.Data;

@Data
public class BundleConfigPutRequest {
    private String name;
    private String clientType;
    private String version;
    private String hash;
    private String path;
    private String downloadType;
    private String clientVersion;
    private String osVersion;
    private String environment;
    private boolean enabled;
    private String throttle;
}
