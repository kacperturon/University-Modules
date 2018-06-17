using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using COMP1551.DAO.Grades;

namespace COMP1551.DAO.Children
{
    public class Child : User
    {
        int id, parentId;
        string username, password;
        string firstName, surname, birthDate, gender;
        private string imageURL;
        List<Grade> grades;

        public Child(string username, string password, string firstName, string surname, string birthDate, string gender, string imageURL, List<Grade> grades, int parentId)
        {
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.surname = surname;
            this.birthDate = birthDate;
            this.gender = gender;
            this.imageURL = imageURL;
            this.grades = grades;
            this.parentId = parentId;
        }

        public Child(int id, string username, string password, string firstName, string surname, string birthDate, string gender, string imageURL, List<Grade> grades, int parentId)
        {
            this.id = id;
            this.username = username;
            this.password = password;
            this.firstName = firstName;
            this.surname = surname;
            this.birthDate = birthDate;
            this.gender = gender;
            this.imageURL = imageURL;
            this.grades = grades;
            this.parentId = parentId;
        }

        public override int Accessibility
        {
            get
            {
                return 0;
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

        public string FirstName
        {
            get
            {
                return firstName;
            }

            set
            {
                firstName = value;
            }
        }

        public string Surname
        {
            get
            {
                return surname;
            }

            set
            {
                surname = value;
            }
        }

        public string BirthDate
        {
            get
            {
                return birthDate;
            }

            set
            {
                birthDate = value;
            }
        }

        public string Gender
        {
            get
            {
                return gender;
            }

            set
            {
                gender = value;
            }
        }

        public int ParentId
        {
            get
            {
                return parentId;
            }

            set
            {
                parentId = value;
            }
        }

        public string ImageUrl
        {
            get { return imageURL; }
            set { imageURL = value; }
        }

        public List<Grade> Grades
        {
            get { return grades; }
            set { grades = value; }
        }
    }
}