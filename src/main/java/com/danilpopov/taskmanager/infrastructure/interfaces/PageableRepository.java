package com.danilpopov.taskmanager.infrastructure.interfaces;

import com.danilpopov.taskmanager.presentation.controller.Dto.Response.Pagination;

import java.util.List;

public interface PageableRepository<T> {

    List<T> findPage(
            int offset,
            int limit
    );

    Long count();
}