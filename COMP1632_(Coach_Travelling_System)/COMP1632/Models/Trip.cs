using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace COMP1632.Models
{
    public class Trip
    {
        [Key]
        public int Id { get; set; }

        public string Title { get; set; }
        public string Description { get; set; }
        public string From { get; set; }
        public string To { get; set; }
        public DateTime DepartureDate { get; set; }
        public DateTime ReturnDate { get; set; }
        [DisplayFormat(DataFormatString = "{0:C2}")]
        public float Price { get; set; }

        public virtual List<TripImage> TripImages { get; set; }
        public virtual List<Ticket> Tickets { get; set; }
        public virtual Coach Coach { get; set; }
        public virtual List<Review> Reviews { get; set; }
        public virtual List<CartItem> CartItems { get; set; }
    }
}