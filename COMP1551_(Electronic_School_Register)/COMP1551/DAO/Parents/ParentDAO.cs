using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace COMP1551.DAO.Parents
{
    interface ParentDAO
    {
        Boolean parentExists(string username); //Check if parent exists from username
        Parent getParent(string username, string password); //Get parent object based on username and password
        List<Parent> getAllParents(); //Get all parents from the database
        List<Parent> getOnlyParentsWithChildren(); //Get only parents with children from the database

        void addParent(Parent parent); //Add parent object to the database
        void deleteParent(int parentID); //Delete parent object from the database based on parentID
        void updateParent(Parent parent); //Update parent in the database based on passed parent object
    }
}
