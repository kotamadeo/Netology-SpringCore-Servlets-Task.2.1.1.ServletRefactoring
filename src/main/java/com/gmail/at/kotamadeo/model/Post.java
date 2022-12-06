package com.gmail.at.kotamadeo.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serial;
import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = PRIVATE)
public class Post implements Serializable {
    @Serial
    static final long serialVersionUID = 1503915132623986593L;
    long id;
    String content;
    boolean isRemoved;
}
