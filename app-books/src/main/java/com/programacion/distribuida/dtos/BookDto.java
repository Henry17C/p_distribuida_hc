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
    //    private Inventory inventory;
    private String title;
    private BigDecimal price;

    private Integer inventorySold;
    private Integer inventorySupplied;

    private List<String> authors;


}
