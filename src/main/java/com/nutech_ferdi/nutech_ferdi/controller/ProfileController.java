package com.nutech_ferdi.nutech_ferdi.controller;

import com.nutech_ferdi.nutech_ferdi.dto.ProfileResponseDTO;
import com.nutech_ferdi.nutech_ferdi.dto.ProfileUpdateRequestDTO;
import com.nutech_ferdi.nutech_ferdi.dto.SuccessResponseDTO;
import com.nutech_ferdi.nutech_ferdi.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<SuccessResponseDTO<ProfileResponseDTO>> getuserProfile() {
        ProfileResponseDTO profile = profileService.getUserProfile();

        SuccessResponseDTO<ProfileResponseDTO> res = new SuccessResponseDTO<>(
                0,
                "Sukses",
                profile
        );

        return ResponseEntity.ok(res);
    }

    @PutMapping("/update")
    public ResponseEntity<SuccessResponseDTO<ProfileResponseDTO>> updateUser(@Valid @RequestBody ProfileUpdateRequestDTO requestDTO) {
        ProfileResponseDTO profile = profileService.updateProfile(requestDTO);

        SuccessResponseDTO<ProfileResponseDTO> res = new SuccessResponseDTO<>(
                0,
                "Update Profile berhasil",
                profile
        );

        return ResponseEntity.ok(res);
    }

    @PutMapping("/image")
    public ResponseEntity<SuccessResponseDTO<ProfileResponseDTO>> updateProfileImage(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        ProfileResponseDTO data = profileService.updateProfilePicture(file);

        SuccessResponseDTO<ProfileResponseDTO> res = new SuccessResponseDTO<>(
                0,
                "Update Profile Image =berhasil",
                data
        );

        return ResponseEntity.ok(res);
    }
}
