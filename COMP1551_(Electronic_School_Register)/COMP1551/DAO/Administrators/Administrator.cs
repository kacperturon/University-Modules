using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace COMP1551.DAO.Administrators
{
    public class Administrator : User
    {
        
        int id;
        string username, password;

        public Administrator(string username, string password)
        {
            this.username = username;
            this.password = password;
        }

        public Administrator(int id, string username, string password)
        {
            this.id = id;
            this.username = username;
            this.password = password;
        }

        public override int Accessibility
        {
            get
            {
                return 2;
            }
        }

        public override int ID
        {
            get
            {
                return id;
            }
        }

        public override string Password
        {
            get
            {
                return password;
            }

            set
            {
                password = value;
            }
        }

        public override string Username
        {
            get
            {
                return username;
            }

            set
            {
                username = value;
            }
        }
    }
}