package org.curs.AppServer.service;

import org.curs.AppServer.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentService {
    @Autowired
    private AgentRepository agentRepository;
}
