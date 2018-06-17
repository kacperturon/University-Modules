using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace COMP1551.DAO.Administrators
{
    interface AdministratorDAO
    {
        Boolean administratorExists(string username); //Check if administrator exists in database based on username
        Administrator getAdministrator(string username, string password);//Get administrator object from username and password used for login screen
        List<Administrator> getAllAdministrators();//Retrives a list of administrator objects

        void addAdministrator(Administrator administrator);//Add administrator object to database
        void deleteAdministrator(int administratorID); //Delete administrator from the database based on ID
        void updateAdministrator(Administrator administrator);//Update administrator in the database
    }
}
