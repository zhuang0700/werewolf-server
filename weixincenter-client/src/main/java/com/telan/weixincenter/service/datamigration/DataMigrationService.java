package com.telan.weixincenter.service.datamigration;

import com.telan.weixincenter.domain.base.WxSubscribeDO;
import com.telan.weixincenter.result.WXResultSupport;

public interface DataMigrationService {

	
	public WXResultSupport migrationSubscribeRecord(WxSubscribeDO subscribe) ;
}
