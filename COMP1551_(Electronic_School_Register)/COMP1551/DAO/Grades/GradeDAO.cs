using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using COMP1551.DAO.Children;

namespace COMP1551.DAO.Grades
{
    interface GradeDAO
    {
        List<Grade> getGrades(Child child); //Get grades for passed child
        List<Grade> getAllGrades(); //Get all grades from the database
        List<Grade> getAllGrades(string quizName); //Get all grades from database based on quiz id

        void addGrade(Grade grade); //Add a passed grade object to database
        void deleteGrade(Grade grade); //Delete passed grade object from database
        void updateGrade(Grade grade); //Update passed grade object in database
    }
}
