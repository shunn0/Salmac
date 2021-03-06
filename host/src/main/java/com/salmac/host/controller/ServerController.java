	package com.salmac.host.controller;

import com.salmac.host.entity.ServerEntity;
import com.salmac.host.service.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
@RequestMapping("/server")
@CrossOrigin(origins = "http://localhost:3000")
public class ServerController {

    @Autowired
    ServerService serverService;

    //@PostMapping("/heartbeat")
    @RequestMapping(value = "/heartbeat", method = {RequestMethod.POST })
    public ResponseEntity heartbeat(String targetEngineAddress) {
        System.out.println("Received request from "+ targetEngineAddress);
        serverService.heartBeat(targetEngineAddress,"");
        ResponseEntity re =  ResponseEntity.ok().build();
		System.out.println(re.toString());
		return re;
    }

    @GetMapping("/all")
    public ResponseEntity getServerList(){
        List<ServerEntity> serverEntities = serverService.getListOfServer();
        if(!serverEntities.isEmpty()){
            return ResponseEntity.ok().body(serverEntities);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
