package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.FeedbackDto;
import com.etalent.etalent_backend.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FeedbackMapperM {
    FeedbackMapperM INSTANCE = Mappers.getMapper(FeedbackMapperM.class);

    //@Mapping(source = "usuario.idUsuario", target = "idUsuario")
    //@Mapping(source = "admin.idAdmin", target = "idAdmin")
    FeedbackDto toFeedbackDto(Feedback feedback);

    //@Mapping(source = "idUsuario", target = "usuario.idUsuario")
    //@Mapping(source = "idAdmin", target = "admin.idAdmin")
    Feedback toFeedback(FeedbackDto feedbackDto);
}
