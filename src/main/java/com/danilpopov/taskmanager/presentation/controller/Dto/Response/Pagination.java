package com.danilpopov.taskmanager.presentation.controller.Dto.Response;

import java.util.List;

public class Pagination<T>{
    List<T> items;
    Long itemsCount;
    Integer pageCount;

    public Pagination(List<T> items, Integer pageCount, Long itemsCount) {
        this.items = items;
        this.pageCount = pageCount;
        this.itemsCount = itemsCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Long getItemsCount() {
        return itemsCount;
    }

    public void setItemsCount(Long itemsCount) {
        this.itemsCount = itemsCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
