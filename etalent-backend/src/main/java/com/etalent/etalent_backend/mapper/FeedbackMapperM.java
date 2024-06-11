package com.etalent.etalent_backend.mapper;

import com.etalent.etalent_backend.dto.FeedbackDto;
import com.etalent.etalent_backend.entity.Feedback;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FeedbackMapperM {
    FeedbackMapperM INSTANCE = Mappers.getMapper(FeedbackMapperM.class);

    FeedbackDto toFeedbackDto(Feedback feedback);
    Feedback toFeedback(FeedbackDto feedbackDto);
}
