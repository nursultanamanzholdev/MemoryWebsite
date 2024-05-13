package com.nursultan.memoryjar.memory;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.StringUtils;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

@Service
public class MemoryService {

    private final MemoryRepository memoryRepository;

    @Autowired
    public MemoryService(MemoryRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
    }

    public List<Memory> getAllMemories(){
        return memoryRepository.findAll();
    }

    public Optional<Memory> getMemoryById(Long memoryId){
        return memoryRepository.findById(memoryId);
    }

    @Transactional
    public Memory createMemory(MemoryDTO memoryDTO){
        validateMemoryDTO(memoryDTO);
        Memory memory = new Memory();
        mapDTOToMemory(memoryDTO, memory);
        return memoryRepository.save(memory);
    }

    @Transactional
    public Optional<Memory> updateMemory(Long memoryId, MemoryDTO updatedMemoryDTO){
        validateMemoryDTO(updatedMemoryDTO);

        return memoryRepository.findById(memoryId).map(existingMemory -> {
            mapDTOToMemory(updatedMemoryDTO, existingMemory);
            return existingMemory;
        });
    }

    public boolean deleteMemory(Long memoryId){
        if(memoryRepository.existsById(memoryId)){
            memoryRepository.deleteById(memoryId);
            return true;
        }
        return false;
    }

    private void mapDTOToMemory(MemoryDTO memoryDTO, Memory memory){
        memory.setTitle(memoryDTO.getTitle());
        memory.setDate(memoryDTO.getDate());
        memory.setContent(memoryDTO.getContent());
        memory.setLocation(memoryDTO.getLocation());
    }

    private void validateMemoryDTO(MemoryDTO memoryDTO){
        if (memoryDTO == null) {
            throw new IllegalArgumentException("MemoryDTO cannot be null");
        }

        if (StringUtils.isBlank(memoryDTO.getTitle())) {
            throw new IllegalArgumentException("Memory title is required");
        }

        if (memoryDTO.getDate() == null) {
            throw new IllegalArgumentException("Memory date is required");
        }

        if (memoryDTO.getContent() == null) {
            throw new IllegalArgumentException("Memory content is required");
        }

        if (memoryDTO.getLocation() == null) {
            throw new IllegalArgumentException("Memory location is required");
        }

        if (StringUtils.isBlank(memoryDTO.getLocation())) {
            throw new IllegalArgumentException("Memory location is required");
        }

        if (StringUtils.isBlank(memoryDTO.getContent())) {
            throw new IllegalArgumentException("Memory content is required");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(memoryDTO.getDate().toString(), formatter);
        } catch (DateTimeParseException exception) {
            throw new IllegalArgumentException("Invalid date format. Use 'yyyy-MM-dd'", exception);
        }


        if (StringUtils.length(memoryDTO.getTitle()) > 255) {
            throw new IllegalArgumentException("Memory title must be 255 characters or less");
        }

        if (StringUtils.length(memoryDTO.getLocation()) > 100) {
            throw new IllegalArgumentException("Memory location must be 100 characters or less");
        }

        if (StringUtils.length(memoryDTO.getContent()) > 1000) {
            throw new IllegalArgumentException("Memory content must be 1000 characters or less");
        }
    }



}
