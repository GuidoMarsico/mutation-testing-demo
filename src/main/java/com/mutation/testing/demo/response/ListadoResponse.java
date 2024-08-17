package com.mutation.testing.demo.response;

import com.mutation.testing.demo.enums.OrderBy;

import java.util.List;

public record ListadoResponse(Integer count, List<Card> cards , OrderBy useOrder) {
}
