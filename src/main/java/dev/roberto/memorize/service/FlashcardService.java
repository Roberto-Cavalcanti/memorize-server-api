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
    public List<Deck> getAllDecks(UUID userId) {
        return deckRepository.findAllByUserId(userId);
    }

    public Optional<Deck> getDeckById(UUID id, UUID userId) {
        return deckRepository.findByIdAndUserId(id, userId);
    }

    public Deck createDeck(Deck deck, UUID userId) {
        deck.setUserId(userId);
        return deckRepository.save(deck);
    }

    public Optional<Deck> updateDeck(UUID id, Deck deckDetails, UUID userId) {
        return deckRepository.findByIdAndUserId(id, userId).map(deck -> {
            deck.setTitle(deckDetails.getTitle());
            deck.setDescription(deckDetails.getDescription());
            return deckRepository.save(deck);
        });
    }

    @Transactional
    public boolean deleteDeck(UUID id, UUID userId) {
        Optional<Deck> deck = deckRepository.findByIdAndUserId(id, userId);
        if (deck.isEmpty()) {
            return false;
        }
        cardRepository.deleteAllByDeckId(id);
        deckRepository.deleteById(id);
        return true;
    }

    // Card operations
    public List<Card> getCardsByDeckId(UUID deckId, UUID userId) {
        if (deckRepository.findByIdAndUserId(deckId, userId).isEmpty()) {
            return List.of();
        }
        return cardRepository.findAllByDeckIdOrderByPositionAsc(deckId);
    }

    public Card createCard(UUID deckId, Card card, UUID userId) {
        if (deckRepository.findByIdAndUserId(deckId, userId).isEmpty()) {
            throw new IllegalArgumentException("Deck not found or access denied");
        }
        card.setDeckId(deckId);
        List<Card> existingCards = cardRepository.findAllByDeckId(deckId);
        card.setPosition(existingCards.size());
        return cardRepository.save(card);
    }

    @Transactional
    public boolean reorderCards(UUID deckId, List<UUID> cardIds, UUID userId) {
        if (deckRepository.findByIdAndUserId(deckId, userId).isEmpty()) {
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

    public boolean deleteCard(UUID cardId, UUID userId) {
        Optional<Card> cardOpt = cardRepository.findById(cardId);
        if (cardOpt.isEmpty()) {
            return false;
        }
        Card card = cardOpt.get();
        if (deckRepository.findByIdAndUserId(card.getDeckId(), userId).isEmpty()) {
            return false;
        }
        cardRepository.deleteById(cardId);
        return true;
    }

    public Optional<Card> updateCard(UUID cardId, Card cardDetails, UUID userId) {
        Optional<Card> cardOpt = cardRepository.findById(cardId);
        if (cardOpt.isEmpty()) {
            return Optional.empty();
        }
        Card card = cardOpt.get();
        if (deckRepository.findByIdAndUserId(card.getDeckId(), userId).isEmpty()) {
            return Optional.empty();
        }
        card.setFront(cardDetails.getFront());
        card.setBack(cardDetails.getBack());
        return Optional.of(cardRepository.save(card));
    }
}
