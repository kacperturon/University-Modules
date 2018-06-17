using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.OleDb;
using System.IO;
using System.Linq;
using System.Web;

namespace COMP1551.DAO.Quizzes
{
    public class QuizDAOImpl : QuizDAO
    {
        private OleDbConnection myConnection;

        public QuizDAOImpl()
        {
            string connString = "Provider = Microsoft.ACE.OLEDB.12.0; Data Source =";
            myConnection = new OleDbConnection(connString + Path.Combine(AppDomain.CurrentDomain.BaseDirectory, ConfigurationManager.AppSettings["dbRelativePath"].ToString()).ToString());
        }

        public Quiz getQuiz(int quizID) //Gives quiz for specific ID
        {
            string query = "SELECT * FROM Quizzes WHERE ID = @quizID ORDER BY ID ASC";

            OleDbCommand myCommand = new OleDbCommand(query, myConnection);
            myCommand.Parameters.AddWithValue("@quizID", quizID); //Add variable to sql string (avoids sql injection)

            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();
                if (myReader.HasRows)
                {
                    myReader.Read();
                    Quiz quiz = new Quiz(int.Parse(myReader["ID"].ToString()), myReader["QuizName"].ToString(), new List<Question>());
                    System.Diagnostics.Debug.WriteLine(myReader["QuizName"].ToString() + " " + quiz.Id);
                    /* get all questions */

                    string query2 = "SELECT * FROM Questions WHERE QuizID = @quizID ORDER BY ID ASC";
                    OleDbCommand myCommand2 = new OleDbCommand(query2, myConnection);
                    myCommand2.Parameters.AddWithValue("@quizID", quiz.Id); //Add variable to sql string (avoids sql injection)

                    OleDbDataReader myReader2 = myCommand2.ExecuteReader();

                    try
                    {
                        while (myReader2.Read())
                        {
                            Question question = new Question(int.Parse(myReader2["ID"].ToString()),
                                myReader2["Question"].ToString(),
                                myReader2["Answer"].ToString(), myReader2["Option1"].ToString(),
                                myReader2["Option2"].ToString(), myReader2["Option3"].ToString(),
                                int.Parse(myReader2["QuizID"].ToString()));
                            quiz.Questions.Add(question);
                        }
                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine("Exception in DBHandler", ex);
                        return null;
                    }

                    return quiz;

                }
                else
                {
                    return null;
                }


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

        public List<Quiz> getAllQuizzes() //Gives a list of all quizzes in the DB
        {
            List<Quiz> quizzes = new List<Quiz>();
            string query = "SELECT * FROM Quizzes ORDER BY ID ASC";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);
            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();

                while (myReader.Read())
                {
                    Quiz quiz = new Quiz(int.Parse(myReader["ID"].ToString()), myReader["QuizName"].ToString(), new List<Question>());


                    /* get all questions */
                    string query2 = "SELECT * FROM Questions WHERE QuizID = @quizID ORDER BY ID ASC";
                    OleDbCommand myCommand2 = new OleDbCommand(query2, myConnection);
                    myCommand2.Parameters.AddWithValue("@quizID", quiz.Id); //Add variable to sql string (avoids sql injection)

                    OleDbDataReader myReader2 = myCommand2.ExecuteReader();
                    try
                    {
                        while (myReader2.Read())
                        {
                            Question question = new Question(int.Parse(myReader2["ID"].ToString()),
                                myReader2["Question"].ToString(),
                                myReader2["Answer"].ToString(), myReader2["Option1"].ToString(),
                                myReader2["Option2"].ToString(), myReader2["Option3"].ToString(),
                                int.Parse(myReader2["QuizID"].ToString()));
                            quiz.Questions.Add(question);

                        }

                        quizzes.Add(quiz);
                    }
                    catch (Exception ex)
                    {
                        Console.WriteLine("Exception in DBHandler", ex);
                        return null;
                    }

                }
                return quizzes;
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

    }
}