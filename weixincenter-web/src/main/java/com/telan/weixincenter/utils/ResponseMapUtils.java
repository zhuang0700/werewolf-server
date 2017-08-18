package com.telan.weixincenter.utils;

import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.result.WeBaseResult;
import com.telan.werewolf.result.WeResultSupport;
import com.telan.werewolf.utils.conventor.ResultConvertor;

import java.util.HashMap;
import java.util.Map;


public class ResponseMapUtils {
	public static Map convertWeResultSupportToMap(WeResultSupport result) {
		Map map = new HashMap();
		map.put("status", result.isSuccess()?1:0);
		map.put("msg", result.getResultMsg());
		map.put("code", result.getErrorCode());
		return map;
	}

	public static Map convertGameInfo(WeBaseResult<GameInfo> result, UserDO userDO) {
		Map map = new HashMap();
		map.put("status", result.isSuccess()?1:0);
		map.put("msg", result.getResultMsg());
		map.put("code", result.getErrorCode());
		if(result.isSuccess()) {
			GameInfo gameInfo = result.getValue();
			GameData gameData = ResultConvertor.convertToData(gameInfo, userDO, false);
			map.put("result", gameData);
		}
		return map;
	}


	public static Map convertError(WeBaseResult<GameInfo> result, UserDO userDO) {
		Map map = new HashMap();
		map.put("status", result.isSuccess()?1:0);
		map.put("msg", result.getResultMsg());
		map.put("code", result.getErrorCode());
		return map;
	}

	public static Map convertActionResult(WeBaseResult<ActionResult> result, UserDO userDO) {
		Map map = new HashMap();
		map.put("status", result.isSuccess()?1:0);
		map.put("msg", result.getResultMsg());
		map.put("code", result.getErrorCode());
		ActionResult actionResult = result.getValue();
		map.put("result", actionResult);
		return map;
	}
}