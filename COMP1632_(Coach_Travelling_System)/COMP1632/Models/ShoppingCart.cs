using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Web;

namespace COMP1632.Models
{
    public partial class ShoppingCart
    {
        [Key, ForeignKey("Customer")]
        public int Id { get; set; }
        public virtual List<CartItem> CartItems { get; set; }
        public virtual Customer Customer { get; set; }
    }
}