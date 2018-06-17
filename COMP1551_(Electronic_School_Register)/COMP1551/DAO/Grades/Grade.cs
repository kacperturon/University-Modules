using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace COMP1551.DAO.Grades
{
    public class Grade
    {
        private int id, userID;
        private string gradeValue, quizName, gradeDate;

        public Grade(string gradeValue, string quizName, string gradeDate, int userID)
        {
            this.gradeValue = gradeValue;
            this.quizName = quizName;
            this.gradeDate = gradeDate;
            this.userID = userID;
        }

        public Grade(int id, string gradeValue, string quizName, string gradeDate, int userID)
        {
            this.id = id;
            this.gradeValue = gradeValue;
            this.quizName = quizName;
            this.gradeDate = gradeDate;
            this.userID = userID;
        }

        public string GradeValue
        {
            get { return gradeValue; }
            set { gradeValue = value; }
        }

        public string QuizName
        {
            get { return quizName; }
            set { quizName = value; }
        }

        public string GradeDate
        {
            get { return gradeDate; }
            set { gradeDate = value; }
        }

        public int Id
        {
            get { return id; }
        }

        public int UserId
        {
            get { return userID; }
        }
    }
}
