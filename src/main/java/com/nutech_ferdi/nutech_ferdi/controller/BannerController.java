package com.nutech_ferdi.nutech_ferdi.controller;

import com.nutech_ferdi.nutech_ferdi.dto.BannerResponseDTO;
import com.nutech_ferdi.nutech_ferdi.dto.SuccessResponseDTO;
import com.nutech_ferdi.nutech_ferdi.service.BannerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BannerController {

    private final BannerService bannerService;

    @GetMapping("/banner")
    public ResponseEntity<SuccessResponseDTO<List<BannerResponseDTO>>> getMasterBanner() {
        List<BannerResponseDTO> banner = bannerService.getMasterBanner();

        SuccessResponseDTO<List<BannerResponseDTO>> res = new SuccessResponseDTO<>(
                0,
                "Sukses",
                banner
        );

        return ResponseEntity.ok(res);
    }
}
