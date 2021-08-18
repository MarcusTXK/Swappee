package com.swappee.domain.item;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * A ItemHistory embeddable
 */
@Embeddable
public class ItemHistory implements Serializable {
    private static final long serialVersionUID = -7007054155282148372L;

    @Column(name = "prev_owner_username", length = 50, nullable = false)
    private String prevOwnerUsername;

    @Column(name = "traded_item_id", nullable = false)
    private Long tradedItemId;

    @Column(name = "traded_item_name", length = 200, nullable = false)
    private String tradedItemName;

    public String getPrevOwnerUsername() {
        return prevOwnerUsername;
    }

    public void setPrevOwnerUsername(String prevOwnerUsername) {
        this.prevOwnerUsername = prevOwnerUsername;
    }

    public Long getTradedItemId() {
        return tradedItemId;
    }

    public void setTradedItemId(Long tradedItemId) {
        this.tradedItemId = tradedItemId;
    }

    public String getTradedItemName() {
        return tradedItemName;
    }

    public void setTradedItemName(String tradedItemName) {
        this.tradedItemName = tradedItemName;
    }

    @Override
    public String toString() {
        return "ItemHistory{" +
                "prevOwnerUsername='" + prevOwnerUsername + '\'' +
                ", tradedItemId=" + tradedItemId +
                ", tradedItemName='" + tradedItemName + '\'' +
                '}';
    }
}
