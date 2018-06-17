using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace COMP1632.Models
{
    public class UserRole
    {
        [Key]
        public int Id { get; set; }
        public string Role { get; set; }
        public virtual List<User> Users { get; set; }
    }
}