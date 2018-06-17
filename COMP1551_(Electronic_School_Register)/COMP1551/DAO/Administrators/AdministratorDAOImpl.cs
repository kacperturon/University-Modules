using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.OleDb;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Web;

namespace COMP1551.DAO.Administrators
{

    public class AdministratorDAOImpl : AdministratorDAO
    {
        private OleDbConnection myConnection;

        public AdministratorDAOImpl()
        {

            string connString = "Provider = Microsoft.ACE.OLEDB.12.0; Data Source =";
            myConnection = new OleDbConnection(connString+Path.Combine(AppDomain.CurrentDomain.BaseDirectory, ConfigurationManager.AppSettings["dbRelativePath"].ToString()).ToString()); 
        }

        public Boolean administratorExists(string username) //Check if administrator exists in database based on username
        {
            string query = "SELECT * FROM Users WHERE Username = @username AND Accessibility = 2";
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

        public Administrator getAdministrator(string username, string password) //Get administrator object from username and password used for login screen
        {
            Debug.WriteLine("TEST");
            string relativePath = ConfigurationManager.AppSettings["filePath"];
            Debug.WriteLine(Path.Combine(AppDomain.CurrentDomain.BaseDirectory, @"App_Data\DB.accdb"));

            string query = "SELECT * FROM Users WHERE Username = @username AND Pass = @pass AND Accessibility = 2";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@username", username); //Add variable to sql string (avoids sql injection)
            myCommand.Parameters.AddWithValue("@pass", password);

            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();

                if (myReader.HasRows)
                {
                    myReader.Read(); //Get the data row
                    return new Administrator(int.Parse(myReader["UserID"].ToString()), myReader["Username"].ToString(), myReader["Pass"].ToString());
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

        public List<Administrator> getAllAdministrators() //Retrives a list of administrator objects
        {
            List<Administrator> administrators = new List<Administrator>();
            string query = "SELECT * FROM Users WHERE Accessibility = 2 ORDER BY UserID ASC";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);
            try
            {
                myConnection.Open();
                OleDbDataReader myReader = myCommand.ExecuteReader();
                while (myReader.Read()) //Goes through all obtained rows from the DB
                {
                    Administrator administrator = new Administrator(int.Parse(myReader["UserID"].ToString()), myReader["Username"].ToString(), myReader["Pass"].ToString());
                    administrators.Add(administrator); //Add administrator to the list
                }
                return administrators;
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

        public void addAdministrator(Administrator administrator) //Add administrator object to database
        {
            string query = "INSERT INTO Users(Username, Pass, Accessibility) VALUES(@username, @pass, @accessibility)";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@username", administrator.Username); //Add variable to sql string (avoids sql injection)
            myCommand.Parameters.AddWithValue("@pass", administrator.Password);
            myCommand.Parameters.AddWithValue("@accessibility", administrator.Accessibility);

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

        public void deleteAdministrator(int administratorID) //Delete administrator from the database based on ID
        {
            string query = "DELETE FROM Users WHERE UserID = @userID";
            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@userID", administratorID); //Add variable to sql string (avoids sql injection)

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

        public void updateAdministrator(Administrator administrator) //Update administrator in the database
        {
            string query = "UPDATE Users SET Username = @username, Pass = @pass WHERE UserID = @userID";

            OleDbCommand myCommand = new OleDbCommand(query, myConnection);

            myCommand.Parameters.AddWithValue("@username", administrator.Username); //Add variable to sql string (avoids sql injection)
            myCommand.Parameters.AddWithValue("@pass", administrator.Password);
            myCommand.Parameters.AddWithValue("@userID", administrator.ID);

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