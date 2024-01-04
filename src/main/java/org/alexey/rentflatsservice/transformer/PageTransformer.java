package org.alexey.rentflatsservice.transformer;

import org.alexey.rentflatsservice.core.dto.FlatInfoDto;
import org.alexey.rentflatsservice.core.dto.PageOfFlatDto;
import org.springframework.data.domain.Page;

public interface PageTransformer {

    PageOfFlatDto transformPageOfFlatDtoFromPage(Page<FlatInfoDto> page);
}
