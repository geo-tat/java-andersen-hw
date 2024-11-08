package com.andersen.hw.util;

import com.andersen.hw.model.BusTicket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class TicketDataReader {

    @Value("ticketData.txt")
    Resource resourceFile;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<BusTicket> getBusTickets() throws IOException {
        List<String> ticketStrings = readDateFromFile();
        List<BusTicket> busTickets = new ArrayList<>();

        for (String ticketString : ticketStrings) {
            if (!ticketString.trim().isEmpty()) {
                BusTicket busTicket = toEntity(ticketString);
                if (busTicket != null) {
                    busTickets.add(busTicket);
                }
            }
        }
        return busTickets;
    }

    private List<String> readDateFromFile() throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resourceFile.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace("“", "\"").replace("”", "\"");
                lines.add(line);
            }
        }
        return lines;
    }

    private BusTicket toEntity(String ticketString) {
        try {
            return objectMapper.readValue(ticketString, BusTicket.class);
        } catch (JsonProcessingException e) {
            System.err.println("Failed to parse ticket data: " + ticketString);
            e.printStackTrace();
            return null;
        }
    }
}
