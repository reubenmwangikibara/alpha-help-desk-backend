package com.alpha.alpha_help_desk_backend.configs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("alpha")
@Data
@Getter
@Setter
public class ApplicationConfigs {
    long expiryDuration;
}
