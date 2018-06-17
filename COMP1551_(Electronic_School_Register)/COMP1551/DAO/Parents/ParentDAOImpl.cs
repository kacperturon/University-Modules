using COMP1551.DAO.Children;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.OleDb;
using System.IO;
using System.Linq;
using System.Web;

namespace COMP1551.DAO.Parents
{
    public class ParentDAOImpl : ParentDAO
    {
        private OleDbConnection myConnection;

        public ParentDAOImpl()
        {
            string connString = "Provider = Microsoft.ACE.OLEDB.12.0; Data Source =";
            myConnection = new OleDbConnection(connString + Path.Combine(AppDomain.CurrentDomain.BaseDirectory, ConfigurationManager.AppSettings["dbRelativePath"].ToString()).ToString());
        }


        public Boolean parentExists(string username) //Check if parent exists from username
        {
            string query = "SELECT * FROM Users WHERE Username = @username AND Accessibility = 1";
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

        public Parent getParent(string username, string password) //Get parent object based on username and password
        {
            List<Child> children = new List<Child>();

            string query = "SELECT * FROM Users WHERE Username = @username AND Pass = @pass AND Accessibility = 1";

            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@username", username); //Add variable to sql string (avoids sql injection)
            myCommand.Parameters.AddWithValue("@pass", password);

            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();
                Parent parent;

                if (myReader.HasRows)
                {
                    myReader.Read();
                    parent = new Parent(int.Parse(myReader["UserID"].ToString()), myReader["Username"].ToString(),
                        myReader["Pass"].ToString(), myReader["FirstName"].ToString(), myReader["Surname"].ToString(),
                        myReader["Postcode"].ToString(), myReader["Telephone"].ToString(), myReader["Email"].ToString(),
                        myReader["PaymentDate"].ToString(), children);
                }
                else
                {
                    return null;
                }

                parent.Children = new ChildDAOImpl().getChildren(parent);

                return parent;
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

        public List<Parent> getAllParents() //Get all parents from the database
        {
            List<Parent> parents = new List<Parent>();
            string query = "SELECT * FROM Users WHERE Accessibility = 1 ORDER BY UserID ASC";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);
            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();

                while (myReader.Read())
                {
                    List<Child> children = new List<Child>();
                    Parent parent = new Parent(int.Parse(myReader["UserID"].ToString()), myReader["Username"].ToString(),
                        myReader["Pass"].ToString(), myReader["FirstName"].ToString(), myReader["Surname"].ToString(),
                        myReader["Postcode"].ToString(), myReader["Telephone"].ToString(), myReader["Email"].ToString(),
                        myReader["PaymentDate"].ToString(), children);

                    parent.Children = new ChildDAOImpl().getChildren(parent);

                    parents.Add(parent);
                }

                return parents;
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

        public List<Parent> getOnlyParentsWithChildren() //Get only parents with children from the database
        {
            List<Parent> parents = new List<Parent>();
            string query = "SELECT * FROM Users u WHERE EXISTS(SELECT ParentID FROM Users c WHERE c.ParentID = u.userID) AND Accessibility = 1 ORDER BY UserID ASC";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);
            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();

                while (myReader.Read())
                {
                    List<Child> children = new List<Child>();
                    Parent parent = new Parent(int.Parse(myReader["UserID"].ToString()), myReader["Username"].ToString(),
                        myReader["Pass"].ToString(), myReader["FirstName"].ToString(), myReader["Surname"].ToString(),
                        myReader["Postcode"].ToString(), myReader["Telephone"].ToString(), myReader["Email"].ToString(),
                        myReader["PaymentDate"].ToString(), children);
                    parent.Children = new ChildDAOImpl().getChildren(parent);

                    parents.Add(parent);
                }

                return parents;
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

        public void addParent(Parent parent)  //Add parent object to the database
        {
            string query =
                "INSERT INTO Users(Username, Pass, Accessibility, FirstName, Surname, Postcode, Telephone, Email) VALUES(@username, @pass, @accessibility, @firstName, @surname, @postcode, @telephone, @email)";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@username", parent.Username); //Add variable to sql string (avoids sql injection)
            myCommand.Parameters.AddWithValue("@pass", parent.Password);
            myCommand.Parameters.AddWithValue("@accessibility", parent.Accessibility);
            myCommand.Parameters.AddWithValue("@firstName", parent.FirstName);
            myCommand.Parameters.AddWithValue("@surname", parent.Surname);
            myCommand.Parameters.AddWithValue("@postcode", parent.Postcode);
            myCommand.Parameters.AddWithValue("@telephone", parent.Telephone);
            myCommand.Parameters.AddWithValue("@email", parent.Email);

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

        public void deleteParent(int parentID) //Delete parent object from the database based on parentID
        {
            string query = "DELETE FROM Users WHERE UserID = @userID";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@userID", parentID); //Add variable to sql string (avoids sql injection)

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

        public void updateParent(Parent parent)  //Update parent in the database based on passed parent object
        {            
            string query = "UPDATE Users SET Username = @username, Pass = @pass, FirstName = @firstName, " +
                           "Surname = @surname, Postcode = @postcode, Telephone = @telephone," +
                           "Email = @email, PaymentDate = @paymentDate WHERE UserID = @userID";

            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@username", parent.Username); //Add variable to sql string (avoids sql injection)
            myCommand.Parameters.AddWithValue("@pass", parent.Password);
            myCommand.Parameters.AddWithValue("@firstName", parent.FirstName);
            myCommand.Parameters.AddWithValue("@surname", parent.Surname);
            myCommand.Parameters.AddWithValue("@postcode", parent.Postcode);
            myCommand.Parameters.AddWithValue("@telephone", parent.Telephone);
            myCommand.Parameters.AddWithValue("@email", parent.Email);
            myCommand.Parameters.AddWithValue("@paymentDate", parent.PaymentDate);
            myCommand.Parameters.AddWithValue("@userID", parent.ID);

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