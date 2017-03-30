package com.telan.werewolf.service.datamigration;

import com.telan.werewolf.domain.base.WxSubscribeDO;
import com.telan.werewolf.result.WXResultSupport;

public interface DataMigrationService {

	
	public WXResultSupport migrationSubscribeRecord(WxSubscribeDO subscribe) ;
}
