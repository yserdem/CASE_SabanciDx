package com.sabancidx.productapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceRangeRequest {
    private double minprice;
    private double maxprice;
}
