package com.nutech_ferdi.nutech_ferdi.service;

import com.nutech_ferdi.nutech_ferdi.dto.ProfileResponseDTO;
import com.nutech_ferdi.nutech_ferdi.dto.ProfileUpdateRequestDTO;
import com.nutech_ferdi.nutech_ferdi.exception.ResourceNotFoundException;
import com.nutech_ferdi.nutech_ferdi.model.User;
import com.nutech_ferdi.nutech_ferdi.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepo;
    private final Path rootLocation = Paths.get("uploads");

    @PostConstruct
    public void init() {
        try {
            if (!Files.exists(rootLocation)) {
                Files.createDirectories(rootLocation);
            }
        }catch (IOException ex) {
            throw new RuntimeException("Could not initialize storage", ex);
        }
    }


    private ProfileResponseDTO mapToResponseDTO(User user) {
        ProfileResponseDTO res = new ProfileResponseDTO();
        res.setEmail(user.getEmail());
        res.setFirst_name(user.getFirstName());
        res.setLast_name(user.getLastName());
        res.setProfile_image(user.getProfile_image());

        return res;
    }

    private void deleteOldProfilePictures(String oldImagePath) {
        try {
            if (oldImagePath == null || oldImagePath.isEmpty()) {
                return;
            }
            String oldFilename = oldImagePath.replace("/uploads/", "");
            Path oldFilePath = this.rootLocation.resolve(oldFilename).toAbsolutePath();

            if (Files.exists(oldFilePath)) {
                Files.delete(oldFilePath);
            }else {
                log.warn("Image Path not found");
            }
        }catch (IOException ex) {
            log.error("Failed delete image ", ex);
        }
    }

    @Override
    public ProfileResponseDTO getUserProfile() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepo.findByEmail(email)
                .orElseThrow(()
                        -> new ResourceNotFoundException("Profile not found with email " + email));
        return mapToResponseDTO(user);
    }

    @Transactional
    @Override
    public ProfileResponseDTO updateProfilePicture(MultipartFile file) {
        try {
            String contentType = file.getContentType();
            if (contentType == null || !List.of("image/jpeg", "image/png").contains(contentType)) {
                throw new IllegalArgumentException("Format Image tidak sesuai");
            }

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();

            User user = userRepo.findByEmail(email).orElseThrow(()
                    -> new ResourceNotFoundException("Data not found with email " + email));

            String oldImagePath = user.getProfile_image();

            String originalFilename= file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String uniqueFilename = UUID.randomUUID().toString() + fileExtension;

            Path destinationFile = this.rootLocation.resolve(uniqueFilename).toAbsolutePath();

            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            deleteOldProfilePictures(oldImagePath);

            String savedPath = "/uploads/" + uniqueFilename;
            user.setProfile_image(savedPath);
            User updatedUser = userRepo.save(user);

            return mapToResponseDTO(updatedUser);

        }catch (IOException ex) {
            throw new RuntimeException("Failed to store file ", ex);
        }
    }

    @Override
    public ProfileResponseDTO updateProfile(ProfileUpdateRequestDTO requestDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Data not found with email " + email));

        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());

        User updatedUser = userRepo.save(user);

        return mapToResponseDTO(updatedUser);
    }
}
