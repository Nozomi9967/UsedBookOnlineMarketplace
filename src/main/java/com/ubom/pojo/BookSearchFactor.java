package com.ubom.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSearchFactor {
    private String bookName;
    private String author;
    private String bottomPrice;
    private String topPrice;
//    private LocalDate beginDate;
//    private LocalDate endDate;
}
