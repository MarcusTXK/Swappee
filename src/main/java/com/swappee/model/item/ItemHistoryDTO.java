package com.swappee.model.item;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Item History DTO
 */

@XmlRootElement
public class ItemHistoryDTO  implements Serializable {
    private static final long serialVersionUID = 6865824316270715233L;

    private String prevOwnerUsername;

    private Long tradedItemId;

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
        return "ItemHistoryDTO{" +
                "prevOwnerUsername='" + prevOwnerUsername + '\'' +
                ", tradedItemId=" + tradedItemId +
                ", tradedItemName='" + tradedItemName + '\'' +
                '}';
    }
}
