package org.nvware.core.repository;


import org.nvware.core.model.FlashCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlashCardRepository extends JpaRepository<FlashCard, Integer> {

    FlashCard getWordByLeftAndUserId(String left, Long userId);

    @Query("SELECT f FROM FlashCard f WHERE f.lesson IN (:rows)")
    List<FlashCard> getAllWordsByLessons(String rows);

    List<FlashCard> findAll();

    List<FlashCard> getAllWordsByLeftAndUserId(String left, Long userId);

    int countByLeftAndUserId(String left, Long userId);

    int deleteByLeftAndUserId(String left, Long userId);

}