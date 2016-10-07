package cz.i.wicket.store;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author jan.hadas@i.cz
 */
public class Commodity implements Serializable {
  private static final long serialVersionUID = 42L;
  private static final Integer DEFAULT_TAX_RATE = 21;

  private String name;
  private String description;
  private BigDecimal price;
  private Integer taxRate;

  public Commodity(String name, BigDecimal price) {
    this(name, "", price, DEFAULT_TAX_RATE);
  }

  public Commodity(String name, String description, BigDecimal price) {
    this(name, description, price, DEFAULT_TAX_RATE);
  }

  public Commodity(String name, String description, BigDecimal price, Integer taxRate) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.taxRate = taxRate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public Integer getTaxRate() {
    return taxRate;
  }

  public void setTaxRate(Integer taxRate) {
    this.taxRate = taxRate;
  }
}
