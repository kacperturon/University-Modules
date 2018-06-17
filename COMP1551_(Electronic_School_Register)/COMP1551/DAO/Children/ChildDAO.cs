using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using COMP1551.DAO.Parents;

namespace COMP1551.DAO.Children
{
    interface ChildDAO
    {
        Boolean childExists(string username);//Check if child exists based on username
        Child getChild(string username, string password);//Get child object based on username and password
        List<Child> getAllChildren();//Get a list of all children
        List<Child> getChildren(Parent parent);//Get a list of children for particular parent
        List<Child> getAllChildrenByAgeAscending();//Get all children with age ascending

        void addChild(Child child); //Add object child to database
        void deleteChild(int childID);//Delete child from database based on childID
        void updateChild(Child child);//Update child's data based on child object

    }
}
