package dev.roberto.memorize.controller;

import dev.roberto.memorize.model.Deck;
import dev.roberto.memorize.model.User;
import dev.roberto.memorize.service.FlashcardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/decks")
@CrossOrigin(origins = "https://memorize-weld.vercel.app/")
public class DeckController {

    private final FlashcardService flashcardService;

    public DeckController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    private UUID getUserId(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return user.getId();
    }

    @GetMapping
    public List<Deck> getAllDecks(Authentication authentication) {
        return flashcardService.getAllDecks(getUserId(authentication));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Deck> getDeckById(@PathVariable UUID id, Authentication authentication) {
        return flashcardService.getDeckById(id, getUserId(authentication))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Deck createDeck(@RequestBody Deck deck, Authentication authentication) {
        return flashcardService.createDeck(deck, getUserId(authentication));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Deck> updateDeck(@PathVariable UUID id, @RequestBody Deck deckDetails, Authentication authentication) {
        return flashcardService.updateDeck(id, deckDetails, getUserId(authentication))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeck(@PathVariable UUID id, Authentication authentication) {
        boolean deleted = flashcardService.deleteDeck(id, getUserId(authentication));
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
