package stockphotosmanager.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PhotoSiteKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "pho_id")
    private Integer photoId;
 
    @Column(name = "sit_id")
    private Integer siteId;

	public Integer getPhotoId() {
		return photoId;
	}

	public void setPhotoId(Integer photoId) {
		this.photoId = photoId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}
    
    
    
    
}
