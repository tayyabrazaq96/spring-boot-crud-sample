package com.item.service.dto.request;

import com.item.service.value.Unit;
import java.math.BigDecimal;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CreateItemReq extends BaseReq {

  @NotEmpty(message = "Please provide title")
  private String title;

  private String description;

  @NotNull(message = "Please provide unit price")
  private BigDecimal unitPrice;

  @NotNull(message = "Please provide unit")
  private Unit unit;
}
