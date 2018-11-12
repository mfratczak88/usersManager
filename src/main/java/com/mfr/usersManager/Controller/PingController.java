package com.mfr.usersManager.Controller;

import com.mfr.usersManager.DTO.PingDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ping")
public class PingController {
    private PingDTO pingDTO;

    public PingController(PingDTO pingDTO) {
        this.pingDTO = pingDTO;
    }

    @GetMapping("/")
    public PingDTO ping() {
        return pingDTO;
    }
}