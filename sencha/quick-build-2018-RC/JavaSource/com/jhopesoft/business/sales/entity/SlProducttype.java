package com.jhopesoft.business.sales.entity;
// default package
// Generated 2018-3-6 16:54:10 by Hibernate Tools 5.2.6.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * SlProducttype generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "sl_producttype", uniqueConstraints = @UniqueConstraint(columnNames = "name_"))
public class SlProducttype implements java.io.Serializable {

	private String id;
	private String name;
	private Set<SlProduct> slProducts = new HashSet<SlProduct>(0);

	public SlProducttype() {
	}

	public SlProducttype(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public SlProducttype(String id, String name, Set<SlProduct> slProducts) {
		this.id = id;
		this.name = name;
		this.slProducts = slProducts;
	}

	@Id

	@Column(name = "id_", unique = true, nullable = false, length = 4)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "name_", unique = true, nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "slProducttype")
	public Set<SlProduct> getSlProducts() {
		return this.slProducts;
	}

	public void setSlProducts(Set<SlProduct> slProducts) {
		this.slProducts = slProducts;
	}

}
