using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.OleDb;
using System.IO;
using System.Linq;
using System.Web;
using COMP1551.DAO.Children;

namespace COMP1551.DAO.Grades
{
    public class GradeDAOImpl : GradeDAO
    {
        private OleDbConnection myConnection;

        public GradeDAOImpl()
        {
            string connString = "Provider = Microsoft.ACE.OLEDB.12.0; Data Source =";
            myConnection = new OleDbConnection(connString + Path.Combine(AppDomain.CurrentDomain.BaseDirectory, ConfigurationManager.AppSettings["dbRelativePath"].ToString()).ToString());
        }

        public List<Grade> getGrades(Child child) //Get grades for passed child
        {
            List<Grade> grades = new List<Grade>();
            string query =
                "SELECT * FROM Grades WHERE UserID = @userID ORDER BY YEAR(GradeDate) DESC, MONTH(GradeDate) DESC, DAY(GradeDate) DESC";

            OleDbCommand myCommand = new OleDbCommand(query, myConnection);
            myCommand.Parameters.AddWithValue("@userID", child.ID); //Add variable to sql string (avoids sql injection)
            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();

                while (myReader.Read())
                {
                    Grade grade = new Grade(int.Parse(myReader["GradeID"].ToString()), myReader["GradeValue"].ToString(), myReader["QuizName"].ToString(), myReader["GradeDate"].ToString(), int.Parse(myReader["userID"].ToString()));
                    grades.Add(grade);
                }
                return grades;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception in DBHandler", ex);
                return null;
            }
            finally
            {
                myConnection.Close();
            }
        }

        public List<Grade> getAllGrades() //Get all grades from the database
        {
            List<Grade> grades = new List<Grade>();
            string query = "SELECT * FROM Grades ORDER BY YEAR(GradeDate) DESC, MONTH(GradeDate) DESC, DAY(GradeDate) DESC";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);
            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();

                while (myReader.Read())
                {
                    Grade grade = new Grade(myReader["gradeValue"].ToString(), myReader["quizName"].ToString(),
                          myReader["gradeDate"].ToString(), int.Parse(myReader["userID"].ToString()));

                    grades.Add(grade);
                }

                return grades;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception in DBHandler", ex);
                return null;
            }
            finally
            {
                myConnection.Close();
            }
        }

        public List<Grade> getAllGrades(string quizName) //Get all grades from the database by quiz
        {
            List<Grade> grades = new List<Grade>();
            string query = "SELECT * FROM Grades WHERE QuizName = @quizName ORDER BY GradeValue DESC";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);
            myCommand.Parameters.AddWithValue("@quizName", quizName);

            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();

                while (myReader.Read())
                {
                    Grade grade = new Grade(myReader["gradeValue"].ToString(), myReader["quizName"].ToString(),
                          myReader["gradeDate"].ToString(), int.Parse(myReader["userID"].ToString()));

                    grades.Add(grade);
                }

                return grades;
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception in DBHandler", ex);
                return null;
            }
            finally
            {
                myConnection.Close();
            }
        }

        public void addGrade(Grade grade) //Add a passed grade object to database
        {
            string query =
                "INSERT INTO Grades(GradeValue, QuizName, GradeDate, UserID) VALUES(@gradeValue, @quizName, @gradeDate, @userID)";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@gradeValue", grade.GradeValue); //Add variable to sql string (avoids sql injection)
            myCommand.Parameters.AddWithValue("@quizName", grade.QuizName);
            myCommand.Parameters.AddWithValue("@gradeDate", grade.GradeDate);
            myCommand.Parameters.AddWithValue("@userID", grade.UserId);

            try
            {
                myConnection.Open();
                myCommand.ExecuteNonQuery();
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception in DBHandler", ex);
            }
            finally
            {
                myConnection.Close();
            }
        }

        public void deleteGrade(Grade grade) //Delete passed grade object from database
        {
            throw new NotImplementedException();
        }

        public void updateGrade(Grade grade) //Update passed grade object in database
        {
            throw new NotImplementedException();
        }
    }
}