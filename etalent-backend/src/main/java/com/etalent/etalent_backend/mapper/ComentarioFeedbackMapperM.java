package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.ComentarioFeedbackDto;
import com.etalent.etalent_backend.entity.ComentarioFeedback;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComentarioFeedbackMapperM {
    ComentarioFeedbackMapperM INSTANCE = Mappers.getMapper(ComentarioFeedbackMapperM.class);

    ComentarioFeedbackDto toComentarioFeedbackDto(ComentarioFeedback comentarioFeedback);
    ComentarioFeedback toComentarioFeedback(ComentarioFeedbackDto comentarioFeedbackDto);
}
