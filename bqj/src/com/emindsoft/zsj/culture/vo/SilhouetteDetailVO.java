package com.emindsoft.zsj.culture.vo;

import java.util.List;
import com.emindsoft.zsj.culture.model.Silhouette;

public class SilhouetteDetailVO {
	
	private Silhouette silhouette;
	private List<String> imageList;
	
	public SilhouetteDetailVO(Silhouette silhouette,List<String> imageList) {
		this.silhouette = silhouette;
		this.imageList = imageList;
	}
	
	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

	public SilhouetteDetailVO (Silhouette silhouette) {
		super();
		this.silhouette = silhouette;
	}

	public Silhouette getSilhouette() {
		return silhouette;
	}

	public void setSilhouette(Silhouette silhouette) {
		this.silhouette = silhouette;
	}
	

}
