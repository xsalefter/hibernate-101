package org.xsalefter.hibernate101.entity;

import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="product_category")
public class ProductCategory implements Serializable {

	private static final long serialVersionUID = -3203716312347993595L;

	private Long id;
	private String name;
	private Set<Product> products;
	
	public ProductCategory() {}
	
	public ProductCategory(String name) {
		this.name = name;
	}

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="category_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="category_name", nullable=false, length=50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="category")
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	
	@Transient
	public boolean addProduct(Product product) {
		if (this.products == null)
			this.products = new TreeSet<Product>();
		
		product.setCategory(this);
		return this.products.add(product);
	}
}
