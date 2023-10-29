package com.example.unscramble.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.unscramble.data.allWords
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GameViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    private lateinit var currentWord: String

    private var usedWords: MutableSet<String> = mutableSetOf()

    var userGuess by mutableStateOf("")
        private set

    init {
        resetGame()
    }

    //Kullanılmamış kelimeleri çekme
    fun pickRandomWordAndShuffle(): String {
        currentWord = allWords.random()
        if (usedWords.contains(currentWord)) {
            return pickRandomWordAndShuffle()//seçili kelime kullanılmış ise tekrar başlat fonksiyonu
        } else {//seçiil kelime kullanılmamışsa kullanılmış kelimelere ekle
            usedWords.add(currentWord)
            return shuffleCurrentWord(currentWord)
        }
    }

    // HARFLERİ KARIŞTIRMA
    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while (String(tempWord).equals(word)) {//HALA AYNI İSE TEKRAR KARIŞTIR
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun resetGame() {
        usedWords.clear()
        _uiState.value = GameUiState(currentScrambledWord = pickRandomWordAndShuffle())
    }

    fun updateUserGuess(guessedWords: String) {
        userGuess = guessedWords //kullancıdan gelen kelime ile güncelle
    }

    fun checkUserGuess() {//Kullanıcı tahmini
        if (userGuess.equals(currentWord, ignoreCase = true)) {
            val updatedScore =
                _uiState.value.score.plus(SCORE_INCREASE)//Eğer doğruysa skoru bir arttırır ve bunu updateGameState fok gönderir
            updateGameState(updatedScore)
        } else {
            _uiState.update { currentState ->
                currentState.copy(isGuessedWordWrong = true)
            }
        }
        updateUserGuess("")
    }

    private fun updateGameState(updatedScore: Int) {//gelen skoru score'a gönderir ve diğer değerleri günceller

        if (usedWords.size == MAX_NO_OF_WORDS) {
            _uiState.update { currentState ->
                    currentState.copy(
                        isGuessedWordWrong = false,
                        score = updatedScore,
                        isGameOver=true
                    )
            }
        } else {
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    currentScrambledWord = pickRandomWordAndShuffle(),
                    currentWordCount = currentState.currentWordCount.inc(),
                    score = updatedScore
                )
            }
        }


    }

    fun skipWord() {
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }
}