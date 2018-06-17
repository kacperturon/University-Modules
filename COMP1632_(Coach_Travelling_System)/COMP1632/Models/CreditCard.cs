using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace COMP1632.Models
{
    public class CreditCard
    {
        [Key, ForeignKey("Customer")]
        public int CustomerId { get; set; }

        public string NameOnCard { get; set; }
        public string CardNumber { get; set; }
        public DateTime ExpiryDate { get; set; }
        public string CCV { get; set; }

        public virtual Customer Customer { get; set; }


    }
}