package stockphotosmanager.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="photos")
public class Photo {

	@Id
	@Column(name = "pho_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "pho_dvd")
	private String dvd;

	@Column(name = "pho_file_name")
	private String fileName;

	@Column(name = "pho_upload_date_millis")
	private Integer uploadDateMillis;
	
	@Column(name = "pho_upload_date_string_madrid")
	private String uploadDateStringMadrid;

	@Column(name = "pho_comments")
	private String comments;
	
    @OneToMany(mappedBy = "photo")//parece que aqui hay que poner una entidad, aunque sea con minusculas, no una tabla
    Set<PhotoSite> photoSites;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDvd() {
		return dvd;
	}

	public void setDvd(String dvd) {
		this.dvd = dvd;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getUploadDateMillis() {
		return uploadDateMillis;
	}

	public void setUploadDateMillis(Integer uploadDateMillis) {
		this.uploadDateMillis = uploadDateMillis;
	}

	public String getUploadDateStringMadrid() {
		return uploadDateStringMadrid;
	}

	public void setUploadDateStringMadrid(String uploadDateStringMadrid) {
		this.uploadDateStringMadrid = uploadDateStringMadrid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Set<PhotoSite> getPhotoSites() {
		return photoSites;
	}

	public void setPhotoSites(Set<PhotoSite> photoSites) {
		this.photoSites = photoSites;
	}


}
