import lombok.Data;

@Data
public class JourneyResponse {
    private Data data;

    @Data
    public static class Data {
        private String id;
        private Attributes attributes;
    }

    @Data
    public static class Attributes {
        private String bundleHost;
        private JourneyConfig[] journeyConfigs;
    }

    @Data
    public static class JourneyConfig {
        private String name;
        private String type;
        private LaunchConfig launchConfig;
    }

    @Data
    public static class LaunchConfig {
        private String launchUrl;
        private String key;
        private BundleConfig bundleConfig;
    }

    @Data
    public static class BundleConfig {
        private String name;
        private String version;
        private String path;
        private String hash;
        private String downloadType;
    }
}
