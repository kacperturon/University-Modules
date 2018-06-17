using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace COMP1632.Models
{
    public class CartItem
    {
        public int Id { get; set; }
        public int Quantity { get; set; }
        public int SeatPreference { get; set; }
        public virtual Trip Trip { get; set; }
        public virtual ShoppingCart ShoppingCart { get; set; }
    }
}