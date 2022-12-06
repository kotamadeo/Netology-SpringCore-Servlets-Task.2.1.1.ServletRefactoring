package com.gmail.at.kotamadeo.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Component
@Scope(SCOPE_PROTOTYPE)
@NoArgsConstructor
@AllArgsConstructor
public class InfoException {
    private String informationMessage;
}
