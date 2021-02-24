package com.alveo.logreader.controller;

import com.alveo.logreader.service.LogReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * Controller class for log file read.
 */
@RestController
public class LogReaderController {

    @Autowired
    private LogReaderService logReaderService;

    @Value("${monitoring.interval}")
    private Integer monitoringInterval;

    @GetMapping(value = {"/log/count/{fileName}" , "/log/count/{fileName}/{intervalInSeconds}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> getLogLevelCount(@PathVariable(required = true, name = "fileName") final String fileName, @PathVariable(required = false, name = "intervalInSeconds") final Optional<Integer> intervalInSeconds){
        return logReaderService.countLogLevelLogs(fileName, intervalInSeconds.isPresent() ? intervalInSeconds.get() : monitoringInterval);
    }
}
