package dev.roberto.memorize.controller;

import dev.roberto.memorize.model.Card;
import dev.roberto.memorize.model.User;
import dev.roberto.memorize.service.FlashcardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/decks/{deckId}/cards")
@CrossOrigin(origins = "https://memorize-weld.vercel.app")
public class CardController {

    private final FlashcardService flashcardService;

    public CardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    private UUID getUserId(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return user.getId();
    }

    @GetMapping
    public ResponseEntity<List<Card>> getCardsByDeckId(@PathVariable UUID deckId, Authentication authentication) {
        UUID userId = getUserId(authentication);
        if (flashcardService.getDeckById(deckId, userId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(flashcardService.getCardsByDeckId(deckId, userId));
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@PathVariable UUID deckId, @RequestBody Card card, Authentication authentication) {
        UUID userId = getUserId(authentication);
        if (flashcardService.getDeckById(deckId, userId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Card created = flashcardService.createCard(deckId, card, userId);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/reorder")
    public ResponseEntity<Void> reorderCards(@PathVariable UUID deckId, @RequestBody List<UUID> cardIds, Authentication authentication) {
        boolean success = flashcardService.reorderCards(deckId, cardIds, getUserId(authentication));
        if (success) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<Void> deleteCard(@PathVariable UUID deckId, @PathVariable UUID cardId, Authentication authentication) {
        boolean deleted = flashcardService.deleteCard(cardId, getUserId(authentication));
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{cardId}")
    public ResponseEntity<Card> updateCard(@PathVariable UUID deckId, @PathVariable UUID cardId, @RequestBody Card cardDetails, Authentication authentication) {
        UUID userId = getUserId(authentication);
        if (flashcardService.getDeckById(deckId, userId).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return flashcardService.updateCard(cardId, cardDetails, userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
