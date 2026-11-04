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

    public Optional<Deck> updateDeck(UUID id, Deck deckDetails) {
        return deckRepository.findById(id).map(deck -> {
            deck.setTitle(deckDetails.getTitle());
            deck.setDescription(deckDetails.getDescription());
            return deckRepository.save(deck);
        });
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
        return cardRepository.findAllByDeckIdOrderByPositionAsc(deckId);
    }

    public Card createCard(UUID deckId, Card card) {
        card.setDeckId(deckId);
        List<Card> existingCards = cardRepository.findAllByDeckId(deckId);
        card.setPosition(existingCards.size());
        return cardRepository.save(card);
    }

    @Transactional
    public boolean reorderCards(UUID deckId, List<UUID> cardIds) {
        if (!deckRepository.existsById(deckId)) {
            return false;
        }

        List<Card> cards = cardRepository.findAllByDeckId(deckId);

        // Update positions based on index in cardIds list
        for (int i = 0; i < cardIds.size(); i++) {
            UUID cardId = cardIds.get(i);
            for (Card card : cards) {
                if (card.getId().equals(cardId)) {
                    card.setPosition(i);
                    cardRepository.save(card);
                    break;
                }
            }
        }
        return true;
    }

    public boolean deleteCard(UUID cardId) {
        if (!cardRepository.existsById(cardId)) {
            return false;
        }
        cardRepository.deleteById(cardId);
        return true;
    }

    public Optional<Card> updateCard(UUID cardId, Card cardDetails) {
        return cardRepository.findById(cardId).map(card -> {
            card.setFront(cardDetails.getFront());
            card.setBack(cardDetails.getBack());
            return cardRepository.save(card);
        });
    }
}
