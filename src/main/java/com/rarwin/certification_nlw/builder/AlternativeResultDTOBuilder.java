package com.rarwin.certification_nlw.builder;

import com.rarwin.certification_nlw.dto.AlternativeResultDTO;
import com.rarwin.certification_nlw.entities.Alternative;

import java.util.List;
import java.util.stream.Collectors;

public class AlternativeResultDTOBuilder {

    public static AlternativeResultDTO from(Alternative alternative) {

        return AlternativeResultDTO
                .builder()
                .id(alternative.getId())
                .description(alternative.getDescription())
                .build();
    }

    public static List<AlternativeResultDTO> from(List<Alternative> alternatives) {

        List<AlternativeResultDTO> list = alternatives.stream().map(alternative -> from(alternative)).collect(Collectors.toList());

        return list;
    }
}
