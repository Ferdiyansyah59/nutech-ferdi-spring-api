package com.nutech_ferdi.nutech_ferdi.service;

import com.nutech_ferdi.nutech_ferdi.dto.BannerResponseDTO;
import com.nutech_ferdi.nutech_ferdi.model.MasterBanner;
import com.nutech_ferdi.nutech_ferdi.repository.BannerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepo;

    private BannerResponseDTO mapMasterBanner(MasterBanner masterBanner) {
        BannerResponseDTO res = new BannerResponseDTO();
        res.setBanner_name(masterBanner.getBannerName());
        res.setBanner_image(masterBanner.getBanner_image());
        res.setDescription(masterBanner.getDescription());

        return res;
    }

    @Override
    public List<BannerResponseDTO> getMasterBanner() {
        return bannerRepo.findAll()
                .stream()
                .map(this::mapMasterBanner)
                .collect(Collectors.toList());
    }
}
