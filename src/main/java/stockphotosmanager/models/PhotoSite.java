package stockphotosmanager.models;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="photos_sites")
public class PhotoSite {

    @EmbeddedId
    PhotoSiteKey id;
    
    @ManyToOne
    @MapsId("pho_id")
    @JoinColumn(name = "pho_id")
    Photo photo;
    
    @ManyToOne
    @MapsId("sit_id")
    @JoinColumn(name = "sit_id")
    Site site;
    
	@Column(name = "ps_status")
	private Integer status;
	
	@Column(name = "ps_uplodad_date_millis")
	private Integer uploadDateMillis;

	@Column(name = "ps_uplodad_date_string_madrid")
	private String uploadDateStringMadrid;

	@Column(name = "ps_last_revision_date_millis")
	private Integer lastRevisionDateMillis;

	@Column(name = "ps_last_revision_date_string_madrid")
	private String lastRevisionDateStringMadrid;

	@Column(name = "ps_comments")
	private String comments;

	public PhotoSiteKey getId() {
		return id;
	}

	public void setId(PhotoSiteKey id) {
		this.id = id;
	}

	public Photo getPhoto() {
		return photo;
	}

	public void setPhoto(Photo photo) {
		this.photo = photo;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public Integer getLastRevisionDateMillis() {
		return lastRevisionDateMillis;
	}

	public void setLastRevisionDateMillis(Integer lastRevisionDateMillis) {
		this.lastRevisionDateMillis = lastRevisionDateMillis;
	}

	public String getLastRevisionDateStringMadrid() {
		return lastRevisionDateStringMadrid;
	}

	public void setLastRevisionDateStringMadrid(String lastRevisionDateStringMadrid) {
		this.lastRevisionDateStringMadrid = lastRevisionDateStringMadrid;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	
	
}
