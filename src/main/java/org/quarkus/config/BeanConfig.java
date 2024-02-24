package org.quarkus.config;

import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class BeanConfig {
    @ApplicationScoped
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
