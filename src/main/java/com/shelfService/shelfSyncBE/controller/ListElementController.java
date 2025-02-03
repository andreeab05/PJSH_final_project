package com.shelfService.shelfSyncBE.controller;

import com.shelfService.shelfSyncBE.entity.ListElement;
import com.shelfService.shelfSyncBE.service.ListElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/list-elements")
public class ListElementController {

    @Autowired
    private ListElementService listElementService;

    // Endpoint pentru a obține un element după ID
    @GetMapping("/{elementId}")
    public ResponseEntity<ListElement> getElementById(@PathVariable Integer elementId) {
        try {
            ListElement element = listElementService.getElementById(elementId);
            return new ResponseEntity<>(element, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pentru a actualiza un element existent
    @PutMapping("/{elementId}")
    public ResponseEntity<ListElement> updateElement(@PathVariable Integer elementId,
                                                     @RequestParam String progress,
                                                     @RequestParam Integer currentPages) {
        try {
            ListElement updatedElement = listElementService.updateElement(elementId, progress, currentPages);
            return new ResponseEntity<>(updatedElement, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint pentru a șterge un element
    @DeleteMapping("/{elementId}")
    public ResponseEntity<String> deleteElement(@PathVariable Integer elementId) {
        try {
            listElementService.deleteElementById(elementId);
            return new ResponseEntity<>("Element deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Could not delete element", HttpStatus.NOT_FOUND);
        }
    }
}

