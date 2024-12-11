package jetbrains.kotlin.course.mastermind.advanced
import kotlin.random.Random

fun getGameRules(wordLength: Int, maxAttemptsCount: Int, secretExample: String) =
    "Welcome to the game! $newLineSymbol" +
            newLineSymbol +
            "Two people play this game: one chooses a word (a sequence of letters), " +
            "the other guesses it. In this version, the computer chooses the word: " +
            "a sequence of $wordLength letters (for example, $secretExample). " +
            "The user has several attempts to guess it (the max number is $maxAttemptsCount). " +
            "For each attempt, the number of complete matches (letter and position) " +
            "and partial matches (letter only) is reported. $newLineSymbol" +
            newLineSymbol +
            "For example, with $secretExample as the hidden word, the BCDF guess will " +
            "give 1 full match (C) and 1 partial match (B)."

fun countPartialMatches(secret: String, guess: String): Int {
    val matches = minOf(
        secret.filter { it in guess }.length,
        guess.filter { it in secret }.length,
    )
    return matches - countExactMatches(guess, secret)
}

fun isCorrectInput(userInput: String, wordLength: Int, alphabet: String): Boolean {
    if (userInput.length != wordLength) {
        return false
    }

    for (char in userInput) {
        if (char !in alphabet) {
            return false
        }
    }

    return true
}

fun safeUserInput(wordLength: Int, alphabet: String): String {
    var userInput: String
    do {
        println("Please input your guess. It should be of length $wordLength and only contain letters from $alphabet.")
        userInput = safeReadLine()
    } while (!isCorrectInput(userInput, wordLength, alphabet))

    return userInput
}



fun generateSecret(wordLength: Int, alphabet: String): String {
    return (1..wordLength)
        .map { alphabet.random() }
        .joinToString("")
}


fun countExactMatches(secret: String, guess: String): Int =
    guess.filterIndexed { index, letter -> letter == secret[index] }.length

fun generateSecret() = "ABCD"

fun isComplete(secret: String, guess: String) = secret == guess

fun printRoundResults(secret: String, guess: String) {
    val fullMatches = countExactMatches(secret, guess)
    val partialMatches = countPartialMatches(secret, guess)
    println("Your guess has $fullMatches full matches and $partialMatches partial matches.")
}

fun isWon(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = complete && attempts <= maxAttemptsCount

fun isLost(complete: Boolean, attempts: Int, maxAttemptsCount: Int) = !complete && attempts > maxAttemptsCount

fun playGame(secret: String, wordLength: Int, maxAttemptsCount: Int, alphabet: String) {
    var complete: Boolean
    var attempts = 0
    do {
        val guess = safeUserInput(wordLength, alphabet)
        printRoundResults(secret, guess)
        complete = isComplete(secret, guess)
        attempts++
        if (isLost(complete, attempts, maxAttemptsCount)) {
            println("Sorry, you lost! :( My word is $secret")
            break
        } else if (isWon(complete, attempts, maxAttemptsCount)) {
            println("Congratulations! You guessed it!")
        }
    } while (!complete)
}

fun main() {
    val wordLength = 4
    val maxAttemptsCount = 3
    val secretExample = "ACEB"
    val alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    println(getGameRules(wordLength, maxAttemptsCount, secretExample))
    playGame(generateSecret(wordLength, alphabet), wordLength, maxAttemptsCount, alphabet)
}
