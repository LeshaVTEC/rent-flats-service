package org.alexey.rentflatsservice.transformer.impl;

import org.alexey.rentflatsservice.core.dto.FlatInfoDto;
import org.alexey.rentflatsservice.core.dto.PageOfFlatDto;
import org.alexey.rentflatsservice.transformer.PageTransformer;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageTransformerImpl implements PageTransformer {

    @Override
    public PageOfFlatDto transformPageOfFlatDtoFromPage(Page<FlatInfoDto> page) {
        return (PageOfFlatDto) new PageOfFlatDto()
                .setContent(page.getContent())
                .setNumber(page.getNumber())
                .setSize(page.getSize())
                .setTotalPages(page.getTotalPages())
                .setTotalElements(page.getTotalElements())
                .setFirst(page.isFirst())
                .setNumberOfElements(page.getNumberOfElements())
                .setLast(page.isLast());
    }
}
