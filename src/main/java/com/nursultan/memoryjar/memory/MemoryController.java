package com.nursultan.memoryjar.memory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/memories")
public class MemoryController {
    private final MemoryService memoryService;

    @Autowired
    public MemoryController(MemoryService memoryService) {
        this.memoryService = memoryService;
    }

    @GetMapping
    public List<Memory> gelAllMemories() {
        return memoryService.getAllMemories();
    }

    @GetMapping("/{memoryId}")
    public ResponseEntity<Memory> getMemoryById(@PathVariable Long memoryId){
        return memoryService.getMemoryById(memoryId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Memory> createMemory(@RequestBody MemoryDTO memoryDTO){
            Memory createdMemory = memoryService.createMemory(memoryDTO);
            return ResponseEntity.created(URI.create("/api/memories" + createdMemory.getId())).body(createdMemory);
    }

    @PutMapping("/{memoryId}")
    public ResponseEntity<Memory> updateMemory(@PathVariable Long memoryId, @RequestBody MemoryDTO updatedMemoryDTO){
            return memoryService.updateMemory(memoryId, updatedMemoryDTO)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{memoryId}")
    public ResponseEntity<Void> deleteMemory(@PathVariable Long memoryId) {
        if (memoryService.deleteMemory(memoryId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
