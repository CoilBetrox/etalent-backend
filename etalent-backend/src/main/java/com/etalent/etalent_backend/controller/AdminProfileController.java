package com.etalent.etalent_backend.controller;

import com.etalent.etalent_backend.dto.*;
import com.etalent.etalent_backend.entity.Admin;
import com.etalent.etalent_backend.mapper.AdminMapperM;
import com.etalent.etalent_backend.service.AdminProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/admins")
public class AdminProfileController {

    private AdminProfileService adminProfileService;

    @GetMapping("/profile")
    public ResponseEntity<AdminPerfilDto> getAdminProfile(){
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            AdminPerfilDto adminPerfilDto = adminProfileService.getAdminByEmail(email);
            return new ResponseEntity<>(adminPerfilDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/profile-update")
    public ResponseEntity<String> updateAdminPartial(@RequestBody AdminUpdateDto adminUpdateDto){
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            adminProfileService.updateAdminPartial(adminUpdateDto, email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/profile-update-status/{idAdmin}")
    public ResponseEntity<AdminPerfilDto> updateEstadoAdmin(@PathVariable("idAdmin") Integer adminId,
                                                            @RequestBody AdminPerfilDto adminUpdateDto){
        AdminPerfilDto adminDto = adminProfileService.updateAdminEstado(adminId, adminUpdateDto);
        return ResponseEntity.ok(adminDto);
    }

    @GetMapping("/byRole")
    public ResponseEntity<List<AdminDto>> getAdminsByRole(Authentication authentication){

        if (authentication == null || !authentication.isAuthenticated()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String email = authentication.getName();

        List<AdminDto> admins = adminProfileService.getAdminsByRole("AdminTienda");
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/userAdmin/{adminId}")
    public ResponseEntity<List<UsuarioDto>> getUsuariosByAdmin(@PathVariable Integer adminId){
        List<UsuarioDto> usuarios = adminProfileService.getUsuariosByAdmin(adminId);
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{oldAdminId}/reassign/{newAdminId}")
    public ResponseEntity<?> reassignUsers(
            @PathVariable Integer oldAdminId,
            @PathVariable Integer newAdminId){
        try {
            adminProfileService.reassignUsers(oldAdminId, newAdminId);
            return ResponseEntity.ok("Usuarios reasignados correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/bySap/{sapAdmin}")
    public ResponseEntity<AdminPerfilDto> getAdminBySap(@PathVariable String sapAdmin){
        return null;
    }

    @PostMapping("/bulk-jefes")
    public ResponseEntity<List<AdminRegisterDto>>createJefesBulk(@RequestBody List<AdminRegisterDto> adminsDtos){
        List<AdminRegisterDto> savedAdmins = adminProfileService.createJefesBulk(adminsDtos);
        return new ResponseEntity<>(savedAdmins, HttpStatus.CREATED);

    }
}