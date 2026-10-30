package dev.roberto.memorize.service;

import dev.roberto.memorize.model.Card;
import dev.roberto.memorize.model.Deck;
import dev.roberto.memorize.repository.CardRepository;
import dev.roberto.memorize.repository.DeckRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FlashcardService {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;

    public FlashcardService(DeckRepository deckRepository, CardRepository cardRepository) {
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
    }

    // Deck operations
    public List<Deck> getAllDecks() {
        return deckRepository.findAll();
    }

    public Optional<Deck> getDeckById(UUID id) {
        return deckRepository.findById(id);
    }

    public Deck createDeck(Deck deck) {
        return deckRepository.save(deck);
    }

    @Transactional
    public boolean deleteDeck(UUID id) {
        if (!deckRepository.existsById(id)) {
            return false;
        }
        cardRepository.deleteAllByDeckId(id);
        deckRepository.deleteById(id);
        return true;
    }

    // Card operations
    public List<Card> getCardsByDeckId(UUID deckId) {
        return cardRepository.findAllByDeckId(deckId);
    }

    public Card createCard(UUID deckId, Card card) {
        card.setDeckId(deckId);
        return cardRepository.save(card);
    }

    public boolean deleteCard(UUID cardId) {
        if (!cardRepository.existsById(cardId)) {
            return false;
        }
        cardRepository.deleteById(cardId);
        return true;
    }
}
