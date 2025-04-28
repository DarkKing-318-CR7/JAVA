package com.example.pickleball.Service;

import com.example.pickleball.model.dto.SupportDto;
import com.example.pickleball.model.entity.SupportRequest;
import com.example.pickleball.Repositories.SupportRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SupportService {

    @Autowired
    private SupportRequestRepository supportRequestRepository;

    public void saveSupportRequest(SupportDto supportDto) {
        SupportRequest request = new SupportRequest();
        request.setFullname(supportDto.getFullname());
        request.setEmail(supportDto.getEmail());
        request.setSubject(supportDto.getSubject());
        request.setMessage(supportDto.getMessage());

        supportRequestRepository.save(request);
    }
}
