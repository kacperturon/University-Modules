using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;
using COMP1632.Models;

namespace COMP1632.ViewModels
{
    public class ReviewProfileViewModel
    {
        public int Rating { get; set; }
        public int OrderItemId { get; set; }

        [Required]
        [DataType(DataType.MultilineText)]
        public string Description { get; set; }
        public string TripTitle { get; set; }
        public int TripId { get; set; }
    }
}