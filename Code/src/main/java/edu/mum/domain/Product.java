package edu.mum.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "ITEM")
@Transactional
//@NamedQueries(value = { @NamedQuery(name = "Product.findByCategoryName", query = "Select i from Item i Join i.categories c where c.name =:name") })
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ITEM_ID")
	private Long id = null;

	@Version
	@Column(name = "OBJ_VERSION")
	private int version = 0;

	@Column(name = "NAME", length = 255, nullable = false, updatable = false)
	@Size(min = 4, max = 20, message = "{size}")
	private String name;

	@Column(name = "DESCRIPTION", length = 4000, nullable = false)
	@Size(min = 4, max = 4000, message = "{size}")
	private String description;

	@Column
	@Digits(integer=6, fraction=2)

	private BigDecimal price;

	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<Category> categories = new HashSet<Category>();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = true, updatable = false)
	private Date created = new Date();

	// ********************** Accessor Methods ********************** //

	public Long getId() {
		return id;
	}

	public int getVersion() {
		return version;
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

	public void setPrice(BigDecimal initialPrice) {
		this.price = initialPrice;
	}

	public void addCategory(Category category) {
		this.categories.add(category);
		category.getItems().add(this);
	}

	// Read-only, modify through Category#addItem() and Category@removeItem();
	public Set<Category> getCategories() {
		return Collections.unmodifiableSet(categories);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public Date getCreated() {
		return created;
	}
	
	private int remainingQuantity;

	// ********************** Common Methods ********************** //

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Product))
			return false;

		final Product item = (Product) o;

		if (!(created.getTime() == item.created.getTime()))
			return false;
		if (name != null ? !name.equals(item.name) : item.name != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result;
		result = (name != null ? name.hashCode() : 0);
		result = 29 * result + created.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Item ('" + getId() + "'), " + "Name: '" + getName() + "' " + "Initial Price: '" + getPrice()
				+ "'";
	}

	public int compareTo(Object o) {
		if (o instanceof Product) {
			// Don't compare Date objects! Use the time in milliseconds!
			return Long.valueOf(this.getCreated().getTime())
					.compareTo(Long.valueOf(((Product) o).getCreated().getTime()));
		}
		return 0;
	}

	public int getRemainingQuantity() {
		return remainingQuantity;
	}

	public void setRemainingQuantity(int remainingQuantity) {
		this.remainingQuantity = remainingQuantity;
	}

	// ********************** Business Methods ********************** //

}
