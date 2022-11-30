package com.gmail.at.kotamadeo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
    @Serial
    private static final long serialVersionUID = 1503915132623986593L;
    private long id;
    private String content;
}
