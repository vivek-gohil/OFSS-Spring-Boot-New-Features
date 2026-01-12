package com.ofss.main.actuator;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHealthIndicator implements HealthIndicator {

	private final DataSource dataSource;

	public DatabaseHealthIndicator(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	@Override
	public Health health() {
		try (Connection connection = dataSource.getConnection()) {
			return Health.up().withDetail("Database", "Oracle").withDetail("status", "Connection Successfull").build();
		} catch (Exception e) {
			return Health.down().withDetail("Database", "Oracle").withDetail("error", e.getMessage()).build();
		}
	}
}
