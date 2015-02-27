package com.lios.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HangmanController {

	@Autowired
	private HangmanRepository hangmanRepository;

	@RequestMapping("/add")
	public HangmanWord add(
			@RequestParam(value = "word", defaultValue = "") String word,
			@RequestParam(value = "category", defaultValue = "words") String category,
			@RequestParam(value = "hint", defaultValue = "") String hint
			) {
		
		if (word.length() > 0) {
			HangmanWord hangmanWord = new HangmanWord();
			hangmanWord.setCategory(category);
			hangmanWord.setHint(hint);
			hangmanWord.setWord(word);
			hangmanRepository.save(hangmanWord);
			return hangmanWord;
		}
		else {
			return null;
		}
	}

	@RequestMapping("/hangman")
	public HangmanWord getWord(
			@RequestParam(value = "category", defaultValue = "words") String category) {
		
		return hangmanRepository.getRandomHangmanWord(category);
	}
}