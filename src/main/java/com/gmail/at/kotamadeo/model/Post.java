package com.gmail.at.kotamadeo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Component
@Scope(SCOPE_PROTOTYPE)
public class Post implements Serializable {
    @Serial
    static final long serialVersionUID = 1503915132623986593L;
    long id;
    String content;
}
