package com.telan.weixincenter.model.response;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.telan.weixincenter.event.parser.XStreamCDATA;

@XStreamAlias("xml")
public class ImageMessageResponse extends BaseRspMsg{
	@XStreamAlias("Image")
	private Image image;
	
	public String getMediaId() {
		if(image != null){
			return image.getMediaId();
		}
		return null;
	}

	public void setMediaId(String mediaId) {
		this.image = new Image(mediaId);
	}
	
	static class Image{
		@XStreamAlias("MediaId")
		@XStreamCDATA
		private String mediaId;
		
		public Image(String mediaId){
			this.mediaId = mediaId;
		}

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
		
		
	}

}
