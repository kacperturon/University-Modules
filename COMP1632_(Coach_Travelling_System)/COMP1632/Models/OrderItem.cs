using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace COMP1632.Models
{
    public class OrderItem
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public string From { get; set; }
        public string To { get; set; }
        public DateTime DepartureDate { get; set; }
        public DateTime ReturnDate { get; set; }
        [DisplayFormat(DataFormatString = "{0:C2}")]
        public float Price { get; set; }
        public int Quantity { get; set; }
        public virtual Order Order { get; set; }
        public int? ReviewId { get; set; }
        public int TripId { get; set; }
        public List<int> SeatNumbers { get; set; }
    }
}