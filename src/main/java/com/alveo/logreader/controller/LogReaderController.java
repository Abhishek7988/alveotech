package com.alveo.logreader.controller;

import com.alveo.logreader.service.LogReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LogReaderController {

    @Autowired
    private LogReaderService logReaderService;

    @GetMapping(value = "/log/count/{fileName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Integer> getLogLevelCount(@PathVariable(required = true, name = "fileName") final String fileName, @PathVariable(required = false, name = "intervalInSeconds") final Integer intervalInSeconds){
        return logReaderService.countLogLevelLogs(fileName, intervalInSeconds == null ? 5 : intervalInSeconds);
    }
}
