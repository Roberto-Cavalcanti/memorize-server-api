package dev.roberto.memorize.controller;

import dev.roberto.memorize.model.Deck;
import dev.roberto.memorize.service.FlashcardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/decks")
@CrossOrigin(origins = "https://memorize-weld.vercel.app")
public class DeckController {

    private final FlashcardService flashcardService;

    public DeckController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @GetMapping
    public List<Deck> getAllDecks() {
        return flashcardService.getAllDecks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deck> getDeckById(@PathVariable UUID id) {
        return flashcardService.getDeckById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Deck createDeck(@RequestBody Deck deck) {
        return flashcardService.createDeck(deck);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deck> updateDeck(@PathVariable UUID id, @RequestBody Deck deckDetails) {
        return flashcardService.updateDeck(id, deckDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeck(@PathVariable UUID id) {
        boolean deleted = flashcardService.deleteDeck(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
