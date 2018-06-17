using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;
using System.Data.Entity;

namespace COMP1632.Models
{
    public class Customer
    {
        [Key, ForeignKey("User")]
        public int UserId { get; set; }
        public string Email { get; set; }
        public virtual CreditCard CreditCard { get; set; }
        public virtual ShoppingCart ShoppingCart { get; set; }
        public virtual List<Enquiry> Enquiries { get; set; }
        public virtual List<Order> Orders { get; set; }
        public virtual User User { get; set; }
    }

}