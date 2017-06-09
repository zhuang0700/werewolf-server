package com.telan.werewolf.query;

import java.util.List;

/**
 * Created by weiwenliang on 2015/11/3.
 *
 */
public class GamePageQuery extends PageQuery {

    private static final long serialVersionUID = -3142728607169207697L;
    private List<Long> ids;

    private Integer status; //状态

    private Long creatorId;

    private Integer type;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
