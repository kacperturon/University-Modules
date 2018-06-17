using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using COMP1632.Models;

namespace COMP1632.ViewModels
{
    public class DetailsHomeViewModel
    {
        public int TripId { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }
        public string From { get; set; }
        public string To { get; set; }

        [DataType(DataType.Date)]
        public DateTime DepartureDate { get; set; }
        [DataType(DataType.Date)]
        public DateTime ReturnDate { get; set; }
        [DisplayFormat(DataFormatString = "{0:C2}")]
        public float Price { get; set; }
        public string MainImageFilename { get; set; }
        public List<TripImage> TripImages { get; set; }
        public List<Ticket> Tickets { get; set; }
        public List<Review> Reviews { get; set; }
    }
}