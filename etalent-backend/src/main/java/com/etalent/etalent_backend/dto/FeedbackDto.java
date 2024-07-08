package com.etalent.etalent_backend.dto;

import com.etalent.etalent_backend.entity.ComentarioFeedback;
import com.etalent.etalent_backend.entity.RolUsuario;
import com.etalent.etalent_backend.entity.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {

    private Integer idFeedback;
    private String descripcionFeedback;
    private Date fechaCreacionFeedback;
    private Integer usuarioId;
    private String nombreUsuario;
    private String sapUsuario;
    private String estadoUsuario;
    private String rolUsuario;
    private Integer adminId;
    private String nombreAdmin;
    private Set<ComentarioFeedback> comentarios;
}
