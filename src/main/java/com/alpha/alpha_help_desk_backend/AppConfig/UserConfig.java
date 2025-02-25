package com.alpha.alpha_help_desk_backend.AppConfig;

import com.alpha.alpha_help_desk_backend.Entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }




}
