package pl.xcodesoftware.exchangerate.vendors.nbp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rate {

    private String no;

    private LocalDate effectiveDate;

    private BigDecimal mid;

}
