package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.FeedbackDto;
import com.etalent.etalent_backend.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FeedbackMapperM {
    FeedbackMapperM INSTANCE = Mappers.getMapper(FeedbackMapperM.class);

    @Mapping(source = "usuario.idUsuario", target = "usuarioId")
    @Mapping(source = "usuario.nombreUsuario", target = "nombreUsuario")
    @Mapping(source = "usuario.sapUsuario", target = "sapUsuario")
    @Mapping(source = "usuario.estadoUsuario", target = "estadoUsuario")
    @Mapping(source = "usuario.rolUsuario.nombreRolUsuario", target = "rolUsuario")
    @Mapping(source = "admin.idAdmin", target = "adminId")
    @Mapping(source = "admin.nombreAdmin", target = "nombreAdmin")
    FeedbackDto toFeedbackDto(Feedback feedback);

    @Mapping(target = "usuario.idUsuario", source = "usuarioId")
    @Mapping(target = "admin.idAdmin", source = "adminId")
    Feedback toFeedback(FeedbackDto feedbackDto);
}
