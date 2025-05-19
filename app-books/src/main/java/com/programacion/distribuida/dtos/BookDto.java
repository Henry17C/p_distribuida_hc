package com.programacion.distribuida.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
@Getter
@Setter
@ToString
public class BookDto {




    private String isbn;
    private String title;
    private BigDecimal sold;
    private BigDecimal price;

    private Integer invertarySold;
    private Integer invetarySupplied;

    private List<String> authors;



}
