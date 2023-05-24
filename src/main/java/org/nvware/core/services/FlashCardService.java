package org.nvware.core.services;

import org.nvware.core.model.FlashCard;
import org.nvware.core.repository.FlashCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class FlashCardService {
    private final FlashCardRepository flashCardRepository;

    @Autowired
    public FlashCardService(FlashCardRepository flashCardRepository) {
        this.flashCardRepository = flashCardRepository;
    }

    public FlashCard getWordByLeftAndUserId(String left, Long userId) {
        return flashCardRepository.getWordByLeftAndUserId(left, userId);
    }

    public List<FlashCard> getAllWordsByLessons(String rows) {
        return flashCardRepository.getAllWordsByLessons(rows);
    }

    public List<FlashCard> getAllWords() {
        return flashCardRepository.findAll();
    }

    public List<FlashCard> getAllWordsByLeftAndUserId(String left, Long userId) {
        return flashCardRepository.getAllWordsByLeftAndUserId(left, userId);
    }

    public int countWordsByLeftAndUserId(String left, Long userId) {
        return flashCardRepository.countByLeftAndUserId(left, userId);
    }

    public int deleteWordsByLeftAndUserId(String left, Long userId) {
        return flashCardRepository.deleteByLeftAndUserId(left, userId);
    }
    public int saveWords(String left, String right, Long userId) {
        FlashCard flashCard = new FlashCard();
        flashCard.setLeft(left);
        flashCard.setRight(right);
        flashCard.setUserId(userId);

        FlashCard savedFlashCard = flashCardRepository.save(flashCard);

        if (savedFlashCard != null) {
            return savedFlashCard.getId();
        } else {
            return -1; // or throw an exception to indicate the save operation failed
        }
    }
    public int updateWords(String left, String right, Long userId) {
        FlashCard existingFlashCard = flashCardRepository.getWordByLeftAndUserId(left, userId);

        if (existingFlashCard != null) {
            existingFlashCard.setRight(right);
            FlashCard updatedFlashCard = flashCardRepository.save(existingFlashCard);
            return updatedFlashCard.getId();
        } else {
            return -1; // or throw an exception to indicate the update operation failed
        }
    }

    public int deleteWords(String left, Long userId) {
        int deletedCount = flashCardRepository.deleteByLeftAndUserId(left, userId);
        return deletedCount;
    }

}

