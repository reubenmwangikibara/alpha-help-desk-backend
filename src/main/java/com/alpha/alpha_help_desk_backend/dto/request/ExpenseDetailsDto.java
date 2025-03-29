package com.alpha.alpha_help_desk_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDetailsDto implements Serializable {

    @NotBlank(message = "name  is required")
    private String name;

    @NotNull(message = "price  is required")
    private Long price;

    @NotNull(message = "quantity  is required")
    private Long quantity;

    @NotNull(message = "noOfDays  is required")
    private Long noOfDays;

    @NotNull(message = "weekNo  is required")
    private Long weekNo;


    private String dueDate;

}
