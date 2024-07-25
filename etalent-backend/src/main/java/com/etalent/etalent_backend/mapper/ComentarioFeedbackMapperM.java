package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.ComentarioFeedbackDto;
import com.etalent.etalent_backend.entity.ComentarioFeedback;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ComentarioFeedbackMapperM {
    ComentarioFeedbackMapperM INSTANCE = Mappers.getMapper(ComentarioFeedbackMapperM.class);

    @Mapping(target = "feedbackId", source = "feedback.idFeedback")
    @Mapping(target = "usuarioId", source = "usuario.idUsuario")
    @Mapping(target = "adminId", source = "admin.idAdmin")
    @Mapping(target = "nombreAdmin", source = "admin.nombreAdmin")
    ComentarioFeedbackDto toComentarioFeedbackDto(ComentarioFeedback comentarioFeedback);

    @InheritInverseConfiguration
    ComentarioFeedback toComentarioFeedback(ComentarioFeedbackDto comentarioFeedbackDto);
}
