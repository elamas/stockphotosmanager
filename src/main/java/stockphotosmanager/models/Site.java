package stockphotosmanager.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sites")
public class Site {

	@Id
	@Column(name = "sit_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "sit_name")
	private String name;
	
	@Column(name = "sit_num_keywords")
	private int numKeywords;
	
	@Column(name = "sit_num_categories")
	private int numCategories;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumKeywords() {
		return numKeywords;
	}

	public void setNumKeywords(int numKeywords) {
		this.numKeywords = numKeywords;
	}

	public int getNumCategories() {
		return numCategories;
	}

	public void setNumCategories(int numCategories) {
		this.numCategories = numCategories;
	}
	
	
}
