package com.mutation.testing.demo.controllers;

import com.mutation.testing.demo.enums.OrderBy;
import com.mutation.testing.demo.enums.TipoPublicacion;
import java.util.List;
import java.util.Optional;

public record SearchParams(Optional<List<Integer>> publicacionesToExclude, Optional<List<String>> anunciantesToExclude, OrderBy order , TipoPublicacion tipoPublicacion) {
}
