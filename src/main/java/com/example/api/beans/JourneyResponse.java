package com.example.api.beans;

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

	// Override toString(), equals(), and hashCode() for necessary classes
	@Override
	public String toString() {
		return "JourneyResponse(data=" + data + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		JourneyResponse that = (JourneyResponse) o;
		return data != null ? data.equals(that.data) : that.data == null;
	}

	@Override
	public int hashCode() {
		return data != null ? data.hashCode() : 0;
	}
}
