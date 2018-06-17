using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace COMP1551.DAO.Quizzes
{
    public class Quiz
    {
        private int id;
        private string quizName;
        private List<Question> questions;


        public Quiz(string quizName, List<Question> questions)
        {
            this.quizName = quizName;
            this.questions = questions;
        }

        public Quiz(int id, string quizName, List<Question> questions )
        {
            this.id = id;
            this.quizName = quizName;
            this.questions = questions;
        }

        public int Id
        {
            get { return id; }
        }

        public string QuizName
        {
            get { return quizName; }
            set { quizName = value; }
        }

        public List<Question> Questions
        {
            get { return questions; }
            set { questions = value; }
        }
    }
}