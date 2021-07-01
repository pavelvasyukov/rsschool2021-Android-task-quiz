package com.rsschool.quiz


class DataObjectAccess {


    private val quizGames: List<QuizObject> = listOf(
        QuizObject("1+1", listOf("1", "Да", "Нет", "2", "3"), 1, 4),
        QuizObject("2+2", listOf("1", "2", "3", "4", "5"), 2,4),
        QuizObject("3+3", listOf("2", "4", "6", "8", "11"), 3,3),
        QuizObject("4+4", listOf("8", "-999999999", "Слишком много что бы считать", "!", "Фиолетовый"), 4,1),
        QuizObject("5+5", listOf("81742", "10", "-1", "0", "Сам считай"), 5,2),
    )


    private val numAnswers: HashMap<Int, Int> = hashMapOf()


    fun addAnswer(numberQuest: Int, numberAnswer: Int) {
        numAnswers[numberQuest-1] = numberAnswer

    }

    fun checkAnswers(): Boolean {
        return numAnswers.size == quizGames.size
    }

    fun getResultMessage(): User {
        var result = 0
        var history = "История вопросов и ответов.\n"

        for (x in 0..quizGames.lastIndex) {

            val y = numAnswers[x]!!
            if (quizGames[x].numberCorrectAnswer == numAnswers[x]) ++result
            history += "\nВопрос: ${quizGames[x].question}\nВаш ответ: ${quizGames[x].answers[y - 1]}\nПравильный ответ: ${quizGames[x].answers[quizGames[x].numberCorrectAnswer - 1]}"
        }

        return User("jesus@haven.com", "Результат квиза: $result из 5.", history)
    }

    fun getQuizObject(num: Int) : QuizObject {
        return if (num <= quizGames.lastIndex) quizGames[num] else nullQuizObject
    }

    fun getSize() : Int = quizGames.lastIndex + 1

    private val nullQuizObject = QuizObject("nullQuizObject", listOf("nullQuizObject", "nullQuizObject", "nullQuizObject", "nullQuizObject", "nullQuizObject"), 1,1)
}