package com.guard.resourcecenter.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.guard.resourcecenter.mananger.helper.ItemHelper;
import com.guard.ic.client.model.domain.ScenicDO;
import com.guard.resourcecenter.entity.*;
import org.springframework.util.CollectionUtils;

import com.guard.ic.client.model.domain.item.ItemDO;
import com.guard.ic.client.model.domain.item.ItemFeature;
import com.guard.ic.client.model.enums.ItemType;
import com.guard.resourcecenter.domain.ArticleDO;
import com.guard.resourcecenter.domain.ArticleItemDO;
import com.guard.resourcecenter.domain.DestinationDO;
import com.guard.resourcecenter.domain.MediaDO;
import com.guard.resourcecenter.dto.ArticleDTO;
import com.guard.resourcecenter.dto.WrapDTO;
import com.guard.resourcecenter.dto.WrapTargetDTO;
import com.guard.resourcecenter.enums.WrapInfoType;
import com.guard.resourcecenter.mananger.helper.ArticleHelper;
import com.guard.resourcecenter.model.enums.ArticleItemType;
import com.guard.resourcecenter.model.enums.ArticleShareStatus;
import com.guard.resourcecenter.model.enums.ArticleType;
import com.guard.resourcecenter.util.DateUtil;
import com.guard.user.client.domain.MerchantDO;
import com.guard.user.client.dto.MerchantUserDTO;
import com.guard.user.client.dto.TalentDTO;
import com.guard.user.client.dto.UserDTO;
import com.guard.user.client.enums.ServiceFacilityOption;

/**
 * H5文章转换
 *
 * @author xiemingna
 */
public class ArticleConverter {

    public static Article toArticle(ArticleDO articleDO) {
        if (articleDO == null) {
            return null;
        }
        Article article = new Article();
        article.id = articleDO.getId();
        article.type = articleDO.getType();
        article.typeName=ArticleType.getArticleType(articleDO.getType()).name();
        article.typeDesc= ArticleType.getArticleType(articleDO.getType()).getDesc();
        article.title = articleDO.getTitle();
        article.subTitle = articleDO.getSubTitle();
        article.pv = articleDO.getPv();
        article.frontcover = articleDO.getFrontcover();
        article.author = articleDO.getAuthor();
        return article;
    }

    public static ArticleItem do2ArticleItem(ArticleItemDO articleItemDO) {
        if (articleItemDO == null) {
            return null;
        }
        ArticleItem articleItem = new ArticleItem();
        articleItem.articleId = articleItemDO.getArticleId();
        articleItem.title = articleItemDO.getTitle();
        articleItem.type = articleItemDO.getType();
        articleItem.subType = articleItemDO.getSubType();
        articleItem.sort = articleItemDO.getSort();
        articleItem.content = articleItemDO.getContent();
        return articleItem;
    }
    public static List<ArticleRecommend> article2ArticleRecommends(List<ArticleDO> list,  ArticleShareStatus status) {
    	Map<Long, Integer> supprotMap = null;
    	return article2ArticleRecommends(list, status, supprotMap);
    }
	public static List<ArticleRecommend> article2ArticleRecommends(List<ArticleDO> list,  ArticleShareStatus status, Map<Long, Integer> supprotMap) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<ArticleRecommend> articleRecommends = new ArrayList<ArticleRecommend>();
        for (ArticleDO articleDO : list) {
            ArticleRecommend articleRecommend = new ArticleRecommend();
            articleRecommend.title = articleDO.getTitle();
            articleRecommend.id = articleDO.getId();
            articleRecommend.articleUrl = ArticleHelper.getArticleUrl(articleDO);
            articleRecommend.subTitle = articleDO.getSubTitle();
			articleRecommend.articlePic= articleDO.getFrontcover();
            if( supprotMap != null ){
            	Integer num = supprotMap.get(articleDO.getId());
            	num = num == null ? 0 : num.intValue() ;
            	articleRecommend.supportNum = num ;
            }
            articleRecommends.add(articleRecommend);
        }
        return articleRecommends;
    }

	public static List<ArticleDTO> do2ArticleDTOs(List<ArticleDO> articleDOs, Map<Long, Integer> pvMap){
    	if( CollectionUtils.isEmpty(articleDOs) ){
    		return null;
    	}
    	List<ArticleDTO> dtos = new ArrayList<ArticleDTO>() ;
    	for( ArticleDO articleDO : articleDOs ){
    		int pv = 0 ;
    		if( pvMap != null ){
    			Integer value = pvMap.get(articleDO.getId());
    			pv = value == null ? 0 : value.intValue() ;
    		}
    		ArticleDTO dto = do2ArticleDTO(articleDO, pv);
    		if( dto != null ){
    			dtos.add(dto);
    		}
    	}
    	return dtos ;
    }
    public static ArticleDTO do2ArticleDTO(ArticleDO articleDO, int pv){
    	if( articleDO == null ){
    		return null;
    	}
		ArticleDTO dto = new ArticleDTO() ;
		dto.setArticleDO(articleDO);
		dto.setArticleUrl(ArticleHelper.getArticleUrl(articleDO));
		dto.setPv( pv );
    	return dto ;
    }
    
    
    
    /**
     * articleDTO转换成article
     * @param articleDTO
     * @return
     */
    public static Article dto2Article(ArticleDTO articleDTO) {
        ArticleDO articleDO = articleDTO.getArticleDO();
        Article article = toArticle(articleDO);
        article.pv = articleDTO.getPv();
        
        List<WrapDTO> wrapDTOs = articleDTO.getWrapDTOs();
        article.articleItems = wrapDTOs2ArticleInfos(wrapDTOs);
        return article;
    }
    
    private static List<ArticleItem> wrapDTOs2ArticleInfos(List<WrapDTO> wrapDTOs) {
    	if( CollectionUtils.isEmpty(wrapDTOs) ){
        	return null ;
        }
    	List<ArticleItem> articleItems = new ArrayList<ArticleItem>();
    	for (WrapDTO wrapDTO : wrapDTOs) {
            ArticleItem articleItem = wrapDTO2ArticleInfo(wrapDTO);
            articleItems.add(articleItem);
        }
    	return articleItems ;
    }
    
    
    private static ArticleItem wrapDTO2ArticleInfo(WrapDTO wrapDTO) {
    	if( wrapDTO == null ){
    		return null ;
    	}
		ArticleItemDO articleItemDO = wrapDTO.getArticleItemDO();
		ArticleItem articleItem = do2ArticleItem(articleItemDO);
		if (articleItem == null || wrapDTO.geArticleItemType() == null ) {
			return null ;
		}
		ArticleItemType articleItemType = wrapDTO.geArticleItemType() ;
		
		WrapTargetDTO wrapTargetDTO = wrapDTO.getWrapTargetDTO() ;
		if(wrapTargetDTO==null){
			return articleItem;
		}
		Object obj = wrapTargetDTO.getT() ;
		if( obj == null || articleItemType == null ){
			return articleItem;
		}
		
		if (obj instanceof ItemDO) {
			ItemDO itemDO = (ItemDO) obj ;
			if( ArticleItemType.PRODUCT == articleItemType ){
				//商品
				MerchantDO merchantDO = (MerchantDO)wrapTargetDTO.getTargetInfo(WrapInfoType.MERCHANT);
				ArticleProductItem articleProductItem = wrapItem2ArticleProductItem(itemDO, merchantDO) ;
				if(articleProductItem!=null){
					articleProductItem.oldPrice = articleItemDO.getAttachString();
				}
				articleItem.articleProductItem =articleProductItem;
			}else if( ArticleItemType.CONSULTSERVICE == articleItemType ){
				//咨询服务
				List<DestinationDO> destinationDOs = (List<DestinationDO>)wrapTargetDTO.getTargetInfo(WrapInfoType.DESTINATIONS);
				List<String> serviceCities = ItemHelper.getServiceCities(destinationDOs);
				articleItem.articleConsultServiceItem = wrapItem2ArticleConsultServiceItem(itemDO, serviceCities);
			}
		}else if (obj instanceof MediaDO) {
			//音频
			articleItem.audioItem = media2ArticleAudioItem((MediaDO) obj, articleItemDO) ;
		} else if (obj instanceof TalentDTO) {
			//达人
			articleItem.articleExpertManItem = wrapTalent2ArticleExpertManItem((TalentDTO) obj) ;
		} else if (obj instanceof MerchantUserDTO) {
			//美食商家
			MerchantUserDTO merchantUserDTO =(MerchantUserDTO) obj;
			articleItem.articleFoodItem = merchant2ArticleFoodItem( merchantUserDTO.getMerchantDO(), articleItemDO) ;
		}
    	return articleItem;
    }
    
    
    
    
    
    public static ArticleProductItem wrapItem2ArticleProductItem(ItemDO itemDO, MerchantDO merchantDO ) {
        if (itemDO == null ) {
            return null;
        }
        ArticleProductItem articleProductItem = new ArticleProductItem();
        articleProductItem.itemPrice = String.valueOf(itemDO.getPrice());
        articleProductItem.itemTag = ItemHelper.getTags(itemDO) ;
        articleProductItem.itemTitle = itemDO.getTitle();
        articleProductItem.oldPrice = String.valueOf( itemDO.getOriginalPrice() );
        articleProductItem.itemPic = ItemHelper.getItemPic(itemDO);
        ItemType itemType = ItemType.get(itemDO.getItemType()) ;
        if( itemType != null ){
        	articleProductItem.itemType = itemType.getText();
            articleProductItem.itemTypeValue=itemType.name();
        }
        if( merchantDO != null ){
        	articleProductItem.merchantLogo = merchantDO.getLogo();
        	articleProductItem.merchantName = merchantDO.getName();
        }
        articleProductItem.point = itemDO.getItemFeature().getMaxPoint();
        return articleProductItem;
    }
    
    public static ArticleConsultServiceItem wrapItem2ArticleConsultServiceItem(ItemDO itemDO, List<String> serviceCities ) {
		if (itemDO == null || CollectionUtils.isEmpty(serviceCities)) {
            return null;
        }
		ArticleConsultServiceItem articleConsultServiceItem = new ArticleConsultServiceItem();
		articleConsultServiceItem.serviceCity = serviceCities;
		articleConsultServiceItem.serviceCurrentPrice = itemDO.getPrice();
		articleConsultServiceItem.serviceHeadPic = ItemHelper.getItemPic(itemDO);
		articleConsultServiceItem.serviceName = itemDO.getTitle() ;
		articleConsultServiceItem.serviceOriginalPrice = itemDO.getOriginalPrice();
		ItemFeature itemFeature = itemDO.getItemFeature();
        if (itemFeature != null) {
            long consultTime = itemFeature.getConsultTime();
            articleConsultServiceItem.consultTime = consultTime;
        }
		return articleConsultServiceItem;
	}
    
	public static ArticleExpertManItem wrapTalent2ArticleExpertManItem(TalentDTO talentDTO) {
		if (talentDTO == null || talentDTO.getUserDTO() == null) {
			return null;
		}
		UserDTO userDTO = talentDTO.getUserDTO();

		ArticleExpertManItem articleExpertManItem = new ArticleExpertManItem();
		articleExpertManItem.headPic = userDTO.getAvatar();
		articleExpertManItem.nickName = userDTO.getNickname();
		articleExpertManItem.signatures = userDTO.getSignature();
		return articleExpertManItem;
	}
	
//	public static ArticleHotelResourceItem wrapHotel2ArticleHotelResourceItem(SolrHotelDO solrHotelDO) {
////		if (solrHotelDO == null ) {
////			return null;
////		}
////		ArticleHotelResourceItem articleHotelResourceItem = new ArticleHotelResourceItem();
////		articleHotelResourceItem.resourcePrice = solrHotelDO.getPrice();
////		articleHotelResourceItem.tradeArea = solrHotelDO.getTradeArea();
////		articleHotelResourceItem.resourcePic = solrHotelDO.getIcon();
////		articleHotelResourceItem.resourceName = solrHotelDO.getHotelName();
////		return articleHotelResourceItem;
//		return null;
//	}
	
	public static ArticleAudioItem media2ArticleAudioItem(MediaDO media, ArticleItemDO articleItemDO) {
		if (media == null || articleItemDO == null ) {
			return null;
		}
		ArticleAudioItem articleAudioItem = new ArticleAudioItem();
        articleAudioItem.audioPic = articleItemDO.getAttachString();
        articleAudioItem.audioPicName = articleItemDO.getTitle();
        articleAudioItem.audioTime = DateUtil.parseLong2Time(media.getDuration());
        articleAudioItem.audioUrl = media.getRemoteUrl() ;
        articleAudioItem.title = media.getInputFileTitle();
		return articleAudioItem;
	}

	public static ArticleFoodItem merchant2ArticleFoodItem(MerchantDO merchantDO, ArticleItemDO articleItemDO) {
		if (merchantDO == null || articleItemDO == null) {
			return null;
		}
		ArticleFoodItem articleFoodItem = new ArticleFoodItem();
		articleFoodItem.avgPrice = merchantDO.getAvgprice();
		articleFoodItem.id = merchantDO.getId();
		articleFoodItem.image = merchantDO.getLogo();
		articleFoodItem.name = merchantDO.getName();
		long serviceFacility = merchantDO.getServiceFacility();
		List<ServiceFacilityOption> containedOptions = ServiceFacilityOption.getContainedOptions(serviceFacility);
		StringBuilder sb = new StringBuilder();
		for (ServiceFacilityOption containedOption : containedOptions) {
			if (containedOption != null) {
				sb.append(containedOption.getDesc()).append("  ");
			}
		}
		articleFoodItem.service = sb.toString();
		articleFoodItem.top = articleItemDO.getAttachString();
		articleFoodItem.itemTitle = articleItemDO.getTitle();
		articleFoodItem.sellerId = merchantDO.getSellerId();
		articleFoodItem.cityName = merchantDO.getCityName();
		return articleFoodItem;
	}

//	public static ArticleScenicResourceItem wrapScenic2ArticleScenicResourceItem(SolrScenicDO scenicDO) {
////		if (scenicDO == null ) {
////			return null;
////		}
////		ArticleScenicResourceItem articleScenicResourceItem = new ArticleScenicResourceItem();
////		articleScenicResourceItem.resourcePrice = scenicDO.getPrice();
////		articleScenicResourceItem.resourceCity = Lists.newArrayList(scenicDO.getCityName());
////		articleScenicResourceItem.resourcePic = scenicDO.getIcon();
////		articleScenicResourceItem.resourceName = scenicDO.getScenicName();
//////		articleScenicResourceItem.itemType =scenicDO
////		return articleScenicResourceItem;
//		return null;
//	}

}
