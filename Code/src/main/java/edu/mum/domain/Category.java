package edu.mum.domain;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "CATEGORY")
public class Category implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "CATEGORY_ID")
	private Long id = null;

	
	@Column(name = "CATEGORY_NAME", length = 255, nullable = false)
	@Size(min = 4, max = 20, message = "{size}")
	private String name;

	@ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Product> items = new ArrayList<Product>();

	@Transient
	private Map<Product, User> itemsAndUser = new HashMap<Product, User>();

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED", nullable = false, updatable = false)
	private Date created = new Date();

	/**
	 * No-arg constructor for JavaBean tools
	 */
	public Category() {
	}

	/**
	 * Simple constructors
	 */
	public Category(String name) {
		this.name = name;
	}

	public Category(String name, Category parentCategory) {
		this.name = name;
	}

	// ********************** Accessor Methods ********************** //

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Regular many-to-many
	public List<Product> getItems() {
		return items;
	}

	public void addItem(Product item) {
		if (item == null)
			throw new IllegalArgumentException("Null item!");
		items.add(item);
		item.getCategories().add(this);
	}

	public void removeItem(Product item) {
		if (item == null)
			throw new IllegalArgumentException("Null item!");
		items.remove(item);
		item.getCategories().remove(this);
	}

	public Date getCreated() {
		return created;
	}

	// ********************** Common Methods ********************** //

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		final Category category = (Category) o;

		if (!created.equals(category.created))
			return false;
		if (!name.equals(category.name))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		int result;
		result = name.hashCode();
		result = 29 * result + created.hashCode();
		return result;
	}

	public int compareTo(Object o) {
		if (o instanceof Category) {
			return this.getName().compareTo(((Category) o).getName());
		}
		return 0;
	}

	@Override
	public String toString() {
		return "(" + getId() + ") Name: '" + getName();
	}

	// ********************** Business Methods ********************** //

}
