package pl.xcodesoftware.exchangerate.vendors.nbp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Data;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class NbpExchangeRate {

    private String table;

    private String currency;

    private String code;

    private List<Rate> rates;

}
