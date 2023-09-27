import lombok.Data;

@Data
public class ApplicationInstanceResponse {
    private Data data;

    @Data
    public static class Data {
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
