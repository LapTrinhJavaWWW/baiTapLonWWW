package com.shop.app.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
	@GeneratedValue
	@Column(name = "id", length = 50)
	private Integer id;

	@Column(name = "name", length = 100)
	private String name;

	@ElementCollection
	@CollectionTable(name = "img", joinColumns = { @JoinColumn( name="id") })
	@Column(name = "img", nullable = false)
	private Set<String> img;

	@Column(name = "chip", length = 50)
	private String chip;

	@Column(name = "screen", columnDefinition = "decimal(10,2)")
	private double screen;

	@Column(name = "rom", columnDefinition = "int")
	private int rom;

	@Column(name = "ram", columnDefinition = "int")
	private int ram;

	@Column(name = "price", columnDefinition = "decimal(10,2)")
	private double price;
	
	@Column(name = "slug",length = 50)
	private String slug;
	
	@Column(name = "type", length = 50)
	private String type;
	
	@Column(name = "bestSeller",length = 50)
	private String bestSeller;

	@Column(name = "specialfeature")
	private List<String> specialfeature;

	@Column(name = "camera")
	private List<String> camera;
}
