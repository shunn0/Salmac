package com.salmac.host.service;

import com.salmac.host.entity.ServerEntity;
import com.salmac.host.repo.ScriptRepo;
import com.salmac.host.repo.ServerRepo;
import com.salmac.host.utils.Constants;
import com.salmac.host.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ServerService {

    @Autowired
    ServerRepo serverRepo;

    @Autowired
    ScriptRepo scriptRepo;

    public void heartBeat(String ip, String name) {
        Optional<ServerEntity> optionalServerEntity = serverRepo.getServerEntityByIp(ip);
        if (optionalServerEntity.isPresent()) {
            ServerEntity serverEntity = optionalServerEntity.get();
            serverEntity.setLatestDowntime(LocalDateTime.now());
            serverEntity.setStatus(Constants.STATUS_ACTIVE);
            serverRepo.save(serverEntity);
        } else {
            ServerEntity serverEntity = ServerEntity.builder().ip(ip).name(name)
                    .latestUptime(LocalDateTime.now())
                    .status(Constants.STATUS_ACTIVE)
                    .build();
            serverRepo.save(serverEntity);
        }
        Utils.serverLastHeartbeatMap.put(ip, LocalDateTime.now());
    }

    public void updateServerStatusScheduler() {
//        Utils.serverLastHeartbeatMap.entrySet().stream()
//                .filter(e-> Duration.between(e.getValue(), LocalDateTime.now()).toMillis() > Constants.SERVER_INACTIVATE_DELAY)
//                .forEach(e -> serverRepo.updateServerStatusByIP(e.getKey()));
        Iterator<Map.Entry<String, LocalDateTime>> iter = Utils.serverLastHeartbeatMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<String, LocalDateTime> e = iter.next();
            if (Duration.between(e.getValue(), LocalDateTime.now()).toMillis() > Constants.SERVER_INACTIVATE_DELAY) {
                Optional<ServerEntity> optionalServerEntity = serverRepo.getServerEntityByIp(e.getKey());
                if(optionalServerEntity.isPresent()){
                    ServerEntity serverEntity = optionalServerEntity.get();
                    serverEntity.setStatus(Constants.STATUS_INACTIVE);
                    serverEntity.setLatestDowntime(LocalDateTime.now());
                }
                iter.remove();
            }
        }

    }

    public List<ServerEntity> getListOfServer() {
        return serverRepo.findAll();
    }
}
