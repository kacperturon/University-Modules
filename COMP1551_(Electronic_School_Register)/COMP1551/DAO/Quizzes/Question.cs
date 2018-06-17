using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace COMP1551.DAO.Quizzes
{
    public class Question
    {
        private int id, quizID;
        private string question, answer, option1, option2, option3;

        public Question(int id, string question, string answer, string option1, string option2, string option3, int quizID)
        {
            this.id = id;
            this.question = question;
            this.answer = answer;
            this.option1 = option1;
            this.option2 = option2;
            this.option3 = option3;
            this.quizID = quizID;
        }

        public int Id
        {
            get { return id; }
        }

        public int QuizId
        {
            get { return quizID; }
        }

        public string QuestionValue
        {
            get { return question; }
            set { question = value; }
        }

        public string Answer
        {
            get { return answer; }
            set { answer = value; }
        }

        public string Option1
        {
            get { return option1; }
            set { option1 = value; }
        }

        public string Option2
        {
            get { return option2; }
            set { option2 = value; }
        }

        public string Option3
        {
            get { return option3; }
            set { option3 = value; }
        }
    }
}