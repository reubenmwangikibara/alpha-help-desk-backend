package com.alpha.alpha_help_desk_backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    private String expenseDate;

    private String dueDate;

}
