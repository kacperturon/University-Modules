using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.OleDb;
using System.IO;
using System.Linq;
using System.Web;
using COMP1551.DAO.Grades;
using COMP1551.DAO.Parents;
namespace COMP1551.DAO.Children
{
    public class ChildDAOImpl : ChildDAO
    {
        private OleDbConnection myConnection;

        public ChildDAOImpl()
        {
            string connString = "Provider = Microsoft.ACE.OLEDB.12.0; Data Source =";
            myConnection = new OleDbConnection(connString + Path.Combine(AppDomain.CurrentDomain.BaseDirectory, ConfigurationManager.AppSettings["dbRelativePath"].ToString()).ToString());
        }

        public Boolean childExists(string username) //Check if child exists based on username
        {
            string query = "SELECT * FROM Users WHERE Username = @username AND Accessibility = 0";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@username", username); //Add variable to sql string (avoids sql injection)

            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();

                if (myReader.HasRows)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine("Exception in DBHandler", ex);
                return false;
            }
            finally
            {
                myConnection.Close();
            }
        }

        public Child getChild(string username, string password) //Get child object based on username and password
        {
            string query = "SELECT * FROM Users WHERE Username = @username AND Pass = @pass AND Accessibility = 0";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@username", username); //Add variable to sql string (avoids sql injection)
            myCommand.Parameters.AddWithValue("@pass", password);

            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();

                if (myReader.HasRows)
                {
                    myReader.Read(); //Get the row from database
                    Child child = new Child(int.Parse(myReader["UserID"].ToString()), myReader["Username"].ToString(),
                        myReader["Pass"].ToString(),
                        myReader["FirstName"].ToString(), myReader["Surname"].ToString(), myReader["DOB"].ToString(),
                        myReader["Gender"].ToString(), myReader["ImageURL"].ToString(), new List<Grade>(), int.Parse(myReader["ParentID"].ToString()));
                    child.Grades = new GradeDAOImpl().getGrades(child);
                    return child;
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

        public List<Child> getAllChildren() //Get a list of all children
        {
            List<Child> children = new List<Child>();
            string query =
                "SELECT * FROM Users WHERE Accessibility = 0 AND DATEDIFF('yyyy', DOB, DATE()) BETWEEN 5 AND 16 ORDER BY UserID ASC";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();
                while (myReader.Read())
                {
                    Child child = new Child(int.Parse(myReader["UserID"].ToString()), myReader["Username"].ToString(),
                        myReader["Pass"].ToString(),
                        myReader["FirstName"].ToString(), myReader["Surname"].ToString(), myReader["DOB"].ToString(),
                        myReader["Gender"].ToString(), myReader["ImageURL"].ToString(), new List<Grade>(), int.Parse(myReader["ParentID"].ToString()));
                    child.Grades = new GradeDAOImpl().getGrades(child);

                    children.Add(child);
                }
                return children;


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

        public List<Child> getChildren(Parent parent) //Get a list of children for particular parent
        {
            List < Child > children = new List<Child>();
            string query =
                "SELECT * FROM Users WHERE ParentID = @parentID AND Accessibility = 0 AND DATEDIFF('yyyy', DOB, DATE()) BETWEEN 5 AND 16 ORDER BY DOB DESC";

            OleDbCommand myCommand = new OleDbCommand(query, myConnection);
            myCommand.Parameters.AddWithValue("@parentID", parent.ID); //Add variable to sql string (avoids sql injection)
            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();
                while (myReader.Read())
                {
                    Child child = new Child(int.Parse(myReader["UserID"].ToString()), myReader["Username"].ToString(),
                        myReader["Pass"].ToString(),
                        myReader["FirstName"].ToString(), myReader["Surname"].ToString(), myReader["DOB"].ToString(),
                        myReader["Gender"].ToString(), myReader["ImageURL"].ToString(), new List<Grade>(), int.Parse(myReader["ParentID"].ToString()));
                    child.Grades = new GradeDAOImpl().getGrades(child); //Get all grades for this child
                    children.Add(child); //Add child to children list
                }
                return children;
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

        public List<Child> getAllChildrenByAgeAscending() //Get all children with age ascending
        {
            List<Child> children = new List<Child>();
            string query =
                    "SELECT * FROM Users WHERE Accessibility = 0 AND DATEDIFF('yyyy', DOB, DATE()) BETWEEN 5 AND 16 ORDER BY YEAR(DOB) DESC, MONTH(DOB) DESC, DAY(DOB) DESC";
                //Descending because it is looking on year of birth not age (only 5-16 year olds)
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();
                while (myReader.Read())
                {
                    Child child = new Child(int.Parse(myReader["UserID"].ToString()), myReader["Username"].ToString(),
                        myReader["Pass"].ToString(),
                        myReader["FirstName"].ToString(), myReader["Surname"].ToString(), myReader["DOB"].ToString(),
                        myReader["Gender"].ToString(), myReader["ImageURL"].ToString(), new List<Grade>(), int.Parse(myReader["ParentID"].ToString()));
                    child.Grades = new GradeDAOImpl().getGrades(child);

                    children.Add(child);
                }
                return children;
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

        public void addChild(Child child) //Add object child to database
        {
            string query =
                "INSERT INTO Users(Username, Pass, Accessibility, FirstName, Surname, DOB, Gender, ParentID) VALUES(@username, @pass, @accessibility, @firstname, @surname, @dob, @gender, @parentId)";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@username", child.Username); //Add variable to sql string (avoids sql injection)
            myCommand.Parameters.AddWithValue("@pass", child.Password);
            myCommand.Parameters.AddWithValue("@accessibility", child.Accessibility);
            myCommand.Parameters.AddWithValue("@firstname", child.FirstName);
            myCommand.Parameters.AddWithValue("@surname", child.Surname);
            myCommand.Parameters.AddWithValue("@dob", child.BirthDate);
            myCommand.Parameters.AddWithValue("@gender", child.Gender);
            myCommand.Parameters.AddWithValue("@parentId", child.ParentId);

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

        public void deleteChild(int childID) //Delete child from database based on childID
        {
            string query = "DELETE FROM Users WHERE UserID = @userID";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@userID", childID); //Add variable to sql string (avoids sql injection)

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

        public void updateChild(Child child) //Update child's data based on child object
        {

            string query = "UPDATE Users SET Username = @username, Pass = @pass, FirstName = @firstName, " +
                           "Surname = @surname, DOB = @dob, Gender = @gender, ImageURL = @imageURL  WHERE UserID = @userID";

            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@username", child.Username); //Add variable to sql string (avoids sql injection)
            myCommand.Parameters.AddWithValue("@pass", child.Password);
            myCommand.Parameters.AddWithValue("@firstName", child.FirstName);
            myCommand.Parameters.AddWithValue("@surname", child.Surname);
            myCommand.Parameters.AddWithValue("@dob", child.BirthDate);
            myCommand.Parameters.AddWithValue("@gender", child.Gender);
            myCommand.Parameters.AddWithValue("@imageURL", child.ImageUrl);
            myCommand.Parameters.AddWithValue("@userID", child.ID);

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
 
    }
}