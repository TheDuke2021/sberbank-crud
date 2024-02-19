package ru.theduke2021.sberbankcrud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${api.default_per_page}")
    private int DEFAULT_PER_PAGE;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();

        resolver.setOneIndexedParameters(true);
        resolver.setPageParameterName("page");
        resolver.setSizeParameterName("perPage");
        resolver.setMaxPageSize(DEFAULT_PER_PAGE);

        // Добавляет дефолтные параметры пагинации для всех эндпоинтов,
        // если эти параметры явно не указаны клиентом в HTTP-запросе
        resolver.setFallbackPageable(Pageable.ofSize(DEFAULT_PER_PAGE).withPage(1));

        argumentResolvers.add(resolver);
    }
}