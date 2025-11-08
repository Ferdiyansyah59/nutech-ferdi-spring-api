package com.nutech_ferdi.nutech_ferdi.service;

import com.nutech_ferdi.nutech_ferdi.dto.MasterServiceDTO;
import com.nutech_ferdi.nutech_ferdi.model.MasterService;
import com.nutech_ferdi.nutech_ferdi.repository.MasterServiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MasterServiceServiceImpl implements MasterServiceService{
    private final MasterServiceRepository serRepo;

    private MasterServiceDTO mapMasterService(MasterService ser) {
        MasterServiceDTO res = new MasterServiceDTO();
        res.setService_code(ser.getServiceCode());
        res.setService_name(ser.getServiceName());
        res.setService_icon(ser.getServiceIcon());
        res.setService_tariff(ser.getServiceTariff());

        return res;
    }
    @Override
    public List<MasterServiceDTO> getMasterService() {
        return serRepo.findAll()
                .stream()
                .map(this::mapMasterService)
                .collect(Collectors.toList());
    }
}
