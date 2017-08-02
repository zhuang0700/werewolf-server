package com.telan.werewolf.query;

import java.util.List;

/**
 * Created by weiwenliang on 2015/11/3.
 *
 */
public class GamePageQuery extends PageQuery {

    private static final long serialVersionUID = -3142728607169207697L;
    private List<Long> ids;

    private List<Integer> statusList;

    private Long creatorId;

    private Integer type;

    private Integer result;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
