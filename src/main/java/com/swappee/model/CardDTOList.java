package com.swappee.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;

/**
 * DTO containing a list of cards (either item cards or user view)
 * Used when showing list of items/cards on front end
 */
public class CardDTOList implements Serializable {
    private static final long serialVersionUID = -7614892591191464871L;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private Object data;

    private long totalItems;

    private long timeTaken;

}
