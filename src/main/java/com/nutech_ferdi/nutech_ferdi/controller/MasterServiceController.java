package com.nutech_ferdi.nutech_ferdi.controller;

import com.nutech_ferdi.nutech_ferdi.dto.MasterServiceDTO;
import com.nutech_ferdi.nutech_ferdi.dto.SuccessResponseDTO;
import com.nutech_ferdi.nutech_ferdi.service.MasterServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MasterServiceController {
    private final MasterServiceService masterService;

    @GetMapping("/services")
    public ResponseEntity<SuccessResponseDTO<List<MasterServiceDTO>>> getMasterService() {
        List<MasterServiceDTO> serv = masterService.getMasterService();

        SuccessResponseDTO<List<MasterServiceDTO>> res = new SuccessResponseDTO<>(
                0,
                "Sukses",
                serv
        );

        return ResponseEntity.ok(res);
    }
}
