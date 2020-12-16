package com.example.vich_syst_rgr_back.core.servises;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {
    public String getCurrentUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
