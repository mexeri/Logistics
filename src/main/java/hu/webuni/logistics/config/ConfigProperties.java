package hu.webuni.logistics.config;

import java.util.TreeMap;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "logistics")
@Component
public class ConfigProperties {
	
	private TreeMap<Integer, Double> limits;
	
	public TreeMap<Integer, Double> getLimits() {
		return limits;
	}

	public void setLimits(TreeMap<Integer, Double> limits) {
		this.limits = limits;
	}
	
}
