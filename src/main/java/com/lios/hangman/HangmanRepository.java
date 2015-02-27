package com.lios.hangman;

import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class HangmanRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public HangmanWord getRandomHangmanWord(String category) {

		Query countQuery = entityManager
				.createQuery("select count(w) from HangmanWord w WHERE w.category=:category");
		countQuery.setParameter("category", category);
		long count = (Long) countQuery.getSingleResult();
		// "words"; //13368
		// "bgtowns"; //257
		// "countries"; //192
		// "capitals"; //197

		Random random = new Random();
		int number = random.nextInt((int) count);

		Query selectQuery = entityManager
				.createQuery("select w from HangmanWord w WHERE category=:category");
		selectQuery.setParameter("category", category);

		selectQuery.setFirstResult(number);
		selectQuery.setMaxResults(1);
		return (HangmanWord) selectQuery.getSingleResult();
	}

	@Transactional
	public void save(HangmanWord hangmanWord) {
		entityManager.persist(hangmanWord);
	}
}