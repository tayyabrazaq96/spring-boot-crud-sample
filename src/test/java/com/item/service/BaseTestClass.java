package com.item.service;

import com.item.service.dto.request.SaveItemReq;
import com.item.service.dto.response.ItemDto;
import com.item.service.entity.Item;
import com.item.service.value.Unit;
import java.math.BigDecimal;
import java.time.Instant;

public abstract class BaseClass {

    private SaveItemReq getSaveItemReq() {
        return SaveItemReq.builder()
                .title("Title")
                .description("description")
                .unit(Unit.KG)
                .unitPrice(BigDecimal.TEN)
                .build();
    }

    private Item getItem() {
        Item item = new Item();
        item.setCreatedAt(Instant.now());
        item.setUpdatedAt(Instant.now());
        item.setUnit(Unit.KG);
        item.setUnitPrice(BigDecimal.TEN);
        item.setTitle("Title");
        item.setDescription("Description");
        return item;
    }

    private ItemDto getItemDto() {
        ItemDto itemDto = new ItemDto();

        itemDto.setCreatedAtTime(Instant.now().toEpochMilli());
        itemDto.setUpdatedAtTime(Instant.now().toEpochMilli());
        itemDto.setUnit(Unit.KG);
        itemDto.setUnitPrice(BigDecimal.TEN);
        itemDto.setTitle("Title");
        itemDto.setDescription("Description");

        return itemDto;
    }
}
