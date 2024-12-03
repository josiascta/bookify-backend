package br.com.ifpb.ads.bookifyapi.dto;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookDTO {


    private long id;

    @NotNull
    @NotEmpty
    private String title;

    private int quantity_stock;

    private double price;
}
