package com.alpha.alpha_help_desk_backend.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public ModelMapper modelMapper() {

        return new ModelMapper();
    }




}
