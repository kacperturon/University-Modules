using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using COMP1551.DAO.Children;

namespace COMP1551.DAO.Parents
{
    public class Parent : User
    {
        int id;
        string username, password;
        string firstName, surname, postcode, telephone, email;
        private string paymentDate;
        List<Child> children;

        public Parent(string username, string password, string firstName, string surname, string postcode, string telephone, string email, string paymentDate, List<Child> children) //For inserting data to database the ID will be autoincremented
        {
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.surname = surname;
            this.postcode = postcode;
            this.telephone = telephone;
            this.email = email;
            this.paymentDate = paymentDate;
            this.children = children;
        }

        public Parent(int id, string username, string password, string firstName, string surname, string postcode, string telephone, string email, string paymentDate, List<Child> children) //For data returned from database
        {
            this.id = id;
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.surname = surname;
            this.postcode = postcode;
            this.telephone = telephone;
            this.email = email;
            this.paymentDate = paymentDate;
            this.children = children;
        }

        public string Email
        {
            get { return email; }
            set { email = value; }
        }

        public string FirstName
        {
            get { return firstName; }
            set { firstName = value; }
        }

        public string Postcode
        {
            get { return postcode; }
            set { postcode = value; }
        }

        public string Surname
        {
            get { return surname; }
            set { surname = value; }
        }

        public string Telephone
        {
            get { return telephone; }
            set { telephone = value; }
        }

        public override string Username
        {
            get { return username; }
            set { username = value; }
        }

        public override string Password
        {
            get { return password; }
            set { password = value; }
        }

        public override int Accessibility
        {
            get { return 1; }
        }

        public override int ID
        {
            get { return id; }
        }

        public List<Child> Children
        {
            get { return children; }
            set { children = value; }
        }

        public string PaymentDate
        {
            get { return paymentDate; }
            set { paymentDate = value; }
        }
    }
}