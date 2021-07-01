package com.rsschool.quiz


class DataObjectAccess {


    private val quizGames: List<QuizObject> = listOf(
        QuizObject("Вы готовы, дети?", listOf("Да, капитан!", "Да!", "Нет.", "Нет, капитан", "буль-буль-буль"), 1, 1),
        QuizObject("Ктоооооооо... Кто проживает на дне океана?", listOf("Рыбки", "Губка Боб Квадратные Штаны!", "Водолаз", "Никто не проживает", "Terror from the Deep"), 2,2),
        QuizObject("Жёлтая губка, малыш без изъяна?", listOf("С изъяном", "Не малышь", "Губка Боб Квадратные Штаны!", "Синяя губка", "Жёлтая подводная лодка"), 3,3),
        QuizObject("Кто побеждает всегда и везде?", listOf("Годзилла", "Рэмбо", "Капитан Америка", "Губка Боб Квадратные Штаны!", "Фиолетовый из Повер Рэнджерс"), 4,4),
        QuizObject("Кто также ловок, как рыба в воде?", listOf("Другая рыба", "Аквамен", "Глубина", "Тазик залитый бетоном", "Губка Боб Квадратные Штаны!"), 5,5),
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