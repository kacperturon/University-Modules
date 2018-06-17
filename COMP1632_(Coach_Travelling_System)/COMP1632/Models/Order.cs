using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace COMP1632.Models
{
    public class Order
    {
        [Key]
        public int Id { get; set; }
        [DisplayFormat(DataFormatString = "{0:C2}")]
        public float Total { get; set; }
        public virtual List<OrderItem> OrderItems { get; set; }

        public virtual Customer Customer { get; set; }
    }
}