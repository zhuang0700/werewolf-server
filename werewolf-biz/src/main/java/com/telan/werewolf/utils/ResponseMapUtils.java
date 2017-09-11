package com.telan.werewolf.utils;

import com.alibaba.fastjson.JSON;
import com.telan.werewolf.domain.UserDO;
import com.telan.werewolf.game.domain.*;
import com.telan.werewolf.param.BaseResponseData;
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

	public static Map syncGameInfo(WeBaseResult<GameInfo> result, UserDO userDO) {
		Map map = convertGameInfo(result, userDO);
		Map<Long, String> msgMap = new HashMap<>();
		msgMap.put(userDO.getId(), JSON.toJSONString(map));
		return map;
	}

	public static BaseResponseData getFailResponse(WeResultSupport resultSupport) {
		BaseResponseData response = new BaseResponseData();
		response.setStatus(0);
		response.setCode(resultSupport.getErrorCode());
		response.setMsg(resultSupport.getResultMsg());
		return response;
	}

	public static BaseResponseData getGameInfoResponse(WeBaseResult<GameInfo> result, UserDO userDO) {
		BaseResponseData response = new BaseResponseData();
		response.setStatus(1);
		response.setCode(result.getErrorCode());
		response.setMsg(result.getResultMsg());
		if(result.isSuccess()) {
			GameData gameData = ResultConvertor.convertToData(result.getValue(), userDO, false);
			response.setGameData(gameData);
		}
		return response;
	}


	public static BaseResponseData getGameInfoResponse(GameInfo gameInfo, UserDO userDO) {
		BaseResponseData response = new BaseResponseData();
		response.setStatus(1);
		GameData gameData = ResultConvertor.convertToData(gameInfo, userDO, false);
		response.setGameData(gameData);
		return response;
	}

	public static BaseResponseData getActionResultResponse(WeBaseResult<ActionResult> result, UserDO userDO) {
		BaseResponseData response = new BaseResponseData();
		response.setStatus(1);
		response.setCode(result.getErrorCode());
		response.setMsg(result.getResultMsg());
		if(result.isSuccess()) {
			response.setActionResult(result.getValue());
		}
		return response;
	}
}