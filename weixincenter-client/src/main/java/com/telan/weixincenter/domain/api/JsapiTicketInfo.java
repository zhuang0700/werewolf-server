package com.telan.weixincenter.domain.api;

import java.io.Serializable;

/**
 * Created by p on 8/1/16.
 */
public class JsapiTicketInfo extends BaseResultInfo implements Serializable {

    private static final long serialVersionUID = -8095050088371570233L;

    private String ticket;
    private int expires_in;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

}
