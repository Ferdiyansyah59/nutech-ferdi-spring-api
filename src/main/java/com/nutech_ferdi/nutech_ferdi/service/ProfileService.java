package com.nutech_ferdi.nutech_ferdi.service;

import com.nutech_ferdi.nutech_ferdi.dto.ProfileResponseDTO;
import com.nutech_ferdi.nutech_ferdi.dto.ProfileUpdateRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProfileService {
    ProfileResponseDTO getUserProfile();
    ProfileResponseDTO updateProfilePicture(MultipartFile file);
    ProfileResponseDTO updateProfile(ProfileUpdateRequestDTO requestDTO);
}
