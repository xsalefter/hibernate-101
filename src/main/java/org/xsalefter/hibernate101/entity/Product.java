package org.xsalefter.hibernate101.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product implements Serializable {

	private static final long serialVersionUID = -8177427657389495073L;

	private Long id;
	private String name;
	private BigDecimal defaultPrice;
	private ProductCategory category;
	
	public Product() {}

	public Product(String name, BigDecimal price, ProductCategory category) {
		super();
		this.name = name;
		this.defaultPrice = price;
		this.category = category;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="product_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="product_name", nullable=false, length=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="default_price", nullable=false, scale=2)
	public BigDecimal getDefaultPrice() {
		return defaultPrice;
	}

	public void setDefaultPrice(BigDecimal defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	@ManyToOne(fetch=FetchType.LAZY, targetEntity=ProductCategory.class)
	@JoinColumn(name="category_id", nullable=false)
	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}
	
}
