using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace COMP1632.Models
{
    public class User
    {
        [Key]
        public int Id { set; get; }
        public string Username { set; get; }
        public string Password { set; get; }
        public string FirstName { set; get; }
        public string Surname { set; get; }

        public virtual UserRole UserRole { get; set; }

    }
}