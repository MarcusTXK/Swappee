package com.swappee.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.swappee.domain.request.Request;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import java.time.LocalDateTime;

public class RequestDTO {
    @JsonProperty("Id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonProperty("OwnerId")
    private Long ownerId;

    @JsonProperty("TraderId")
    private Long traderId;

    @JsonProperty("ItemId")
    private Long itemId;

    @JsonProperty("Status")
    private String status;

    @Lob
    @Column(name = "remarks")
    private String remarks;

    @Column(name = "hidden", nullable = false)
    private boolean hidden;

    @JsonProperty("CreatedDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
    private LocalDateTime createdDate;

    @JsonProperty("LastUpdatedDate")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yyyy HH:mm:ss")
    private LocalDateTime lastModifiedDate;
}
