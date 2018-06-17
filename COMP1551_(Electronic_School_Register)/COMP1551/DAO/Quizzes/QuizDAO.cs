using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace COMP1551.DAO.Quizzes
{
    interface QuizDAO
    {
        Quiz getQuiz(int quizID); //Gives quiz for specific ID
        List<Quiz> getAllQuizzes(); //Gives all quizzes from the DB

    }
}
