package dev.roberto.memorize.controller;

import dev.roberto.memorize.model.Card;
import dev.roberto.memorize.service.FlashcardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/decks/{deckId}/cards")
@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "https://memorize-weld.vercel.app")
public class CardController {

    private final FlashcardService flashcardService;

    public CardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getCardsByDeckId(@PathVariable UUID deckId) {
        if (flashcardService.getDeckById(deckId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flashcardService.getCardsByDeckId(deckId));
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@PathVariable UUID deckId, @RequestBody Card card) {
        if (flashcardService.getDeckById(deckId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Card created = flashcardService.createCard(deckId, card);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/reorder")
    public ResponseEntity<Void> reorderCards(@PathVariable UUID deckId, @RequestBody List<UUID> cardIds) {
        boolean success = flashcardService.reorderCards(deckId, cardIds);
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable UUID cardId) {
        boolean deleted = flashcardService.deleteCard(cardId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
